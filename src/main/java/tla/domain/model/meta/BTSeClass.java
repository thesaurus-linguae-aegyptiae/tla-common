package tla.domain.model.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Put this on top of any subclass of {@link AbstractBTSBaseClass} in order to
 * set the correct <code>eClass</code> value for each instance.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BTSeClass {

    public String value();

}
