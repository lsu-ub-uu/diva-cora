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

import java.util.Map;

import se.uu.ub.cora.search.RecordIndexer;
import se.uu.ub.cora.search.RecordSearch;
import se.uu.ub.cora.spider.authentication.Authenticator;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionalityProvider;

public class DependencyProviderSpy extends SpiderDependencyProvider {

	public DependencyProviderSpy(Map<String, String> initInfo) {
		super(initInfo);
	}

	@Override
	protected void tryToInitialize() throws Exception {

	}

	@Override
	protected void readInitInfo() {

	}

	@Override
	public ExtendedFunctionalityProvider getExtendedFunctionalityProvider() {
		return null;
	}

	@Override
	public Authenticator getAuthenticator() {
		return null;
	}

	@Override
	public RecordSearch getRecordSearch() {
		return null;
	}

	@Override
	public RecordIndexer getRecordIndexer() {
		return null;
	}

}
