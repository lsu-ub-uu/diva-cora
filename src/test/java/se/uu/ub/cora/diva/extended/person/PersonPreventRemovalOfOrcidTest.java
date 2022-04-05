/*
 * Copyright 2022 Uppsala University Library
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
package se.uu.ub.cora.diva.extended.person;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataAtomicProvider;
import se.uu.ub.cora.diva.spies.data.DataAtomicFactorySpy;
import se.uu.ub.cora.diva.spies.data.DataAtomicSpy;
import se.uu.ub.cora.diva.spies.data.DataGroupExtendedSpy;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;
import se.uu.ub.cora.spider.record.DataException;

public class PersonPreventRemovalOfOrcidTest {

	private ExtendedFunctionalityData dataUpdatedPerson;
	private DataAtomicFactorySpy dataAtomicFactory;
	private DataGroupExtendedSpy previousPerson;
	private DataGroupExtendedSpy updatedPerson;

	@BeforeMethod
	public void setUp() {
		setupDataFactories();
		setupPreviousPersonWithOrcidId();
		setupUpdatedPersonWithoutOrcidId();
		createExtendedFunctionalityData();
	}

	private void setupDataFactories() {
		dataAtomicFactory = new DataAtomicFactorySpy();
		DataAtomicProvider.setDataAtomicFactory(dataAtomicFactory);
	}

	private void setupPreviousPersonWithOrcidId() {
		previousPerson = new DataGroupExtendedSpy("person");
		previousPerson.addChild(new DataAtomicSpy("ORCID_ID", "0000", "1"));
	}

	private void setupUpdatedPersonWithoutOrcidId() {
		updatedPerson = new DataGroupExtendedSpy("person");
	}

	private void createExtendedFunctionalityData() {
		dataUpdatedPerson = new ExtendedFunctionalityData();
		dataUpdatedPerson.recordType = "person";
		dataUpdatedPerson.recordId = "authority-person:001";
		dataUpdatedPerson.previouslyStoredTopDataGroup = previousPerson;
		dataUpdatedPerson.dataGroup = updatedPerson;
	}

	@Test
	public void testNoRemovedOrcid() throws Exception {
		ExtendedFunctionality functionality = new PersonPreventRemovalOfOrcid();
		dataUpdatedPerson.dataGroup = previousPerson;

		functionality.useExtendedFunctionality(dataUpdatedPerson);
		makeSureNoErrorIsThrownBeforeThisLine();
	}

	private void makeSureNoErrorIsThrownBeforeThisLine() {
		assertTrue(true);
	}

	@Test(expectedExceptions = DataException.class, expectedExceptionsMessageRegExp = ""
			+ "ORCIDs that already exist, can not be removed.")
	public void testOneOrcidPreviousRecordRemovedFromUpdated() {
		ExtendedFunctionality functionality = new PersonPreventRemovalOfOrcid();

		functionality.useExtendedFunctionality(dataUpdatedPerson);
	}

	@Test(expectedExceptions = DataException.class, expectedExceptionsMessageRegExp = ""
			+ "ORCIDs that already exist, can not be removed.")
	public void testTwoOrcidPreviousRecordRemovedFromUpdatedOneLeftOneAdded() {
		addMoreOrcidsToPreviousAndUpdatedPerson();
		ExtendedFunctionality functionality = new PersonPreventRemovalOfOrcid();

		functionality.useExtendedFunctionality(dataUpdatedPerson);
	}

	private void addMoreOrcidsToPreviousAndUpdatedPerson() {
		previousPerson.addChild(new DataAtomicSpy("ORCID_ID", "1111", "2"));
		previousPerson.addChild(new DataAtomicSpy("ORCID_ID", "2222", "3"));

		updatedPerson.addChild(new DataAtomicSpy("ORCID_ID", "2222", "1"));
		updatedPerson.addChild(new DataAtomicSpy("ORCID_ID", "3333", "2"));
	}
}
