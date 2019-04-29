package yang;

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
        if (args.length != 5){
            throw new IllegalArgumentException("Requires all parameters.");
        }
        long   numberOfNodes = Integer.parseInt(args[0]);
        double radius = Double.parseDouble(args[1]);
        double density = Double.parseDouble(args[2]);
        int masterX = Integer.parseInt(args[3]);
        int masterY = Integer.parseInt(args[4]);
        NetworkGenerator ng = new NetworkGenerator(numberOfNodes,radius,1,masterX,masterY,false);
        ng.generateNetwork();
        NetworkStream.saveNetwork(ng,"test1.txt");
    }
}
