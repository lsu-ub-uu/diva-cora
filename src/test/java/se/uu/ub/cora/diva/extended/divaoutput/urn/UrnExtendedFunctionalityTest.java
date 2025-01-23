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

	private static final String ID = "id";
	private static final String URN = "urn:nbn";
	private static final String RECORD_INFO = "recordInfo";
	private static final String SOME_ID = "someId";
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
	public void testRecordInfoIsNull() throws Exception {
		extendedFunctionalityData.dataRecordGroup = null;

		urnExtFunc.useExtendedFunctionality(extendedFunctionalityData);
		someRecord.MCR.assertMethodNotCalled("getFirstGroupWithNameInData");
	}

	@Test
	public void testRecordInfoDoesNotExist() throws Exception {
		someRecord.MRV.setDefaultReturnValuesSupplier("containsChildWithNameInData", () -> false);

		urnExtFunc.useExtendedFunctionality(extendedFunctionalityData);

		someRecord.MCR.assertParameters("containsChildWithNameInData", 0, RECORD_INFO);
		someRecord.MCR.assertMethodNotCalled("getFirstGroupWithNameInData");
	}

	@Test
	public void testUrnNumberAlreadyExist() throws Exception {
		someRecord.MRV.setDefaultReturnValuesSupplier("containsChildWithNameInData", () -> true);
		recordInfoGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData",
				() -> true, URN);

		urnExtFunc.useExtendedFunctionality(extendedFunctionalityData);

		someRecord.MCR.assertParameters("containsChildWithNameInData", 0, RECORD_INFO);

		recordInfoGroup.MCR.assertMethodWasCalled("removeFirstChildWithNameInData");
		recordInfoGroup.MCR.assertParameters("getFirstAtomicValueWithNameInData", 0, ID);

		recordInfoGroup.MCR.assertMethodWasCalled("addChild");

	}

	@Test
	public void testAlvinUrnNumberIsAdded() throws Exception {
		someRecord.MRV.setDefaultReturnValuesSupplier("containsChildWithNameInData", () -> true);

		recordInfoGroup.MRV.setSpecificReturnValuesSupplier("containsChildWithNameInData",
				() -> false, URN);
		recordInfoGroup.MRV.setSpecificReturnValuesSupplier("getFirstAtomicValueWithNameInData",
				() -> SOME_ID, ID);

		urnExtFunc.useExtendedFunctionality(extendedFunctionalityData);

		someRecord.MCR.assertParameters("containsChildWithNameInData", 0, RECORD_INFO);
		recordInfoGroup.MCR.assertParameters("getFirstAtomicValueWithNameInData", 0, ID);
		recordInfoGroup.MCR.assertParameters("getFirstAtomicValueWithNameInData", 1,
				RECORD_CONTENT_SOURCE);

		recordInfoGroup.MCR.assertMethodWasCalled("addChild");
	}

}
