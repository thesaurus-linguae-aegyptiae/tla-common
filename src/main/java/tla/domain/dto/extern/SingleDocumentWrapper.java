package tla.domain.dto.extern;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tla.domain.dto.meta.AbstractDto;
import tla.domain.dto.meta.DocumentDto;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleDocumentWrapper<T extends AbstractDto> {

    private static ObjectMapper mapper = new ObjectMapper();

    private T doc;

    @Setter(AccessLevel.NONE)
    private Map<String, Map<String, DocumentDto>> related;

    public SingleDocumentWrapper(T doc) {
        this.doc = doc;
    }

    /**
    * Stores an entire document object in the <code>related</code> section
    * of this wrapper instance, under the path <code>{eclass}.{id}</code>.
    */
    public void addRelated(DocumentDto obj) {
        if (this.related == null) {
            this.related = new HashMap<>();
        }
        String eclass = obj.getEclass();
        if (!this.related.containsKey(eclass)) {
            this.related.put(eclass, new HashMap<>());
        }
        this.related.get(eclass).put(
            obj.getId(),
            obj
        );
    }

    @SuppressWarnings({"unchecked"})
    public static SingleDocumentWrapper<? extends AbstractDto> from(String src) throws Exception {
        return mapper.readValue(
            src,
            SingleDocumentWrapper.class
        );
    }

}
