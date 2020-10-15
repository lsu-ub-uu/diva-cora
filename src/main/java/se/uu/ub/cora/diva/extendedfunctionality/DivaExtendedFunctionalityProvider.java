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
package se.uu.ub.cora.diva.extendedfunctionality;

import java.util.List;

import se.uu.ub.cora.metacreator.extended.MetacreatorExtendedFunctionalityProvider;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionality;

public class DivaExtendedFunctionalityProvider extends MetacreatorExtendedFunctionalityProvider {

	public DivaExtendedFunctionalityProvider(SpiderDependencyProvider dependencyProvider) {
		super(dependencyProvider);
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityForUpdateAfterMetadataValidation(
			String recordType) {
		List<ExtendedFunctionality> extendedFunctionalities = super.getFunctionalityForUpdateAfterMetadataValidation(
				recordType);
		if ("divaOrganisation".equals(recordType)) {
			extendedFunctionalities.add(new OrganisationTypeRemover());
		}
		return extendedFunctionalities;
	}

	public SpiderDependencyProvider getDependencyProvider() {
		return dependencyProvider;
	}
}
