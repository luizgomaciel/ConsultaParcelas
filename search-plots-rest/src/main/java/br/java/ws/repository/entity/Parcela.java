package br.java.ws.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.java.ws.vo.ParcelaVO;

@Entity
@Table(name = "PARCELAS")
public class Parcela implements Serializable {

	private static final long serialVersionUID = 5004807292241625780L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NUM_PARC")
	private Integer numeroParcela;
	@Column(name = "VALOR")
	private BigDecimal valor;
	@Column(name = "JUROS")
	private Double taxaJurosAoMes;
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "ID")
	@Column(name = "PRODUTO_FK")
	private Long produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getProduto() {
		return produto;
	}

	public void setProduto(Long produto) {
		this.produto = produto;
	}

	@Transient
	private static Parcela getInstance() {
		return new Parcela();

	}

	@Transient
	public static List<Parcela> toListEntity(List<ParcelaVO> listaParcelas, Produto prod) {
		List<Parcela> parcelas = new ArrayList<Parcela>();
		Parcela parcela = null;

		for (ParcelaVO vo : listaParcelas) {
			parcela = Parcela.getInstance();
			parcela.setNumeroParcela(vo.getNumeroParcela());
			parcela.setTaxaJurosAoMes(vo.getTaxaJurosAoMes());
			parcela.setValor(vo.getValor());
			parcela.setProduto(prod.getCodigo());
			parcelas.add(parcela);
		}

		return parcelas;
	}

}
