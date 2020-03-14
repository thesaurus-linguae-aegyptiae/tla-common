package tla.domain.model.extern;

import lombok.Getter;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tla.domain.model.ObjectReference;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;

/**
 * Nested data structure for temporally grouped lemma attestation statistics.
 *
 * <p>If an instance contains child nodes, its own attestation counts will be ignored,
 * and the values returned within the {@link AttestedTimespan#getAttestations()} result
 * will be recursively summed up.
 * </p>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttestedTimespan {

    private Period period;

    private AttestationStats attestations;

    private List<AttestedTimespan> contains;


    /**
     * Return an instance's attestation stats unlesz those can be
     * calculated from its children.
     *
     * @return an {@link AttestationStats} instance holding text, object, and sentence count
     */
    public AttestationStats getAttestations() {
        if (this.contains != null && !this.contains.isEmpty()) {
            AttestationStats result = new AttestationStats();
            this.contains.forEach(
                child -> result.add(child.getAttestations())
            );
            return result;
        } else {
            return this.attestations;
        }
    }

    /**
     * Container for document counts.
     */
    @Getter
    @Builder
    @EqualsAndHashCode
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class AttestationStats {

        private int texts;
        private int sentences;
        private int objects;

        /**
         * Create an instance with all counts set to zero.
         */
        public AttestationStats() {
            this.texts = 0;
            this.sentences = 0;
            this.objects = 0;
        }

        /**
         * Add another instance's stats to own.
         */
        public AttestationStats add(AttestationStats summand) {
            this.texts += summand.texts;
            this.sentences += summand.sentences;
            this.objects += summand.objects;
            return this;
        }

    }

    /**
     * Time period identified by first and last year, and
     * a link to the corresponding thesaurus entry.
     */
    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Period implements Comparable<Period> {

        private int begin;
        private int end;

        /** Link to thesaurus entry */
        private ObjectReference ths;

        @Override
        public int compareTo(Period arg0) {
            return this.begin - arg0.begin;
        }
    }

}