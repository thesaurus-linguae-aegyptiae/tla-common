package tla.domain.model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Paths extends LinkedList<List<ObjectReference>> {

    private static final long serialVersionUID = 1L;

    /**
     * TODO
     */
    public static Paths of(List<List<ObjectReference>> pathList) {
        Paths paths = new Paths();
        pathList.forEach(
            path -> {
                List<ObjectReference> uniqueItemPath = new ArrayList<ObjectReference>();
                path.forEach(
                    item -> {
                        if (!uniqueItemPath.contains(item)) {
                            uniqueItemPath.add(item);
                        }
                    }
                );
                paths.add(uniqueItemPath);
            }
        );
        return paths;
    }
}