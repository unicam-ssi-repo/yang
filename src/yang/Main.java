package yang;

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
    public static void main(String[] args) {
        if (args.length != 6){
            throw new IllegalArgumentException("Requires all parameters.");
        }
        long   numberOfNodes = Integer.parseInt(args[1]);
        double radius = Double.parseDouble(args[2]);
        double density = Double.parseDouble(args[3]);
        int masterX = Integer.parseInt(args[4]);
        int masterY = Integer.parseInt(args[5]);
        NetworkGenerator ng = new NetworkGenerator(numberOfNodes,radius,density,masterX,masterY);
        ng.generateNetwork();
        NetworkStream.printNetwork(ng);
    }
}
