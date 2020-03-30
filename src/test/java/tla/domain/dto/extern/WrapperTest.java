package tla.domain.dto.extern;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.Util;
import tla.domain.dto.LemmaDto;
import tla.domain.dto.ThsEntryDto;

public class WrapperTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addRelated() throws Exception {
        LemmaDto l = (LemmaDto) Util.loadFromFile(LemmaDto.class, "10070.json");
        ThsEntryDto t = (ThsEntryDto) Util.loadFromFile(ThsEntryDto.class, "2AVEQ3VFT5EEPF7NBH7RHCVBXA.json");
        SingleDocumentWrapper w = SingleDocumentWrapper.builder()
            .doc(l)
            .build();
        w.addRelated(t);
        assertAll("check single documentwrapper",
            () -> assertNotNull(w.getDoc(), "should contain payload"),
            () -> assertTrue(w.getDoc() instanceof LemmaDto, "payload should be lemma"),
            () -> assertNotNull(w.getRelated(), "should contain related object map"),
            () -> assertEquals(1, w.getRelated().size(), "expect 1 eclass in related map"),
            () -> assertTrue(w.getRelated().containsKey(t.getEclass()), "only ths entry eclass should be in related map"),
            () -> assertTrue(w.getRelated().get(t.getEclass()).containsKey(t.getId()), "ths entry should be stored under its ID"),
            () -> assertEquals(t, w.getRelated().get(t.getEclass()).get(t.getId()), "ths entry should be in data structure")
        );
    }

    @Test
    void deserialize() throws Exception {
        LemmaDto l = (LemmaDto) Util.loadFromFile(LemmaDto.class, "10070.json");
        ThsEntryDto t = (ThsEntryDto) Util.loadFromFile(ThsEntryDto.class, "2AVEQ3VFT5EEPF7NBH7RHCVBXA.json");
        SingleDocumentWrapper w = SingleDocumentWrapper.builder()
            .doc(l)
            .build();
        w.addRelated(t);
        SingleDocumentWrapper w2 = mapper.readValue(
            mapper.writeValueAsString(w),
            SingleDocumentWrapper.class
        );
        assertAll("test deserialization",
            () -> assertEquals(w, w2, "deserialized instance should equal serialized origin"),
            () -> assertEquals(w.toString(), w2.toString(), "toString repr of both instances should equal")
        );

    }

}