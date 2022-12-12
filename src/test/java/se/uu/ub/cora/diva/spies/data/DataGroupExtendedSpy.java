/// *
// * Copyright 2020 Uppsala University Library
// *
// * This file is part of Cora.
// *
// * Cora is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * Cora is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with Cora. If not, see <http://www.gnu.org/licenses/>.
// */
// package se.uu.ub.cora.diva.spies.data;
//
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;
// import java.util.function.Predicate;
//
// import se.uu.ub.cora.data.DataAtomic;
// import se.uu.ub.cora.data.DataAttribute;
// import se.uu.ub.cora.data.DataChild;
// import se.uu.ub.cora.data.DataGroup;
//
// public class DataGroupExtendedSpy implements DataGroup {
//
// public String nameInData;
// public List<DataChild> children = new ArrayList<>();
// public List<DataChild> groupChildren = new ArrayList<>();
// public List<String> getAllGroupsUsedNameInDatas = new ArrayList<>();
// public List<String> removeAllGroupsUsedNameInDatas = new ArrayList<>();
// public List<DataChild> addedChildren = new ArrayList<>();
// public List<String> returnContainsTrueNameInDatas = new ArrayList<>();
// public List<String> requestedAtomicNameInDatas = new ArrayList<>();
// public List<DataGroup> totalReturnedDataGroups = new ArrayList<>();
// private String repeatId;
// public List<String> nameInDatasRequestedFromContains = new ArrayList<>();
//
// public DataGroupExtendedSpy(String nameInData) {
// this.nameInData = nameInData;
// }
//
// @Override
// public void setRepeatId(String repeatId) {
// this.repeatId = repeatId;
//
// }
//
// @Override
// public String getRepeatId() {
// return repeatId;
// }
//
// @Override
// public String getNameInData() {
// return nameInData;
// }
//
// @Override
// public boolean hasChildren() {
// // TODO Auto-generated method stub
// return false;
// }
//
// @Override
// public boolean containsChildWithNameInData(String nameInData) {
// nameInDatasRequestedFromContains.add(nameInData);
// for (DataChild dataElement : children) {
// if (nameInData.equals(dataElement.getNameInData())) {
// return true;
// }
// }
// return false;
// }
//
// @Override
// public void addChildren(Collection<DataChild> dataElements) {
// for (DataChild dataElement : dataElements) {
// children.add(dataElement);
// }
// }
//
// @Override
// public List<DataChild> getChildren() {
// return children;
// }
//
// @Override
// public List<DataChild> getAllChildrenWithNameInData(String nameInData) {
// List<DataChild> currentListToReturn = new ArrayList<>();
// for (DataChild dataElement : children) {
// if (nameInData.equals(dataElement.getNameInData())) {
// currentListToReturn.add(dataElement);
// }
// }
// return currentListToReturn;
// }
//
// @Override
// public List<DataChild> getAllChildrenWithNameInDataAndAttributes(String nameInData,
// DataAttribute... childAttributes) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public DataChild getFirstChildWithNameInData(String nameInData) {
// for (DataChild dataElement : children) {
// if (nameInData.equals(dataElement.getNameInData())) {
// return dataElement;
// }
// }
// return null;
// }
//
// @Override
// public String getFirstAtomicValueWithNameInData(String nameInData) {
// requestedAtomicNameInDatas.add(nameInData);
// for (DataChild dataElement : children) {
// if (nameInData.equals(dataElement.getNameInData())) {
// if (dataElement instanceof DataAtomic) {
// return ((DataAtomic) dataElement).getValue();
// }
// }
// }
// throw new RuntimeException("Atomic value not found for childNameInData:" + nameInData);
// }
//
// @Override
// public List<DataAtomic> getAllDataAtomicsWithNameInData(String childNameInData) {
// List<DataAtomic> currentListToReturn = new ArrayList<>();
// for (DataChild dataElement : children) {
// if (childNameInData.equals(dataElement.getNameInData())
// && dataElement instanceof DataAtomic) {
// currentListToReturn.add((DataAtomic) dataElement);
// }
// }
// return currentListToReturn;
// }
//
// @Override
// public DataGroup getFirstGroupWithNameInData(String childNameInData) {
// for (DataChild dataElement : children) {
// if (childNameInData.equals(dataElement.getNameInData())) {
// if (dataElement instanceof DataGroup) {
// totalReturnedDataGroups.add((DataGroup) dataElement);
// return ((DataGroup) dataElement);
// }
// }
// }
// return null;
// }
//
// @Override
// public void addChild(DataChild dataElement) {
// if (dataElement instanceof DataGroup) {
// groupChildren.add(dataElement);
// }
// children.add(dataElement);
//
// }
//
// @Override
// public List<DataGroup> getAllGroupsWithNameInData(String nameInData) {
// getAllGroupsUsedNameInDatas.add(nameInData);
// List<DataGroup> currentListToReturn = new ArrayList<>();
// for (DataChild dataElement : children) {
// if (nameInData.equals(dataElement.getNameInData())
// && dataElement instanceof DataGroup) {
// totalReturnedDataGroups.add((DataGroup) dataElement);
// currentListToReturn.add((DataGroup) dataElement);
// }
// }
// return currentListToReturn;
// }
//
// @Override
// public Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String childNameInData,
// DataAttribute... childAttributes) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public boolean removeFirstChildWithNameInData(String childNameInData) {
// for (DataChild dataElement : children) {
// if (childNameInData.equals(dataElement.getNameInData())) {
// children.remove(dataElement);
// return true;
// }
// }
// return false;
// }
//
// @Override
// public boolean removeAllChildrenWithNameInData(String childNameInData) {
// removeAllGroupsUsedNameInDatas.add(childNameInData);
// return getChildren().removeIf(filterByNameInData(childNameInData));
// // return false;
// }
//
// private Predicate<DataChild> filterByNameInData(String childNameInData) {
// return dataElement -> dataElementsNameInDataIs(dataElement, childNameInData);
// }
//
// private boolean dataElementsNameInDataIs(DataChild dataElement, String childNameInData) {
// return dataElement.getNameInData().equals(childNameInData);
// }
//
// @Override
// public boolean removeAllChildrenWithNameInDataAndAttributes(String childNameInData,
// DataAttribute... childAttributes) {
// // TODO Auto-generated method stub
// return false;
// }
//
// @Override
// public DataAtomic getFirstDataAtomicWithNameInData(String childNameInData) {
// for (DataChild dataElement : children) {
// if (childNameInData.equals(dataElement.getNameInData())) {
// if (dataElement instanceof DataAtomic) {
// return ((DataAtomic) dataElement);
// }
// }
// }
// return null;
// }
//
// @Override
// public void addAttributeByIdWithValue(String nameInData, String value) {
// // TODO Auto-generated method stub
//
// }
//
// @Override
// public boolean hasAttributes() {
// // TODO Auto-generated method stub
// return false;
// }
//
// @Override
// public DataAttribute getAttribute(String nameInData) {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public Collection<DataAttribute> getAttributes() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public Collection<DataAtomic> getAllDataAtomicsWithNameInDataAndAttributes(
// String childNameInData, DataAttribute... childAttributes) {
// // TODO Auto-generated method stub
// return null;
// }
//
// }
