package com.jack006.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/29 11:27
 */
@Documented
@Constraint(
        validatedBy = { IsMobileValidator.class }
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RUNTIME)
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};

    String regexp() default "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";

}
