package br.java.ws.vo;

import java.io.Serializable;

import org.json.JSONArray;

public class SelicVO implements Serializable {

	private static final long serialVersionUID = -6571761387048484511L;
	private String data;
	private Double valor;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	private static SelicVO getInstance() {
		return new SelicVO();
	}

	public static SelicVO toVO(JSONArray array) {
		SelicVO vo = SelicVO.getInstance();
		vo.setData(array.getJSONObject(0).getString("data"));
		vo.setValor(array.getJSONObject(0).getDouble("valor"));
		return vo;
	}

}
