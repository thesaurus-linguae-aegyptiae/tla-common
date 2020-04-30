package tla.domain.command;

import lombok.Data;
import tla.domain.model.Script;

@Data
public class LemmaSearch {

    private Script[] script;

    private String transcription;

    private TranslationCriteria translation;

}