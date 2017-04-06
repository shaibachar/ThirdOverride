package com.thirdOverride;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.thirdOverride.aspect.OverrideThirdPartyAspect;
import com.thirdOverride.dataTypes.TestNegObjectChild;
import com.thirdOverride.dataTypes.TestPosObjectChild;

public class ThirdOverrideApplicationTests {

	private TestNegObjectChild testNegObjectChild;

	private TestPosObjectChild testPosObjectChild;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void init() {
		testNegObjectChild = new TestNegObjectChild();
		testPosObjectChild = new TestPosObjectChild();
	}

	@Test
	public void negativeTest() throws Exception {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage(OverrideThirdPartyAspect.ERROR_MESSAGE);
		testNegObjectChild.printer();

	}

	@Test
	public void positiveTest() {
		testPosObjectChild.printer();
	}

}
