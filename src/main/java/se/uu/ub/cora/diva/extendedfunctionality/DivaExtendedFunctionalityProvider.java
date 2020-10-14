package se.uu.ub.cora.diva.extendedfunctionality;

import java.util.List;

import se.uu.ub.cora.metacreator.extended.MetacreatorExtendedFunctionalityProvider;
import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionality;

public class DivaExtendedFunctionalityProvider extends MetacreatorExtendedFunctionalityProvider {

	public DivaExtendedFunctionalityProvider(SpiderDependencyProvider dependencyProvider) {
		super(dependencyProvider);
	}

	@Override
	public List<ExtendedFunctionality> getFunctionalityForUpdateAfterMetadataValidation(
			String recordType) {
		List<ExtendedFunctionality> extendedFunctionalities = super.getFunctionalityForUpdateAfterMetadataValidation(
				recordType);
		if ("divaOrganisation".equals(recordType)) {
			extendedFunctionalities.add(new OrganisationTypeRemover());
		}
		return extendedFunctionalities;
	}

	public SpiderDependencyProvider getDependencyProvider() {
		return dependencyProvider;
	}
}
