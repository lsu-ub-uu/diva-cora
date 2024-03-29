/// *
// * Copyright 2019, 2021 Uppsala University Library
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
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import se.uu.ub.cora.data.DataAtomic;
// import se.uu.ub.cora.data.DataAtomicFactory;
//
// public class DataAtomicFactorySpy implements DataAtomicFactory {
//
// public DataAtomicSpy factoredDataAtomic;
// public List<DataAtomicSpy> factoredDataAtomics = new ArrayList<>();
// public Map<String, DataAtomicSpy> factoredDataAtomicsMap = new HashMap<>();
// public List<String> usedNameInDatas = new ArrayList<>();
// public List<String> usedValues = new ArrayList<>();
// public List<String> usedRepeatIds = new ArrayList<>();
//
// @Override
// public DataAtomic factorUsingNameInDataAndValue(String nameInData, String value) {
// usedNameInDatas.add(nameInData);
// usedValues.add(value);
// factoredDataAtomic = new DataAtomicSpy(nameInData, value);
// factoredDataAtomics.add(factoredDataAtomic);
// factoredDataAtomicsMap.put(nameInData, factoredDataAtomic);
// return factoredDataAtomic;
// }
//
// @Override
// public DataAtomic factorUsingNameInDataAndValueAndRepeatId(String nameInData, String value,
// String repeatId) {
// usedNameInDatas.add(nameInData);
// usedValues.add(value);
// usedRepeatIds.add(repeatId);
// factoredDataAtomic = new DataAtomicSpy(nameInData, value);
// factoredDataAtomics.add(factoredDataAtomic);
// return factoredDataAtomic;
// }
//
// }
