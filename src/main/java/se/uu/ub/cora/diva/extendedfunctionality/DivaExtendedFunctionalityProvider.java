package se.uu.ub.cora.diva.extendedfunctionality;

import java.util.ArrayList;
import java.util.List;

import se.uu.ub.cora.metacreator.extended.MetacreatorExtendedFunctionalityProvider;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionality;

public class DivaExtendedFunctionalityProvider extends MetacreatorExtendedFunctionalityProvider {

	public DivaExtendedFunctionalityProvider(SpiderDependencyProvider dependencyProvider) {
		super(dependencyProvider);
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityForCreateBeforeMetadataValidation(
			String recordType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityForCreateAfterMetadataValidation(
			String recordType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityForCreateBeforeReturn(String recordType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityForUpdateBeforeMetadataValidation(
			String recordType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityForUpdateAfterMetadataValidation(
			String recordType) {
		List<ExtendedFunctionality> extendedFunctionalities = new ArrayList<>();
		if ("divaOrganisation".equals(recordType)) {
			extendedFunctionalities.add(null);
		}
		return extendedFunctionalities;
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityBeforeDelete(String recordType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityAfterDelete(String recordType) {
		// TODO Auto-generated method stub
		return null;
	}

	public SpiderDependencyProvider getDependencyProvider() {
		return dependencyProvider;
	}
}
