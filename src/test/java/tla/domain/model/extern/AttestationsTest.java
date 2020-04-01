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
    void nestedTimespanSum() {
        AttestedTimespan t = AttestedTimespan.builder()
            .contains(
                List.of(
                    AttestedTimespan.builder()
                        .attestations(
                            AttestationStats.builder()
                                .objects(10)
                                .sentences(20)
                                .texts(30)
                                .build()
                        ).build(),
                    AttestedTimespan.builder()
                        .contains(
                            List.of(
                                AttestedTimespan.builder()
                                    .attestations(
                                        AttestationStats.builder()
                                            .objects(5)
                                            .sentences(6)
                                            .texts(7)
                                            .build()
                                    ).build(),
                                AttestedTimespan.builder()
                                    .attestations(
                                        AttestationStats.builder()
                                            .objects(40)
                                            .sentences(50)
                                            .texts(60)
                                            .build()
                                    ).build()
                            )
                        ).build()
                )
            ).build();
        AttestationStats expected = AttestationStats.builder().objects(55).sentences(76).texts(97).build();
        assertAll("attestation stats should add up",
            () -> assertEquals(expected, t.getAttestations()),
            () -> assertEquals(expected.hashCode(), t.getAttestations().hashCode(), "check hashcode"),
            () -> assertEquals(55, t.getAttestations().getObjects(), "object count should match"),
            () -> assertEquals(76, t.getAttestations().getSentences(), "sentence count should match"),
            () -> assertEquals(97, t.getAttestations().getTexts(), "text count should match")
        );
    }

    @Test
    void periodCompare() throws Exception {
        Period p1 = ThsEntryTest.loadThsEntryFromFileAndConvertToTimePeriod(
            "677YHBKQIRHB3HVZG45V2N6DU4"
        );
        Period p2 = ThsEntryTest.loadThsEntryFromFileAndConvertToTimePeriod(
            "WMSRSEY4LBALVAQEKU4GKNLTMU"
        );
        assertAll("compare attestation periods",
            () -> assertTrue(p2.compareTo(p1) < 0, "Thutmosis earlier than siamun"),
            () -> assertTrue(p1.compareTo(p2) > 0, "siamun later than thutmosis"),
            () -> assertTrue(p2.getEnd() < p1.getBegin(), "no overlap")
        );
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
            () -> assertTrue(p2.getThs() instanceof ObjectReference, "expect thesaurus reference object")
        );
    }

}