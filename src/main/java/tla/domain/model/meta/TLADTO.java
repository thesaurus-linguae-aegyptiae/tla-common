package tla.domain.model.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tla.domain.dto.meta.AbstractDto;

/**
 * Use this on your client code's model classes in order to assign
 * a corresponding DTO type to them.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TLADTO {

    public Class<? extends AbstractDto> value();

}