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
package se.uu.ub.cora.diva.fedora;

import se.uu.ub.cora.diva.classic.RepeatableRelatedLinkCollector;
import se.uu.ub.cora.diva.exception.NotImplementedException;
import se.uu.ub.cora.fedora.FedoraConnectionInfo;
import se.uu.ub.cora.httphandler.HttpHandlerFactory;
import se.uu.ub.cora.xmlutils.transformer.CoraTransformationFactory;
import se.uu.ub.cora.xmlutils.transformer.XsltTransformationFactory;

public class ClassicFedoraModifierFactoryImp implements ClassicFedoraModifierFactory {

	private HttpHandlerFactory httpHandlerFactory;
	private RepeatableRelatedLinkCollector repeatableLinkCollector;
	private FedoraConnectionInfo fedoraConnectionInfo;

	public ClassicFedoraModifierFactoryImp(HttpHandlerFactory httpHandlerFactory,
			RepeatableRelatedLinkCollector repeatableLinkCollector,
			FedoraConnectionInfo fedoraConnectionInfo) {
		this.httpHandlerFactory = httpHandlerFactory;
		this.repeatableLinkCollector = repeatableLinkCollector;
		this.fedoraConnectionInfo = fedoraConnectionInfo;
	}

	@Override
	public ClassicFedoraModifier factor(String recordType) {
		if ("person".equals(recordType)) {
			DivaFedoraConverterFactory divaFedoraConverterFactory = createDivaFedoraConverterFactory();

			return new ClassicFedoraModifierImp(httpHandlerFactory, divaFedoraConverterFactory,
					fedoraConnectionInfo);
		}
		throw NotImplementedException
				.withMessage("Factor ClassicFedoraUpdater not implemented for " + recordType);
	}

	private DivaFedoraConverterFactory createDivaFedoraConverterFactory() {
		CoraTransformationFactory transformerFactory = new XsltTransformationFactory();
		return DivaFedoraConverterFactoryImp.usingFedoraURLAndTransformerFactory(
				fedoraConnectionInfo.fedoraUrl, transformerFactory, repeatableLinkCollector);
	}

	public HttpHandlerFactory getHttpHandlerFactory() {
		return httpHandlerFactory;
	}

	public RepeatableRelatedLinkCollector getRepeatableRelatedLinkCollector() {
		return repeatableLinkCollector;
	}

	public FedoraConnectionInfo getFedoraConnectionInfo() {
		return fedoraConnectionInfo;
	}

}
