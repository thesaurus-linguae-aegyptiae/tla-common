package tla.domain.model.meta;

import java.util.List;

import tla.domain.model.ObjectPath;

/**
 * Implementations have a {@link #getPaths()} method returning a list of at least {@literal 1}
 * {@link ObjectPath} instance.
 *
 * <p>A document's paths are most likely put together by traversing the <code>partOf</code> relations
 * interlinking it to its respective ancestors, and then reversing the results. Consider the following
 * hierarchic order between document <code>I</code> and its ancestors
 * (where <code>-></code> represents an outgoing <code>partOf</code> relation):
 * 
 * <pre>
 * A <- B <- C <- I
 * </pre>
 * 
 * In the example illustrated, calling <code>I</code>'s method {@link #getPaths()} would return a list
 * containing a single {@link ObjectPath}, which itself is basically a list, and whose first element
 * is the <em>root documents</em> <code>A</code>, followed by <code>B</code> and <code>C</code>.
 * The document <code>I</code> itself should not be part of a path leading to itself.
 * </p>
 *
 * @author Jakob Hoeper
 */
public interface Hierarchic {

    /**
     * One or more paths leading to the position where this
     * document can be located within the hierarchic order in which all documents
     * of its type are being placed.
     */
    public List<ObjectPath> getPaths();

}