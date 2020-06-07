package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.dto.LemmaDto;
import tla.domain.model.Script;
import tla.domain.model.meta.BTSeClass;
import tla.domain.model.meta.TLADTO;

@Getter
@Setter
@TLADTO(LemmaDto.class)
@BTSeClass("BTSLemmaEntry")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LemmaSearch extends SearchCommand<LemmaDto> {

    /**
     * Only looking for lemma being used in one or more particular
     * egyptian language stages (e.g. coptic).
     */
    private Script[] script;

    /**
     * lemma is written like this.
     */
    private String transcription;

    /**
     * Lemma we are looking for needs to have the specified part of speech.
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TypeSpec.EmptyObjectFilter.class
    )
    private TypeSpec wordClass;

    /**
     * Transcription of the lemma's root lemma.
     */
    private String root;

    /**
     * Search for lemma with an annotation of the specified type
     * and subtype.
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TypeSpec.EmptyObjectFilter.class
    )
    @JsonAlias("annotationType")
    private TypeSpec anno;

    /**
     * Require lemma to have a specifig meaning in one or more languages.
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TranslationSpec.EmptyObjectFilter.class
    )
    private TranslationSpec translation;

    /**
     * Lemma entry is referencing a specific bibliographic source.
     */
    private String bibliography;

}
