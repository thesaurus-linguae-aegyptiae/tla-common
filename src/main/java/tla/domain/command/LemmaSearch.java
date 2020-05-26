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

    private TypeSpec pos;

    private String root;

    private TypeSpec annotationType;

    private TranslationSpec translation;

    private String bibliography;

}