package se.uu.ub.cora.diva.extendedfunctionality;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.spider.dependency.SpiderDependencyProvider;
import se.uu.ub.cora.spider.extended.ExtendedFunctionality;

public class DivaExtendedFunctionalityProviderTest {

	private SpiderDependencyProvider dependencyProvider;
	private DivaExtendedFunctionalityProvider divaExtendedFunctionalityProvider;

	@BeforeMethod
	public void setUp() {
		dependencyProvider = new DependencyProviderSpy(new HashMap<>());

		divaExtendedFunctionalityProvider = new DivaExtendedFunctionalityProvider(
				dependencyProvider);
	}

	@Test
	public void testFunctionalityForUpdateAfterMetadataValidationWhenNotImplementedForRecordType() {

		List<ExtendedFunctionality> extendedFunctionalitiesForUpdateAfterValidation = divaExtendedFunctionalityProvider
				.getFunctionalityForUpdateAfterMetadataValidation("someRecordType");

		assertEquals(extendedFunctionalitiesForUpdateAfterValidation, Collections.emptyList());
	}

	@Test
	public void testFunctionalityForUpdateAfterMetadataValidationForDivaOrganisation() {

		List<ExtendedFunctionality> extendedFunctionalitiesForUpdateAfterValidation = divaExtendedFunctionalityProvider
				.getFunctionalityForUpdateAfterMetadataValidation("divaOrganisation");

		assertTrue(extendedFunctionalitiesForUpdateAfterValidation.size() > 0);
		// assertEquals(extendedFunctionalitiesForUpdateAfterValidation, Collections.emptyList());

		// TODO:
		// Loop through list and asser that at least one is the new ExtendedFunctionality
		// Write test to make sure that the parents method is called as well
	}
}
