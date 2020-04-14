package tla.domain.dto;

import java.util.Collections;
import java.util.SortedMap;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import tla.domain.model.EditorInfo;
import tla.domain.model.ExternalReference;
import tla.domain.model.ObjectReference;
import tla.domain.model.Passport;
import tla.domain.model.meta.AbstractBTSBaseClass;

/**
 * TLA base class
 */
@Data
@SuperBuilder
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "eclass"
)
@JsonSubTypes(
    {
        @Type(value = LemmaDto.class, name = "BTSLemmaEntry"),
        @Type(value = AnnotationDto.class, name = "BTSAnnotation"),
        @Type(value = TextDto.class, name = "BTSText"),
        @Type(value = ThsEntryDto.class, name = "BTSThsEntry"),
        @Type(value = CorpusObjectDto.class, name = "BTSTCObject")
    }
)
public abstract class DocumentDto extends AbstractBTSBaseClass {

    private String id;
    private String name;
    private String type;
    private String subtype;

    @JsonAlias("revisionState")
    private String reviewState;

    private EditorInfo editors;

    private Passport passport;

    @Singular
    private SortedMap<String, SortedSet<ExternalReference>> externalReferences;

    @Singular
    private SortedMap<String, SortedSet<ObjectReference>> relations;

    /**
     * This no arguments constructor is required so that instances deserialized by jackson
     * contain initialized external references and relations maps.
     */
    public DocumentDto() {
        this.externalReferences = Collections.emptySortedMap();
        this.relations = Collections.emptySortedMap();
    }

    /**
     * Creates a {@link ObjectReference} representation of this instance.
     */
    public ObjectReference toObjectReference() {
        return ObjectReference.of(this);
    }

}
