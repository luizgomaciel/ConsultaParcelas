package br.java.ws.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.java.ws.vo.ProdutoVO;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = -306029136353700512L;
	@Id
	@Column(name = "ID")
	private Long codigo;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "VALOR")
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
	
	@Transient
	private static Produto getInstance() {
		return new Produto();
	}

	@Transient
	public static Produto toEntity(ProdutoVO produto) {
		Produto p = Produto.getInstance();
		p.setCodigo(produto.getCodigo());
		p.setNome(produto.getNome());
		p.setValor(produto.getValor());
		return p;
	}

}
