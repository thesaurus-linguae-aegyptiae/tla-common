package tla.domain.model.meta;

import java.lang.annotation.Annotation;

public class Util {

    /**
     * Extract BTS <code>eclass</code> specifier from class annotations
     * ({@link BTSeClass} or {@link TLADTO}).
     *
     * @return <code>eClass</code> value if one was found, null otherwise
     */
    public static String extractEclass(Class<?> clazz) {
        for (Annotation a : clazz.getAnnotations()) {
            if (a instanceof BTSeClass) {
                return ((BTSeClass) a).value();
            } else if (a instanceof TLADTO) {
                return extractEclass(((TLADTO) a).value());
            }
        }
        return null;
    }

}
