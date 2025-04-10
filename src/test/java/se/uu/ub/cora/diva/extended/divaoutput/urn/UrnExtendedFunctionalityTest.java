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

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.data.DataProvider;
import se.uu.ub.cora.data.spies.DataFactorySpy;
import se.uu.ub.cora.data.spies.DataGroupSpy;
import se.uu.ub.cora.data.spies.DataRecordGroupSpy;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;

public class UrnExtendedFunctionalityTest {

	private static final String RECORD_INFO = "recordInfo";
	private static final String RECORD_CONTENT_SOURCE = "recordContentSource";
	private DataRecordGroupSpy someRecord;
	private DataGroupSpy recordInfoGroup;
	private ExtendedFunctionalityData extendedFunctionalityData;
	private DataFactorySpy dataFactory;
	private UrnExtendedFunctionality urnExtFunc;

	@BeforeMethod
	private void beforeTest() {
		someRecord = new DataRecordGroupSpy();
		recordInfoGroup = new DataGroupSpy();

		someRecord.MRV.setDefaultReturnValuesSupplier("getFirstGroupWithNameInData",
				() -> recordInfoGroup);

		extendedFunctionalityData = new ExtendedFunctionalityData();
		extendedFunctionalityData.dataRecordGroup = someRecord;

		dataFactory = new DataFactorySpy();
		DataProvider.onlyForTestSetDataFactory(dataFactory);

		urnExtFunc = new UrnExtendedFunctionality();
	}

	@Test
	public void testInit() throws Exception {
		assertTrue(urnExtFunc instanceof ExtendedFunctionality);
	}

	@Test
	public void testDivaUrnNumberIsAdded() throws Exception {
		urnExtFunc.useExtendedFunctionality(extendedFunctionalityData);

		someRecord.MCR.assertParameters("getFirstGroupWithNameInData", 0, RECORD_INFO);
		someRecord.MCR.assertMethodWasCalled("getId");
		recordInfoGroup.MCR.assertParameters("getFirstAtomicValueWithNameInData", 0,
				RECORD_CONTENT_SOURCE);

		recordInfoGroup.MCR.assertMethodWasCalled("addChild");
	}
}