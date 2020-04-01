package tla.domain.dto.extern;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import tla.domain.Util;
import tla.domain.dto.DocumentDto;
import tla.domain.dto.LemmaDto;
import tla.domain.dto.ThsEntryDto;

public class WrapperTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addRelated() throws Exception {
        LemmaDto l = (LemmaDto) Util.loadFromFile("lemma", "10070.json");
        ThsEntryDto t = (ThsEntryDto) Util.loadFromFile("ths", "2AVEQ3VFT5EEPF7NBH7RHCVBXA.json");
        SingleDocumentWrapper<LemmaDto> w = SingleDocumentWrapper.<LemmaDto>builder()
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
        w.addRelated(t);
        assertEquals(1, w.getRelated().get(t.getEclass()).size(), "number of ths entries should have stayed the same");
        ThsEntryDto t2 = (ThsEntryDto) Util.loadFromFile("ths", "ACJUYKAESFH4JAWU2KOOHKW3HM.json");
        w.addRelated(t2);
        assertAll("adding another related object of same type",
            () -> assertEquals(2, w.getRelated().get(t.getEclass()).size(), "expect 2 related ths entries now")
        );
    }

    @Test
    void deserialize() throws Exception {
        LemmaDto l = (LemmaDto) Util.loadFromFile("lemma", "10070.json");
        ThsEntryDto t = (ThsEntryDto) Util.loadFromFile("ths", "2AVEQ3VFT5EEPF7NBH7RHCVBXA.json");
        SingleDocumentWrapper<LemmaDto> w = new SingleDocumentWrapper<>(l);
        w.addRelated(t);
        SingleDocumentWrapper<DocumentDto> w2 = SingleDocumentWrapper.of(mapper.writeValueAsString(w));
        assertAll("test deserialization",
            () -> assertEquals(w, w2, "deserialized instance should equal serialized origin"),
            () -> assertEquals(w.toString(), w2.toString(), "toString repr of both instances should equal"),
            () -> assertEquals(w.hashCode(), w2.hashCode(), "hashcodes should be same"),
            () -> assertTrue(w.getDoc() instanceof LemmaDto, "payload should be lemmadto instance")
        );

    }

}