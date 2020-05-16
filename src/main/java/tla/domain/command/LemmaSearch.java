package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.model.Script;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LemmaSearch {

    private Script[] script;

    private String transcription;

    private TypeSpec pos;

    private String root;

    private TypeSpec annotationType;

    private TranslationSpec translation;

    private String bibliography;

}