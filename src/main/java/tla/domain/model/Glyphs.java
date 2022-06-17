package tla.domain.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Glyphs {

    /**
     * transcription in ancient egyptian alphabet.
     */
    private String unicode;

    
    @JsonAlias({"mdc_compact"})
    private String mdcCompact;

    @JsonIgnore
    public boolean isEmpty() {
        return !(this.getMdcCompact() != null && !this.getMdcCompact().isBlank() ||
            this.getUnicode() != null && !this.getUnicode().isBlank()
        );
    }

    /**
     * Determines if an instance should be considered "empty" by jackson
     * object mapper.
     */
    public static class EmptyObjectFilter {
        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Transcription) {
                return ((Glyphs) obj).isEmpty();
            }
            return true;
        }
    }

}
