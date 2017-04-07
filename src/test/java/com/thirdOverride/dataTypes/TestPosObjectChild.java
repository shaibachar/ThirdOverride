package com.thirdOverride.dataTypes;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.thirdOverride.aspect.OverrideThirdParty;

public class TestPosObjectChild extends Logger {

	public TestPosObjectChild(String name) {
		super(name);
	}

	@OverrideThirdParty(overrideDateFormat = "dd-MM-yyyy HH:mm:ss", overrideDateValue = "15-02-2015 22:28:00")
	static public Logger getLogger(String name) {
		if (name == null || name.trim().isEmpty()) {
			String className = Thread.currentThread().getStackTrace()[2].getClassName();
			return LogManager.getLogger(className);
		}
		return LogManager.getLogger(name);
	}
}
