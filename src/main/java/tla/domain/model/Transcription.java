package tla.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Transcription {

    /**
     * transcription in ancient egyptian alphabet.
     */
    private String unicode;

    /**
     * Transcription using the Manuel de Codage (MdC) notation.
     */
    private String mdc;

    /**
     * Determines if an instance should be considered "empty" by jackson
     * object mapper.
     */
    public static class EmptyObjectFilter {
        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Transcription) {
                Transcription t = (Transcription) obj;
                if (t.getMdc() != null && !t.getMdc().isBlank() ||
                    t.getUnicode() != null && !t.getUnicode().isBlank()
                ) {
                    return false;
                }
            }
            return true;
        }
    }

}