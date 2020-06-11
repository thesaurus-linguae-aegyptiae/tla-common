package tla.domain.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Value;
import tla.domain.dto.meta.DocumentDto;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.model.meta.Resolvable;

/**
 * Reference to a fully qualified TLA document containing type, name, and eclass.
 */
@Value
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ObjectReference implements Comparable<Resolvable>, Resolvable {

    /**
     * ID of the referenced TLA document. Must not be null.
     */
    @NonNull
    private String id;
    /**
     * The TLA document's eclass. Must not be null.
     */
    @NonNull
    private String eclass;
    /**
     * The TLA document's type.
     */
    private String type;
    /**
     * The document's name.
     */
    private String name;
    /**
     * An optional collection of ranges within the referenced object to which
     * the reference's subject refers to specifically. Only be used by
     * annotations, comments, and some subtexts ("glosses").
     */
    private List<Range> ranges;

    /**
     * Default constructor.
     *
     * @param id TLA document ID
     * @param eclass TLA document eclass
     * @param type TLA document type
     * @param name TLA document name
     */
    @JsonCreator
    public ObjectReference(
        @JsonProperty(value = "id", required = true) String id,
        @JsonProperty(value = "eclass", required = true) String eclass,
        @JsonProperty(value = "type", required = false) String type,
        @JsonProperty(value = "name", required = false) String name,
        @JsonProperty(value = "ranges", required = false) List<Range> ranges
    ) {
        this.id = id;
        this.eclass = eclass;
        this.type = type;
        this.name = name;
        this.ranges = ranges;
    }

    @Override
    public String toString() {
        Map<String, Object> mapRepr = Map.of(
            "id", id,
            "name", name != null ? name : "None",
            "type", type != null ? type : "None",
            "eclass", eclass
        );
        if (this.ranges != null && !this.ranges.isEmpty()) {
            mapRepr.put("ranges", this.ranges);
        }
        return mapRepr.toString();
    }

    @Override
    public int compareTo(Resolvable arg0) {
        int diff = 0;
        if (this.getEclass().equals(arg0.getEclass())) {
            diff = this.getId().compareTo(arg0.getId());
        }
        else {
            diff = this.getEclass().compareTo(arg0.getEclass());
        }
        return diff;
    }

    /**
     * Creates a reference to the provided TLA document.
     *
     * @param object A TLA document instance.
     * @return Reference object specifying the TLA document.
     */
    public static ObjectReference from(DocumentDto object) {
        if (object instanceof NamedDocumentDto) {
            return new ObjectReference(
                object.getId(),
                object.getEclass(),
                ((NamedDocumentDto) object).getType(),
                ((NamedDocumentDto) object).getName(),
                null
            );
        } else {
            return new ObjectReference(
                object.getId(),
                object.getEclass(),
                null, null, null
            );
        }
    }

}
