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
     * Checks whether another instance's duration lies within the period covered by this
     * instance.
     *
     * @param timespan
     * @return
     */
    public boolean contains(AttestedTimespan timespan) {
        return this.period.contains(timespan.period);
    }

    /**
     * Container for document counts.
     */
    @Getter
    @Builder
    @EqualsAndHashCode
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AttestationStats {

        private long count;
        private long texts;
        private long sentences;
        private long objects;

        /**
         * Create an instance with all counts set to zero.
         */
        public AttestationStats() {
            this.count = 0;
            this.texts = 0;
            this.sentences = 0;
            this.objects = 0;
        }

        /**
         * Add another instance's stats to own.
         */
        public AttestationStats add(AttestationStats summand) {
            this.count += summand.count;
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
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Period implements Comparable<Period> {

        private int begin;
        private int end;

        /** Link to thesaurus entry */
        private ObjectReference ths;

        @Override
        public int compareTo(Period arg0) {
            return this.begin - arg0.begin;
        }

        /**
         * Returns true if another period lies within this one.
         */
        public boolean contains(Period period) {
            return (period.begin >= this.begin) && (period.end <= this.end);
        }
    }

}