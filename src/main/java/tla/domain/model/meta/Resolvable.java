package tla.domain.model.meta;

/**
 * Reference to an object identified by its ID and eclass.
 */
public interface Resolvable {

    public String getId();

    public String getEclass();

    public String getName();

    public String getType();

}