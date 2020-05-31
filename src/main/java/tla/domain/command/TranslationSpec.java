package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.model.Language;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TranslationSpec {

    private String text;
    private Language[] lang;

    public static class EmptyObjectFilter {
        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof TranslationSpec) {
                TranslationSpec t = (TranslationSpec) o;
                return t.text == null && (t.lang == null || t.lang.length < 1);
            }
            return true;
        }
    }

}
