package se.uu.ub.cora.diva.extendedfunctionality;

import java.util.Map;

import se.uu.ub.cora.search.RecordIndexer;
import se.uu.ub.cora.search.RecordSearch;
import se.uu.ub.cora.spider.authentication.Authenticator;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionalityProvider;

public class DependencyProviderSpy extends SpiderDependencyProvider {

	public DependencyProviderSpy(Map<String, String> initInfo) {
		super(initInfo);
	}

	@Override
	protected void tryToInitialize() throws Exception {

	}

	@Override
	protected void readInitInfo() {

	}

	@Override
	public ExtendedFunctionalityProvider getExtendedFunctionalityProvider() {
		return null;
	}

	@Override
	public Authenticator getAuthenticator() {
		return null;
	}

	@Override
	public RecordSearch getRecordSearch() {
		return null;
	}

	@Override
	public RecordIndexer getRecordIndexer() {
		return null;
	}

}
