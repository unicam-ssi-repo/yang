package yang.generators.zerod;

import yang.generators.NetworkGeneratorCore;
import yang.nodes.NoSpaceNode;

public abstract class NetworkGeneratorNoSpaceCore extends NetworkGeneratorCore {
    protected  int number_edges;
    public NetworkGeneratorNoSpaceCore(int n,int e){
        super(n);
        this.number_edges = e;
    }

    @Override
    protected void createNodes(int n) {
        for (int i = 0; i < n; i++) {
            NoSpaceNode node = new NoSpaceNode(i);
            this.nodes.add(node);
        }
    }

    protected abstract void createEdges(int e);


}
