/*
 * Copyright 2020 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.uu.ub.cora.diva;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityPosition.UPDATE_AFTER_METADATA_VALIDATION;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.connection.ContextConnectionProviderImp;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProviderSpy;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityContext;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityFactory;
import se.uu.ub.cora.sqldatabase.DataReaderImp;

public class DivaExtendedFunctionalityFactoryTest {

	private ExtendedFunctionalityFactory factory;
	private Map<String, String> initInfo;
	private SpiderDependencyProviderSpy spiderDependencyProvider;

	@BeforeMethod
	public void setUp() {
		factory = new DivaExtendedFunctionalityFactory();
		initInfo = new HashMap<>();
		spiderDependencyProvider = new SpiderDependencyProviderSpy(initInfo);

		factory.initializeUsingDependencyProvider(spiderDependencyProvider);
	}

	@Test
	public void testInit() {
		assertEquals(factory.getExtendedFunctionalityContexts().size(), 2);
		assertCorrectContextUsingIndexAndRecordType(0, "commonOrganisation");
		assertCorrectContextUsingIndexAndRecordType(1, "rootOrganisation");
	}

	private void assertCorrectContextUsingIndexAndRecordType(int index, String recordType) {
		ExtendedFunctionalityContext updateBefore = factory.getExtendedFunctionalityContexts()
				.get(index);
		assertEquals(updateBefore.position, UPDATE_AFTER_METADATA_VALIDATION);
		assertEquals(updateBefore.recordType, recordType);
		assertEquals(updateBefore.runAsNumber, 0);
	}

	@Test
	public void factorCommonOrganisationUpdateAfter() {
		List<ExtendedFunctionality> functionalities = factory
				.factor(UPDATE_AFTER_METADATA_VALIDATION, "commonOrganisation");
		assertEquals(functionalities.size(), 2);
		assertTrue(functionalities.get(0) instanceof OrganisationDuplicateLinksRemover);

		OrganisationDisallowedDependencyDetector dependencyDetector = (OrganisationDisallowedDependencyDetector) functionalities
				.get(1);
		assertTrue(dependencyDetector.getDataReader() instanceof DataReaderImp);

		DataReaderImp dataReader = (DataReaderImp) dependencyDetector.getDataReader();
		ContextConnectionProviderImp sqlConnectionProvider = (ContextConnectionProviderImp) dataReader
				.getSqlConnectionProvider();
		sqlConnectionProvider.getContext();
		sqlConnectionProvider.getName();

		assertTrue(functionalities.get(1) instanceof OrganisationDisallowedDependencyDetector);

	}

	@Test
	public void factorRootOrganisationUpdateAfter() {
		List<ExtendedFunctionality> functionalities = factory
				.factor(UPDATE_AFTER_METADATA_VALIDATION, "rootOrganisation");
		assertEquals(functionalities.size(), 2);
		assertTrue(functionalities.get(0) instanceof OrganisationDuplicateLinksRemover);
		assertTrue(functionalities.get(1) instanceof OrganisationDisallowedDependencyDetector);

	}

}
