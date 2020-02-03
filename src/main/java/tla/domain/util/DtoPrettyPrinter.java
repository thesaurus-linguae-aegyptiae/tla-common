package tla.domain.util;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Separators;

public class DtoPrettyPrinter extends DefaultPrettyPrinter {

    private static final long serialVersionUID = -857602864138545423L;

    public static DefaultPrettyPrinter create() {
        DefaultPrettyPrinter pp = new DtoPrettyPrinter()
            .withSeparators(new Separators());
        pp.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        return pp;
    }

    @Override
    public DefaultPrettyPrinter createInstance() {
        return create();
    }

    @Override
    public DefaultPrettyPrinter withSeparators(Separators separators) {
        _separators = separators;
        _objectFieldValueSeparatorWithSpaces = separators.getObjectFieldValueSeparator() + " ";
        return this;
    }

    
}