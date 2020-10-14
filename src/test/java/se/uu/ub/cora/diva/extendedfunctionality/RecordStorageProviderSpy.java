package se.uu.ub.cora.diva.extendedfunctionality;

import java.util.Map;

import se.uu.ub.cora.storage.RecordStorage;
import se.uu.ub.cora.storage.RecordStorageProvider;

public class RecordStorageProviderSpy implements RecordStorageProvider {

	@Override
	public int getOrderToSelectImplementionsBy() {
		return 0;
	}

	@Override
	public RecordStorage getRecordStorage() {
		return null;
	}

	@Override
	public void startUsingInitInfo(Map<String, String> arg0) {
	}

}
