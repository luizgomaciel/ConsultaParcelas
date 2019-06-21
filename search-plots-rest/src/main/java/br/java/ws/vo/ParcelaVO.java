package br.java.ws.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ParcelaVO implements Serializable {

	private static final long serialVersionUID = 7971613019860353785L;
	private Integer numeroParcela;
	private BigDecimal valor;
	private Double taxaJurosAoMes;

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Double getTaxaJurosAoMes() {
		return taxaJurosAoMes;
	}

	public void setTaxaJurosAoMes(Double taxaJurosAoMes) {
		this.taxaJurosAoMes = taxaJurosAoMes;
	}

	public static ParcelaVO getIntance() {
		return new ParcelaVO();
	}
}
