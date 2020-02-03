package tla.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class Transcription {

    private String unicode;
    private String mdc;
    
    @JsonCreator
    public Transcription(
        @JsonProperty(value = "unicode", required = true) String unicode,
        @JsonProperty(value = "mdc", required = true) String mdc
    ) {
        this.unicode = unicode;
        this.mdc = mdc;
    }
}