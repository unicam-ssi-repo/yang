package yang.helpers;

import yang.generators.twod.RadiusClusterNetworkGenerator;
import yang.generators.twod.interconnected.NetworkGeneratorInterconnected2DInstance;
import yang.nodes.YangNetwork;

public class NetworkGeneratorHelper {

    public static YangNetwork generateInterconnectedRadiusNetwork(int numberOfNodes, int NetworkRange, int minNodeDistance, int maxNodeDistance, int masterX, int masterY, int maxAttempt){
        boolean  found = false;
        int attempt;
        attempt = 0;
        NetworkGeneratorInterconnected2DInstance neighbornSpace;
        RadiusClusterNetworkGenerator networkSpace;
        do {
            networkSpace = new RadiusClusterNetworkGenerator(numberOfNodes, NetworkRange, 0, masterX, masterY);
            //System.out.println(ng);
            RadiusClusterNetworkInstance ngi = new RadiusClusterNetworkInstance(networkSpace);
            // radius = ngi.decidMinimumeNodeRadiusForInterconnectedNetwork(1000, 2000);
            neighbornSpace = networkSpace.toInterConnected2D(minNodeDistance,maxNodeDistance);
            if (neighbornSpace != null) {
                if (neighbornSpace.isInterconnected()){ found = true; break; }
            }

            attempt++;
        }while(maxAttempt == -1 || attempt<maxAttempt);

        if (!found){
            return null;
        }else{
            return new YangNetwork(
                    networkSpace,
                    neighbornSpace
            );
            //return new SimulationNetworkWithDistance(neighbornSpace);
        }
    }

}
