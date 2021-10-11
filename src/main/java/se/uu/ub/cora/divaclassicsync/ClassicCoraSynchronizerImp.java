package se.uu.ub.cora.divaclassicsync;

import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.httphandler.HttpHandler;
import se.uu.ub.cora.httphandler.HttpHandlerFactory;
import se.uu.ub.cora.storage.RecordNotFoundException;
import se.uu.ub.cora.storage.RecordStorage;

//TODO: better name for class
public class ClassicCoraSynchronizerImp implements ClassicCoraSynchronizer {

	private static final int NOT_FOUND = 404;
	private HttpHandlerFactory httpHandlerFactory;
	private String baseURL;
	private DivaFedoraConverterFactory fedoraConverterFactory;
	private RecordStorage recordStorage;

	public ClassicCoraSynchronizerImp(RecordStorage recordStorage,
			HttpHandlerFactory httpHandlerFactory,
			DivaFedoraConverterFactory fedoraConverterFactory, String baseURL) {
		this.recordStorage = recordStorage;
		this.httpHandlerFactory = httpHandlerFactory;
		this.fedoraConverterFactory = fedoraConverterFactory;
		this.baseURL = baseURL;
	}

	public HttpHandlerFactory getHttpHandlerFactory() {
		return httpHandlerFactory;
	}

	@Override
	public void synchronize(String recordType, String recordId) {
		HttpHandler httpHandler = setUpHttpHandlerForRead(recordId);
		throwErrorIfRecordNotFound(recordType, recordId, httpHandler);

		String responseText = httpHandler.getResponseText();
		convertAndStore(recordType, recordId, responseText);

	}

	private HttpHandler setUpHttpHandlerForRead(String recordId) {
		HttpHandler httpHandler = httpHandlerFactory
				.factor(baseURL + "objects/" + recordId + "/datastreams/METADATA/content");
		httpHandler.setRequestMethod("GET");
		return httpHandler;
	}

	private void throwErrorIfRecordNotFound(String recordType, String recordId,
			HttpHandler httpHandler) {
		if (httpHandler.getResponseCode() == NOT_FOUND) {
			throw new RecordNotFoundException("Record not found for recordType: " + recordType
					+ " and recordId: " + recordId);
		}
	}

	private void convertAndStore(String recordType, String recordId, String responseText) {
		DivaFedoraToCoraConverter toCoraConverter = fedoraConverterFactory // *
				.factorToCoraConverter("person");
		DataGroup dataGroup = toCoraConverter.fromXML(responseText);
		// TODO: collectedTerms, linklist, dataDivider
		recordStorage.create(recordType, recordId, dataGroup, null, null, "");
	}

	// * internt gör den
	// transformation.transform(xmlToTransform);
	// converter.convert(coraXml);
}
