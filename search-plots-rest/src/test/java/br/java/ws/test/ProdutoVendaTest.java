package br.java.ws.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.java.ws.model.ProdutoVendaModel;

public class ProdutoVendaTest {

	@Test
	public void pagamentoParcelaUnica() {
		ProdutoVendaMock mock = new ProdutoVendaMock();
		ProdutoVendaModel model = new ProdutoVendaModel();
		assertFalse(model.isParcelado(mock.dadosPagamentoVista()));
		assertTrue(model.isValorEntradaPagaValorProduto(mock.dadosPagamentoVista()));
	}

	@Test
	public void pagamentoPacelado() {
		ProdutoVendaMock mock = new ProdutoVendaMock();
		ProdutoVendaModel model = new ProdutoVendaModel();
		assertTrue(model.isParcelado(mock.dadosParcelamento()));
		assertFalse(model.isValorEntradaPagaValorProduto(mock.dadosParcelamento()));
	}

	@Test
	public void inconsistenciaDados() {
		ProdutoVendaMock mock = new ProdutoVendaMock();
		ProdutoVendaModel model = new ProdutoVendaModel();
		assertFalse(model.isParcelado(mock.dadosPagamentoVista()));
		assertFalse(model.isValorEntradaPagaValorProduto(mock.dadosParcelamento()));
	}

}
