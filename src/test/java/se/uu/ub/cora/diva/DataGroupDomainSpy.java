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
package se.uu.ub.cora.diva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataElement;
import se.uu.ub.cora.data.DataGroup;

public class DataGroupDomainSpy implements DataGroup {

	public String nameInData;
	public List<DataElement> children = new ArrayList<>();
	public List<DataElement> groupChildren = new ArrayList<>();
	public List<String> getAllGroupsUsedNameInDatas = new ArrayList<>();
	public List<String> removeAllGroupsUsedNameInDatas = new ArrayList<>();
	public List<DataElement> addedChildren = new ArrayList<>();
	public List<String> returnContainsTrueNameInDatas = new ArrayList<>();
	public List<String> requestedAtomicNameInDatas = new ArrayList<>();
	public List<DataGroup> totalReturnedDataGroups = new ArrayList<>();

	public DataGroupDomainSpy(String nameInData) {
		this.nameInData = nameInData;
	}

	@Override
	public void setRepeatId(String repeatId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRepeatId() {
		// TODO Auto-generated method stub
		return null;
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
		for (DataElement dataElement : children) {
			if (nameInData.equals(dataElement.getNameInData())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addChildren(Collection<DataElement> dataElements) {
		for (DataElement dataElement : dataElements) {
			children.add(dataElement);
		}
	}

	@Override
	public List<DataElement> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataElement> getAllChildrenWithNameInData(String nameInData) {
		return null;
	}

	@Override
	public List<DataElement> getAllChildrenWithNameInDataAndAttributes(String nameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataElement getFirstChildWithNameInData(String nameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstAtomicValueWithNameInData(String nameInData) {
		requestedAtomicNameInDatas.add(nameInData);
		for (DataElement dataElement : children) {
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
		for (DataElement dataElement : children) {
			if (childNameInData.equals(dataElement.getNameInData())) {
				if (dataElement instanceof DataGroup) {
					totalReturnedDataGroups.add((DataGroup) dataElement);
					return ((DataGroup) dataElement);
				}
			}
		}
		return null;
	}

	@Override
	public void addChild(DataElement dataElement) {
		if (dataElement instanceof DataGroup) {
			groupChildren.add(dataElement);
		}
		children.add(dataElement);

	}

	@Override
	public List<DataGroup> getAllGroupsWithNameInData(String nameInData) {
		getAllGroupsUsedNameInDatas.add(nameInData);
		List<DataGroup> currentListToReturn = new ArrayList<>();
		for (DataElement dataElement : children) {
			if (nameInData.equals(dataElement.getNameInData())
					&& dataElement instanceof DataGroup) {
				totalReturnedDataGroups.add((DataGroup) dataElement);
				currentListToReturn.add((DataGroup) dataElement);
			}
		}
		return currentListToReturn;
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
		// TODO Auto-generated method stub
		return null;
	}

}
