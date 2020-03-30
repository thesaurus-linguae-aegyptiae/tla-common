package tla.domain;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import tla.domain.dto.AnnotationDto;
import tla.domain.dto.DocumentDto;
import tla.domain.dto.LemmaDto;
import tla.domain.dto.TextDto;
import tla.domain.dto.ThsEntryDto;

public class Util {

    static final String RESOURCE_PATH = "src/test/resources/sample/";

    static final Map<Class<? extends DocumentDto>, String> SAMPLE_DIR_PATHS = Map.of(
        LemmaDto.class, "lemma",
        TextDto.class, "text",
        ThsEntryDto.class, "ths",
        AnnotationDto.class, "annotation"
    );

    private static ObjectMapper mapper = new ObjectMapper();

    public static String getSampleFileFullPath(String path, String filename) {
        return String.format(
            RESOURCE_PATH + "%s/%s",
            path,
            filename
        );
    }

    public static String loadFromFileAsString(String path, String filename) throws Exception {
        return Files.readString(
            new File(
                getSampleFileFullPath(path, filename)
            ).toPath(),
            StandardCharsets.UTF_8
        );
    }

    public static DocumentDto loadFromFile(Class<? extends DocumentDto> clazz, String filename) throws Exception {
        return mapper.readValue(
            new File(
                getSampleFileFullPath(SAMPLE_DIR_PATHS.get(clazz), filename)
            ),
            clazz
        );
    }
}