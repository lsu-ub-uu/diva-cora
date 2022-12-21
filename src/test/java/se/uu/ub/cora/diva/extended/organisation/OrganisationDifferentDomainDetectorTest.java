/// **Copyright 2020 Uppsala University Library**This file is part of Cora.**Cora is free
/// software:you can redistribute it and/or modify*it under the terms of the GNU General Public
/// License as published by*the Free Software Foundation,either version 3 of the License,or*(at your
/// option)any later version.**Cora is distributed in the hope that it will be useful,*but WITHOUT
/// ANY WARRANTY;without even the implied warranty of*MERCHANTABILITY or FITNESS FOR A PARTICULAR
/// PURPOSE.See the*GNU General Public License for more details.**You should have received a copy of
/// the GNU General Public License*along with Cora.If not,see<http://www.gnu.org/licenses/>.
// */package se.uu.ub.cora.diva.extended.organisation;
//
// import static org.testng.Assert.assertEquals;import static org.testng.Assert.assertSame;
//
// import java.util.ArrayList;import java.util.List;
//
// import org.testng.annotations.BeforeMethod;import org.testng.annotations.Test;
//
// import se.uu.ub.cora.data.DataChild;import se.uu.ub.cora.data.DataGroup;import
/// se.uu.ub.cora.diva.spies.data.DataAtomicSpy;import
/// se.uu.ub.cora.diva.spies.data.DataGroupExtendedSpy;import
/// se.uu.ub.cora.diva.spies.storage.RecordStorageSpy;import
/// se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;import
/// se.uu.ub.cora.spider.record.DataException;
//
// public class OrganisationDifferentDomainDetectorTest {
//
// private String authToken = "someAuthToken";
// private OrganisationDifferentDomainDetector functionality;
// private DataGroupExtendedSpy dataGroup;
// private RecordStorageSpy recordStorage;
//
// @BeforeMethod
// public void setUp() {
// recordStorage = new RecordStorageSpy();
// functionality = new OrganisationDifferentDomainDetector(recordStorage);
// createDefaultDataGroup();
// }
//
// private void createDefaultDataGroup() {
// dataGroup = new DataGroupExtendedSpy("organisation");
// DataGroupExtendedSpy recordInfo = new DataGroupExtendedSpy("recordInfo");
// recordInfo.addChild(new DataAtomicSpy("id", "4567"));
// recordInfo.addChild(new DataAtomicSpy("domain", "someDomain"));
// dataGroup.addChild(recordInfo);
// }
//
// @Test
// public void testInit() {
// assertSame(functionality.getRecordStorage(), recordStorage);
// }
//
// @Test
// public void testNoParentNoPredecessor() {
// functionality.useExtendedFunctionality(createDefaultData(dataGroup));
// DataGroupExtendedSpy recordInfo = (DataGroupExtendedSpy) dataGroup.totalReturnedDataGroups
// .get(0);
// assertEquals(recordInfo.requestedAtomicNameInDatas.get(0), "domain");
// assertEquals(dataGroup.getAllGroupsUsedNameInDatas.get(0), "parentOrganisation");
// assertEquals(dataGroup.getAllGroupsUsedNameInDatas.get(1), "earlierOrganisation");
// }
//
// private ExtendedFunctionalityData createDefaultData(DataGroup dataGroup) {
// ExtendedFunctionalityData data = new ExtendedFunctionalityData();
// data.authToken = "someAuthToken";
// data.dataGroup = dataGroup;
// return data;
// }
//
// @Test
// public void testOneParentNoPredecessorSameDomain() {
// List<DataChild> parents = createParentsUsingNumOfParents(1);
// dataGroup.addChildren(parents);
//
// addOrganisationToReturnFromStorage("organisation_parent0", "someDomain");
//
// functionality.useExtendedFunctionality(createDefaultData(dataGroup));
// DataGroupExtendedSpy returnedParent = (DataGroupExtendedSpy) dataGroup.totalReturnedDataGroups
// .get(1);
// DataGroupExtendedSpy organisationLink = (DataGroupExtendedSpy)
// / returnedParent.totalReturnedDataGroups.get(0);
// assertEquals(organisationLink.requestedAtomicNameInDatas.get(0), "linkedRecordId");
// assertEquals(recordStorage.readRecordTypes.get(0), "organisation");
// assertEquals(recordStorage.readRecordIds.get(0), "parent0");
// }
//
// private void addOrganisationToReturnFromStorage(String key, String domain) {
// DataGroupExtendedSpy parentToReturnFromStorage = new DataGroupExtendedSpy("organisation");
// DataGroupExtendedSpy recordInfo = new DataGroupExtendedSpy("recordInfo");
// recordInfo.addChild(new DataAtomicSpy("domain", domain));
// parentToReturnFromStorage.addChild(recordInfo);
// recordStorage.returnOnRead.put(key, parentToReturnFromStorage);
// }
//
// public List<DataChild> createParentsUsingNumOfParents(int numOfParents) {
// String nameInData = "parentOrganisation";
// return createOrganisationLinksUsingNameInDataAndNumOf(nameInData, numOfParents, "parent");
//
// }
//
// public List<DataChild> createPredecessorsUsingNumOfPredecessors(int numOf) {
// String nameInData = "earlierOrganisation";
// return createOrganisationLinksUsingNameInDataAndNumOf(nameInData, numOf, "predecessor");
//
// }
//
// private List<DataChild> createOrganisationLinksUsingNameInDataAndNumOf(String nameInData,
// int numOfParents, String prefix) {
// List<DataChild> parents = new ArrayList<>();
// for (int i = 0; i < numOfParents; i++) {
// String id = String.valueOf(i);
// DataGroup parent = createOrganisationLinkUsingNameInDataRepeatIdAndOrgId(nameInData, id,
// prefix + id);
// parents.add(parent);
// }
// return parents;
// }
//
// public DataGroup createOrganisationLinkUsingNameInDataRepeatIdAndOrgId(String nameInData,
// String repeatId, String parentId) {
// DataGroupExtendedSpy parentGroup = new DataGroupExtendedSpy(nameInData);
// parentGroup.setRepeatId(repeatId);
// DataGroupExtendedSpy organisationLink = new DataGroupExtendedSpy("organisationLink");
// DataAtomicSpy linkedRecordId = new DataAtomicSpy("linkedRecordId", parentId);
// organisationLink.addChild(linkedRecordId);
// DataAtomicSpy linkedRecordType = new DataAtomicSpy("linkedRecordType", "organisation");
// organisationLink.addChild(linkedRecordType);
// parentGroup.addChild(organisationLink);
// return parentGroup;
// }
//
// public List<DataChild> createListAndAddPredecessorUsingRepeatIdAndId(DataGroup dataGroup,
// String repeatId, String parentId) {
// List<DataChild> predecessors = new ArrayList<>();
// DataGroup predecessor = createOrganisationLinkUsingNameInDataRepeatIdAndOrgId(
// "earlierOrganisation", repeatId, parentId);
// predecessors.add(predecessor);
// return predecessors;
// }
//
// @Test(expectedExceptions = DataException.class, expectedExceptionsMessageRegExp = ""
// + "Links to organisations from another domain is not allowed.")
// public void testOneParentNoPredecessorDifferentDomain() {
// List<DataChild> parents = createParentsUsingNumOfParents(1);
// dataGroup.addChildren(parents);
// addOrganisationToReturnFromStorage("organisation_parent0", "someOtherDomain");
//
// functionality.useExtendedFunctionality(createDefaultData(dataGroup));
// }
//
// @Test(expectedExceptions = DataException.class, expectedExceptionsMessageRegExp = ""
// + "Links to organisations from another domain is not allowed.")
// public void testTwoParentsNoPredecessorOneSameOneDifferentDomain() {
// List<DataChild> parents = createParentsUsingNumOfParents(2);
// addOrganisationToReturnFromStorage("organisation_parent0", "someDomain");
// addOrganisationToReturnFromStorage("organisation_parent1", "someOtherDomain");
// dataGroup.addChildren(parents);
//
// functionality.useExtendedFunctionality(createDefaultData(dataGroup));
// }
//
// @Test
// public void testNoParentOnePredecessorSameDomain() {
// List<DataChild> predecessors = createPredecessorsUsingNumOfPredecessors(1);
// dataGroup.addChildren(predecessors);
//
// addOrganisationToReturnFromStorage("organisation_predecessor0", "someDomain");
//
// functionality.useExtendedFunctionality(createDefaultData(dataGroup));
// DataGroupExtendedSpy returnedPredecessor = (DataGroupExtendedSpy)
// / dataGroup.totalReturnedDataGroups.get(1);
// DataGroupExtendedSpy organisationLink = (DataGroupExtendedSpy)
// / returnedPredecessor.totalReturnedDataGroups.get(0);
// assertEquals(organisationLink.requestedAtomicNameInDatas.get(0), "linkedRecordId");
// assertEquals(recordStorage.readRecordTypes.get(0), "organisation");
// assertEquals(recordStorage.readRecordIds.get(0), "predecessor0");
// }
//
// @Test(expectedExceptions = DataException.class, expectedExceptionsMessageRegExp = ""
// + "Links to organisations from another domain is not allowed.")
// public void testNoParentOnePredecessorDifferentDomain() {
// List<DataChild> predecessors = createPredecessorsUsingNumOfPredecessors(1);
// dataGroup.addChildren(predecessors);
// addOrganisationToReturnFromStorage("organisation_predecessor0", "someOtherDomain");
//
// functionality.useExtendedFunctionality(createDefaultData(dataGroup));
// }
//
// @Test
// public void testOneParentOnePredecessorSameDomain() {
// List<DataChild> parents = createParentsUsingNumOfParents(1);
// dataGroup.addChildren(parents);
// List<DataChild> predecessors = createPredecessorsUsingNumOfPredecessors(1);
// dataGroup.addChildren(predecessors);
//
// addOrganisationToReturnFromStorage("organisation_parent0", "someDomain");
// addOrganisationToReturnFromStorage("organisation_predecessor0", "someDomain");
//
// functionality.useExtendedFunctionality(createDefaultData(dataGroup));
// assertEquals(dataGroup.totalReturnedDataGroups.size(), 3);
// DataGroupExtendedSpy returnedParent = (DataGroupExtendedSpy) dataGroup.totalReturnedDataGroups
// .get(1);
// assertCorrectOrganisationLink(returnedParent, "parent0", 0);
// DataGroupExtendedSpy returnedPredecessor = (DataGroupExtendedSpy)
// / dataGroup.totalReturnedDataGroups.get(2);
// assertCorrectOrganisationLink(returnedPredecessor, "predecessor0", 1);
// }
//
// private void assertCorrectOrganisationLink(DataGroupExtendedSpy returnedParent, String recordId,
// int indexInStorage) {
// DataGroupExtendedSpy organisationLink = (DataGroupExtendedSpy)
// / returnedParent.totalReturnedDataGroups.get(0);
// assertEquals(organisationLink.requestedAtomicNameInDatas.get(0), "linkedRecordId");
// assertEquals(recordStorage.readRecordTypes.get(0), "organisation");
// assertEquals(recordStorage.readRecordIds.get(indexInStorage), recordId);
// }
//
// }
