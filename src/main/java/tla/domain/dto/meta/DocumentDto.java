package tla.domain.dto.meta;

import java.util.Collections;
import java.util.SortedMap;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import tla.domain.model.EditorInfo;
import tla.domain.model.ObjectReference;

/**
 * TLA DTO model semi-base class
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
@JsonInclude(Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DocumentDto extends AbstractDto {

    @JsonAlias("revisionState")
    private String reviewState;

    private EditorInfo editors;

    @Singular
    private SortedMap<String, SortedSet<ObjectReference>> relations;

    /**
     * This no arguments constructor is required so that instances deserialized by jackson
     * contain initialized external references and relations maps.
     */
    public DocumentDto() {
        this.relations = Collections.emptySortedMap();
    }

}