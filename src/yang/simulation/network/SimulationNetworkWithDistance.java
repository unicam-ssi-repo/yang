package yang.simulation.network;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import yang.generators.NetworkGeneratorCore;
import yang.nodes.Neighbor;
import yang.nodes.NoSpaceNode;
import yang.nodes.Node;
import yang.nodes.SpaceConnectedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimulationNetworkWithDistance {
    public SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> network;

    public SimulationNetworkWithDistance(NetworkGeneratorCore nospace) {
        network = new SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        ArrayList<Node> nodes = nospace.getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            SpaceConnectedNode n = (SpaceConnectedNode) nodes.get(i);
            Integer source = new Integer(n.getId());
            network.addVertex(source);
            ArrayList<Neighbor> list = n.getNeighbors();
            for (int e = 0; e < n.getNeighbors().size(); e++) {
                SpaceConnectedNode en = (SpaceConnectedNode) nodes.get(n.getNeighbors().get(e).nodeID);
                Integer destination = new Integer(en.getId());
                network.addVertex(destination);
                if (network.getEdge(source,destination)==null){
                    DefaultWeightedEdge edge = network.addEdge(source,destination);
                    network.setEdgeWeight(edge,n.getNeighbors().get(e).distance);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimulationNetworkWithDistance)) return false;
        SimulationNetworkWithDistance that = (SimulationNetworkWithDistance) o;
        return Objects.equals(network, that.network);
    }

    @Override
    public int hashCode() {
        return Objects.hash(network);
    }
}
