package br.java.ws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.java.ws.model.ProdutoVendaModel;
import br.java.ws.vo.ConsultaVO;
import br.java.ws.vo.ParcelaVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/consulta-produto")
@ResponseStatus(HttpStatus.OK)
public class ProdutoPagamentoRest {

	@Autowired
	private ProdutoVendaModel produtoModel;

	@ApiOperation(
			value="Consulta de lista de parcelas com juros", 
			response=Object.class, 
			notes="Consultar lista de parcelas com juros composto baseado na taxação Selic.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna uma lista de parcelas",
					response=Object.class
					),
			@ApiResponse(
					code=400, 
					message="Dados de entrada inconsistentes",
					response=Object.class
					)
 
	})
	@RequestMapping(path = "/parcelas-juros", method = { RequestMethod.POST })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<Object> calculaParcelasJuros(@RequestBody ConsultaVO vo) throws Exception {
		try {

			List<ParcelaVO> listaParcelas = produtoModel.calculoParcelasProdutoJuros(vo);
			vo.setListaParcelas(listaParcelas);
			produtoModel.gravaParcelasProduto(vo);

			return new ResponseEntity<Object>(listaParcelas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
