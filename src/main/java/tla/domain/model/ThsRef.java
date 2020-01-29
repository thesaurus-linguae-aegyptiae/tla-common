package tla.domain.model;

import java.util.Map;

import lombok.Data;

@Data
public class ThsRef {

    private String id;
    private String eclass;
    private String type;
    private String name;

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