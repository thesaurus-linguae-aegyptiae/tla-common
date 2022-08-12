package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TranscriptionSpec {

    /**
     * search terms expected in an object's translations.
     */
    private String text;

    /**
     * Specify zero or more languages in which translations ought to be
     * searched.
     */
    private String enc;

    @JsonIgnore
    public boolean isEmpty() {
        return this.text == null && (this.enc == null);
    }

    public static class EmptyObjectFilter {
        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof TranscriptionSpec) {
                return ((TranscriptionSpec) o).isEmpty();
            }
            return true;
        }
    }

}

