package yang.generators.twod.interconnected;

import yang.generators.NetworkGeneratorCore;
import yang.helpers.Pair;
import yang.nodes.Neighbor;
import yang.nodes.Node;
import yang.nodes.SpaceConnectedNode;
import yang.simulation.network.SimulationNetwork;
import yang.simulation.network.SimulationNetworkWithDistance;

import java.util.ArrayList;
import java.util.HashSet;

public class NetworkGeneratorInterconnected2DInstance extends NetworkGeneratorCore {
    private HashSet<Pair> unodes;
    public NetworkGeneratorInterconnected2DInstance(ArrayList<Node> nodes){
        super(nodes.size());
        this.setNodes(nodes);
        this.unodes = this.getUniqueNodes();
    }

    public HashSet<Pair> getUniqueNodes(){
        HashSet<Pair> list = new HashSet<Pair>();
        Pair couple;
        for (int nc = 0; nc < this.nodes.size(); nc++) {
            SpaceConnectedNode startNode = (SpaceConnectedNode) this.nodes.get(nc);
            for (int neighC = 0; neighC < startNode.getNeighbors().size(); neighC++) {
                Neighbor n = startNode.getNeighbors().get(neighC);
                couple = new Pair(startNode,this.getNodeByID(n.nodeID),n.distance);
                list.add(couple);
            }
        }
        return list;
    }
    private SpaceConnectedNode getNodeByID( int nid){
        for (Node node: this.nodes){
            SpaceConnectedNode n = (SpaceConnectedNode) node;
            if (n.getId() == nid) { return n;}
        }
        return null;
    }
    public double getDensity(){
        double minX = Double.POSITIVE_INFINITY, minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        for (Pair edge:this.unodes){
            // sum+= edge.getFrom().getX();
            // Compute area with these coordinates.
            minX = edge.getFrom().getX() < minX?edge.getFrom().getX():minX;
            maxX = edge.getFrom().getX() > maxX?edge.getFrom().getX():maxX;
            minY = edge.getFrom().getY() < minY?edge.getFrom().getY():minY;
            maxY = edge.getFrom().getY() > maxY?edge.getFrom().getY():maxY;

            // TO
            minX = edge.getTo().getX() < minX?edge.getTo().getX():minX;
            maxX = edge.getTo().getX() > maxX?edge.getTo().getX():maxX;
            minY = edge.getTo().getY() < minY?edge.getTo().getY():minY;
            maxY = edge.getTo().getY() > maxY?edge.getTo().getY():maxY;
        }
        double area = (maxX-minX)*(maxY-minY);
        return area == 0?0:this.unodes.size()/area;
    }
    public double getDensityArea(){
        double minX = Double.POSITIVE_INFINITY, minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        for (Pair edge:this.unodes){
            // sum+= edge.getFrom().getX();
            // Compute area with these coordinates.
            minX = edge.getFrom().getX() < minX?edge.getFrom().getX():minX;
            maxX = edge.getFrom().getX() > maxX?edge.getFrom().getX():maxX;
            minY = edge.getFrom().getY() < minY?edge.getFrom().getY():minY;
            maxY = edge.getFrom().getY() > maxY?edge.getFrom().getY():maxY;

            // TO
            minX = edge.getTo().getX() < minX?edge.getTo().getX():minX;
            maxX = edge.getTo().getX() > maxX?edge.getTo().getX():maxX;
            minY = edge.getTo().getY() < minY?edge.getTo().getY():minY;
            maxY = edge.getTo().getY() > maxY?edge.getTo().getY():maxY;
        }
        double area = (maxX-minX)*(maxY-minY);
        return area;
    }
    public double getDensitySizeX(){
        double minX = Double.POSITIVE_INFINITY, minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        for (Pair edge:this.unodes){
            // sum+= edge.getFrom().getX();
            // Compute area with these coordinates.
            minX = edge.getFrom().getX() < minX?edge.getFrom().getX():minX;
            maxX = edge.getFrom().getX() > maxX?edge.getFrom().getX():maxX;
            minY = edge.getFrom().getY() < minY?edge.getFrom().getY():minY;
            maxY = edge.getFrom().getY() > maxY?edge.getFrom().getY():maxY;

            // TO
            minX = edge.getTo().getX() < minX?edge.getTo().getX():minX;
            maxX = edge.getTo().getX() > maxX?edge.getTo().getX():maxX;
            minY = edge.getTo().getY() < minY?edge.getTo().getY():minY;
            maxY = edge.getTo().getY() > maxY?edge.getTo().getY():maxY;
        }
        return (maxX-minX);
    }
    public double getDensitySizeY(){
        double minX = Double.POSITIVE_INFINITY, minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        for (Pair edge:this.unodes){
            // sum+= edge.getFrom().getX();
            // Compute area with these coordinates.
            minX = edge.getFrom().getX() < minX?edge.getFrom().getX():minX;
            maxX = edge.getFrom().getX() > maxX?edge.getFrom().getX():maxX;
            minY = edge.getFrom().getY() < minY?edge.getFrom().getY():minY;
            maxY = edge.getFrom().getY() > maxY?edge.getFrom().getY():maxY;

            // TO
            minX = edge.getTo().getX() < minX?edge.getTo().getX():minX;
            maxX = edge.getTo().getX() > maxX?edge.getTo().getX():maxX;
            minY = edge.getTo().getY() < minY?edge.getTo().getY():minY;
            maxY = edge.getTo().getY() > maxY?edge.getTo().getY():maxY;
        }
        return (maxY-minY);
    }
    //

    public double getAvgDistance(){
        double sum = 0;
        for (Pair edge:this.unodes){
            sum+= edge.getDistance();
        }
        return this.unodes.size() == 0?0:sum/this.unodes.size();
    }
    public double getVarianceDistance(){
        double variance = 0;
        double avg = this.getAvgDistance();
        double diffNode = 0;
        double sum = 0;
        for (Pair edge:this.unodes){
            diffNode = (edge.getDistance()-avg);
            sum += (diffNode*diffNode);
        }
        return this.unodes.size() <=1?avg:sum/(this.unodes.size()-1);
    }

    public static NetworkGeneratorInterconnected2DInstance load(ArrayList<String> nodes) {
        ArrayList<Node> networkNodes = new ArrayList<Node>();
        for (int i = 0; i < nodes.size(); i++) {
            networkNodes.add(SpaceConnectedNode.toSpaceConnectedNode(nodes.get(i)));
        }
        return new NetworkGeneratorInterconnected2DInstance(networkNodes);
    }

    @Override
    protected void createNodes(int n) {

    }

    public boolean isInterconnected() {
        return new SimulationNetworkWithDistance(this).isInterconnected();
    }
}
