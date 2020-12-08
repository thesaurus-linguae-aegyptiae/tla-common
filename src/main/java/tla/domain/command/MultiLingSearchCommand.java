package tla.domain.command;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tla.domain.model.meta.AbstractBTSBaseClass;

/**
 * An abstract command containing specifications for a search for documents with translations.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class MultiLingSearchCommand<T extends AbstractBTSBaseClass> extends SearchCommand<T> implements IncludingTranslations {

    /**
     * search documents with translations to any of one or more specified languages which
     * contain a search term.
     */
    @JsonInclude(
        value = JsonInclude.Include.CUSTOM,
        valueFilter = TranslationSpec.EmptyObjectFilter.class
    )
    private TranslationSpec translation;

}
