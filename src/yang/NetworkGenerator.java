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

    private boolean intelligentNode;

    public NetworkGenerator(long numberNodes, double radius, double density, long MasterX, long MasterY,boolean intelligentNode) {
        this.numberNodes = numberNodes;
        this.radius = radius;
        this.density = density;
        this.nodes = new ArrayList<Node>();
        this.MasterX = MasterX;
        this.MasterY = MasterY;
        this.intelligentNode = intelligentNode;
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

    public boolean isIntelligentNode() {
        return intelligentNode;
    }

    public void generateNetwork(){
        long iNodes = 0;
        this.nodes = new ArrayList<Node>();
        // We need to create numberNodes in the network.
        for (iNodes = 0; iNodes < this.numberNodes; iNodes++){
            // the id is the node index.
            // we need to compute the x and y.
            // check if we created a node.
            if (this.isIntelligentNode()) {
                this.nodes.add(this.createIntelligentNode(iNodes));
            }else{
                this.nodes.add(this.createNode(iNodes));
            }

        }
    }

    private Node createNode(long iNodes) {
        if (this.nodes.size() == 0){
            return new Node(this.MasterX,this.MasterY,iNodes);
        }else{
            Random r = new Random();
            int selectedNode = r.nextInt(this.nodes.size());
            double randomDensityX,randomDensityY;

            long selectedNodeX = this.nodes.get(selectedNode).getX();
            long selectedNodeY = this.nodes.get(selectedNode).getY();
            // long neighbors = this.nodes.get(selectedNode).getNeighborNumber();
            long addnewNodeSelX,addnewNodeSelY;
            long newNodeSelX,newNodeSelY;
            do {
                randomDensityX = -this.density + (this.density + this.density) * r.nextDouble() ; // negative also.
                randomDensityY = -this.density + (this.density + this.density) * r.nextDouble() ; // negative also.

                addnewNodeSelX = (long) (randomDensityX * radius);
                addnewNodeSelY  = (long) (randomDensityY * radius);
            }while (this.nodes.get(selectedNode).equalsPosition(selectedNodeX+addnewNodeSelX,selectedNodeY+addnewNodeSelY));
            // define node position.
            newNodeSelX = selectedNodeX+addnewNodeSelX;
            newNodeSelY = selectedNodeY+addnewNodeSelY;
            return new Node(newNodeSelX,newNodeSelY,iNodes);
        }
    }

    private Node createIntelligentNode(long iNodes) {
        if (this.nodes.size() == 0){
            return new Node(this.MasterX,this.MasterY,iNodes);
        }else{
            Random r = new Random();
            int selectedNode = r.nextInt(this.nodes.size());
            double randomDensityX,randomDensityY;

            long selectedNodeX = this.nodes.get(selectedNode).getX();
            long selectedNodeY = this.nodes.get(selectedNode).getY();
            // long neighbors = this.nodes.get(selectedNode).getNeighborNumber();
            long addnewNodeSelX,addnewNodeSelY;
            long newNodeSelX,newNodeSelY;
            long prevNodeSelX,prevNodeSelY;
            prevNodeSelX = 0;
            prevNodeSelY = 0;
            do {
                randomDensityX = -this.density + (this.density + this.density) * r.nextDouble() ; // negative also.
                randomDensityY = -this.density + (this.density + this.density) * r.nextDouble() ; // negative also.

                addnewNodeSelX = (long) (randomDensityX * radius);
                addnewNodeSelY  = (long) (randomDensityY * radius);

            }while (this.nodes.get(selectedNode).equalsPosition(selectedNodeX+addnewNodeSelX,selectedNodeY+addnewNodeSelY));
            // define node position.
            newNodeSelX = selectedNodeX+addnewNodeSelX;
            newNodeSelY = selectedNodeY+addnewNodeSelY;
            return new Node(newNodeSelX,newNodeSelY,iNodes);
        }
    }


}
