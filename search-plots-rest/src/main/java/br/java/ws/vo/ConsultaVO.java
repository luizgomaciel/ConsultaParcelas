package br.java.ws.vo;

import java.io.Serializable;
import java.util.List;

public class ConsultaVO implements Serializable {

	private static final long serialVersionUID = -4128460313017026351L;
	private ProdutoVO produto;
	private CondicaoPagamentoVO condicaoPagamento;
	private List<ParcelaVO> listaParcelas;

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public CondicaoPagamentoVO getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamentoVO condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public List<ParcelaVO> getListaParcelas() {
		return listaParcelas;
	}

	public void setListaParcelas(List<ParcelaVO> listaParcelas) {
		this.listaParcelas = listaParcelas;
	}
}
