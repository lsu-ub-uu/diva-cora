package se.uu.ub.cora.diva;

import org.testng.annotations.Test;

import se.uu.ub.cora.therest.initialize.TheRestDependencyProvider;

public class DivaDependencyProviderTest {

	@Test
	public void testInit() {
		TheRestDependencyProvider dependencyProvider = new DivaDependencyProvider();
	}
}
