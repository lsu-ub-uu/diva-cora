package se.uu.ub.cora.diva.extended.divaoutput.urn;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataProvider;
import se.uu.ub.cora.data.DataRecordGroup;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;

public class UrnExtendedFunctionality implements ExtendedFunctionality {

	private static final String ID = "id";
	private static final String URN = "urn:nbn";
	private static final String RECORD_INFO = "recordInfo";
	private static final String URN_FORMAT = "urn:nbn:se:%s:diva-%s";
	private static final String RECORD_CONTENT_SOURCE = "recordContentSource";

	@Override
	public void useExtendedFunctionality(ExtendedFunctionalityData data) {
		DataRecordGroup recordGroup = data.dataRecordGroup;
		if (hasRecordInfo(recordGroup)) {
			addUrnNumber(recordGroup);
		}
	}

	private boolean hasRecordInfo(DataRecordGroup recordGroup) {
		return recordGroup != null && recordGroup.containsChildWithNameInData(RECORD_INFO);
	}

	private void addUrnNumber(DataRecordGroup recordGroup) {
		DataGroup recordInfoGroup = recordGroup.getFirstGroupWithNameInData(RECORD_INFO);
		removeUrnIfPresent(recordInfoGroup);
		createAndAddUrn(recordInfoGroup);
	}

	private void removeUrnIfPresent(DataGroup recordInfoGroup) {
		if (recordInfoHasUrn(recordInfoGroup)) {
			recordInfoGroup.removeFirstChildWithNameInData(URN);
		}
	}

	private boolean recordInfoHasUrn(DataGroup recordInfoGroup) {
		return recordInfoGroup.containsChildWithNameInData(URN);
	}

	private void createAndAddUrn(DataGroup recordInfoGroup) {
		String recordId = recordInfoGroup.getFirstAtomicValueWithNameInData(ID);
		String domain = recordInfoGroup.getFirstAtomicValueWithNameInData(RECORD_CONTENT_SOURCE);
		DataAtomic urnNumber = DataProvider.createAtomicUsingNameInDataAndValue(URN,
				String.format(URN_FORMAT, domain, recordId));

		recordInfoGroup.addChild(urnNumber);
	}
}