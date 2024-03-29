/// *
// * Copyright 2020 Uppsala University Library
// *
// * This file is part of Cora.
// *
// * Cora is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * Cora is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with Cora. If not, see <http://www.gnu.org/licenses/>.
// */
// package se.uu.ub.cora.diva.extended.organisation;
//
// import static org.testng.Assert.assertEquals;
// import static org.testng.Assert.assertSame;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.Test;
//
// import se.uu.ub.cora.data.DataChild;
// import se.uu.ub.cora.data.DataGroup;
// import se.uu.ub.cora.diva.spies.data.DataAtomicSpy;
// import se.uu.ub.cora.diva.spies.data.DataGroupSpy;
// import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;
//
// public class OrganisationDuplicateLinksRemoverTest {
//
// private DataGroupSpy dataGroup;
//
// @BeforeMethod
// public void setUp() {
// dataGroup = new DataGroupSpy("organisation");
// }
//
// @Test
// public void testTwoDifferentParents() {
// dataGroup.returnContainsTrueNameInDatas.add("parentOrganisation");
// List<DataChild> parents = new ArrayList<>();
//
// DataGroup parent1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "parentOrganisation", "0", "51");
// parents.add(parent1);
// DataGroup parent2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "parentOrganisation", "1", "52");
// parents.add(parent2);
// dataGroup.childrenToReturn.put("parentOrganisation", parents);
//
// OrganisationDuplicateLinksRemover extendedFunctionality = new
/// OrganisationDuplicateLinksRemover();
// extendedFunctionality.useExtendedFunctionality(createDefaultData(dataGroup));
//
// assertEquals(dataGroup.addedChildren.size(), 0);
// assertEquals(dataGroup.removeAllGroupsUsedNameInDatas.size(), 0);
// }
//
// private ExtendedFunctionalityData createDefaultData(DataGroup dataGroup) {
// ExtendedFunctionalityData data = new ExtendedFunctionalityData();
// data.authToken = "someToken";
// data.dataGroup = dataGroup;
// return data;
// }
//
// @Test
// public void testTwoSameParentOneOther() {
// dataGroup.returnContainsTrueNameInDatas.add("parentOrganisation");
// List<DataChild> parents = new ArrayList<>();
//
// DataGroup parent1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "parentOrganisation", "0", "51");
// parents.add(parent1);
// DataGroup parent2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "parentOrganisation", "1", "51");
// parents.add(parent2);
// DataGroup parent3 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "parentOrganisation", "1", "567");
// parents.add(parent3);
//
// dataGroup.childrenToReturn.put("parentOrganisation", parents);
//
// OrganisationDuplicateLinksRemover extendedFunctionality = new
/// OrganisationDuplicateLinksRemover();
// extendedFunctionality.useExtendedFunctionality(createDefaultData(dataGroup));
//
// assertEquals(dataGroup.getAllGroupsUsedNameInDatas.get(0), "parentOrganisation");
// assertEquals(dataGroup.removeAllGroupsUsedNameInDatas.get(0), "parentOrganisation");
//
// assertEquals(dataGroup.addedChildren.size(), 2);
// assertSame(dataGroup.addedChildren.get(0), parent1);
// assertSame(dataGroup.addedChildren.get(1), parent3);
//
// }
//
// private DataGroup createOrganisationLinkUsingRepeatIdAndOrganisationId(String nameInData,
// String repeatId, String parentId) {
// DataGroupSpy parentGroup = new DataGroupSpy(nameInData);
// parentGroup.setRepeatId(repeatId);
// DataGroupSpy organisationLink = new DataGroupSpy("organisationLink");
// DataAtomicSpy linkedRecordId = new DataAtomicSpy("linkedRecordId", parentId);
// organisationLink.addChild(linkedRecordId);
// parentGroup.addChild(organisationLink);
// return parentGroup;
// }
//
// @Test
// public void testTwoDifferentPredecessors() {
// dataGroup.returnContainsTrueNameInDatas.add("earlierOrganisation");
// List<DataChild> predecessors = new ArrayList<>();
//
// DataGroup predecessor1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "earlierOrganisation", "0", "51");
// predecessors.add(predecessor1);
// DataGroup predecessor2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "earlierOrganisation", "1", "52");
// predecessors.add(predecessor2);
// dataGroup.childrenToReturn.put("earlierOrganisation", predecessors);
//
// OrganisationDuplicateLinksRemover extendedFunctionality = new
/// OrganisationDuplicateLinksRemover();
// extendedFunctionality.useExtendedFunctionality(createDefaultData(dataGroup));
//
// assertEquals(dataGroup.addedChildren.size(), 0);
// assertEquals(dataGroup.removeAllGroupsUsedNameInDatas.size(), 0);
// }
//
// @Test
// public void testTwoSamePredecessorsOneOther() {
// dataGroup.returnContainsTrueNameInDatas.add("earlierOrganisation");
// List<DataChild> parents = new ArrayList<>();
//
// DataGroup predecessor1 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "earlierOrganisation", "0", "51");
// parents.add(predecessor1);
// DataGroup predecessor2 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "earlierOrganisation", "1", "51");
// parents.add(predecessor2);
// DataGroup predecessor3 = createOrganisationLinkUsingRepeatIdAndOrganisationId(
// "earlierOrganisation", "1", "567");
// parents.add(predecessor3);
//
// dataGroup.childrenToReturn.put("earlierOrganisation", parents);
//
// OrganisationDuplicateLinksRemover extendedFunctionality = new
/// OrganisationDuplicateLinksRemover();
// extendedFunctionality.useExtendedFunctionality(createDefaultData(dataGroup));
//
// assertEquals(dataGroup.getAllGroupsUsedNameInDatas.get(0), "earlierOrganisation");
// assertEquals(dataGroup.removeAllGroupsUsedNameInDatas.get(0), "earlierOrganisation");
//
// assertEquals(dataGroup.addedChildren.size(), 2);
// assertSame(dataGroup.addedChildren.get(0), predecessor1);
// assertSame(dataGroup.addedChildren.get(1), predecessor3);
//
// }
//
// }
