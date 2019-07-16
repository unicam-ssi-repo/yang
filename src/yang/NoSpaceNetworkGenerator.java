package yang;

import yang.nodes.NoSpaceNode;

import java.util.ArrayList;
import java.util.Random;

public class NoSpaceNetworkGenerator {
    private final long numberEdges;
    private int numberNodes;
    private ArrayList<NoSpaceNode> nodes;


    public NoSpaceNetworkGenerator(int numberNodes,long edges) {
        this.numberNodes = numberNodes;
        this.numberEdges = edges;
        this.nodes = new ArrayList<NoSpaceNode>();
    }


    public int getNumberNodes() {
        return numberNodes;
    }


    public ArrayList<NoSpaceNode> getNodes() {
        return nodes;
    }



    public void generateNetwork(){
        int iNodes = 0;
        int random =0;
        this.nodes = new ArrayList<NoSpaceNode>();
        // We need to create numberNodes in the network.
        for (iNodes = 0; iNodes < this.numberNodes; iNodes++){
            // the id is the node index.
            // we need to compute the x and y.
            // check if we created a node.
            this.nodes.add(this.createNode(iNodes));
        }
        for (iNodes = 0; iNodes < this.numberNodes; iNodes++){
            if (iNodes+1 < this.numberNodes){
                this.nodes.get(iNodes).getNeighbors().add(iNodes+1);
            }

            for (int i = 1; i < this.numberEdges; i++) {
                do{
                random = new Random().nextInt(this.numberNodes - 1);
                } while (random == iNodes);
                this.nodes.get(iNodes).getNeighbors().add(random);
            }
        }
    }

    private NoSpaceNode createNode(int iNodes) {
        return new NoSpaceNode(iNodes);
    }

    public String toString(){
        String s = "";
        for (int i = 0; i < this.nodes.size(); i++) {
            s+=this.nodes.get(i).toString()+'\n';
        }
        return s;
    }


}
