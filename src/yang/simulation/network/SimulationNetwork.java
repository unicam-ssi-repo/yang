package yang.simulation.network;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import yang.generators.NetworkGeneratorCore;
import yang.nodes.NoSpaceNode;
import yang.nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class SimulationNetwork {
    public SimpleWeightedGraph<MasterGraphNode, DefaultWeightedEdge> network;

    public SimulationNetwork(NetworkGeneratorCore nospace) {
        network = new SimpleWeightedGraph<MasterGraphNode, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        ArrayList<Node> nodes = nospace.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            NoSpaceNode n = (NoSpaceNode) nodes.get(i);
            MasterGraphNode source = new MasterGraphNode(n.getId());
            network.addVertex(source);
            ArrayList<Integer> list = n.getNeighbors();
            for (int e = 0; e < n.getNeighbors().size(); e++) {
                NoSpaceNode en = (NoSpaceNode) nodes.get(n.getNeighbors().get(e));
                MasterGraphNode destination = new MasterGraphNode(en.getId());
                network.addVertex(destination);
                if (network.getEdge(source,destination)==null){
                    DefaultWeightedEdge edge = network.addEdge(source,destination);
                    network.setEdgeWeight(edge,0);
                }

            }
        }
    }
    public boolean isInterconnected(){
        DijkstraShortestPath dijkstraPaths = new DijkstraShortestPath<MasterGraphNode, DefaultWeightedEdge>(this.network);
        for (int i = 1; i < this.network.vertexSet().size() ; i++) {
            MasterGraphNode endNode = new MasterGraphNode(i); // chooose destination and apply dijkstra
            try {
                GraphPath p = dijkstraPaths.getPath(new MasterGraphNode(0), endNode);
                List path = p.getVertexList();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }


}
