package android.tools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

@Retention(RetentionPolicy.SOURCE)
@Target({TYPE, METHOD ,CONSTRUCTOR ,ANNOTATION_TYPE })
public @interface Unfinished {
    String[] description();
}
