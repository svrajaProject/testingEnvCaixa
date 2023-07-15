		ResponseModel res2 = new ResponseModel();
		
		ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
		
//		Send to obrhCallForGETExternalSource OBRH start
		res2 = obrhCallForGETExternalSource(url);
		LOGGER.info("obrhCallForGETExternalSource obrh call.. " + res2.getData());
//		Send to obrhCallForGETExternalSource OBRH start

		String responseOfPost2 = "";

		try {
			responseOfPost2 = ow.writeValueAsString(res2.getData());
			LOGGER.info("Value of Response JSON block:" + responseOfPost2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			LOGGER.error("Error writing string to response body", (Throwable) e);
		}
		JSONObject resObject2 = null;
		JSONObject postObject2 = null;
		String completeStatus = null;
		try {
			resObject2 = new JSONObject(responseOfPost2);
			postObject2 = resObject.getJSONObject("dataobject2");
			completeStatus = postObject.getString("complete");
			LOGGER.info("obrhCallForGETExternalSource : CompleteStatus:" + completeStatus);
		} catch (JSONException e3) {
			e3.printStackTrace();
		}
