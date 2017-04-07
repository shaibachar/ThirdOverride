package com.thirdOverride;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thirdOverride.aspect.OverrideThirdPartyAspect;
import com.thirdOverride.dataTypes.TestNegObjectChild;
import com.thirdOverride.dataTypes.TestPosObjectChild;

public class ThirdOverrideApplicationTests {

	@Before
	public void init() {
	}

	@Test
	public void negativeTest() {
		boolean exception = false;
		try {
			Logger logger2 = TestNegObjectChild.getLogger("");
		} catch (Exception e) {
			Assert.assertEquals("expect to have message", e.getMessage(), OverrideThirdPartyAspect.ERROR_MESSAGE);
			Assert.assertTrue(e instanceof RuntimeException);
			exception = true;
		}

		Assert.assertTrue(exception);
	}

	@Test
	public void positiveTest() {

		Logger logger2 = TestPosObjectChild.getLogger("");
		logger2.setLevel(Level.INFO);
		logger2.addAppender(new ConsoleAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
		logger2.info("positiveTest pass");
	}

}
