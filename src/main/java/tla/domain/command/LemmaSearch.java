package tla.domain.command;

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

    private Script[] script;

    private String transcription;

    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TypeSpec.EmptyObjectFilter.class
    )
    private TypeSpec pos;

    private String root;

    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TypeSpec.EmptyObjectFilter.class
    )
    private TypeSpec annotationType;

    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TranslationSpec.EmptyObjectFilter.class
    )
    private TranslationSpec translation;

    private String bibliography;

}
