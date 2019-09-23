package yang.generators.twod;

import yang.generators.twod.RadiusClusterNetworkGenerator;
import yang.nodes.SpaceNode;

import java.util.Random;
import java.util.SplittableRandom;

public class RadiusClusterNetworkGeneratorWithDistance  extends NetworkGenerator2DCore {
    private final double minDistance;
    private final double maxDistance;
    private final int radius;
    private final int basex;
    private final int basey;
    private Random random = new Random();

    public RadiusClusterNetworkGeneratorWithDistance(int n, int radius, double minDistance, double maxDistance){
        super(n);
        this.radius = radius;
        Random r =new Random();
        this.basex = r.nextInt(32000);
        this.basey = r.nextInt(32000);
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.createNodes(n);
    }
    public RadiusClusterNetworkGeneratorWithDistance(int n, int radius,int basex,int basey , double minDistance, double maxDistance){
        super(n);
        this.radius = radius;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.basex = basex;
        this.basey = basey;
        this.createNodes(n);
    }
    @Override
    protected void createNodes(int n) {
        double x,y;
        // add Master node.
        this.nodes.add(new SpaceNode(this.nodes.size(),this.basex,this.basey));

        while (this.nodes.size()!=n) {
            x = this.basex + this.generateX(this.radius);
            y = this.basey + this.generateY(this.radius);
            SpaceNode node = new SpaceNode(this.nodes.size(),x,y);

            if (this.checkDistance(node)){ // cannot stay over master.
                this.nodes.add(new SpaceNode(this.nodes.size(),x,y));
            }
        }
    }
    private boolean checkDistance(SpaceNode node) {
        for (int i = 0; i <  this.nodes.size(); i++) {
            double distance = node.getDistance((SpaceNode) this.nodes.get(i));
            if (distance < this.minDistance){
                return false;
            }
            // All the nodes cannot be near. So at least one.
            if (distance <= this.maxDistance){
                return true;
            }
        }
        // if all the node are beyond the range.
        return false;
    }
    private double generateX(double radius){

        double a = this.random.nextDouble()*(2 * Math.PI);
        double r = radius * Math.sqrt(this.random.nextDouble());

// If you need it in Cartesian coordinates
        double x = (r * Math.cos(a));
        return x;
    }

    private double generateY(double radius){
        double a = this.random.nextDouble()*(2 * Math.PI);
        double r = radius * Math.sqrt(this.random.nextDouble());

// If you need it in Cartesian coordinates
        double y = (r * Math.sin(a));
        return y;
    }

    public int getBasex() {
        return basex;
    }

    public int getBasey() {
        return basey;
    }

}
