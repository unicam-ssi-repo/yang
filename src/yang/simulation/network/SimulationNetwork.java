package yang.simulation.network;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import yang.generators.NetworkGeneratorCore;
import yang.nodes.NoSpaceNode;
import yang.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class SimulationNetwork {
    public SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> network;

    public SimulationNetwork(NetworkGeneratorCore nospace) {
        network = new SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        ArrayList<Node> nodes = nospace.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            NoSpaceNode n = (NoSpaceNode) nodes.get(i);
            Integer source = new Integer(n.getId());
            network.addVertex(source);
            ArrayList<Integer> list = n.getNeighbors();
            for (int e = 0; e < n.getNeighbors().size(); e++) {
                NoSpaceNode en = (NoSpaceNode) nodes.get(n.getNeighbors().get(e));
                Integer destination = new Integer(en.getId());
                network.addVertex(destination);
                if (network.getEdge(source,destination)==null){
                    DefaultWeightedEdge edge = network.addEdge(source,destination);
                    network.setEdgeWeight(edge,0);
                }

            }
        }
    }
    public boolean isInterconnected(){
        DijkstraShortestPath dijkstraPaths = new DijkstraShortestPath<Integer, DefaultWeightedEdge>(this.network);
        for (int i = 1; i < this.network.vertexSet().size() ; i++) {
            Integer endNode = new Integer(i); // chooose destination and apply dijkstra
            try {
                GraphPath p = dijkstraPaths.getPath(new Integer(0), endNode);
                List path = p.getVertexList();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }


}
