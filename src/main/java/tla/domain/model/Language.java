package tla.domain.model;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {

    DE("de"),
    EN("en"),
    FR("fr"),
    AR("ar"),
    IT("it");

    private static Map<String, Language> mapping = Arrays.stream(Language.values()).collect(
        Collectors.toMap(lang -> lang.toString(), lang -> lang)
    );

    private final String id;

    Language(String id) {
        this.id = id;
    }

    @JsonValue
    public String toString() {
        return this.id;
    }

    @JsonCreator
    public static Language deserialize(String val) {
        return mapping.get(val);
    }

}
