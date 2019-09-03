package yang;

import yang.generators.twod.RadiusClusterNetworkGenerator;
import yang.generators.twod.interconnected.NetworkGeneratorInterconnected2DInstance;
import yang.generators.zerod.NetworkGeneratorNoSpaceInstance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    /**
     *  private long numberNodes;
     *     private double radius;
     *     private double density;
     *     private long MasterX;
     *     private long MasterY;
     *  - args[1] -> numberNodes
     *  - args[2] -> radius
     *  - args[3] -> density [0..1]
     *  - args[4] -> masterX
     *  - args[5] -> masterY
     * @param args
     */
    public static void main(String[] args) throws IOException {
        int n = 1;
        double radius;
        int attempt = 0;
        RadiusClusterNetworkGenerator ng;
        boolean found;
        while(n!=31){
            attempt = 0;
            found = true;
            do {
                ng = new RadiusClusterNetworkGenerator(n, 10000, 0, 0, 0);
                //System.out.println(ng);
                RadiusClusterNetworkInstance ngi = new RadiusClusterNetworkInstance(ng);
                // radius = ngi.decidMinimumeNodeRadiusForInterconnectedNetwork(1000, 2000);
                NetworkGeneratorInterconnected2DInstance space = ng.toInterConnected2D(100,1500);
                if (space != null) {
                    if (space.isInterconnected()) {
                        found = true;
                        break;
                    }
                }
                   attempt++;
            }while(attempt<1000);


            if (found == false){
                System.out.println("Avoid network with size: "+n);
            }else{
                radius = 1500;
                System.out.println("Network with size: "+n+" with radius:"+radius);
                Main.printSolution("examples/"+n+"_"+radius+"_"+"wifi.txt",ng.toString());
                Main.printSolution("examples/"+n+"_"+radius+"_"+".txt",ng.toInterConnected0D(radius).toString());
            }
            //System.out.println(ng.toString());
            //System.out.println(ng.toInterConnected0D(radius));
            n++;
        }

    }

    public static void printSolution(String  file, String filecontent){
        File f = new File(file);
        if(f.exists() && !f.isDirectory()) {
            return;
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(filecontent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
