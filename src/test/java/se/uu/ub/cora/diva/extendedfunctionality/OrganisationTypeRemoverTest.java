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

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAtomicProvider;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataGroupProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionality;

public class OrganisationTypeRemoverTest {

	private OrganisationTypeRemover organisationTypeRemover;

	@BeforeTest
	public void setUp() {
		organisationTypeRemover = new OrganisationTypeRemover();
	}

	@Test
	public void testOrganisationTypeRemoverExtendsExtendedFunctionality() {
		assertTrue(organisationTypeRemover instanceof ExtendedFunctionality);
	}

	@Test
	public void testOrganisationTypeNotRemovedIfRootOrganisationIsNo() {
		DataGroup dataGroup = createOrganisationDataGroupWithRootOrganisation("no");

		organisationTypeRemover.useExtendedFunctionality("authToken", dataGroup);

		dataGroup.getAllDataAtomicsWithNameInData("organisationType");
		assertEquals(dataGroup.getAllDataAtomicsWithNameInData("organisationType").size(), 1);
	}

	private DataGroup createOrganisationDataGroupWithRootOrganisation(String yesOrNo) {
		DataGroup dataGroup = DataGroupProvider.getDataGroupUsingNameInData("organisation");

		DataAtomic organisationRoot = DataAtomicProvider
				.getDataAtomicUsingNameInDataAndValue("rootOrganisation", yesOrNo);

		DataAtomic organisationType = DataAtomicProvider
				.getDataAtomicUsingNameInDataAndValue("organisationType", "someType");

		dataGroup.addChild(organisationRoot);
		dataGroup.addChild(organisationType);
		return dataGroup;
	}

	@Test
	public void testOrganisationTypeRemovedIfRootOrganisationIsYes() {
		DataGroup dataGroup = createOrganisationDataGroupWithRootOrganisation("yes");

		organisationTypeRemover.useExtendedFunctionality("authToken", dataGroup);

		assertEquals(dataGroup.getAllDataAtomicsWithNameInData("organisationType").size(), 0);

	}

}
