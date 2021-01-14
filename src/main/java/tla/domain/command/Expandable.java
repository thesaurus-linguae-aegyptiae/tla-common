package tla.domain.command;

/**
 * Can be used to identify search command adapters that might want to invoke query expansion, meaning
 * that they don't need to return search hits, but an IDs aggregation instead.
 */
public interface Expandable {

    public void setExpand(boolean expand);
    public boolean isExpand();

    public void setRootIds(String[] ids);
    public String[] getRootIds();

}
