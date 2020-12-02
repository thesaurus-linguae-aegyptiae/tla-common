package tla.domain.model.meta;

import javax.naming.InvalidNameException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import tla.domain.dto.meta.AbstractDto;

/*
 * Subclasses are expected to be annotated with {@link BTSeClass}.
 */
@Slf4j
@ToString
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractBTSBaseClass {

    /**
     * @see AbstractDto
     */
    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String eclass;

    /**
     * Returns the object's <code>eClass</code> value specified via the {@link BTSeClass} annotation.
     */
    public String getEclass() {
        if (eclass == null) {
            eclass = Util.extractEclass(this.getClass());
        }
        return eclass;
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
     *
     * @Deprecated please use {@link Util#extractEclass(Class)}
     */
    public static String getTypesEclass(Class<? extends AbstractBTSBaseClass> modelClass) {
        String eclass = Util.extractEclass(modelClass);
        if (eclass != null) {
            return eclass;
        }
        log.warn(
            "eClass of {} instance not specified via @BTSeClass annotation. Returning class name",
            modelClass.getName()
        );
        return null;
    }

}
