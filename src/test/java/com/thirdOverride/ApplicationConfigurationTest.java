package com.thirdOverride;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thirdOverride.dataTypes.TestObjectChild;
import com.thirdOverride.dataTypes.TestObjectFather;

@Configuration
public class ApplicationConfigurationTest {

    @Bean
    public TestObjectFather getTestObjectFather(){
        return new TestObjectFather();
    }
    
    @Bean
    public TestObjectChild getTestObjectChild(){
        return new TestObjectChild();
    }
   
}
