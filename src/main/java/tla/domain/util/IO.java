package tla.domain.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class allowing for eg. deserializing a modeled object from file.
 */
@Slf4j
public class IO {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Deserialize a JSON file into an object of the specified type.
     * Returns <code>null</code> if anything goes wrong.
     */
    public static <T> T loadFromFile(String filename, Class<T> clazz) throws Exception {
        String contents = Files.readString(
            new File(
                filename
            ).toPath(),
            StandardCharsets.UTF_8
        );
        return mapper.readValue(
            contents,
            clazz
        );
    }

    /**
     * Return JSON serialization of object.
     *
     * @param object any object
     * @return JSON-formatted string or <code>null</code>
     */
    public static String json(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(
                "Serialization of {} object failed: {}",
                object.getClass().getName(),
                e.getMessage()
            );
            return null;
        }
    }

    /** get a jackson {@link ObjectMapper} instance*/
    public static ObjectMapper getMapper() {
        return mapper;
    }

}
