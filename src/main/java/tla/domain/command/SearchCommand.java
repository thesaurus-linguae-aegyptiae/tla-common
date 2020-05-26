package tla.domain.command;

import java.lang.annotation.Annotation;

import tla.domain.dto.meta.DocumentDto;
import tla.domain.model.meta.AbstractBTSBaseClass;
import tla.domain.model.meta.TLADTO;

/**
 * Implementing subclasses should either have a {@link TLADTO} annotation
 * with the same DTO class as its value as with which it (the subclass)
 * is typed, <i>or</i> override {@link #getDTOClass()} to return it
 * (the DTO class).
 */
public abstract class SearchCommand<T extends AbstractBTSBaseClass> {

    /**
     * Retrieve the DTO model class targeted by this search command.
     * Does so by going though the search command class's annotations
     * and see whether there's a {@link TLADTO} annotation with the
     * DTO model class as its value.
     *
     * @return DTO class of which this search command is supposed to produce instances.
     */
    public Class<? extends AbstractBTSBaseClass> getDTOClass() {
        for (Annotation a : this.getClass().getAnnotations()) {
            if (a instanceof TLADTO) {
                return ((TLADTO) a).value();
            }
        }
        return DocumentDto.class;
    }

}