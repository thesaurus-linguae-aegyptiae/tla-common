package tla.domain.command;

import lombok.Getter;
import lombok.Setter;
import tla.domain.model.Language;

@Getter
@Setter
public class TranslationSpec {

    private String text;
    private Language[] lang;

}