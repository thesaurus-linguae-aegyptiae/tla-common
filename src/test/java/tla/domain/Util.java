package tla.domain;

import java.io.File;
import java.nio.file.Files;

public class Util {

    static final String RESOURCE_PATH = "src/test/resources/sample/";

    public static String loadFromFileAsString(String path, String filename) throws Exception {
        return new String(
            Files.readAllBytes(
                new File(
                    String.format(RESOURCE_PATH + "%s/%s", path, filename)
                ).toPath()
            )
        );
    }
}