package tla.domain.dto.extern;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

import tla.domain.dto.DocumentDto;


@Data
@Builder
public class SingleDocumentWrapper {
    
    private DocumentDto doc;

    private Map<String, Map<String, DocumentDto>> related;

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

}
