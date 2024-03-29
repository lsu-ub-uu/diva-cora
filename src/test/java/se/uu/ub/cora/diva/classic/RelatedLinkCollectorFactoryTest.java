/// *
// * Copyright 2021 Uppsala University Library
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
// package se.uu.ub.cora.diva.classic;
//
// import static org.testng.Assert.assertSame;
//
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.Test;
//
// import se.uu.ub.cora.diva.exception.NotImplementedException;
// import se.uu.ub.cora.storage.RecordStorage;
//
// public class RelatedLinkCollectorFactoryTest {
//
// private RecordStorage recordStorage;
// private RelatedLinkCollectorFactoryImp factory;
//
// @BeforeMethod
// public void setUp() {
// recordStorage = new RecordStorageForCollectingLinksSpy();
// factory = new RelatedLinkCollectorFactoryImp(recordStorage);
// }
//
// @Test
// public void testInit() {
// assertSame(factory.getRecordStorage(), recordStorage);
// }
//
// @Test
// public void testFactorForPersonDomainPart() {
// DomainPartOrganisationCollector linkCollector = (DomainPartOrganisationCollector) factory
// .factor("personDomainPart");
// assertSame(linkCollector.getRecordStorage(), recordStorage);
// }
//
// @Test(expectedExceptions = NotImplementedException.class, expectedExceptionsMessageRegExp = ""
// + "Factor not implemented for type otherType")
// public void testFactorOther() {
// factory.factor("otherType");
// }
// }
