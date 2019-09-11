package yang.helpers;

import yang.generators.zerod.NetworkGeneratorNoSpaceInstance;
import yang.generators.twod.RadiusClusterNetworkGenerator;

public class RadiusClusterNetworkInstance {

    private final RadiusClusterNetworkGenerator ng;

    public RadiusClusterNetworkInstance(RadiusClusterNetworkGenerator ng) {
        this.ng = ng;
    }

    public double decidMinimumeNodeRadiusForInterconnectedNetwork(int from,int to){
        NetworkGeneratorNoSpaceInstance nospace;
        for (int i = from; i < to; i++) {
            nospace = ng.toInterConnected0D(i);
            if (nospace.isInterconnected()){ return i;}
        }
        return -1;
    }
    public double decidMaxeNodeRadiusForInterconnectedNetwork(int from,int to){
        NetworkGeneratorNoSpaceInstance nospace;
        int answer  = 0;
        for (int i = to; i > from; i--) {
            nospace = ng.toInterConnected0D(i);
            if (!nospace.isInterconnected()){ return answer;}else{
                answer = i;
            }
        }
        throw new IllegalArgumentException();
    }
}

