package com.thirdOverride.dataTypes;

import com.thirdOverride.aspect.OverrideThirdParty;

public class TestObjectChild extends TestObjectFather{

    @OverrideThirdParty
    public void printer(){
        System.out.println("child");
    }
}
