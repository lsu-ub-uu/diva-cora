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
package se.uu.ub.cora.diva.extended;

import java.util.List;

import se.uu.ub.cora.bookkeeper.termcollector.DataGroupTermCollector;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataGroupProvider;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.storage.RecordStorage;

public class PersonUpdaterAfterDomainPartCreate implements ExtendedFunctionality {

	private static final String PERSON = "person";
	private static final String PERSON_DOMAIN_PART = "personDomainPart";
	private RecordStorage recordStorage;
	private DataGroupTermCollector termCollector;

	public PersonUpdaterAfterDomainPartCreate(RecordStorage recordStorage,
			DataGroupTermCollector termCollector) {
		this.recordStorage = recordStorage;
		this.termCollector = termCollector;
	}

	@Override
	public void useExtendedFunctionality(String authToken, DataGroup dataGroup) {
		String recordId = extractRecordId(dataGroup);

		String personIdPartOfId = recordId.substring(0, recordId.lastIndexOf(":"));
		DataGroup readPerson = recordStorage.read(PERSON, personIdPartOfId);

		createAndAddPersonDomainPartToPerson(recordId, readPerson);
		setNewRepeatIdForRepeatedDataGroupsToEnsureUnique(
				readPerson.getAllGroupsWithNameInData(PERSON_DOMAIN_PART));
		String dataDivider = extractDataDivider(readPerson);

		DataGroup readRecordType = recordStorage.read("recordType", PERSON);
		DataGroup metadataIdLink = readRecordType.getFirstGroupWithNameInData("metadataId");
		String metadataId = metadataIdLink.getFirstAtomicValueWithNameInData("linkedRecordId");

		DataGroup collectedTerms = termCollector.collectTerms(metadataId, readPerson);

		recordStorage.update(PERSON, personIdPartOfId, readPerson, collectedTerms, null,
				dataDivider);
	}

	private String extractDataDivider(DataGroup readPerson) {
		DataGroup recordInfo = readPerson.getFirstGroupWithNameInData("recordInfo");
		DataGroup dataDividerGroup = recordInfo.getFirstGroupWithNameInData("dataDivider");
		return dataDividerGroup.getFirstAtomicValueWithNameInData("linkedRecordId");
	}

	private String extractRecordId(DataGroup dataGroup) {
		DataGroup recordInfo = dataGroup.getFirstGroupWithNameInData("recordInfo");
		return recordInfo.getFirstAtomicValueWithNameInData("id");
	}

	private void createAndAddPersonDomainPartToPerson(String recordId, DataGroup readPerson) {
		DataGroup personDomainPartLink = DataGroupProvider
				.getDataGroupAsLinkUsingNameInDataTypeAndId(PERSON_DOMAIN_PART, PERSON_DOMAIN_PART,
						recordId);
		readPerson.addChild(personDomainPartLink);
	}

	private void setNewRepeatIdForRepeatedDataGroupsToEnsureUnique(List<DataGroup> dataGroups) {
		int counter = 0;
		for (DataGroup repeatedDataGroup : dataGroups) {
			repeatedDataGroup.setRepeatId(String.valueOf(counter));
			counter++;
		}
	}

	public RecordStorage getRecordStorage() {
		return recordStorage;
	}

	public DataGroupTermCollector getTermCollector() {
		return termCollector;
	}

}