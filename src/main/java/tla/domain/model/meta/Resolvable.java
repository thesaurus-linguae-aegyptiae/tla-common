package tla.domain.model.meta;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

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

    public List<Range> getRanges();

    /**
     * This class represents a selected range within a (most likely text- or sentence-)
     * document,
     * identified by the first and last token being covered by it.
     */
    @Getter
    @Setter
    public static class Range {
        @JsonAlias("start")
        private String from;

        @JsonAlias("end")
        private String to;
    }

}