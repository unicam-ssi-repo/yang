package yang.generators.twod.interconnected;

import yang.generators.NetworkGeneratorCore;
import yang.nodes.Node;
import yang.simulation.network.SimulationNetwork;
import yang.simulation.network.SimulationNetworkWithDistance;

import java.util.ArrayList;

public class NetworkGeneratorInterconnected2DInstance extends NetworkGeneratorCore {
    public NetworkGeneratorInterconnected2DInstance(ArrayList<Node> nodes){
        super(nodes.size());
        this.setNodes(nodes);

    }

    @Override
    protected void createNodes(int n) {

    }

    public boolean isInterconnected() {
        return new SimulationNetworkWithDistance(this).isInterconnected();
    }
}
