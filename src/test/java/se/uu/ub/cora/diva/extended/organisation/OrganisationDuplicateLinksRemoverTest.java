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
import static org.testng.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataChild;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataRecordGroup;
import se.uu.ub.cora.data.spies.DataAtomicSpy;
import se.uu.ub.cora.data.spies.DataGroupSpy;
import se.uu.ub.cora.data.spies.DataRecordGroupSpy;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;

public class OrganisationDuplicateLinksRemoverTest {

	private DataRecordGroupSpy dataRecordGroup;

	@BeforeMethod
	public void setUp() {
		dataRecordGroup = new DataRecordGroupSpy();
	}

	@Test
	public void testTwoDifferentParents() {
		dataRecordGroup.returnContainsTrueNameInDatas.add("parentOrganisation");
		List<DataChild> parents = new ArrayList<>();
		//
		DataGroup parent1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "0", "51");
		parents.add(parent1);
		DataGroup parent2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "1", "52");
		parents.add(parent2);
		dataRecordGroup.childrenToReturn.put("parentOrganisation", parents);

		OrganisationDuplicateLinksRemover extendedFunctionality = new OrganisationDuplicateLinksRemover();
		extendedFunctionality.useExtendedFunctionality(createDefaultData(dataRecordGroup));

		assertEquals(dataRecordGroup.addedChildren.size(), 0);
		assertEquals(dataRecordGroup.removeAllGroupsUsedNameInDatas.size(), 0);
	}

	private ExtendedFunctionalityData createDefaultData(DataRecordGroup dataRecordGroupInput) {
		ExtendedFunctionalityData data = new ExtendedFunctionalityData();
		data.authToken = "someToken";
		data.dataRecordGroup = dataRecordGroupInput;
		return data;
	}

	@Test
	public void testTwoSameParentOneOther() {
		dataRecordGroup.returnContainsTrueNameInDatas.add("parentOrganisation");
		List<DataChild> parents = new ArrayList<>();

		DataGroup parent1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "0", "51");
		parents.add(parent1);
		DataGroup parent2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "1", "51");
		parents.add(parent2);
		DataGroup parent3 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"parentOrganisation", "1", "567");
		parents.add(parent3);

		dataRecordGroup.childrenToReturn.put("parentOrganisation", parents);

		OrganisationDuplicateLinksRemover extendedFunctionality = new OrganisationDuplicateLinksRemover();
		extendedFunctionality.useExtendedFunctionality(createDefaultData(dataRecordGroup));

		assertEquals(dataRecordGroup.getAllGroupsUsedNameInDatas.get(0), "parentOrganisation");
		assertEquals(dataRecordGroup.removeAllGroupsUsedNameInDatas.get(0), "parentOrganisation");

		assertEquals(dataRecordGroup.addedChildren.size(), 2);
		assertSame(dataRecordGroup.addedChildren.get(0), parent1);
		assertSame(dataRecordGroup.addedChildren.get(1), parent3);

	}

	private DataGroup createOrganisationLinkUsingRepeatIdAndOrganisationId(String nameInData,
			String repeatId, String parentId) {
		DataGroupSpy parentGroup = new DataGroupSpy(nameInData);
		parentGroup.setRepeatId(repeatId);
		DataGroupSpy organisationLink = new DataGroupSpy("organisationLink");
		DataAtomicSpy linkedRecordId = new DataAtomicSpy("linkedRecordId", parentId);
		organisationLink.addChild(linkedRecordId);
		parentGroup.addChild(organisationLink);
		return parentGroup;
	}

	@Test
	public void testTwoDifferentPredecessors() {
		dataRecordGroup.returnContainsTrueNameInDatas.add("earlierOrganisation");
		List<DataChild> predecessors = new ArrayList<>();

		DataGroup predecessor1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "0", "51");
		predecessors.add(predecessor1);
		DataGroup predecessor2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "1", "52");
		predecessors.add(predecessor2);
		dataRecordGroup.childrenToReturn.put("earlierOrganisation", predecessors);

		OrganisationDuplicateLinksRemover extendedFunctionality = new OrganisationDuplicateLinksRemover();
		extendedFunctionality.useExtendedFunctionality(createDefaultData(dataRecordGroup));

		assertEquals(dataRecordGroup.addedChildren.size(), 0);
		assertEquals(dataRecordGroup.removeAllGroupsUsedNameInDatas.size(), 0);
	}

	@Test
	public void testTwoSamePredecessorsOneOther() {
		dataRecordGroup.returnContainsTrueNameInDatas.add("earlierOrganisation");
		List<DataChild> parents = new ArrayList<>();

		DataGroup predecessor1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "0", "51");
		parents.add(predecessor1);
		DataGroup predecessor2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "1", "51");
		parents.add(predecessor2);
		DataGroup predecessor3 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
				"earlierOrganisation", "1", "567");
		parents.add(predecessor3);

		dataRecordGroup.childrenToReturn.put("earlierOrganisation", parents);

		OrganisationDuplicateLinksRemover extendedFunctionality = new OrganisationDuplicateLinksRemover();
		extendedFunctionality.useExtendedFunctionality(createDefaultData(dataRecordGroup));

		assertEquals(dataRecordGroup.getAllGroupsUsedNameInDatas.get(0), "earlierOrganisation");
		assertEquals(dataRecordGroup.removeAllGroupsUsedNameInDatas.get(0), "earlierOrganisation");

		assertEquals(dataRecordGroup.addedChildren.size(), 2);
		assertSame(dataRecordGroup.addedChildren.get(0), predecessor1);
		assertSame(dataRecordGroup.addedChildren.get(1), predecessor3);

	}

}
