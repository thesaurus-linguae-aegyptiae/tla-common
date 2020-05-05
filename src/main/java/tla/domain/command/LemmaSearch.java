package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import tla.domain.model.Script;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LemmaSearch {

    private Script[] script;

    private String transcription;

    private TranslationCriteria translation;

}