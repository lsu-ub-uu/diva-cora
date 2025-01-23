/*
 * Copyright 2025 Uppsala University Library
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

package se.uu.ub.cora.diva.extended.divaoutput.urn;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataProvider;
import se.uu.ub.cora.data.DataRecordGroup;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;

public class UrnExtendedFunctionality implements ExtendedFunctionality {

	private static final String URN = "urn:nbn";
	private static final String RECORD_INFO = "recordInfo";
	private static final String RECORD_CONTENT_SOURCE = "recordContentSource";
	private static final String URN_FORMAT = "urn:nbn:se:%s:diva-%s";

	@Override
	public void useExtendedFunctionality(ExtendedFunctionalityData data) {
		DataRecordGroup recordGroup = data.dataRecordGroup;
		createAndAddUrn(recordGroup);
	}

	private void createAndAddUrn(DataRecordGroup recordGroup) {
		DataGroup recordInfoGroup = recordGroup.getFirstGroupWithNameInData(RECORD_INFO);
		DataAtomic urnNumber = createUrnNumber(recordGroup, recordInfoGroup);
		recordInfoGroup.addChild(urnNumber);
	}

	private DataAtomic createUrnNumber(DataRecordGroup recordGroup, DataGroup recordInfo) {
		String recordId = recordGroup.getId();
		String domain = recordInfo.getFirstAtomicValueWithNameInData(RECORD_CONTENT_SOURCE);

		return DataProvider.createAtomicUsingNameInDataAndValue(URN,
				String.format(URN_FORMAT, domain, recordId));
	}
}