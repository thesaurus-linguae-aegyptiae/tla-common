package tla.domain.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(Include.NON_NULL)
public class Passport {

    @JsonIgnore
    private String leafNodeValue = null;

    @JsonIgnore
    private Map<String, List<Passport>> properties = null;

    private String id = null;
    private String type = null;
    private String eclass = null;
    private String name = null;

    @Getter(value=AccessLevel.NONE)
    @Setter(value=AccessLevel.NONE)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private ObjectReference thesaurusValue = null;


    public Passport() {
        super();
        properties = new HashMap<String, List<Passport>>();
    }

    public Passport(String value) {
        this.leafNodeValue = value;
    }

    /**
     * Determine whether this node has no contents at all.
     */
    public boolean isEmpty() {
        if (this.properties != null && this.properties.size() > 0) {
            return false;
        }
        return this.get() == null;
    }

    /**
     * Add a passport node at the specified key.
     */
    public void add(String key, Passport child) {
        if (this.properties == null) {
            this.properties = new HashMap<String, List<Passport>>();
        }
        if (this.leafNodeValue != null) {
            this.leafNodeValue = null;
        }
        if (this.properties.containsKey(key)) {
            this.properties.get(key).add(child);
        } else {
            this.properties.put(
                key, 
                new LinkedList<>(Arrays.asList(child))
            );
        }
    }

    /**
     * Extracts a list of values from this passport object which can be reached by following
     * the specified selector path.
     * 
     * Path segments are expected to be passed as individual strings. However, if only 1 single segment
     * gets provided, the method will try and split this string argument along the "<code>.</code>" delimiter
     * and use the result as the path segments list.
     */
    public List<Passport> extractProperty(String... path) throws Exception {
        if (path.length == 1) {
            path = path[0].split("\\.");
        }
        List<Passport> recursionResults = new LinkedList<Passport>();
        if (path.length > 0) {
            String segment = path[0];
            if (this.properties.containsKey(segment)) {
                for (Passport child : this.properties.get(segment)) {
                    recursionResults.addAll(
                        child.extractProperty(
                            Arrays.copyOfRange(path, 1, path.length)
                        )
                    );
                }
                return recursionResults;
            } else {
                throw new Exception("path segment not in passport: " + segment);
            }
        } else {
            recursionResults.add(this);
        }
        return recursionResults;
    }

    /**
     * Extract list of selectors available for this passport object.
     * 
     * Selector path segments delimiter is "<code>.</code>".
     */
    public List<String> extractPaths() {
        List<String> paths = new LinkedList<String>();
        if (this.properties != null) {
            if (this.properties.size() > 0) {
                for (Entry<String, List<Passport>> e : this.properties.entrySet()) {
                    String key = e.getKey();
                    Set<String> remainders = new HashSet<>();
                    boolean hasNonEmptyChildren = false;
                    if (e.getValue().size() > 0) {
                        for (Passport child : e.getValue()) {
                            hasNonEmptyChildren |= !child.isEmpty();
                            List<String> singleRecursionResults = child.extractPaths();
                            if (singleRecursionResults != null) {
                                remainders.addAll(singleRecursionResults);
                            }
                        }
                    }
                    if (remainders.size() > 0) {
                        for (String remainder : remainders) {
                            paths.add(key + "." + remainder);
                        }
                    } else if (hasNonEmptyChildren) {
                        paths.add(key);
                    }
                }
            } else {
                return null;
            }
        }
        return paths;
    }


    /**
     * Walks down tree and collects leaf node values.
     */
    public List<Passport> extractValues() {
        List<Passport> values = new LinkedList<Passport>();
        Object value = this.get();
        if (value != null) {
            return Arrays.asList(this);
        } else {
            for (Entry<String, List<Passport>> e : this.properties.entrySet()) {
                for (Passport child : e.getValue()) {
                    values.addAll(child.extractValues());
                }
            }
        }
        return values;
    }


    /**
     * Extract object reference values, i.e. usually references to thesaurus entries.
     * 
     * @return list of all object references found in this passport
     */
    public List<ObjectReference> extractObjectReferences() {
        List<ObjectReference> refs = this.extractValues().stream()
            .filter(leaf -> leaf.get() instanceof ObjectReference)
            .map(leaf -> (ObjectReference)leaf.get())
            .collect(Collectors.toList());
        return refs;
    }


    /**
     * Required for Jackson to be able to deserialize arbitrary passport fields.
     * @param key field name
     * @param children list of subnodes
     */
    @JsonAnySetter
    public void setProperty(String key, List<Passport> children) {
        properties.put(key, children);
    }


    @Override
    public String toString() {
        if (this.leafNodeValue != null) {
            return this.leafNodeValue;
        } else if (this.id != null) {
            return Collections.unmodifiableMap(
                Stream.of(
                    new SimpleEntry<>("id", id),
                    new SimpleEntry<>("name", name),
                    new SimpleEntry<>("type", type),
                    new SimpleEntry<>("eclass", eclass)
                ).collect(
                    Collectors.toMap(
                        (e) -> e.getKey(),
                        (e) -> e.getValue()
                    )
                )
            ).toString();
        } else {
            return this.properties.toString();
        }
    }


    public Object get() {
        if (this.leafNodeValue != null) {
            return this.leafNodeValue;
        } else if (this.id != null) {
            if (this.thesaurusValue == null) {
                this.thesaurusValue = new ObjectReference(this.id, this.eclass, this.type, this.name);
            }
            return this.thesaurusValue;
        }
        return null;
    }


    @JsonValue
    public Object getContents() {
        Object value = this.get();
        return (value != null) ? value : this.properties;
    }

}