package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.model.Language;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TranslationSpec {

    /**
     * search terms expected in an object's translations.
     */
    private String text;

    /**
     * Specify zero or more languages in which translations ought to be
     * searched.
     */
    private Language[] lang;

    @JsonIgnore
    public boolean isEmpty() {
        return this.text == null && (this.lang == null || this.lang.length < 1);
    }

    public static class EmptyObjectFilter {
        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof TranslationSpec) {
                return ((TranslationSpec) o).isEmpty();
            }
            return true;
        }
    }

}
