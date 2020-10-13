package se.uu.ub.cora.diva;

import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import se.uu.ub.cora.diva.extendedfunctionality.DivaExtendedFunctionalityProvider;
import se.uu.ub.cora.metacreator.extended.MetacreatorExtendedFunctionalityProvider;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionalityProvider;
import se.uu.ub.cora.therest.initialize.TheRestDependencyProvider;

public class DivaDependencyProviderTest {

	@Test
	public void testInit() {
		Map<String, String> initInfo = new HashMap<>();
		initInfo.put("gatekeeperURL", "someGatekeeperURL");
		initInfo.put("solrURL", "someSolrURL");
		DivaDependencyProvider dependencyProvider = new DivaDependencyProvider(initInfo);

		assertTrue(dependencyProvider instanceof DivaDependencyProvider);
		assertTrue(dependencyProvider instanceof TheRestDependencyProvider);

		ExtendedFunctionalityProvider extendedFunctionalityProvider = dependencyProvider
				.getExtendedFunctionalityProvider();

		assertTrue(extendedFunctionalityProvider instanceof DivaExtendedFunctionalityProvider);
		assertTrue(
				extendedFunctionalityProvider instanceof MetacreatorExtendedFunctionalityProvider);

		DivaExtendedFunctionalityProvider divaExtendedFunctionalityProvider = (DivaExtendedFunctionalityProvider) extendedFunctionalityProvider;

		SpiderDependencyProvider spiderDependencyProvider = divaExtendedFunctionalityProvider
				.getDependencyProvider();

		assertSame(spiderDependencyProvider, dependencyProvider);
	}
}
