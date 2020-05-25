package tla.domain.dto.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.AnnotationDto;
import tla.domain.dto.CommentDto;
import tla.domain.dto.CorpusObjectDto;
import tla.domain.dto.LemmaDto;
import tla.domain.dto.TextDto;
import tla.domain.dto.ThsEntryDto;
import tla.domain.model.meta.AbstractBTSBaseClass;

/**
 * TLA DTO base class.
 */
@Data
@SuperBuilder
@NoArgsConstructor
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
        @Type(value = CommentDto.class, name = "BTSComment")
    }
)
public abstract class AbstractDto extends AbstractBTSBaseClass {

    @NonNull
    private String id;

}