package yang.generators.zerod;

import yang.generators.NetworkGeneratorCore;
import yang.nodes.Node;
import yang.simulation.network.SimulationNetwork;

import java.util.ArrayList;

public class NetworkGeneratorNoSpaceInstance extends NetworkGeneratorCore{

    public NetworkGeneratorNoSpaceInstance(ArrayList<Node> nodes){
        super(nodes.size());
        this.setNodes(nodes);

    }

    @Override
    protected void createNodes(int n) {

    }


    public boolean isInterconnected() {
        return new SimulationNetwork(this).isInterconnected();
    }
}
