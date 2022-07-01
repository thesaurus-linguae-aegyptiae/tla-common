package tla.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import tla.domain.dto.meta.DocumentDto;
import tla.domain.dto.meta.NamedDocumentDto;
import tla.domain.dto.TextDto;
import tla.domain.dto.SentenceDto;
import tla.domain.model.meta.Resolvable;

/**
 * Reference to a fully qualified TLA document containing type, name, and eclass.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ObjectReference implements Comparable<Resolvable>, Resolvable {

    /**
     * ID of the referenced TLA document. Must not be null.
     */
	 @NonNull
    private String id;
    /**
     * The TLA document's eclass. Must not be null.
     *
     */
    private String _class;
    
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
     * optional just for <code>parts</code> relation in Text Object
     */

    private int pos;
    
    private int variants;
    /**
     * An optional collection of ranges within the referenced object to which
     * the reference's subject refers to specifically. Only be used by
     * annotations, comments, and some subtexts ("glosses").
     */
    @Builder.Default
    @JsonPropertyOrder(alphabetic = true)
    private List<Range> ranges = null;

    /**
     * Default constructor.
     *
     * @param id TLA document ID
     * @param _class 
     * @param eclass TLA document eclass
     * @param type TLA document type
     * @param name TLA document name
     * @param name TLA sentence pos
     * @param name TLA sentence variants
     */
    @JsonCreator
    public ObjectReference(
        @JsonProperty(value = "id", required = false) String id,
        @JsonProperty(value = "_class", required = false) String _class,
        @JsonProperty(value = "eclass", required = true) String eclass,
        @JsonProperty(value = "type", required = false) String type,
        @JsonProperty(value = "name", required = false) String name,
        @JsonProperty(value = "pos", required = false) int pos,
        @JsonProperty(value = "variants", required = false) int variants,
        @JsonProperty(value = "ranges", required = false) List<Range> ranges
    ) {
        this.id = id;
        this._class = _class;
        this.eclass = eclass;
        this.type = type;
        this.name = name;
        this.pos=pos;
        this.variants=variants;
        this.ranges = ranges;
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
    public static ObjectReference from(SentenceDto object) {
    	
            return new ObjectReference(
                object.getId(),
                null,
                object.getEclass(),
                object.getType(),
                null,
                object.getContext().getPosition(),
                object.getContext().getVariants(),
                null
            );
    	
        } 
    	
     public static ObjectReference from(DocumentDto object) {	
    	 if (object instanceof NamedDocumentDto) {
            return new ObjectReference(
                object.getId(),
                null,
                object.getEclass(),
                ((NamedDocumentDto) object).getType(),
                ((NamedDocumentDto) object).getName(),
                -1,-1,null
            );
        } else {
            return new ObjectReference(
                object.getId(),
                null,
                object.getEclass(),
                null, null,-1, -1, null
            );
        }
    }

}
