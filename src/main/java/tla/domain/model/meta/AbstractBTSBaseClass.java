package tla.domain.model.meta;

import java.lang.annotation.Annotation;

import javax.naming.InvalidNameException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

/*
 * Subclasses are expected to be annotated with {@link BTSeClass}.
 */
@Slf4j
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
public abstract class AbstractBTSBaseClass {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String eclass;

    /**
     * Returns the object's <code>eClass</code> value specified via the {@link BTSeClass} annotation.
     */
    public String getEclass() {
        String eclass = getTypesEclass(this.getClass());
        if (eclass != null) {
            return eclass;
        } else {
            return this.getClass().getName();
        }
    }


    /**
     * Throws an exception if the value passed doesn't match the one specified via {@link BTSeClass} annotation.
     */
    public void setEclass(String eclass) throws Exception {
        if (!eclass.equals(getEclass())) {
            throw new InvalidNameException(
                String.format(
                    "wrong eClass. Expected %s, got %s!",
                    getEclass(),
                    eclass
                )
            );
        }
    }

    /**
     * extract a model class's <code>eClass</code> from its {@link BTSeClass} annotation.
     */
    public static String getTypesEclass(Class<? extends AbstractBTSBaseClass> modelClass) {
        for (Annotation annotation : modelClass.getAnnotations()) {
            if (annotation instanceof BTSeClass) {
                return ((BTSeClass) annotation).value();
            }
        }
        log.warn(
            "eClass of {} instance not specified via @BTSeClass annotation. Returning class name",
            modelClass.getName()
        );
        return null;
    }

}
