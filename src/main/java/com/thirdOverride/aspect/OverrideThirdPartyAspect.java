package com.thirdOverride.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class OverrideThirdPartyAspect {

    @Before("@annotation(com.thirdOverride.aspect.OverrideThirdParty)")
    public void checkOverrideThirdParty(){
        System.out.println("in");
    }
}
