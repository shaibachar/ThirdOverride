package com.thirdOverride.dataTypes;

import com.thirdOverride.aspect.OverrideThirdParty;

public class TestNegObjectChild extends TestObjectFather{

    @OverrideThirdParty(overrideDateFormat="dd-MM-yyyy HH:mm:ss",overrideDateValue="15-02-2015 22:28:00")
    public void printer(){
        System.out.println("child");
    }
}
