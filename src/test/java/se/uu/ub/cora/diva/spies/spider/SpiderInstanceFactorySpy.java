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
// package se.uu.ub.cora.diva.spies.spider;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import se.uu.ub.cora.spider.dependency.SpiderInstanceFactory;
// import se.uu.ub.cora.spider.record.Downloader;
// import se.uu.ub.cora.spider.record.IncomingLinksReader;
// import se.uu.ub.cora.spider.record.RecordCreator;
// import se.uu.ub.cora.spider.record.RecordDeleter;
// import se.uu.ub.cora.spider.record.RecordListIndexer;
// import se.uu.ub.cora.spider.record.RecordListReader;
// import se.uu.ub.cora.spider.record.RecordReader;
// import se.uu.ub.cora.spider.record.RecordSearcher;
// import se.uu.ub.cora.spider.record.RecordUpdater;
// import se.uu.ub.cora.spider.record.RecordValidator;
// import se.uu.ub.cora.spider.record.Uploader;
//
// public class SpiderInstanceFactorySpy implements SpiderInstanceFactory {
//
// public List<RecordCreator> spiderRecordCreators = new ArrayList<>();
// public List<RecordReader> spiderRecordReaders = new ArrayList<>();
//
// @Override
// public String getDependencyProviderClassName() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordReader factorRecordReader() {
// SpiderRecordReaderSpy spiderRecordReaderSpy = new SpiderRecordReaderSpy();
// spiderRecordReaders.add(spiderRecordReaderSpy);
// return spiderRecordReaderSpy;
// }
//
// @Override
// public IncomingLinksReader factorIncomingLinksReader() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordListReader factorRecordListReader() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordCreator factorRecordCreator() {
// SpiderRecordCreatorSpy spiderRecordCreatorSpy = new SpiderRecordCreatorSpy();
// spiderRecordCreators.add(spiderRecordCreatorSpy);
// return spiderRecordCreatorSpy;
// }
//
// @Override
// public RecordUpdater factorRecordUpdater() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordDeleter factorRecordDeleter() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public Uploader factorUploader() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public Downloader factorDownloader() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordSearcher factorRecordSearcher() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordValidator factorRecordValidator() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordListIndexer factorRecordListIndexer() {
// // TODO Auto-generated method stub
// return null;
// }
//
// }
