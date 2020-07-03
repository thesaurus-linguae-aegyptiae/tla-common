package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.dto.SentenceDto;
import tla.domain.model.Transcription;
import tla.domain.model.SentenceToken.Lemmatization;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;

@Setter
@Getter
@TLADTO(SentenceDto.class)
@BTSeClass("BTSSentence")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OccurrenceSearch extends SearchCommand<SentenceDto> {

    /**
     * looking for usages of a specific lemma entry.
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = Lemmatization.EmptyObjectFilter.class
    )
    private Lemmatization lemma;

    /**
     * word uses these hieroglyphs
     */
    private String glyphs;

    /**
     * looking for word uses written in a specific way.
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = Transcription.EmptyObjectFilter.class
    )
    private Transcription transcription;

    /**
     * search for particular meaning in one or more languages mentioned
     * in either token or surrounding sentence translation.
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TranslationSpec.EmptyObjectFilter.class
    )
    private TranslationSpec translation;

    /**
     * type of the surrounding sentence
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TypeSpec.EmptyObjectFilter.class
    )
    private TypeSpec type;

}