package tla.domain.dto.meta;

import java.util.Collections;
import java.util.SortedMap;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import tla.domain.model.ExternalReference;
import tla.domain.model.ObjectReference;
import tla.domain.model.Passport;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class NamedDocumentDto extends DocumentDto {
    
	private String name;
    private String type;
    private String subtype;
    private Passport passport;

    @Singular
    private SortedMap<String, SortedSet<ExternalReference>> externalReferences;

    public NamedDocumentDto() {
        this.externalReferences = Collections.emptySortedMap();
    }

    /**
     * Creates a {@link ObjectReference} representation of this instance.
     */
    public ObjectReference toObjectReference() {
        return ObjectReference.from(this);
    }
}