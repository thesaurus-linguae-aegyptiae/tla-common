package tla.domain.dto.extern;

import java.util.HashMap;
import java.util.Map;

import tla.domain.dto.meta.DocumentDto;

public interface DocumentWrapper {

    /**
     * Returns contents of this container's <code>related</code> field, or
     * an empty {@link HashMap} if {@literal null}.
     *
     * <p>The map stored in an object detail container's <code>related</code> field
     * contains a structure designed for quick lookup of related objects.
     * Related objects are grouped by their <code>eclass</code> values, and stored
     * inside one additional nested map per <code>eclass</code>, where they can be found
     * under their respective IDs:</p>
     *
     * <pre>
     * {"BTSLemmaEntry": {"10070": {"eclass": "BTSLemmaEntry", "id": "10070", "name": ...
     * </pre>
     */
    public Map<String, Map<String, DocumentDto>> getRelated();

    /**
    * Stores an entire document object in the <code>related</code> section
    * of this wrapper instance, under the path <code>{eclass}.{id}</code>.
    */
    default void addRelated(DocumentDto obj) {
        String eclass = obj.getEclass();
        Map<String, Map<String, DocumentDto>> relatedObjects = this.getRelated();
        relatedObjects.putIfAbsent(eclass, new HashMap<>());
        relatedObjects.get(eclass).put(
            obj.getId(),
            obj
        );
    }
}
