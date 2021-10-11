package se.uu.ub.cora.divaclassicsync;

import se.uu.ub.cora.httphandler.HttpHandler;
import se.uu.ub.cora.httphandler.HttpHandlerFactory;
import se.uu.ub.cora.storage.RecordNotFoundException;

//TODO: better name for class
public class ClassicCoraSynchronizerImp implements ClassicCoraSynchronizer {

	private HttpHandlerFactory httpHandlerFactory;
	private String baseURL;
	private DivaFedoraConverterFactory fedoraConverterFactory;

	public ClassicCoraSynchronizerImp(HttpHandlerFactory httpHandlerFactory,
			DivaFedoraConverterFactory fedoraConverterFactory, String baseURL) {
		this.httpHandlerFactory = httpHandlerFactory;
		this.fedoraConverterFactory = fedoraConverterFactory;
		this.baseURL = baseURL;
	}

	public HttpHandlerFactory getHttpHandlerFactory() {
		return httpHandlerFactory;
	}

	@Override
	public void synchronize(String recordType, String recordId) {
		HttpHandler httpHandler = httpHandlerFactory
				.factor(baseURL + "objects/" + recordId + "/datastreams/METADATA/content");
		httpHandler.setRequestMethod("GET");
		int responseCode = httpHandler.getResponseCode();
		throwErrorIfRecordNotFound(recordType, recordId, responseCode);

		String responseText = httpHandler.getResponseText();
		// internt gör den
		// transformation.transform(xmlToTransform);
		// converter.convert(coraXml);
		DivaFedoraToCoraConverter toCoraConverter = fedoraConverterFactory
				.factorToCoraConverter("person");
		toCoraConverter.fromXML(responseText);

	}

	private void throwErrorIfRecordNotFound(String recordType, String recordId, int responseCode) {
		if (responseCode == 404) {
			throw new RecordNotFoundException("Record not found for recordType: " + recordType
					+ " and recordId: " + recordId);
		}
	}
}
