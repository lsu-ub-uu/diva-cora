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

package se.uu.ub.cora.diva.extended.organisation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataChild;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.spies.DataGroupSpy;
import se.uu.ub.cora.data.spies.DataRecordLinkSpy;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;

public class OrganisationDuplicateLinksRemoverTest {

	private DataGroupSpy dataGroup;
	private OrganisationDuplicateLinksRemover extendedFunctionality;
	private ExtendedFunctionalityData extendedData;

	@BeforeMethod
	public void setUp() {
		dataGroup = new DataGroupSpy();
		extendedData = createExtendedFunctionalityDataFromGroup(dataGroup);
		extendedFunctionality = new OrganisationDuplicateLinksRemover();

	}

	private ExtendedFunctionalityData createExtendedFunctionalityDataFromGroup(
			DataGroup dataGroup) {
		ExtendedFunctionalityData data = new ExtendedFunctionalityData();
		data.authToken = "someToken";
		data.dataGroup = dataGroup;
		return data;
	}

	@Test
	public void testTwoDifferentParents() {
		dataGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData", () -> true,
				"parentOrganisation");
		List<DataChild> parents = new ArrayList<>();

		DataGroup parent1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "51");
		parents.add(parent1);
		DataGroup parent2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "52");
		parents.add(parent2);
		dataGroup.MRV.setSpecificReturnValuesSupplier("getAllGroupsWithNameInData", () -> parents,
				"parentOrganisation");
		dataGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData", () -> true,
				"parentOrganisation");

		extendedFunctionality.useExtendedFunctionality(extendedData);

		dataGroup.MCR.assertMethodNotCalled("addChild");
		dataGroup.MCR.assertMethodNotCalled("removeChildWithNameInData");
	}

	private DataGroup createOrganisationLinkUsingRepeatIdAndOrganisationId(String nameInData,
			String parentId) {
		DataGroupSpy orgGroup = new DataGroupSpy();
		DataRecordLinkSpy organisationLink = new DataRecordLinkSpy();
		organisationLink.MRV.setDefaultReturnValuesSupplier("getLinkedRecordId", () -> parentId);
		orgGroup.MRV.setSpecificReturnValuesSupplier("getFirstChildWithNameInData",
				() -> organisationLink, "organisationLink");
		return orgGroup;
	}

	@Test
	public void testTwoSameParentOneOther() {
		dataGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData", () -> true,
				"parentOrganisation");
		List<DataChild> parents = new ArrayList<>();

		DataGroup parent1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "51");
		parents.add(parent1);
		DataGroup parent2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "51");
		parents.add(parent2);
		DataGroup parent3 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "567");
		parents.add(parent3);

		dataGroup.MRV.setSpecificReturnValuesSupplier("getAllGroupsWithNameInData", () -> parents,
				"parentOrganisation");
		dataGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData", () -> true,
				"parentOrganisation");

		extendedFunctionality.useExtendedFunctionality(extendedData);

		dataGroup.MCR.assertParameters("getAllGroupsWithNameInData", 0, "parentOrganisation");
		dataGroup.MCR.assertNumberOfCallsToMethod("getAllGroupsWithNameInData", 1);

		dataGroup.MCR.assertParameters("removeAllChildrenWithNameInData", 0, "parentOrganisation");
		dataGroup.MCR.assertNumberOfCallsToMethod("removeAllChildrenWithNameInData", 1);

		dataGroup.MCR.assertNumberOfCallsToMethod("addChildren", 1);
		Collection<DataChild> addedChildren = (Collection<DataChild>) dataGroup.MCR
				.getValueForMethodNameAndCallNumberAndParameterName("addChildren", 0,
						"dataChildren");
		assertEquals(addedChildren.size(), 2);
		assertTrue(addedChildren.contains(parent1));
		assertTrue(addedChildren.contains(parent3));
	}

	@Test
	public void testTwoDifferentPredecessors() {
		dataGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData", () -> true,
				"earlierOrganisation");
		List<DataChild> predecessors = new ArrayList<>();

		DataGroup predecessor1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "51");
		predecessors.add(predecessor1);
		DataGroup predecessor2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "52");
		predecessors.add(predecessor2);
		dataGroup.MRV.setSpecificReturnValuesSupplier("getAllGroupsWithNameInData",
				() -> predecessors, "earlierOrganisation");
		dataGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData", () -> true,
				"earlierOrganisation");

		extendedFunctionality.useExtendedFunctionality(extendedData);

		dataGroup.MCR.assertMethodNotCalled("addChild");
		dataGroup.MCR.assertMethodNotCalled("removeChildWithNameInData");
	}

	@Test
	public void testTwoSamePredecessorsOneOther() {
		List<DataChild> predecessors = new ArrayList<>();

		DataGroup predecessor1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "51");
		predecessors.add(predecessor1);
		DataGroup predecessor2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "51");
		predecessors.add(predecessor2);
		DataGroup predecessor3 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "567");
		predecessors.add(predecessor3);

		dataGroup.MRV.setSpecificReturnValuesSupplier("getAllGroupsWithNameInData",
				() -> predecessors, "earlierOrganisation");
		dataGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData", () -> true,
				"earlierOrganisation");

		extendedFunctionality.useExtendedFunctionality(extendedData);

		dataGroup.MCR.assertParameters("getAllGroupsWithNameInData", 0, "earlierOrganisation");
		dataGroup.MCR.assertNumberOfCallsToMethod("getAllGroupsWithNameInData", 1);

		dataGroup.MCR.assertParameters("removeAllChildrenWithNameInData", 0, "earlierOrganisation");
		dataGroup.MCR.assertNumberOfCallsToMethod("removeAllChildrenWithNameInData", 1);

		dataGroup.MCR.assertNumberOfCallsToMethod("addChildren", 1);
		Collection<DataChild> addedChildren = (Collection<DataChild>) dataGroup.MCR
				.getValueForMethodNameAndCallNumberAndParameterName("addChildren", 0,
						"dataChildren");
		assertEquals(addedChildren.size(), 2);
		assertTrue(addedChildren.contains(predecessor1));
		assertTrue(addedChildren.contains(predecessor3));

	}

}
