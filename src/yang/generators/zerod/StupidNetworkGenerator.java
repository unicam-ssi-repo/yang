package yang.generators.zerod;

import yang.nodes.NoSpaceNode;

import java.util.Random;

public class StupidNetworkGenerator extends NetworkGeneratorNoSpaceCore {


    public StupidNetworkGenerator(int n, int e) {
        super(n, e); this.createEdges(e);
    }


    @Override
    protected void createEdges(int e) {
        for (int i = 0; i < e; i++) {
            int node1 = (new Random()).nextInt(this.nodes_count);
            int node2 = (new Random()).nextInt(this.nodes_count);
            // Add it one way
            NoSpaceNode n1 = (NoSpaceNode) this.nodes.get(node1);
            n1.getNeighbors().add(node2);
            // Add it the other way.
            NoSpaceNode n2 = (NoSpaceNode) this.nodes.get(node2);
            n2.getNeighbors().add(node1);
        }
    }
}
