package tla.domain.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class allowing for eg. deserializing a modeled object from file.
 */
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

}
