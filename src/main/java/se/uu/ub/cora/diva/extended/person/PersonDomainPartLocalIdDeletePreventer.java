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
// package se.uu.ub.cora.diva.extended.person;
//
// import se.uu.ub.cora.data.DataGroup;
// import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionality;
// import se.uu.ub.cora.spider.extendedfunctionality.ExtendedFunctionalityData;
// import se.uu.ub.cora.spider.record.DataException;
//
// public class PersonDomainPartLocalIdDeletePreventer implements ExtendedFunctionality {
//
// private DataGroup personDomainPart;
//
// @Override
// public void useExtendedFunctionality(ExtendedFunctionalityData data) {
// personDomainPart = data.dataGroup;
// throwErrorIfPersonDomianHasAnIdentifier();
// }
//
// private void throwErrorIfPersonDomianHasAnIdentifier() {
// if (identifierExists()) {
// throw new DataException(
// "PersonDomainPart contains at least one identifier and can therefor not be deleted.");
// }
// }
//
// private boolean identifierExists() {
// return personDomainPart.containsChildWithNameInData("identifier");
// }
//
// }
