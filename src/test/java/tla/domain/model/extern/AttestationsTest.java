package tla.domain.model.extern;

import org.junit.jupiter.api.Test;

import tla.domain.model.ObjectReference;
import tla.domain.model.extern.AttestedTimespan.AttestationStats;
import tla.domain.model.extern.AttestedTimespan.Period;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AttestationsTest {

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
    void periodCompare() {
        Period p1 = Period.builder()
            .begin(-986)
            .end(-968)
            .ths(
                ObjectReference.builder()
                    .id("677YHBKQIRHB3HVZG45V2N6DU4")
                    .name("Siamun Netjerycheperre-Setepenamun")
                    .type("date")
                    .eclass("BTSThsEntry")
                    .build()
            ).build();
        Period p2 = Period.builder()
            .begin(-1400)
            .end(-1390)
            .ths(
                ObjectReference.builder()
                    .id("WMSRSEY4LBALVAQEKU4GKNLTMU")
                    .name("Thutmosis IV. Mencheperure")
                    .type("date")
                    .eclass("BTSThsEntry")
                    .build()
            ).build();
        assertAll("compare attestation periods",
            () -> assertTrue(p2.compareTo(p1) < 0, "Thutmosis earlier than siamun"),
            () -> assertTrue(p1.compareTo(p2) > 0, "siamun later than thutmosis"),
            () -> assertTrue(p2.getEnd() < p1.getBegin(), "no overlap")
        );
    }

}