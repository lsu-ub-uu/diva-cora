/*
 * Copyright 2021 Uppsala University Library
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
package se.uu.ub.cora.diva.classic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.uu.ub.cora.data.DataGroup;

public class RelatedLinkCollectorFactorySpy implements RelatedLinkCollectorFactory {

	public RelatedLinkCollectorSpy returnedLinkCollector;

	public Map<Integer, List<String>> idsForDataGroupsToReturnForIndex = new HashMap<>();
	public Map<Integer, Map<String, Map<String, DataGroup>>> mapsToReturnFromCollectorSpy = new HashMap<>();

	public String type;

	@Override
	public RelatedLinkCollector factor(String type) {
		this.type = type;
		returnedLinkCollector = new RelatedLinkCollectorSpy();
		returnedLinkCollector.idsForDataGroupsToReturnForIndex = idsForDataGroupsToReturnForIndex;
		returnedLinkCollector.mapsToReturn = mapsToReturnFromCollectorSpy;
		return returnedLinkCollector;
	}

}
