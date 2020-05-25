package tla.domain.dto;

import java.util.List;
import java.util.SortedMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tla.domain.dto.meta.AbstractDto;
import tla.domain.model.Language;
import tla.domain.model.SentenceToken;
import tla.domain.model.Transcription;
import tla.domain.model.meta.BTSeClass;

@Data
@SuperBuilder
@BTSeClass("BTSSentence")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SentenceDto extends AbstractDto {

    private String textId;

    private String type;

    private String line;

    private String paragraph;

    @Singular
    private SortedMap<Language, List<String>> translations;

    private Transcription transcription;

    @Singular
    private List<SentenceToken> tokens;

}