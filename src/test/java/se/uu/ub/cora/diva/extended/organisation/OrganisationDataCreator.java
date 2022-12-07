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
// package se.uu.ub.cora.diva.extended.organisation;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import se.uu.ub.cora.data.DataChild;
// import se.uu.ub.cora.data.DataGroup;
// import se.uu.ub.cora.diva.spies.data.DataAtomicSpy;
// import se.uu.ub.cora.diva.spies.data.DataGroupSpy;
//
// public class OrganisationDataCreator {
//
// public static List<DataChild> createListWithOneParentUsingRepeatIdAndParentId(
// DataGroup dataGroup, String repeatId, String parentId) {
// return createListWithOneParentUsingRepeatIdAndParentIdAndDomain(dataGroup, repeatId,
// parentId, "someDomain");
//
// }
//
// public static List<DataChild> createListWithOneParentUsingRepeatIdAndParentIdAndDomain(
// DataGroup dataGroup, String repeatId, String parentId, String domain) {
// List<DataChild> parents = new ArrayList<>();
// DataGroup parent = createAndAddOrganisationLinkToDefaultUsingRepeatIdAndOrganisationId(
// dataGroup, "parentOrganisation", repeatId, parentId);
// parent.addChild(new DataAtomicSpy("domain", domain));
// parents.add(parent);
// return parents;
// }
//
// public static DataGroup createAndAddOrganisationLinkToDefaultUsingRepeatIdAndOrganisationId(
// DataGroup dataGroup, String nameInData, String repeatId, String parentId) {
// DataGroupSpy parentGroup = new DataGroupSpy(nameInData);
// parentGroup.setRepeatId(repeatId);
// DataGroupSpy organisationLink = new DataGroupSpy("organisationLink");
// DataAtomicSpy linkedRecordId = new DataAtomicSpy("linkedRecordId", parentId);
// organisationLink.addChild(linkedRecordId);
// parentGroup.addChild(organisationLink);
// dataGroup.addChild(parentGroup);
// return parentGroup;
// }
//
// public static List<DataChild> createListAndAddPredecessorUsingRepeatIdAndId(DataGroup dataGroup,
// String repeatId, String parentId) {
// List<DataChild> predecessors = new ArrayList<>();
// DataGroup predecessor = OrganisationDataCreator
// .createAndAddOrganisationLinkToDefaultUsingRepeatIdAndOrganisationId(dataGroup,
// "earlierOrganisation", repeatId, parentId);
// predecessors.add(predecessor);
// return predecessors;
// }
// }
