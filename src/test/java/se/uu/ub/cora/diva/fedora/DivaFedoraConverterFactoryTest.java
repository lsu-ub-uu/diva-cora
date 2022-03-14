/*
 * Copyright 2018, 2021 Uppsala University Library
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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.diva.classic.RepeatableLinkCollectorSpy;
import se.uu.ub.cora.diva.classic.RepeatableRelatedLinkCollector;
import se.uu.ub.cora.diva.exception.NotImplementedException;

public class DivaFedoraConverterFactoryTest {
	private DivaFedoraConverterFactoryImp divaToCoraConverterFactoryImp;
	private String fedoraURL = "someFedoraUrl";
	private TransformationFactorySpy transformationFactory;
	private RepeatableRelatedLinkCollector repeatableLinkCollector;

	@BeforeMethod
	public void beforeMethod() {
		transformationFactory = new TransformationFactorySpy();
		repeatableLinkCollector = new RepeatableLinkCollectorSpy();
		divaToCoraConverterFactoryImp = DivaFedoraConverterFactoryImp
				.usingFedoraURLAndTransformerFactory(fedoraURL, transformationFactory,
						repeatableLinkCollector);
	}

	@Test
	public void testInit() {
		assertSame(divaToCoraConverterFactoryImp.getCoraTransformerFactory(),
				transformationFactory);
		assertSame(divaToCoraConverterFactoryImp.getRepeatableRelatedLinkCollector(),
				repeatableLinkCollector);
	}

	// @Test(expectedExceptions = NotImplementedException.class, expectedExceptionsMessageRegExp =
	// ""
	// + "No converter implemented for: someType")
	// public void factorUnknownTypeThrowsException() throws Exception {
	// divaToCoraConverterFactoryImp.factorToCoraConverter("someType");
	// }

	// @Test
	// public void testFactoryPerson() throws Exception {
	// DivaFedoraToCoraConverter converter = divaToCoraConverterFactoryImp
	// .factorToCoraConverter("person");
	// DivaFedoraToCoraConverterImp personConverter = (DivaFedoraToCoraConverterImp) converter;
	//
	// assertNotNull(personConverter.getCoraTransformation());
	// assertSame(personConverter.getCoraTransformation(),
	// transformationFactory.transformationSpy);
	//
	// assertEquals(transformationFactory.xsltPath, "person/coraPerson.xsl");
	// }

	// @Test
	// public void testFactoryPersonDomainPart() throws Exception {
	// DivaFedoraToCoraConverter converter = divaToCoraConverterFactoryImp
	// .factorToCoraConverter("personDomainPart");
	// assertTrue(converter instanceof DivaFedoraToCoraConverterImp);
	// DivaFedoraToCoraConverterImp personDomainPartConverter = (DivaFedoraToCoraConverterImp)
	// converter;
	//
	// assertNotNull(personDomainPartConverter.getCoraTransformation());
	// assertSame(personDomainPartConverter.getCoraTransformation(),
	// transformationFactory.transformationSpy);
	//
	// assertEquals(transformationFactory.xsltPath, "person/coraPersonDomainPart.xsl");
	// }

	@Test(expectedExceptions = NotImplementedException.class, expectedExceptionsMessageRegExp = ""
			+ "No converter implemented for: someType")
	public void factorToFedoraUnknownTypeThrowsException() throws Exception {
		divaToCoraConverterFactoryImp.factorToFedoraConverter("someType");
	}

	@Test
	public void testFactoryToFedoraPerson() throws Exception {
		DivaCoraToFedoraConverter converter = divaToCoraConverterFactoryImp
				.factorToFedoraConverter("person");
		assertTrue(converter instanceof DivaCoraToFedoraPersonConverter);
	}

	@Test
	public void testFactorToFedoraForPersonHasCorrectDependencies() throws Exception {
		DivaCoraToFedoraPersonConverter converter = (DivaCoraToFedoraPersonConverter) divaToCoraConverterFactoryImp
				.factorToFedoraConverter("person");
		assertSame(converter.getTransformationFactory(), transformationFactory);

		assertSame(converter.getRepeatbleRelatedLinkCollector(), repeatableLinkCollector);

	}

	@Test
	public void testGetFedoraURLNeededForTests() throws Exception {
		assertEquals(divaToCoraConverterFactoryImp.getFedoraURL(), fedoraURL);
	}
}