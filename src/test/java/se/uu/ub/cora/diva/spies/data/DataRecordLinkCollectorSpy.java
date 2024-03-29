/// *
// * Copyright 2022 Uppsala University Library
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
// import java.util.List;
//
// import se.uu.ub.cora.bookkeeper.linkcollector.DataRecordLinkCollector;
// import se.uu.ub.cora.data.DataGroup;
//
// public class DataRecordLinkCollectorSpy implements DataRecordLinkCollector {
//
// public List<String> metadataIds = new ArrayList<>();
// public List<DataGroup> dataGroups = new ArrayList<>();
// public List<String> fromRecordTypes = new ArrayList<>();
// public List<String> fromRecordIds = new ArrayList<>();
// public List<DataGroupExtendedSpy> returnedCollectedLinks = new ArrayList<>();
//
// @Override
// public DataGroup collectLinks(String metadataId, DataGroup dataGroup, String fromRecordType,
// String fromRecordId) {
// metadataIds.add(metadataId);
// dataGroups.add(dataGroup);
// fromRecordTypes.add(fromRecordType);
// fromRecordIds.add(fromRecordId);
// DataGroupExtendedSpy collectedLinks = new DataGroupExtendedSpy("collectedLinks");
// returnedCollectedLinks.add(collectedLinks);
// return collectedLinks;
// }
//
// }
