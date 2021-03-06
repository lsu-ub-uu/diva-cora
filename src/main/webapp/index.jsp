<%--
  ~ Copyright 2015 Uppsala University Library
  ~
  ~ This file is part of Cora.
  ~
  ~     Cora is free software: you can redistribute it and/or modify
  ~     it under the terms of the GNU General Public License as published by
  ~     the Free Software Foundation, either version 3 of the License, or
  ~     (at your option) any later version.
  ~
  ~     Cora is distributed in the hope that it will be useful,
  ~     but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~     GNU General Public License for more details.
  ~
  ~     You should have received a copy of the GNU General Public License
  ~     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
  --%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Hello Rest of The World!</title>
</head>
<body>
	<h1>Hello Rest of The World!</h1>
	When "the rest" starts up, the
	<strong>SystemInitializer</strong> class is started through a @WebListener. It
	will create a new
	<strong>SystemOneDependencyProvider</strong> (implementing
	SpiderDependencyProvider).
	<strong>SystemOneDependencyProvider</strong> initializes the needed dependencies
	for the system:
	<br> recordStorage (used by spider) currently as
	<strong>RecordStorageInMemory</strong>
	<em>(this is the same class that also provides metadataStorage)</em>
	<br> idGenerator (used by spider) currently as
	<strong>TimeStampIdGenerator</strong>
	<br> keyCalculator (used by spider) currently as
	<strong>RecordPermissionKeyCalculator</strong>
	<br> metadataStorage (used by metadataformat) currently as
	<strong>RecordStorageInMemory</strong>
	<em>(this is the same class that also provides recordStorage)</em>
	<br> dataValidator (used by metadataformat) currently as
	<strong>DataValidatorImp</strong>
	<br> authorizator (used by beefeater) currently as
	<strong>AuthorizatorImp</strong>
	<br>
	<br>
	<strong>SystemOneDependencyProvider</strong> also adds to storage, the basic
	metadata that is needed for the system to work. The needed metadata is:
	<br>Metadata groups including all subgroups and variables for,
	<strong>metadataNew</strong>,
	<strong>recordType</strong> and
	<strong>RecordTypeNew</strong>
	<br>
	<br>
	<strong>SystemOneDependencyProvider</strong> also adds to storage, the basic
	recordtypes that is needed for the system to work. The needed
	recordTypes are:
	<br>
	<strong>Metadata</strong> and
	<strong>RecordType</strong>
	<br>
	<h2>Use these tools</h2>
	Use the firefox addon RESTClient to play with this...
	<br> and the site: <a href="http://www.jsoneditoronline.org/">http://www.jsoneditoronline.org/</a> might also
	help
	<br>


	<h1>Examples</h1>

	Examples can be found in our <a href="http://epc.ub.uu.se/fitnesse/TheRestTests">acceptance tests</a>
	<h3>Create</h3>
	To create data, use: <br> POST http://epc.ub.uu.se/cora/rest/record/theTypeYouWantToCreate
	<br>
	<br>
	Examples of what the body should look like can be found here: <br>
	<a href="http://epc.ub.uu.se/fitnesse/TheRestTests.CallThroughJavaCode.RecordTypeTests.AbstractRecordType">AbstractRecordType</a>
	<br>
	<h3>Read</h3>
	To read a list of types, use: <br> GET http://epc.ub.uu.se/cora/rest/record/theTypeYouWantToRead
	<br>
	<br>
	To read an instance of a type, use: <br> GET http://epc.ub.uu.se/cora/rest/record/theTypeYouWantToRead/theIdOfTheInstanceYouWantToRead
	<br>
	<h3>Update</h3>
	To update data use: <br> POST http://epc.ub.uu.se/cora/rest/record/theTypeYouWantToUpdate/theIdOfTheDataYouWantToUpdate
	<br>
	<br>
	Examples of what the body should look like can be found here: <br>
	<a href="http://epc.ub.uu.se/fitnesse/TheRestTests.CallThroughJavaCode.RecordTypeTests.AbstractRecordType">AbstractRecordType</a>
	<br>
	<h3>Delete</h3>
	To delete data use: <br> DELETE http://epc.ub.uu.se/cora/rest/record/theTypeYouWantToUpdate/theIdOfTheDataYouWantToDelete
	<br>
	<br>
	<br>

</body>
</html>
