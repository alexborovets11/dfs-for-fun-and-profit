import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice {

  /**
   * Prints the value of every vertex reachable from the given starting vertex,
   * including the starting vertex itself. Each value is printed on a separate line.
   * The order of printing is unimportant.
   *
   * Each vertex's value should be printed only once, even if it is reachable via multiple paths.
   * It is guaranteed that no two vertices will have the same value.
   *
   * If the given vertex is null, this method prints nothing.
   *
   * @param vertex The starting vertex for the traversal.
   */
  public <T> void printVertexVals(Vertex<T> vertex) {
    if (vertex == null) {
      return;
    }
    for (Vertex<T> v : reachable(vertex)) {
      System.out.println(v.data);
    }
  }

  /**
   * Returns a set of all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> reachable(Vertex<T> start) {
    Set<Vertex<T>> visited = new HashSet<>();
    if (start == null) {
        return visited;
    }
    dfsReachable(start, visited);
    return visited;
  }

  private <T> void dfsReachable(Vertex<T> v, Set<Vertex<T>> visited) {
    if (v == null || visited.contains(v)) {
        return;
    }
    visited.add(v);
    if (v.neighbors != null) {
        for (Vertex<T> neighbor : v.neighbors) {
            dfsReachable(neighbor, visited);
        }
    }
  }

  /**
   * Returns the maximum value among all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, the method returns Integer.MIN_VALUE.
   *
   * @param vertex The starting vertex for the traversal.
   * @return The maximum value of any reachable vertex, or Integer.MIN_VALUE if vertex is null.
   */
  public int max(Vertex<Integer> vertex) {
    if (vertex == null) {
      return Integer.MIN_VALUE;
    }
    int max = Integer.MIN_VALUE;
    for (Vertex<Integer> v : reachable(vertex)) {
      if (v.data > max) {
          max = v.data;
      }
    }
    return max;
}

  /**
   * Returns a set of all leaf vertices reachable from the given starting vertex.
   * A vertex is considered a leaf if it has no outgoing edges (no neighbors).
   *
   * The starting vertex itself is included in the set if it is a leaf.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable leaf vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {
    Set<Vertex<T>> leaves = new HashSet<>();
        if (vertex == null) {
            return leaves;
        }
        for (Vertex<T> v : reachable(vertex)) {
            if (v.neighbors == null || v.neighbors.isEmpty()) {
                leaves.add(v);
            }
        }
        return leaves;
    }


  /**
   * Returns whether all reachable vertices (including the starting vertex) hold
   * odd values. Returns false if at least one reachable vertex (including the starting vertex)
   * holds an even value.
   * 
   * If the given vertex is null, returns true.
   * 
   * @param vertex The starting vertex
   * @return true if all reachable vertices hold odd values, false otherwise
   */
  public boolean allOdd(Vertex<Integer> vertex) {
    if (vertex == null) {
      return true;
    }
    for (Vertex<Integer> v : reachable(vertex)) {
      if (v.data % 2 == 0) {
          return false;
      }
    }
    return true;
}

  /**
   * Determines whether there exists a strictly increasing path from the given start vertex
   * to the target vertex.
   *
   * A path is strictly increasing if each visited vertex has a value strictly greater than
   * (not equal to) the previous vertex in the path.
   *
   * If either start or end is null, a NullPointerException is thrown.
   *
   * @param start The starting vertex.
   * @param end The target vertex.
   * @return True if a strictly increasing path exists, false otherwise.
   * @throws NullPointerException if either start or end is null.
   */
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) {
    if (start == null || end == null) {
        throw new NullPointerException();
    }
    return dfsIncreasing(start, end, start.data, new HashSet<>());
  }

  private boolean dfsIncreasing(Vertex<Integer> current,
                                Vertex<Integer> end,
                                int lastVal,
                                Set<Vertex<Integer>> visited) {
      if (current == end) {
          return true;
      }
      visited.add(current);
      if (current.neighbors != null) {
          for (Vertex<Integer> neighbor : current.neighbors) {
              if (!visited.contains(neighbor) && neighbor.data > lastVal) {
                  if (dfsIncreasing(neighbor, end, neighbor.data, visited)) {
                      return true;
                  }
              }
          }
      }
      return false;
  }
}
