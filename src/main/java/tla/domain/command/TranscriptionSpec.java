package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TranscriptionSpec {

    
    private String text;

   
    private String[] enc;

    @JsonIgnore
    public boolean isEmpty() {
        return this.text == null;
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

