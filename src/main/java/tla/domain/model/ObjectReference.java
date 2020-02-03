package tla.domain.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ObjectReference {

    private String id;
    private String eclass;
    private String type;
    private String name;

    @JsonCreator
    public ObjectReference(
        @JsonProperty(value = "id", required = true) String id,
        @JsonProperty(value = "eclass", required = true) String eclass,
        @JsonProperty(value = "type", required = false) String type,
        @JsonProperty(value = "name", required = false) String name
    ) {
        this.id = id;
        this.eclass = eclass;
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return Map.of(
            "id", id,
            "name", name,
            "type", type,
            "eclass", eclass
        ).toString();
    }
    
}