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

    private WordClass pos;

    private String root;

    private TranslationCriteria translation;

    private String bibliography;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class WordClass {
        private String type;
        private String subtype;
    }

}