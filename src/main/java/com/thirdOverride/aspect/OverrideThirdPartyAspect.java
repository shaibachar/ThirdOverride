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

	public static final String ERROR_MESSAGE = "it is possible your class needs to be updated";

	@Around("execution(@com.thirdOverride.aspect.OverrideThirdParty * * (..)) && @annotation(overrideThirdPartyAnnotation)")
	public Object checkOverrideThirdParty(ProceedingJoinPoint proceedingJoinPoint,
			OverrideThirdParty overrideThirdPartyAnnotation) throws Throwable {
		Object res = null;
		System.out.println("aspect check");
		Date overrideDate = convertToDateTime(overrideThirdPartyAnnotation.overrideDateValue(),
				overrideThirdPartyAnnotation.overrideDateFormat());
		// Signature signature = proceedingJoinPoint.getSignature();

		Class<?> superclass = proceedingJoinPoint.getTarget().getClass().getSuperclass();
		Class<?> superClassForName = Class.forName(superclass.getName());
		Date superClassDate = OverrideThirdPartyClassMapper.getInst().getClassDate(superClassForName.getName());
		if (superClassDate != null && superClassDate.after(overrideDate)) {
			throw new RuntimeException(ERROR_MESSAGE);
		} else {
			// TODO: check if class should have been loaded to classloader
			System.out.println("************************" + superClassForName.getName() + " date:" + superClassDate);
		}
		// String longString = signature.toLongString();
		// System.out.println(longString);

		res = proceedingJoinPoint.proceed();

		return res;
	}

	private Date convertToDateTime(String overrideDateValue, String overrideDateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(overrideDateFormat);
		Date parse = formatter.parse(overrideDateValue);
		return parse;
	}
}
