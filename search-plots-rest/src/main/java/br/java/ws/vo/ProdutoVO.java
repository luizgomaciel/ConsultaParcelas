package br.java.ws.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoVO implements Serializable {

	private static final long serialVersionUID = 8909044173818826540L;
	private Long codigo;
	private String nome;
	private BigDecimal valor;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
