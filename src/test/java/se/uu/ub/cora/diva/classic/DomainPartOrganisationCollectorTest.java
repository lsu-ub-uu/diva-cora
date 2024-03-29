/// *
// * Copyright 2021 Uppsala University Library
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
// package se.uu.ub.cora.diva.classic;
//
// import static org.testng.Assert.assertEquals;
// import static org.testng.Assert.assertSame;
//
// import java.util.List;
// import java.util.Map;
//
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.Test;
//
// import se.uu.ub.cora.data.DataGroup;
// import se.uu.ub.cora.data.spies.DataGroupSpy;
// import se.uu.ub.cora.data.spies.DataRecordLinkSpy;
//
// public class DomainPartOrganisationCollectorTest {
//
// private RecordStorageForCollectingLinksSpy mixedStorage;
// private DomainPartOrganisationCollector collector;
//
// @BeforeMethod
// public void setUp() {
// mixedStorage = new RecordStorageForCollectingLinksSpy();
// collector = new DomainPartOrganisationCollector(mixedStorage);
// }
//
// @Test
// public void testInit() {
// assertSame(collector.getRecordStorage(), mixedStorage);
// }
//
// @Test
// public void testOneOrganisationInDomainPartNoParent() {
// DataGroupSpy personDomainPartLink = createPersonDomainPartLink("authority-person:111:test");
// DataGroupSpy personDomainPart = setUpDefaultDataGroup();
// mixedStorage.dataGroupsToReturn.put("personDomainPart_authority-person:111:test",
// personDomainPart);
//
// Map<String, Map<String, DataGroup>> links = collector.collectLinks(personDomainPartLink);
// assertEquals(mixedStorage.ids.get(0), "authority-person:111:test");
// assertEquals(mixedStorage.types.get(0), "personDomainPart");
// assertCorrectReadOrganisation(1, "56");
//
// DataGroup returnedOrganisation = mixedStorage.returnedDataGroups.get(1);
// Map<String, DataGroup> organisations = links.get("organisation");
//
// assertSame(organisations.get("56"), returnedOrganisation);
//
// Map<String, DataGroup> domainParts = links.get("personDomainPart");
// assertSame(domainParts.get("authority-person:111:test"),
// mixedStorage.returnedDataGroups.get(0));
//
// }
//
// private DataGroupSpy createPersonDomainPartLink(String linkedRecordId) {
// DataGroupSpy personDomainPartLink = new DataGroupSpy("personDomainPart");
// personDomainPartLink.addChild(new DataAtomicSpy("linkedRecordType", "personDomainPart"));
// personDomainPartLink.addChild(new DataAtomicSpy("linkedRecordId", linkedRecordId));
// return personDomainPartLink;
// }
//
// private DataGroupSpy setUpDefaultDataGroup() {
// DataGroupSpy personDomainPart = new DataGroupSpy("personDomainPart");
// DataGroupSpy affiliation = createAffiliation("56");
// personDomainPart.addChild(affiliation);
// return personDomainPart;
// }
//
// private DataGroupSpy createAffiliation(String orgId) {
// DataGroupSpy affiliation = new DataGroupSpy("affiliation");
// DataGroupSpy orgLink = new DataGroupSpy("organisationLink");
// orgLink.addChild(new DataAtomicSpy("linkedRecordId", orgId));
// affiliation.addChild(orgLink);
// return affiliation;
// }
//
// @Test
// public void testOneOrganisationInDomainPartWithParentAndGrandParent() {
// DataGroupSpy personDomainPartLink = createPersonDomainPartLink("authority-person:111:test");
// DataGroupSpy personDomainPart = setUpDefaultDataGroup();
// mixedStorage.dataGroupsToReturn.put("personDomainPart_authority-person:111:test",
// personDomainPart);
//
// setUpDbStorgageToReturnParentAndGrandParent();
//
// Map<String, Map<String, DataGroup>> links = collector.collectLinks(personDomainPartLink);
// assertEquals(mixedStorage.ids.get(0), "authority-person:111:test");
// assertEquals(mixedStorage.types.get(0), "personDomainPart");
//
// assertCorrectReadOrganisation(1, "56");
// assertCorrectReadOrganisation(2, "156");
// assertCorrectReadOrganisation(3, "256");
//
// List<DataGroup> returnedDataGroups = mixedStorage.returnedDataGroups;
// Map<String, DataGroup> organisations = links.get("organisation");
//
// assertSame(organisations.get("56"), returnedDataGroups.get(1));
// assertSame(organisations.get("156"), mixedStorage.returnedDataGroups.get(2));
// assertSame(organisations.get("256"), mixedStorage.returnedDataGroups.get(3));
//
// Map<String, DataGroup> domainParts = links.get("personDomainPart");
// assertSame(domainParts.get("authority-person:111:test"),
// mixedStorage.returnedDataGroups.get(0));
// }
//
// private void assertCorrectReadOrganisation(int index, String organisationId) {
// assertEquals(mixedStorage.ids.get(index), organisationId);
// assertEquals(mixedStorage.types.get(index), "organisation");
// }
//
// private void setUpDbStorgageToReturnParentAndGrandParent() {
// createOrganisationWithParentAndPutInSpy("organisation_56", "156");
// createOrganisationWithParentAndPutInSpy("organisation_156", "256");
//
// }
//
// private void createOrganisationWithParentAndPutInSpy(String key, String linkedRecordId) {
// DataGroupSpy orgToReturnFromStorage = new DataGroupSpy("organisation");
// DataGroupSpy parentLink = createParentOrganisationLink(linkedRecordId);
// orgToReturnFromStorage.addChild(parentLink);
// mixedStorage.dataGroupsToReturn.put(key, orgToReturnFromStorage);
// }
//
// private DataGroupSpy createParentOrganisationLink(String linkedRecordId) {
// // DataGroupSpy parentOrg = new DataGroupSpy("parentOrganisation");
// // DataGroupSpy organisationLink = new DataGroupSpy("organisationLink");
// // organisationLink.addChild(new DataAtomicSpy("linkedRecordId", linkedRecordId));
// // parentOrg.addChild(organisationLink);
// DataGroupSpy parentOrg = new DataGroupSpy();
// DataRecordLinkSpy organisationLink = new DataRecordLinkSpy();
// organisationLink.MRV.setDefaultReturnValuesSupplier("getLinkedRecordId",
// () -> linkedRecordId);
// parentOrg.addChild(organisationLink);
// return parentOrg;
// }
//
// @Test
// public void testSameParentLinkOnlyAddedOnce() {
// DataGroupSpy personDomainPartLink = createPersonDomainPartLink("authority-person:111:test");
// DataGroupSpy personDomainPart = setUpDefaultDataGroup();
// mixedStorage.dataGroupsToReturn.put("personDomainPart_authority-person:111:test",
// personDomainPart);
//
// DataGroupSpy affiliation2 = createAffiliation("57");
// personDomainPart.addChild(affiliation2);
//
// createOrganisationWithParentAndPutInSpy("organisation_56", "156");
// createOrganisationWithParentAndPutInSpy("organisation_57", "156");
//
// Map<String, Map<String, DataGroup>> links = collector.collectLinks(personDomainPartLink);
// assertEquals(links.size(), 2);
// assertCorrectReadOrganisation(1, "56");
// assertCorrectReadOrganisation(2, "156");
// assertCorrectReadOrganisation(3, "57");
// assertCorrectReadOrganisation(4, "156");
//
// List<DataGroup> returnedDataGroups = mixedStorage.returnedDataGroups;
// Map<String, DataGroup> organisations = links.get("organisation");
//
// assertSame(organisations.get("56"), returnedDataGroups.get(1));
// assertSame(organisations.get("57"), mixedStorage.returnedDataGroups.get(3));
// assertSame(organisations.get("156"), mixedStorage.returnedDataGroups.get(4));
//
// Map<String, DataGroup> domainParts = links.get("personDomainPart");
// assertSame(domainParts.get("authority-person:111:test"),
// mixedStorage.returnedDataGroups.get(0));
// }
// }
