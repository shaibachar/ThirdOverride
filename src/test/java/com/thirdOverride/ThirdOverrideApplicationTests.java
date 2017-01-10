package com.thirdOverride;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thirdOverride.aspect.OverrideThirdParty;
import com.thirdOverride.dataTypes.TestObjectChild;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThirdOverrideApplicationTests {

    @Autowired
    private TestObjectChild testObjectChild;
    
	@Test
	public void contextLoads() {
	}
	
	@Test
	@OverrideThirdParty
	public void testMe(){
	    testObjectChild.printer();
	}

}
