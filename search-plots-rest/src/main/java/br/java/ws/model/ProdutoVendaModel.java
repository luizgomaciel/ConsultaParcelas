package br.java.ws.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.java.ws.repository.ParcelaRepository;
import br.java.ws.repository.ProdutoRepository;
import br.java.ws.repository.entity.Parcela;
import br.java.ws.repository.entity.Produto;
import br.java.ws.service.SelicRest;
import br.java.ws.vo.ConsultaVO;
import br.java.ws.vo.ParcelaVO;
import br.java.ws.vo.SelicVO;

@Service
public class ProdutoVendaModel {

	@Autowired
	private SelicRest selicService;

	@Autowired
	private ProdutoRepository daoProduto;
	
	@Autowired
	private ParcelaRepository daoParcela;

	public List<ParcelaVO> calculoParcelasProdutoJuros(ConsultaVO vo) throws Exception {

		List<ParcelaVO> lista = new ArrayList<ParcelaVO>();
		ParcelaVO pvo = null;

		if (isParcelado(vo) && !isValorEntradaPagaValorProduto(vo)) {
			Double taxaJurosSelic = 1.15;
			BigDecimal auxValorProdutoComposto = vo.getProduto().getValor();
			
			if(vo.getCondicaoPagamento().getQtdeParcelas() > 6) {
				taxaJurosSelic = getAtualTaxaJurosSelic().getValor();
			}
			taxaJurosSelic = converteTaxaJurosDecimal(taxaJurosSelic);

			for (int i = 1; i <= vo.getCondicaoPagamento().getQtdeParcelas(); i++) {
				pvo = ParcelaVO.getIntance();
				//Calculo de juros composto
				auxValorProdutoComposto = auxValorProdutoComposto.add((auxValorProdutoComposto.multiply(new BigDecimal(taxaJurosSelic))));
				auxValorProdutoComposto = auxValorProdutoComposto.setScale(2, RoundingMode.HALF_EVEN);

				pvo.setNumeroParcela(i);
				pvo.setTaxaJurosAoMes(taxaJurosSelic);
				pvo.setValor(auxValorProdutoComposto);
				lista.add(pvo);
			}

		} else if (!isParcelado(vo) && isValorEntradaPagaValorProduto(vo)) {
			// Sem Juros
			pvo = ParcelaVO.getIntance();
			pvo.setNumeroParcela(1);
			pvo.setTaxaJurosAoMes(0.0);
			pvo.setValor(vo.getProduto().getValor().setScale(2, RoundingMode.HALF_EVEN));

			lista.add(pvo);
		} else {
			throw new Exception("Dados inconsistentes");
		}

		return lista;
	}

	public Double converteTaxaJurosDecimal(Double taxaJurosSelic) {

		if (taxaJurosSelic > 1.0) {
			taxaJurosSelic = taxaJurosSelic / 100;
		}

		return taxaJurosSelic;
	}

	public SelicVO getAtualTaxaJurosSelic() {
		try {
			// Conforme solicitado na documentação do git.
			//selicService.consumesWSByUltimosDiasJSON(30);
			return selicService.consumesWSTaxaJurosByUltimoDia();
		} catch (Exception e) {
			return null;
		}

	}

	public Boolean isParcelado(ConsultaVO vo) {
		Boolean ret = false;

		if (vo.getCondicaoPagamento().getQtdeParcelas() != null && vo.getCondicaoPagamento().getQtdeParcelas() > 1) {
			ret = true;
		}
		return ret;
	}

	public Boolean isValorEntradaPagaValorProduto(ConsultaVO vo) {
		Boolean ret = true;
		if (vo.getCondicaoPagamento().getValorEntrada().compareTo(vo.getProduto().getValor()) == -1) {
			ret = false;
		}
		return ret;	
	}

	public void gravaParcelasProduto(ConsultaVO vo) {
		try {
			Produto prod = Produto.toEntity(vo.getProduto());
			prod = daoProduto.saveAndFlush(prod);
			
			List<Parcela> parcelas = Parcela.toListEntity(vo.getListaParcelas(), prod);
			daoParcela.saveAll(parcelas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
