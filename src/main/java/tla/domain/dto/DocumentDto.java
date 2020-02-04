package tla.domain.dto;

import java.util.Collections;
import java.util.SortedMap;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tla.domain.model.EditorInfo;
import tla.domain.model.ExternalReference;
import tla.domain.model.ObjectReference;
import tla.domain.model.Passport;

/**
 * TLA base class
 */
@Data
@SuperBuilder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DocumentDto {

    private String id;
    private String eclass;
    private String name;
    private String type;
    private String subtype;

    @JsonAlias("revisionState")
    private String reviewState;

    private EditorInfo editors;

    private Passport passport;

    @Singular
    private SortedMap<String, SortedSet<ExternalReference>> externalReferences;

    @Singular
    private SortedMap<String, SortedSet<ObjectReference>> relations;

    /**
     * This no arguments constructor is required so that instances deserialized by jackson
     * contain initialized external references and relations maps.
     */
    public DocumentDto() {
        this.externalReferences = Collections.emptySortedMap();
        this.relations = Collections.emptySortedMap();
    }

}
