package yang;

import java.util.ArrayList;
import java.util.Random;

public class NetworkGenerator {
    private long numberNodes;
    private double radius;
    private double density;
    private long MasterX;
    private long MasterY;
    private ArrayList<Node> nodes;

    public NetworkGenerator(long numberNodes, double radius, double density, long MasterX, long MasterY) {
        this.numberNodes = numberNodes;
        this.radius = radius;
        this.density = density;
        this.nodes = new ArrayList<Node>();
        this.MasterX = MasterX;
        this.MasterY = MasterY;

    }


    public long getNumberNodes() {
        return numberNodes;
    }

    public double getRadius() {
        return radius;
    }

    public double getDensity() {
        return density;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public long getMasterX() {
        return MasterX;
    }

    public long getMasterY() {
        return MasterY;
    }

    public void generateNetwork(){
        long iNodes = 0;
        this.nodes = new ArrayList<Node>();
        // We need to create numberNodes in the network.
        for (iNodes = 0; iNodes < this.numberNodes; iNodes++){
            // the id is the node index.
            // we need to compute the x and y.
            // check if we created a node.

           this.nodes.add(this.createNode(iNodes));
        }
    }

    private Node createNode(long iNodes) {
        if (this.nodes.size() == 0){
            return new Node(this.MasterX,this.MasterY,iNodes);
        }else{
            Random r = new Random();
            int selectedNode = r.nextInt(this.nodes.size());
            double randomDensity;

            long selectedNodeX = this.nodes.get(selectedNode).getX();
            long selectedNodeY = this.nodes.get(selectedNode).getY();
            // long neighbors = this.nodes.get(selectedNode).getNeighborNumber();
            long addnewNodeSelX,addnewNodeSelY;
            long newNodeSelX,newNodeSelY;
            do {
                randomDensity = -this.density + (this.density + this.density) * r.nextDouble() ; // negative also.
                addnewNodeSelX = (long) (randomDensity * radius);
                addnewNodeSelY  = (long) (randomDensity * radius);
            }while (this.nodes.get(selectedNode).equalsPosition(selectedNodeX+addnewNodeSelX,selectedNodeY+addnewNodeSelY));
            // define node position.
            newNodeSelX = selectedNodeX+addnewNodeSelX;
            newNodeSelY = selectedNodeY+addnewNodeSelY;
            return new Node(newNodeSelX,newNodeSelY,iNodes);
        }
    }


}
