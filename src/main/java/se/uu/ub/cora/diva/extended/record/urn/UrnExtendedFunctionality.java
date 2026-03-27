/*
 * Copyright 2025, 2026 Uppsala University Library
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

package se.uu.ub.cora.diva.extended.record.urn;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataProvider;
import se.uu.ub.cora.data.DataRecordGroup;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;

public class UrnExtendedFunctionality implements ExtendedFunctionality {

	private DataRecordGroup recordGroup;
	private DataGroup recordInfoGroup;

	@Override
	public void useExtendedFunctionality(ExtendedFunctionalityData data) {
		recordGroup = data.dataRecordGroup;
		recordInfoGroup = recordGroup.getFirstGroupWithNameInData("recordInfo");
		if (!recordInfoGroup.containsChildWithNameInData("urn")) {
			createAndAddUrnnbnChild();
		}
	}

	private void createAndAddUrnnbnChild() {
		DataAtomic urnNumber = createUrnnbnChild();
		recordInfoGroup.addChild(urnNumber);
	}

	private DataAtomic createUrnnbnChild() {
		String urnnbn = String.format("urn:nbn:se:diva-%s", recordGroup.getId());
		return DataProvider.createAtomicUsingNameInDataAndValue("urn", urnnbn);
	}
}