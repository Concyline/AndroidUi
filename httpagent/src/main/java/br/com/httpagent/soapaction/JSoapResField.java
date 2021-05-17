package br.com.httpagent.soapaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JSoapResField {
    String name() default "JSOAP_DEFAULT_FIELD_NAME";
}