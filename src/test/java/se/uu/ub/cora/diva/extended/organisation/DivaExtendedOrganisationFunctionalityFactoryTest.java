/*
 * Copyright 2020, 2021, 2022 Uppsala University Library
 *
 * This file is part of Cora.
 *
 * Cora is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Cora is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Cora. If not, see <http://www.gnu.org/licenses/>.
 */
package se.uu.ub.cora.diva.extended.organisation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityPosition.UPDATE_BEFORE_STORE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityContext;

public class DivaExtendedOrganisationFunctionalityFactoryTest {

	private DivaExtendedOrganisationFunctionalityFactory extendedFactory;
	private Map<String, String> initInfo;
	// private SpiderDependencyProviderSpy spiderDependencyProvider;
	// private SqlDatabaseFactorySpy databaseFactorySpy;
	// private RecordStorage recordStorage;

	@BeforeMethod
	public void setUp() {
		extendedFactory = new DivaExtendedOrganisationFunctionalityFactory();
		setUpInitInfo();
		// spiderDependencyProvider = new SpiderDependencyProviderSpy(initInfo);
		// recordStorage = spiderDependencyProvider.getRecordStorage();
		// databaseFactorySpy = new SqlDatabaseFactorySpy();

		// divaExtendedFunctionality.initializeUsingDependencyProvider(spiderDependencyProvider);
		extendedFactory.initializeUsingDependencyProvider(null);
	}

	private void setUpInitInfo() {
		initInfo = new HashMap<>();
	}

	@Test
	public void testInit() {
		List<ExtendedFunctionalityContext> contexts = extendedFactory
				.getExtendedFunctionalityContexts();

		assertEquals(contexts.size(), 3);
		extendedFunctionalityIsBeforeStoreForType(contexts.get(0), "subOrganisation");
		extendedFunctionalityIsBeforeStoreForType(contexts.get(1), "topOrganisation");
		extendedFunctionalityIsBeforeStoreForType(contexts.get(2), "rootOrganisation");
	}

	private void extendedFunctionalityIsBeforeStoreForType(ExtendedFunctionalityContext context,
			String recordType) {
		int runAsNumber = 0;
		assertTrue(UPDATE_BEFORE_STORE.equals(context.position)
				&& recordType.equals(context.recordType) && context.runAsNumber == runAsNumber);
	}

	public void factorSubOrganisationUpdateBeforeStore() {
		// extendedFactory.onlyForTestSetSqlDatabaseFactory(databaseFactorySpy);

		List<ExtendedFunctionality> functionalities = extendedFactory.factor(UPDATE_BEFORE_STORE,
				"subOrganisation");
		assertCorrectFactoredFunctionalities(functionalities);
	}

	private void assertCorrectFactoredFunctionalities(List<ExtendedFunctionality> functionalities) {
		// assertEquals(functionalities.size(), 3);
		assertEquals(functionalities.size(), 1);
		assertTrue(functionalities.get(0) instanceof OrganisationDuplicateLinksRemover);

		// OrganisationDisallowedDependencyDetector dependencyDetector =
		// (OrganisationDisallowedDependencyDetector) functionalities
		// .get(1);
		// DatabaseFacade factoredDatabaseFacade =
		// dependencyDetector.onlyForTestGetDatabaseFacade();
		// assertTrue(factoredDatabaseFacade instanceof DatabaseFacade);
		//
		// databaseFactorySpy.MCR.assertReturn("factorDatabaseFacade", 0, factoredDatabaseFacade);
		//
		// OrganisationDifferentDomainDetector differentDomainDetector =
		// (OrganisationDifferentDomainDetector) functionalities
		// .get(2);
		// assertNotNull(differentDomainDetector.getRecordStorage());
		// assertSame(differentDomainDetector.getRecordStorage(), recordStorage);
	}

	@Test
	public void factorRootOrganisationUpdateBeforeStore() {
		List<ExtendedFunctionality> functionalities = extendedFactory.factor(UPDATE_BEFORE_STORE,
				"rootOrganisation");
		assertCorrectFactoredFunctionalities(functionalities);
	}

	@Test
	public void factorTopOrganisationUpdateBeforeStore() {
		List<ExtendedFunctionality> functionalities = extendedFactory.factor(UPDATE_BEFORE_STORE,
				"topOrganisation");
		assertCorrectFactoredFunctionalities(functionalities);
	}

	// @Test
	// public void factorClassicOrganisationUpdaterUpdateAfterStoreForSubOrganisation() {
	// List<ExtendedFunctionality> functionalities = divaExtendedFunctionality
	// .factor(ExtendedFunctionalityPosition.UPDATE_AFTER_STORE, "subOrganisation");
	// assertEquals(functionalities.size(), 1);
	// assertTrue(functionalities.get(0) instanceof ClassicOrganisationReloader);
	// ClassicOrganisationReloader functionality = (ClassicOrganisationReloader) functionalities
	// .get(0);
	// HttpHandlerFactory httpHandlerFactory = functionality.getHttpHandlerFactory();
	// assertTrue(httpHandlerFactory instanceof HttpHandlerFactoryImp);
	// assertEquals(functionality.getUrl(), initInfo.get("classicListUpdateURL"));
	// }
	//
	// @Test
	// public void factorClassicOrganisationUpdaterUpdateAfterStoreForRootOrganisation() {
	// List<ExtendedFunctionality> functionalities = divaExtendedFunctionality
	// .factor(ExtendedFunctionalityPosition.UPDATE_AFTER_STORE, "rootOrganisation");
	// assertEquals(functionalities.size(), 1);
	// assertTrue(functionalities.get(0) instanceof ClassicOrganisationReloader);
	// }
	//
	// @Test
	// public void factorClassicOrganisationUpdaterUpdateAfterStoreForTopOrganisation() {
	// List<ExtendedFunctionality> functionalities = divaExtendedFunctionality
	// .factor(ExtendedFunctionalityPosition.UPDATE_AFTER_STORE, "topOrganisation");
	// assertEquals(functionalities.size(), 1);
	// assertTrue(functionalities.get(0) instanceof ClassicOrganisationReloader);
	// }

}
