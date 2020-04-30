package tla.domain.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
* Enum specifying different egyptian language stages.
*/
public enum Script {

    HIERATIC("hieratic"),
    DEMOTIC("demotic"),
    COPTIC("coptic");

    private String id;

    private static Map<String, Script> valueMap = Arrays.stream(
        Script.values()
    ).collect(
        Collectors.toMap(
            dict -> dict.id,
            dict -> dict
        )
    );

    Script(String id) {
        this.id = id;
    }

    @JsonCreator
    public static Script fromString(String id) {
        return valueMap.get(id);
    }

    @Override
    @JsonValue
    public String toString() {
        return this.id;
    }

    public static Script ofLemmaId(String id) {
        char c = id.toLowerCase().charAt(0);
        if (c == 'c') {
            return COPTIC;
        } else if (c == 'd') {
            return DEMOTIC;
        } else {
            return HIERATIC;
        }
    }

}