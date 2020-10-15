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

import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import se.uu.ub.cora.diva.extendedfunctionality.DivaExtendedFunctionalityProvider;
import se.uu.ub.cora.metacreator.extended.MetacreatorExtendedFunctionalityProvider;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionalityProvider;
import se.uu.ub.cora.therest.initialize.TheRestDependencyProvider;

public class DivaDependencyProviderTest {

	@Test
	public void testInit() {
		Map<String, String> initInfo = new HashMap<>();
		initInfo.put("gatekeeperURL", "someGatekeeperURL");
		initInfo.put("solrURL", "someSolrURL");
		DivaDependencyProvider divaDependencyProvider = new DivaDependencyProvider(initInfo);

		assertTrue(divaDependencyProvider instanceof TheRestDependencyProvider);

		ExtendedFunctionalityProvider extendedFunctionalityProvider = divaDependencyProvider
				.getExtendedFunctionalityProvider();

		assertTrue(
				extendedFunctionalityProvider instanceof MetacreatorExtendedFunctionalityProvider);

		DivaExtendedFunctionalityProvider divaExtendedFunctionalityProvider = (DivaExtendedFunctionalityProvider) extendedFunctionalityProvider;

		SpiderDependencyProvider spiderDependencyProvider = divaExtendedFunctionalityProvider
				.getDependencyProvider();

		assertSame(spiderDependencyProvider, divaDependencyProvider);
	}
}
