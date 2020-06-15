package tla.domain.model.meta;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Reference to an object identified by its ID and eclass.
 */
public interface Resolvable {

    public String getId();

    public String getEclass();

    public String getName();

    public String getType();

    /**
     * An optional collection of ranges within the referenced object to which
     * the reference's subject refers to specifically. Only be used by
     * annotations, comments, and some subtexts ("glosses").
     */
    public List<Range> getRanges();

    /**
     * This class represents a selected range within a (most likely text- or sentence-)
     * document,
     * identified by the first and last token being covered by it.
     */
    @Getter
    @Setter
    @EqualsAndHashCode
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Range {
        @JsonAlias("start")
        private String from;

        @JsonAlias("end")
        private String to;

        public static Range of(String startId, String endId) {
            Range r = new Range();
            r.setFrom(startId);
            r.setTo(endId);
            return r;
        }
    }

}