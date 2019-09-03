package yang;

import yang.generators.twod.RadiusClusterNetworkGenerator;
import yang.generators.twod.interconnected.NetworkGeneratorInterconnected2DInstance;
import yang.simulation.network.SimulationNetworkWithDistance;

public class NetworkGeneratorHelper {

    public static SimulationNetworkWithDistance generateInterconnectedRadiusNetwork(int numberOfNodes, int NetworkRange,int minNodeDistance,int maxNodeDistance, int masterX, int masterY, int maxAttempt){
        boolean  found = false;
        int attempt;
        attempt = 0;
        NetworkGeneratorInterconnected2DInstance space;

        do {
            RadiusClusterNetworkGenerator ng = new RadiusClusterNetworkGenerator(numberOfNodes, NetworkRange, 0, masterX, masterY);
            //System.out.println(ng);
            RadiusClusterNetworkInstance ngi = new RadiusClusterNetworkInstance(ng);
            // radius = ngi.decidMinimumeNodeRadiusForInterconnectedNetwork(1000, 2000);
            space = ng.toInterConnected2D(minNodeDistance,maxNodeDistance);
            if (space != null) {
                if (space.isInterconnected()){ found = true; break; }
            }
            attempt++;
        }while(maxAttempt == -1 || attempt<maxAttempt);

        if (!found){
            return null;
        }else{
            return new SimulationNetworkWithDistance(space);
        }
    }

}
