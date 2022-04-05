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
 * PersonDomainPartPreventRemovalOfIdentifier ensures that an identifier can not be changed or
 * removed from a personDomainPart. Any removed identifiers will result in a DataException.
 */
public class PersonDomainPartPreventRemovalOfIdentifier implements ExtendedFunctionality {
	private static final String IDENTIFIER = "identifier";

	@Override
	public void useExtendedFunctionality(ExtendedFunctionalityData data) {
		List<String> previousIdentifierValues = extractIdentifiers(
				data.previouslyStoredTopDataGroup);
		List<String> currentIdentifierValues = extractIdentifiers(data.dataGroup);
		ensureNoIdentifiersHasBeenDeleted(previousIdentifierValues, currentIdentifierValues);
	}

	private List<String> extractIdentifiers(DataGroup dataRecord) {
		List<DataAtomic> identifiers = dataRecord.getAllDataAtomicsWithNameInData(IDENTIFIER);
		return ExtendedFunctionalityUtils.getDataAtomicValuesAsList(identifiers);
	}

	private void ensureNoIdentifiersHasBeenDeleted(List<String> previousValues,
			List<String> currentValues) {
		for (String previousValue : previousValues) {
			throwExceptionIfIdentifierIsDeleted(currentValues, previousValue);
		}
	}

	private void throwExceptionIfIdentifierIsDeleted(List<String> currentValues,
			String previousValue) {
		if (valueHasBeenRemoved(currentValues, previousValue)) {
			throw new DataException("LocalIds that already exist, can not be removed.");
		}
	}

	private boolean valueHasBeenRemoved(List<String> currentValues, String previousValue) {
		return !currentValues.contains(previousValue);
	}
}
