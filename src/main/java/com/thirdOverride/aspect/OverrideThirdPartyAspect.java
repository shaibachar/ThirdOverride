package com.thirdOverride.aspect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.thirdOverride.OverrideThirdPartyClassMapper;

@Aspect
public class OverrideThirdPartyAspect {

    @Around("execution(@com.thirdOverride.aspect.OverrideThirdParty * * (..)) && @annotation(overrideThirdPartyAnnotation)")
    public Object checkOverrideThirdParty(ProceedingJoinPoint proceedingJoinPoint, OverrideThirdParty overrideThirdPartyAnnotation) {
        Object res = null;
        try {

            Date overrideDate = convertToDateTime(overrideThirdPartyAnnotation.overrideDateValue(), overrideThirdPartyAnnotation.overrideDateFormat());
//            Signature signature = proceedingJoinPoint.getSignature();
            
            Class<?> superclass = proceedingJoinPoint.getTarget().getClass().getSuperclass();
            Class<?> superClassForName = Class.forName(superclass.getName());
            Date superClassDate = OverrideThirdPartyClassMapper.getInst().getClassDate(superClassForName.getName());
            if (superClassDate.after(overrideDate)){
                throw new RuntimeException("it is possible your class needs to be updated ");
            }
//            String longString = signature.toLongString();
//            System.out.println(longString);

            res = proceedingJoinPoint.proceed();

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return res;
    }

    private Date convertToDateTime(String overrideDateValue, String overrideDateFormat) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(overrideDateFormat);
        Date parse = formatter.parse(overrideDateValue);
        return parse;
    }
}
