package yang.generators.twod;

import yang.generators.NetworkGeneratorCore;
import yang.generators.twod.interconnected.NetworkGeneratorInterconnected2DInstance;
import yang.generators.zerod.NetworkGeneratorNoSpaceInstance;
import yang.nodes.*;

import java.util.ArrayList;

public abstract class NetworkGenerator2DCore extends NetworkGeneratorCore {
    protected  int number_edges;

    public NetworkGenerator2DCore(int n){
        super(n);

    }
    @Override
    protected abstract void createNodes(int n);

    public NetworkGeneratorNoSpaceInstance toInterConnected0D(double nodeRadius){
        NoSpaceNode node;
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < this.nodes.size(); i++) {
            node = new NoSpaceNode(i);
            for (int ni = 0; ni < this.nodes.size(); ni++) {
                if (ni!=i&&this.isReachable(ni,i,nodeRadius)){
                    node.getNeighbors().add(ni);
                }
            }
            nodes.add(node);
        }
        return new NetworkGeneratorNoSpaceInstance(nodes);
    }

    public NetworkGeneratorInterconnected2DInstance toInterConnected2D(double minNodeRadius,double maxNodeRadius){
        SpaceNode node2;
        SpaceConnectedNode node;
        Neighbor neighbor;
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < this.nodes.size(); i++) {
            node2 = (SpaceNode) this.nodes.get(i);
            node = node2.toSpaceInterConnectedNode();
            for (int ni = 0; ni < this.nodes.size(); ni++) {
                if (ni!=i&&this.isReachable(ni,i,maxNodeRadius)){
                    neighbor = new Neighbor();
                    SpaceNode master = (SpaceNode) this.nodes.get(i);
                    neighbor.distance = master.getDistance((SpaceNode)this.nodes.get(ni));
                    if (neighbor.distance < minNodeRadius){
                        return null;
                    }
                    neighbor.nodeID = ni;
                    node.getNeighbors().add(neighbor);
                }
            }
            nodes.add(node);
        }
        return new NetworkGeneratorInterconnected2DInstance(nodes);
    }

    /**
     * Check if @to node is inside the range of from node.
     * @param from
     * @param to
     * @return
     */
    public boolean isReachable(SpaceNode from,SpaceNode to, double radius){
        double x1 = from.getX();
        double y1 = from.getY();
        double x2 = to.getX();
        double y2 = to.getY();
        return this.isAPointInACirconference(x2,y2,x1,y1,radius);
    }

    public boolean isAPointInACirconference(double x, double y, double center_x,double center_y, double radius){
        return (((x - center_x)*(x - center_x)) + ((y - center_y)*(y - center_y))) < (radius*radius);
    }

    public boolean isReachable(int from,int to,double radius){
        return this.isReachable((SpaceNode) this.nodes.get(from),(SpaceNode) this.nodes.get(to),radius);
    }
    public NetworkGeneratorNoSpaceInstance convertNetwork(double nodeRadius){
        NoSpaceNode node;
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < this.nodes.size(); i++) {
            node = new NoSpaceNode(i);
            for (int ni = 0; ni < this.nodes.size(); ni++) {
                if (ni!=i&&this.isReachable(ni,i,nodeRadius)){
                    node.getNeighbors().add(ni);
                }
            }
            nodes.add(node);
        }
        return new NetworkGeneratorNoSpaceInstance(nodes);
    }
}

