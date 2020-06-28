package tla.domain.dto.extern;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tla.domain.dto.meta.AbstractDto;
import tla.domain.dto.meta.DocumentDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SingleDocumentWrapper<T extends AbstractDto> {

    private static ObjectMapper mapper = tla.domain.util.IO.getMapper();

    /**
     * The object being the main payload which this container is expected to deliver
     * at the very least.
     */
    private T doc;

    /**
     * Map containing objects related to the object constituting this container's
     * main payload, organized in a manner that allows for quick convenient access.
     */
    @Default
    private Map<String, Map<String, DocumentDto>> related = new HashMap<>();

    /**
     * Initializes a wrapper around the given object.
     */
    public SingleDocumentWrapper(T doc) {
        this.doc = doc;
    }

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
    public Map<String, Map<String, DocumentDto>> getRelated() {
        if (this.related == null) {
            this.related = new HashMap<>();
        }
        return this.related;
    }

    /**
    * Stores an entire document object in the <code>related</code> section
    * of this wrapper instance, under the path <code>{eclass}.{id}</code>.
    */
    public void addRelated(DocumentDto obj) {
        String eclass = obj.getEclass();
        Map<String, Map<String, DocumentDto>> relatedObjects = this.getRelated();
        relatedObjects.putIfAbsent(eclass, new HashMap<>());
        relatedObjects.get(eclass).put(
            obj.getId(),
            obj
        );
    }

    /**
     * Creates a new object details container by deserializing a JSON string.
     */
    @SuppressWarnings({"unchecked"})
    public static SingleDocumentWrapper<? extends AbstractDto> from(String src) throws Exception {
        return mapper.readValue(
            src,
            SingleDocumentWrapper.class
        );
    }

}
