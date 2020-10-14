package se.uu.ub.cora.diva.extendedfunctionality;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import se.uu.ub.cora.spider.extended.ExtendedFunctionality;

public class OrganisationTypeRemoverTest {

	@Test
	public void testOrganisationTypeRemoverExtendsExtendedFunctionality() {
		OrganisationTypeRemover organisationTypeRemover = new OrganisationTypeRemover();

		assertTrue(organisationTypeRemover instanceof ExtendedFunctionality);
	}

}
