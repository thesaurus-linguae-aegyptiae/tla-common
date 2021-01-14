package tla.domain.model.meta;

import java.util.Map;
import java.util.Collection;

/**
 * Instances can be in relations with one another.
 */
public interface Relatable<E extends Collection<? extends Resolvable>> {

    public Map<String, E> getRelations();

}
