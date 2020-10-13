package se.uu.ub.cora.diva;

import java.util.Map;

import se.uu.ub.cora.diva.extendedfunctionality.DivaExtendedFunctionalityProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionalityProvider;
import se.uu.ub.cora.therest.initialize.TheRestDependencyProvider;

public class DivaDependencyProvider extends TheRestDependencyProvider {

	public DivaDependencyProvider(Map<String, String> initInfo) {
		super(initInfo);
	}

	@Override
	public ExtendedFunctionalityProvider getExtendedFunctionalityProvider() {
		return new DivaExtendedFunctionalityProvider(this);
	}
}
