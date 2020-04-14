package tla.domain.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import tla.domain.dto.DocumentDto;

/**
 * Reference to a fully qualified TLA document containing type, name, and eclass.
 */
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ObjectReference implements Comparable<ObjectReference> {

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
        @JsonProperty(value = "name", required = false) String name
    ) {
        this.id = id;
        this.eclass = eclass;
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return Map.of(
            "id", id,
            "name", name != null ? name : "None",
            "type", type != null ? type : "None",
            "eclass", eclass
        ).toString();
    }

    @Override
    public int compareTo(ObjectReference arg0) {
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
    public static ObjectReference of(DocumentDto object) {
        return new ObjectReference(
            object.getId(),
            object.getEclass(),
            object.getType(),
            object.getName()
        );
    }

}
