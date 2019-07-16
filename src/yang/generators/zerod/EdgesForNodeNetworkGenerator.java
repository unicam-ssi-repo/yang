package yang.generators.zerod;

import yang.nodes.NoSpaceNode;

import java.util.Random;

public class EdgesForNodeNetworkGenerator extends NetworkGeneratorNoSpaceCore {
    public EdgesForNodeNetworkGenerator(int n, int e) {
        super(n, e); this.createEdges(e);
    }


    @Override
    protected void createEdges(int e) {
        for (int n = 0; n < this.nodes_count; n++) {
            NoSpaceNode no = (NoSpaceNode) this.nodes.get(n);
            for (int ec = 0; ec < e; ec++) {
                int n2 = (new Random()).nextInt(this.nodes_count);
                no.getNeighbors().add(n2);
            }
        }
    }
}
