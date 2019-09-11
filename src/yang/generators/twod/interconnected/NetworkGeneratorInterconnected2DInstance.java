package yang.generators.twod.interconnected;

import yang.generators.NetworkGeneratorCore;
import yang.nodes.Node;
import yang.nodes.SpaceConnectedNode;
import yang.simulation.network.SimulationNetwork;
import yang.simulation.network.SimulationNetworkWithDistance;

import java.util.ArrayList;

public class NetworkGeneratorInterconnected2DInstance extends NetworkGeneratorCore {
    public NetworkGeneratorInterconnected2DInstance(ArrayList<Node> nodes){
        super(nodes.size());
        this.setNodes(nodes);

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
