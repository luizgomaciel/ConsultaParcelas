package br.java.ws.service;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.java.ws.vo.SelicVO;

@Service
public class SelicRest {

	private static final String URL_REST = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados";

	public JSONArray consumesWSByUltimosDiasJSON(Integer numDias) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = URL_REST + "/ultimos/" + numDias + "?formato=json";
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		JSONArray array = new JSONArray(response.getBody());
		return array;
	}

	public SelicVO consumesWSTaxaJurosByUltimoDia() {
		JSONArray array = consumesWSByUltimosDiasJSON(1);
		return SelicVO.toVO(array);
	}
}
