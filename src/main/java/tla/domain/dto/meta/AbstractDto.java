package tla.domain.dto.meta;

import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.AnnotationDto;
import tla.domain.dto.CommentDto;
import tla.domain.dto.CorpusObjectDto;
import tla.domain.dto.LemmaDto;
import tla.domain.dto.SentenceDto;
import tla.domain.dto.TextDto;
import tla.domain.dto.ThsEntryDto;
import tla.domain.model.ObjectReference;
import tla.domain.model.meta.AbstractBTSBaseClass;

/**
 * TLA DTO base class.
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
        @Type(value = CommentDto.class, name = "BTSComment"),
        @Type(value = SentenceDto.class, name = "BTSSentence")
    }
)
public abstract class AbstractDto extends AbstractBTSBaseClass {

    @NonNull
    private String id;

    /**
     * labeled references to other objects.
     */
    @Singular
    private Map<String, SortedSet<ObjectReference>> relations;

    /**
     * This no arguments constructor is required so that instances deserialized by jackson
     * contain initialized relations maps.
     */
    public AbstractDto() {
        this.relations = Collections.emptyMap();
    }

}