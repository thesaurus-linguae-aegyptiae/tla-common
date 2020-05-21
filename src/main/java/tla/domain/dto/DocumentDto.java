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
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import tla.domain.model.EditorInfo;
import tla.domain.model.ObjectReference;
import tla.domain.model.meta.AbstractBTSBaseClass;

/**
 * TLA base class
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
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
        @Type(value = CorpusObjectDto.class, name = "BTSTCObject"),
        @Type(value = CommentDto.class, name = "BTSComment")
    }
)
public abstract class DocumentDto extends AbstractBTSBaseClass {

    private String id;

    @JsonAlias("revisionState")
    private String reviewState;

    private EditorInfo editors;

    @Singular
    private SortedMap<String, SortedSet<ObjectReference>> relations;

    /**
     * This no arguments constructor is required so that instances deserialized by jackson
     * contain initialized external references and relations maps.
     */
    public DocumentDto() {
        this.relations = Collections.emptySortedMap();
    }

}
