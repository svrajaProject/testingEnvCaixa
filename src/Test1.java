package oracle.fsgbu.obremo.services.srv.payment.clearing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.HashMap;
import java.util.Map;
import oracle.fsgbu.gus.persistence.provider.GlobalHolder;
import oracle.fsgbu.obremo.common.srv.models.ResponseModel;
import oracle.fsgbu.obremo.services.srv.payment.clearing.service.ObremoCheckScannerDataService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional("PLATO_TX_MANAGER")
public class ObremoCheckScannerDataServiceImpl implements ObremoCheckScannerDataService {
	private static final Logger LOGGER = LoggerFactory.getLogger(
			oracle.fsgbu.obremo.services.srv.payment.clearing.service.ObremoCheckScannerDataServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	private ObjectMapper mapper = new ObjectMapper();

	public ResponseModel obrhCallForExternalSource() {
		JSONObject json = null;
		LOGGER.info("obrhCallForExternalSource obrhCall happening");
		try {
			json = new JSONObject("{\"body\":{\"data\": \"test1\"}}");
			LOGGER.info("Try block 1" + json);
		} catch (JSONException e) {
			LOGGER.info("Catch block 1" + e);
			e.printStackTrace();
		}
		JSONObject sourcepayload = null;
		try {
			sourcepayload = json.getJSONObject("body");
			LOGGER.info("Try block2" + sourcepayload);
		} catch (JSONException e) {
			LOGGER.info("Catch block2" + e);
			e.printStackTrace();
		}
		HttpHeaders obrhHeaders = new HttpHeaders();
		obrhHeaders.add("userId", GlobalHolder.getRequestContext().getUserId());
		obrhHeaders.add("appId", "CMNCORE");
		obrhHeaders.add("branchCode", GlobalHolder.getRequestContext().getCurrBranch());
		obrhHeaders.add("Content-Type", "application/json");
		obrhHeaders.add("Accept", "application/json");
		obrhHeaders.add("authToken", "Y");
		obrhHeaders.add("entityId", "DEFAULTENTITY");
		obrhHeaders.add("SERVICE-CONSUMER", "CARTA_CID_SERVICE");
		obrhHeaders.add("SERVICE-CONSUMER-SERVICE", "postcheckdata");
		HttpEntity<Object> entity = new HttpEntity(sourcepayload.toString(), (MultiValueMap) obrhHeaders);
		Map<String, String> parameterMap = new HashMap<>();
		ResponseEntity<ResponseModel> response = this.restTemplate.exchange(
				"http://CMC-OBRH-SERVICES/cmc-obrh-services/route/dispatch", HttpMethod.POST, entity,
				ResponseModel.class, parameterMap);
		return (ResponseModel) response.getBody();
	}

//POST OBRH end

	@Override
	public ResponseModel postCheckStatusDetails() {
//		obrhCallForExternalSource Data start
		ResponseModel res = new ResponseModel();
		res = obrhCallForExternalSource();
		String responseOfPost = "";
		JSONObject resObject = null;
		JSONObject postObject = null;
		String url = null;
		try {
			resObject = new JSONObject(responseOfPost);
			postObject = resObject.getJSONObject("data");
			url = postObject.getString("url");
			LOGGER.info("URL INSIDE TRY:" + url);
		} catch (JSONException e3) {
			e3.printStackTrace();
		}
		LOGGER.info("URL OUTTER:" + url);
//		obrhCallForGETExternalSource Data start

//		obrhCallForGETExternalSource Data end
		return res;
	}

// GET OBRH start
	public ResponseModel obrhCallForGETExternalSource(String url) {
		String URL = url;
		JSONObject json = null;
		LOGGER.info("obrhCallForGETExternalSource obrhCall happening");
		try {
			json = new JSONObject("{\"body\":{\"data\": \"test2\"}}");
			LOGGER.info("Try block 3" + json);
		} catch (JSONException e) {
			LOGGER.info("Catch block 3" + e);
			e.printStackTrace();
		}
		JSONObject sourcepayload = null;
		try {
			sourcepayload = json.getJSONObject("body");
			LOGGER.info("Try block4" + sourcepayload);
		} catch (JSONException e) {
			LOGGER.info("Catch block4" + e);
			e.printStackTrace();
		}

		HttpHeaders obrhHeaders = new HttpHeaders();
		obrhHeaders.add("SOURCE", "FCUBS");
		obrhHeaders.add("USERID", GlobalHolder.getRequestContext().getUserId());
		obrhHeaders.add("ENTITY", "ENTITY_ID1");
		obrhHeaders.add("BRANCH", "000");
//		obrhHeaders.add("SERVICE-CONSUMER", "CARTA_CID_SERVICE");
//		obrhHeaders.add("SERVICE-CONSUMER-SERVICE", "gettcheckdata");
		HttpEntity<Object> entity = new HttpEntity(sourcepayload.toString(), (MultiValueMap) obrhHeaders);
		Map<String, String> parameterMap = new HashMap<>();
		ResponseEntity<ResponseModel> response = this.restTemplate.exchange(URL, HttpMethod.GET, entity,
				ResponseModel.class, parameterMap);
		return (ResponseModel) response.getBody();
	}
	// GET OBRH end

}
