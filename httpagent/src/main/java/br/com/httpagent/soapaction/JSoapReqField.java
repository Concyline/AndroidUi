package br.com.httpagent.soapaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JSoapReqField {
    String fieldName() default "JSOAP_DEFAULT_FIELDNAME";
    String namespace() default "JSOAP_DEFAULT_NAMESPACE";
    int order();
}