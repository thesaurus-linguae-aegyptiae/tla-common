package tla.domain.model.extern;

import org.junit.jupiter.api.Test;

import tla.domain.dto.ThsEntryTest;
import tla.domain.model.ObjectReference;
import tla.domain.model.extern.AttestedTimespan.AttestationStats;
import tla.domain.model.extern.AttestedTimespan.Period;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AttestationsTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void serialize() throws Exception {
        var t = AttestedTimespan.builder()
            .contains(
                List.of(
                    new AttestedTimespan(),
                    AttestedTimespan.builder()
                        .attestations(
                            AttestationStats.builder()
                                .objects(13)
                                .sentences(17)
                                .texts(23)
                                .build()
                        ).build(),
                    AttestedTimespan.builder()
                        .attestations(
                            AttestationStats.builder()
                                .objects(5)
                                .sentences(7)
                                .texts(11)
                                .build()
                        ).build()
                )
            ).build();
        String s = mapper.writeValueAsString(t);
        assertEquals(1, s.split("\"contains\":").length - 1, "contains-field not serialized for child nodes");
    }

    @Test
    void attestationSum() throws Exception {
        AttestationStats t1 = AttestationStats.builder().objects(5).sentences(7).texts(11).build();
        AttestationStats t2 = AttestationStats.builder().objects(13).sentences(17).texts(19).build();
        t1.add(t2);
        assertAll("attestation stats should add up",
            () -> assertEquals(18, t1.getObjects(), "object count should match"),
            () -> assertEquals(24, t1.getSentences(), "sentence count should match"),
            () -> assertEquals(30, t1.getTexts(), "text count should match")
        );
    }

    @Test
    void periodCompare() throws Exception {
        Period siam = ThsEntryTest.loadThsEntryFromFileAndConvertToTimePeriod(
            "677YHBKQIRHB3HVZG45V2N6DU4"
        );
        Period thut = ThsEntryTest.loadThsEntryFromFileAndConvertToTimePeriod(
            "WMSRSEY4LBALVAQEKU4GKNLTMU"
        );
        assertAll("compare attestation periods",
            () -> assertTrue(thut.compareTo(siam) < 0, "Thutmosis earlier than siamun"),
            () -> assertTrue(siam.compareTo(thut) > 0, "siamun later than thutmosis"),
            () -> assertTrue(thut.getEnd() < siam.getBegin(), "no overlap"),
            () -> assertFalse(thut.contains(siam), "no inclusion")
        );
        Period test = new Period(thut.getBegin() + 1, thut.getEnd());
        assertTrue(thut.contains(test));
    }

    @Test
    void deserializePeriod() throws Exception {
        Period p1 = ThsEntryTest.loadThsEntryFromFileAndConvertToTimePeriod(
            "677YHBKQIRHB3HVZG45V2N6DU4"
        );
        String ser = mapper.writeValueAsString(p1);
        Period p2 = mapper.readValue(ser, Period.class);
        assertAll("test deserialization of time period DTO support type",
            () -> assertNotNull(p2, "should not be null"),
            () -> assertEquals(-986, p2.getBegin(), "first year should match"),
            () -> assertTrue(p2.getRef() instanceof ObjectReference, "expect object reference"),
            () -> assertEquals("BTSThsEntry", p2.getRef().getEclass(), "expect thesaurus reference")
        );
    }

}