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
package se.uu.ub.cora.diva.extended.organisation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.uu.ub.cora.data.DataChild;
import se.uu.ub.cora.data.DataGroup;
import se.uu.ub.cora.data.DataRecordLink;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;

/**
 * OrganisationDuplicateLinksRemover removes duplicates from the existing parentOrganisations and
 * earlierOrganisations.
 * 
 * @implNote This implementation is NOT threadsafe, make sure a new instance is returned for each
 *           call.
 */
public class OrganisationDuplicateLinksRemover implements ExtendedFunctionality {

	private DataGroup dataGroup;

	@Override
	public void useExtendedFunctionality(ExtendedFunctionalityData data) {
		dataGroup = data.dataGroup;
		removeDuplicateBasedOnLinkedOrganisation("parentOrganisation");
		removeDuplicateBasedOnLinkedOrganisation("earlierOrganisation");
	}

	private void removeDuplicateBasedOnLinkedOrganisation(String nameInData) {
		if (dataGroup.containsChildWithNameInData(nameInData)) {
			removeDuplicatesFromLinkedOrganisationType(nameInData);
		}
	}

	private void removeDuplicatesFromLinkedOrganisationType(String nameInData) {
		List<DataGroup> organisations = dataGroup.getAllGroupsWithNameInData(nameInData);
		List<DataChild> elementsToKeep = calculateParentsToKeep(organisations);

		if (organisationListHasBeenReduced(organisations, elementsToKeep)) {
			dataGroup.removeAllChildrenWithNameInData(nameInData);
			dataGroup.addChildren(elementsToKeep);
		}
	}

	private boolean organisationListHasBeenReduced(List<DataGroup> organisations,
			List<DataChild> elementsToKeep) {
		return organisations.size() != elementsToKeep.size();
	}

	private List<DataChild> calculateParentsToKeep(List<DataGroup> parentOrganisations) {
		Set<String> sortedParents = new HashSet<>();
		List<DataChild> elementsToKeep = new ArrayList<>();
		for (DataGroup parentGroup : parentOrganisations) {
			calculateWhetherToKeepOrganisation(sortedParents, elementsToKeep, parentGroup);
		}
		return elementsToKeep;
	}

	private void calculateWhetherToKeepOrganisation(Set<String> sortedParents,
			List<DataChild> elementsToKeep, DataGroup parentGroup) {
		String organisationId = getLinkedOrganisationId(parentGroup);

		if (organisationDoesNotAlreadyExist(sortedParents, organisationId)) {
			elementsToKeep.add(parentGroup);
			sortedParents.add(organisationId);
		}
	}

	private String getLinkedOrganisationId(DataGroup parentGroup) {
		DataRecordLink parentLink = (DataRecordLink) parentGroup
				.getFirstChildWithNameInData("organisationLink");
		return parentLink.getLinkedRecordId();
	}

	private boolean organisationDoesNotAlreadyExist(Set<String> sortedParents,
			String organisationId) {
		return !sortedParents.contains(organisationId);
	}

}
