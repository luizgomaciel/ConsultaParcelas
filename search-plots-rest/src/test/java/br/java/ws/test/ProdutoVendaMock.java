package br.java.ws.test;

import java.math.BigDecimal;

import br.java.ws.vo.CondicaoPagamentoVO;
import br.java.ws.vo.ConsultaVO;
import br.java.ws.vo.ProdutoVO;

public class ProdutoVendaMock {

	private ConsultaVO vo;

	public ProdutoVendaMock() {
		this.vo = new ConsultaVO();
	}

	public ConsultaVO dadosParcelamento() {
		ProdutoVO p = new ProdutoVO();
		p.setCodigo(123L);
		p.setNome("TECLADO");
		p.setValor(new BigDecimal(1550.10));

		CondicaoPagamentoVO c = new CondicaoPagamentoVO();
		c.setQtdeParcelas(10);
		c.setValorEntrada(new BigDecimal(250.00));

		vo.setProduto(p);
		vo.setCondicaoPagamento(c);

		return vo;

	}

	public ConsultaVO dadosPagamentoVista() {
		ProdutoVO p = new ProdutoVO();
		p.setCodigo(123L);
		p.setNome("TECLADO");
		p.setValor(new BigDecimal(1550.10));

		CondicaoPagamentoVO c = new CondicaoPagamentoVO();
		c.setQtdeParcelas(1);
		c.setValorEntrada(new BigDecimal(1550.10));

		vo.setProduto(p);
		vo.setCondicaoPagamento(c);

		return vo;

	}

}
