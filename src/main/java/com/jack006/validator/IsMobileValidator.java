package com.jack006.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号码校验类
 *
 * @Author jack
 * @Since 1.0 2020/1/29 11:27
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    /**
     * 手机验证规则
     */
    private Pattern pattern;

    @Override
    public void initialize(IsMobile isMobile) {
        pattern = Pattern.compile(isMobile.regexp());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return pattern.matcher(value).matches();
    }

}
