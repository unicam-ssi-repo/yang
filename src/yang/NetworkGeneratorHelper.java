package yang;

import yang.generators.twod.RadiusClusterNetworkGenerator;
import yang.generators.twod.interconnected.NetworkGeneratorInterconnected2DInstance;
import yang.simulation.network.SimulationNetworkWithDistance;

public class NetworkGeneratorHelper {

    public static SimulationNetworkWithDistance generateInterconnectedRadiusNetwork(int maxAttempt){
        boolean  found = false;
        int attempt;
        attempt = 0;
        NetworkGeneratorInterconnected2DInstance space;
        do {
            RadiusClusterNetworkGenerator ng = new RadiusClusterNetworkGenerator(n, 10000, 0, 0, 0);
            //System.out.println(ng);
            RadiusClusterNetworkInstance ngi = new RadiusClusterNetworkInstance(ng);
            // radius = ngi.decidMinimumeNodeRadiusForInterconnectedNetwork(1000, 2000);
            space = ng.toInterConnected2D(1500);
            if (space.isInterconnected()){ found = true; break; }
            attempt++;
        }while(attempt<maxAttempt);
        if (found == false){
            return null;
        }else{
            return new SimulationNetworkWithDistance(space);
        }
    }

}
