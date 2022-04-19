/*
 * Copyright 2020 Uppsala University Library
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
package se.uu.ub.cora.diva.spies.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataChild;
import se.uu.ub.cora.data.DataGroup;

public class DataGroupSpy implements DataGroup {

	public String nameInData;
	public List<DataChild> children = new ArrayList<>();
	public List<DataChild> groupChildren = new ArrayList<>();
	public List<String> getAllGroupsUsedNameInDatas = new ArrayList<>();
	public Map<String, List<DataChild>> childrenToReturn = new HashMap<>();
	public List<String> removeAllGroupsUsedNameInDatas = new ArrayList<>();
	public List<DataChild> addedChildren = new ArrayList<>();
	public List<String> returnContainsTrueNameInDatas = new ArrayList<>();
	public List<String> requestedAtomicNameInDatas = new ArrayList<>();
	public String recordType;
	public String recordId;
	private String repeatId;

	public DataGroupSpy(String nameInData) {
		this.nameInData = nameInData;
	}

	public DataGroupSpy(String nameInData, String recordType, String recordId) {
		this.nameInData = nameInData;
		this.recordType = recordType;
		this.recordId = recordId;
	}

	@Override
	public void setRepeatId(String repeatId) {
		this.repeatId = repeatId;

	}

	@Override
	public String getRepeatId() {
		return repeatId;
	}

	@Override
	public String getNameInData() {
		return nameInData;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsChildWithNameInData(String nameInData) {
		return returnContainsTrueNameInDatas.contains(nameInData);
	}

	@Override
	public void addChildren(Collection<DataChild> dataElements) {
		for (DataChild dataElement : dataElements) {
			addedChildren.add(dataElement);
		}

	}

	@Override
	public List<DataChild> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataChild> getAllChildrenWithNameInData(String nameInData) {
		// getAllGroupsUsedNameInDatas.add(nameInData);
		if (childrenToReturn.containsKey(nameInData)) {
			return childrenToReturn.get(nameInData);
		}
		return null;
	}

	@Override
	public List<DataChild> getAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataChild getFirstChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstAtomicValueWithNameInData(String nameInData) {
		requestedAtomicNameInDatas.add(nameInData);
		for (DataChild dataElement : children) {
			if (nameInData.equals(dataElement.getNameInData())) {
				if (dataElement instanceof DataAtomic) {
					return ((DataAtomic) dataElement).getValue();
				}
			}
		}
		throw new RuntimeException("Atomic value not found for childNameInData:" + nameInData);
	}

	@Override
	public List<DataAtomic> getAllDataAtomicsWithNameInData(String childNameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataGroup getFirstGroupWithNameInData(String childNameInData) {
		for (DataChild dataElement : children) {
			if (childNameInData.equals(dataElement.getNameInData())) {
				if (dataElement instanceof DataGroup) {
					return ((DataGroup) dataElement);
				}
			}
		}
		return null;
	}

	@Override
	public void addChild(DataChild dataElement) {
		if (dataElement instanceof DataGroup) {
			groupChildren.add(dataElement);
		}
		children.add(dataElement);

	}

	@Override
	public List<DataGroup> getAllGroupsWithNameInData(String nameInData) {
		getAllGroupsUsedNameInDatas.add(nameInData);
		List<DataGroup> matchingDataGroups = new ArrayList<>();
		if (childrenToReturn.containsKey(nameInData)) {
			for (DataChild dataElement : childrenToReturn.get(nameInData)) {
				if (nameInData.equals(dataElement.getNameInData())
						&& dataElement instanceof DataGroup) {
					matchingDataGroups.add((DataGroup) dataElement);
				}
			}
		}
		return matchingDataGroups;
	}

	@Override
	public Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String childNameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstChildWithNameInData(String childNameInData) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllChildrenWithNameInData(String childNameInData) {
		removeAllGroupsUsedNameInDatas.add(childNameInData);
		return false;
	}

	@Override
	public boolean removeAllChildrenWithNameInDataAndAttributes(String childNameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataAtomic getFirstDataAtomicWithNameInData(String childNameInData) {
		for (DataChild dataElement : children) {
			if (childNameInData.equals(dataElement.getNameInData())) {
				if (dataElement instanceof DataAtomic) {
					return ((DataAtomic) dataElement);
				}
			}
		}
		return null;
	}

	@Override
	public void addAttributeByIdWithValue(String nameInData, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasAttributes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataAttribute getAttribute(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataAttribute> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataAtomic> getAllDataAtomicsWithNameInDataAndAttributes(
			String childNameInData, DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

}
