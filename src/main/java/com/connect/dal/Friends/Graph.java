package com.connect.dal.Friends;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    // Alternatively, use a Multimap:
    // http://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/collect/Multimap.html
    private Map<String, List<String>> edges = new HashMap<String, List<String>>();

    public void addEdge(String src, String dest) {
    	System.out.println("src"+src+"dest"+dest);
        List<String> srcNeighbors = this.edges.get(src);
        if (srcNeighbors == null) {
            this.edges.put(src,
                srcNeighbors = new ArrayList<String>()
            );
        }
        srcNeighbors.add(dest);
        System.out.println("List sizes:"+edges.size());
    }

    public Iterable<String> getNeighbors(String vertex) {
        List<String> neighbors = this.edges.get(vertex);
        if (neighbors == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(neighbors);
        }
    }
}