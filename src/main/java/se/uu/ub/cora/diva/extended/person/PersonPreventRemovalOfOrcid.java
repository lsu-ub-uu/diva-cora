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

import java.util.List;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.diva.extended.ExtendedFunctionalityUtils;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;
import se.uu.ub.cora.spider.record.DataException;

/**
 * PersonPreventRemovalOfOrcid ensures that an orcid can not be changed or removed from a person.
 * Any removed orcid will result in a DataException.
 */
public class PersonPreventRemovalOfOrcid implements ExtendedFunctionality {
	private static final String ORCID_ID = "ORCID_ID";

	@Override
	public void useExtendedFunctionality(ExtendedFunctionalityData data) {
		List<String> previousOrcidValues = extractOrcids(data.previouslyStoredTopDataGroup);
		List<String> currentOrcidValues = extractOrcids(data.dataGroup);
		ensureNoOrcidsHasBeenDeleted(previousOrcidValues, currentOrcidValues);
	}

	private List<String> extractOrcids(DataGroup dataRecord) {
		List<DataAtomic> orcids = dataRecord.getAllDataAtomicsWithNameInData(ORCID_ID);
		return ExtendedFunctionalityUtils.getDataAtomicValuesAsList(orcids);
	}

	private void ensureNoOrcidsHasBeenDeleted(List<String> previousValues,
			List<String> currentValues) {
		for (String previousValue : previousValues) {
			throwExceptionIfOrcidIsDeleted(currentValues, previousValue);
		}
	}

	private void throwExceptionIfOrcidIsDeleted(List<String> currentValues, String previousValue) {
		if (valueHasBeenRemoved(currentValues, previousValue)) {
			throw new DataException("ORCIDs that already exist, can not be removed.");
		}
	}

	private boolean valueHasBeenRemoved(List<String> currentOrcidValues, String previousOrcid) {
		return !currentOrcidValues.contains(previousOrcid);
	}
}
