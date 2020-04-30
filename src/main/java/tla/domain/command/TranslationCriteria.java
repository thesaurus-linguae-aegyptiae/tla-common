package tla.domain.command;

import lombok.Data;
import tla.domain.model.Language;

@Data
public class TranslationCriteria {

    private String text;
    private Language[] lang;

}