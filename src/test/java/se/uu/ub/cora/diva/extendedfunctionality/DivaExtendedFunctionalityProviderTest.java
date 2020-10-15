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
package se.uu.ub.cora.diva.extendedfunctionality;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionality;

public class DivaExtendedFunctionalityProviderTest {

	private SpiderDependencyProvider dependencyProvider;
	private DivaExtendedFunctionalityProvider divaExtendedFunctionalityProvider;

	@BeforeMethod
	public void setUp() {
		dependencyProvider = new DependencyProviderSpy(new HashMap<>());

		divaExtendedFunctionalityProvider = new DivaExtendedFunctionalityProvider(
				dependencyProvider);
	}

	@Test
	public void testFunctionalityForUpdateAfterMetadataValidationWhenNotImplementedForRecordType() {

		List<ExtendedFunctionality> extendedFunctionalitiesForUpdateAfterValidation = divaExtendedFunctionalityProvider
				.getFunctionalityForUpdateAfterMetadataValidation("someRecordType");

		assertEquals(extendedFunctionalitiesForUpdateAfterValidation, Collections.emptyList());
	}

	@Test
	public void testFunctionalityForUpdateAfterMetadataValidationForDivaOrganisation() {

		List<ExtendedFunctionality> extendedFunctionalitiesForUpdateAfterValidation = divaExtendedFunctionalityProvider
				.getFunctionalityForUpdateAfterMetadataValidation("divaOrganisation");

		assertTrue(extendedFunctionalitiesForUpdateAfterValidation.size() > 0);

		boolean foundOrganisationTypeRemover = false;
		for (ExtendedFunctionality extendedFunctionality : extendedFunctionalitiesForUpdateAfterValidation) {
			if (extendedFunctionality instanceof OrganisationTypeRemover) {
				foundOrganisationTypeRemover = true;
			}
		}

		assertTrue(foundOrganisationTypeRemover);
	}

	@Test
	public void testFunctionalityForUpdateAfterMetadataValidationCallsSuperMethod() {
		RecordStorageProviderSpy recordStorageProviderSpy = new RecordStorageProviderSpy();
		dependencyProvider.setRecordStorageProvider(recordStorageProviderSpy);

		String recordTypeThatIsHandledOnlyInParentNotInDivaExtendedFunctionalityProvider = "metadataGroup";
		List<ExtendedFunctionality> extendedFunctionalitiesForUpdateAfterValidation = divaExtendedFunctionalityProvider
				.getFunctionalityForUpdateAfterMetadataValidation(
						recordTypeThatIsHandledOnlyInParentNotInDivaExtendedFunctionalityProvider);

		assertTrue(extendedFunctionalitiesForUpdateAfterValidation.size() > 0);
	}
}
