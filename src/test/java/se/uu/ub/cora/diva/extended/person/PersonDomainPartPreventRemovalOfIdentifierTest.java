/// *
// * Copyright 2022 Uppsala University Library
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
// package se.uu.ub.cora.diva.extended.person;
//
// import static org.testng.Assert.assertTrue;
//
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.Test;
//
// import se.uu.ub.cora.data.DataAtomicProvider;
// import se.uu.ub.cora.diva.spies.data.DataAtomicFactorySpy;
// import se.uu.ub.cora.diva.spies.data.DataAtomicSpy;
// import se.uu.ub.cora.diva.spies.data.DataGroupExtendedSpy;
// import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
// import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;
// import se.uu.ub.cora.spider.record.DataException;
//
// public class PersonDomainPartPreventRemovalOfIdentifierTest {
//
// private DataGroupExtendedSpy previousPersonDomainPart;
// private ExtendedFunctionalityData data;
// private DataGroupExtendedSpy updatedPersonDomainPart;
// private DataAtomicFactorySpy dataAtomicFactory;
//
// @BeforeMethod
// public void setUp() {
// setupDataFactories();
// createPreviousPersonDomainPart();
// createUpdatedPersonDomainPart();
// createExtendedFunctionalityData();
// }
//
// private void setupDataFactories() {
// dataAtomicFactory = new DataAtomicFactorySpy();
// DataAtomicProvider.setDataAtomicFactory(dataAtomicFactory);
// }
//
// private void createPreviousPersonDomainPart() {
// previousPersonDomainPart = new DataGroupExtendedSpy("personDomainPart");
// previousPersonDomainPart.addChild(new DataAtomicSpy("identifier", "aaaa", "1"));
// }
//
// private void createUpdatedPersonDomainPart() {
// updatedPersonDomainPart = new DataGroupExtendedSpy("personDomainPart");
// }
//
// private void createExtendedFunctionalityData() {
// data = new ExtendedFunctionalityData();
// data.recordType = "personDomainPart";
// data.recordId = "authorityPerson:123:uu";
// data.previouslyStoredTopDataGroup = previousPersonDomainPart;
// data.dataGroup = updatedPersonDomainPart;
// }
//
// @Test
// public void testNoRemovedLocalId() throws Exception {
// ExtendedFunctionality functionality = new PersonDomainPartPreventRemovalOfIdentifier();
// data.dataGroup = previousPersonDomainPart;
//
// functionality.useExtendedFunctionality(data);
// makeSureNoErrorIsThrownBeforeThisLine();
// }
//
// private void makeSureNoErrorIsThrownBeforeThisLine() {
// assertTrue(true);
// }
//
// @Test(expectedExceptions = DataException.class, expectedExceptionsMessageRegExp = ""
// + "LocalIds that already exist, can not be removed.")
// public void testOneLocalIdPreviousRecordRemovedFromUpdated() {
// ExtendedFunctionality functionality = new PersonDomainPartPreventRemovalOfIdentifier();
//
// functionality.useExtendedFunctionality(data);
// }
//
// @Test(expectedExceptions = DataException.class, expectedExceptionsMessageRegExp = ""
// + "LocalIds that already exist, can not be removed.")
// public void testTwoIdsPreviousRecordRemovedFromUpdatedOneLeftOneAdded() {
// addMoreIdsToPreviousAndUpdatedPerson();
// ExtendedFunctionality functionality = new PersonDomainPartPreventRemovalOfIdentifier();
//
// functionality.useExtendedFunctionality(data);
// }
//
// private void addMoreIdsToPreviousAndUpdatedPerson() {
// previousPersonDomainPart.addChild(new DataAtomicSpy("identifier", "bbbb", "2"));
// previousPersonDomainPart.addChild(new DataAtomicSpy("identifier", "cccc", "3"));
//
// updatedPersonDomainPart.addChild(new DataAtomicSpy("identifier", "cccc", "1"));
// updatedPersonDomainPart.addChild(new DataAtomicSpy("identifier", "dddd", "2"));
// }
// }
