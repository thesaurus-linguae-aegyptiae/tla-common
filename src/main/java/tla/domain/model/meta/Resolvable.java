package tla.domain.model.meta;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Reference to an object identified by its ID and eclass.
 */
public interface Resolvable {

    public String getId();

    public String getEclass();
    public String get_class();

    public String getName();

    public String getType();
    public int getPos();
    public int getVariants();
    
    
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
    @JsonPropertyOrder({"start", "end"})
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Range {
        @JsonAlias("from")
        private String start;

        @JsonAlias("to")
        private String end;

        public static Range of(String startId, String endId) {
            Range r = new Range();
            r.setStart(startId);
            r.setEnd(endId);
            return r;
        }
    }

}