package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * An undirected graph of Node<T>s.
 * @param <T> the type of values in this Graph's Nodes.
 */
public class Graph<T> {

    // instance variable
	// Node to the Set of Nodes that is adjacent to this Node
	private Map<Node<T>, Set<Node<T>>> content;
	// id to Node
	private Map<Integer, Node<T>> items;
	
    /**
     * Creates a new empty Graph.
     */
        // constructor
	public Graph(){
		this.content = new HashMap<>();	
		this.items = new HashMap<>();
	}

    /**
     * Returns a Set of Nodes in this Graph.
     * @return a Set of Nodes in this Graph
     */
    	// getNodes
	public Set<Node<T>> getNodes(){
		return content.keySet();
	}
    /**
     * Returns the Node from this Graph with the given ID.
     * @param id the ID of the Node to return
     * @return the Node from this Graph with the given ID
     * @throws NoSuchNodeException if there is no Node with ID
     *    id in this Graphs
     */
        // getNode
	public Node<T> getNode(int id) throws NoSuchNodeException{
		if (this.items.containsKey(id)){
			return items.get(id);
		}else{	
			throw new NoSuchNodeException("No Node with such ID " + id);
		}
	}

    /**
     * Returns a Set of neighbours of the given Node.
     * @param node the Node whose neighbours are returned
     * @return a Set of neighbours of node
     */
    // getNeighbours
	public Set<Node<T>> getNeighbours(Node<T> node){
		return content.get(node);
	}

    /**
     * Returns whether Nodes with the given IDs are adjacent in this Graph.
     * @param id1 ID of the node to test for adjacency
     * @param id2 ID of the node to test for adjacency
     * @return true, if Nodes with IDs id1 and id2 are adjacent in this Graph,
     *    and false otherwise
     * @throws NoSuchNodeException if node with ID id1 or id2 is not in this Graph
     */
    // areAdjacent
	public boolean areAdjacent(int id1, int id2) throws NoSuchNodeException{
		if(items.containsKey(id1) && items.containsKey(id2)){
			return content.get(items.get(id1)).contains(this.items.get(id2));
		}else{
			throw new NoSuchNodeException("Node with ID " + id1 + "or " + id2 
					+ " is not in this Graph.");
		}
	}

    /**
     * Returns whether the given Nodes are adjacent in this Graph.
     * @param node1 the Node to test for adjacency with node2
     * @param node2 the Node to test for adjacency with node1
     * @return true, if node1 and node2 are adjacent in this Graph,
     *    and false otherwise
     * @throws NoSuchNodeException if node1 or node2 are not in this Graph
     */
    // areAdjacent
	public boolean areAdjacent(Node<T> node1, Node<T> node2) throws 
	NoSuchNodeException{
		if (content.containsKey(node1) && content.containsKey(node2)){
			return content.get(node1).contains(node2) && 
					content.get(node2).contains(node1);
		}else{
			throw new NoSuchNodeException("Node " + node1 + " or Node " + 
		node2 + " is not in this Graph.");
		}
	}

    /**
     * Returns the number of nodes in this Graph.
     * @return The number of nodes in this Graph.
     */
    public int getNumNodes() {
        return getNodes().size();	
    }

    /**
     * Returns the number of edges in this Graph.
     * @return The number of edges in this Graph.
     */
    public int getNumEdges() {	
        int total = 0;

        for (Node<T> node : getNodes()) {
            total += getNeighbours(node).size();
        }

        return total / 2;
    }

    /**
     * Adds a new Node with the given value to this Graph. 
     * @param id the ID of the new Node
     * @param value the value of the new Node
     */
    // addNode
    public void addNode(int id, T value){
    	Node<T> item = new Node<>(id, value);
    	Set<Node<T>> nodes = new TreeSet<>();
    	this.content.put(item, nodes);
    	this.items.put(id, item);
    }
    
    /**
     * Adds an edge between the given nodes in this Graph. If there 
     * is already an edge between node1 and node2, does nothing.
     * @param node1 the node to add an edge to and from node2
     * @param node2 the node to add an edge to and from node1
     * @throws NoSuchNodeException if node1 or node2 is not in
     *    this Graph
     */
    // addEdge
    public void addEdge(Node<T> node1, Node<T> node2) throws 
	NoSuchNodeException{
    	if (this.getNodes().contains(node1) && this.getNodes().contains(node2))
    	{
    		if (node1.compareTo(node2) != 0){
	    		if (!this.content.get(node1).contains(node2)){
			    	this.content.get(node1).add(node2);
			    	this.content.get(node2).add(node1);
	    		}
    		}
    	}else{
    		throw new NoSuchNodeException("Node " + node1 + " or " + node2
    				+ " is not in this Graph");
    	}
    }
    
    /**
     * Adds an edge between the nodes with the given IDs in this Graph. 
     * @param id1 ID of the node to add an edge to and from
     * @param id2 ID of the node to add an edge to and from
     * @throws NoSuchNodeExceptionf there is no Node with ID 
     *    id1 or ID id2 in this Graph.
     */
    // addEdge
    public void addEdge(int id1, int id2) throws 
	NoSuchNodeException{
    	if (this.items.containsKey(id1) && this.items.containsKey(id2)){
    		if (id1 != id2){
	    		if (!this.content.get(this.getNode(id1)).contains(this.
	    				getNode(id2))){
			    	this.content.get(this.getNode(id1)).add(this.getNode(id2));
			    	this.content.get(this.getNode(id2)).add(this.getNode(id1));
	    		}
    		}
    	}else{
    		throw new NoSuchNodeException("Node with ID " + id1 + " or " + id2
    				+ " is not in this Graph");
    	}
    }    

    @Override
    public String toString() {

        String result = "";
        result += "Number of nodes: " + getNumNodes() + "\n";
        result += "Number of edges: " + getNumEdges() + "\n";

        for (Node<T> node: getNodes()) {
            result += node + " is adjacent to: ";
            for    (Node<T> neighbour: getNeighbours(node)) {
                result += neighbour + " ";
            }
            result += "\n";
        }
        return result;
    }
}