package yang.generators.twod;

import yang.nodes.SpaceNode;

import java.util.Random;

public class RadiusInterconnectedNetworkGenerator extends NetworkGenerator2DCore {
    private int radius;
    private int density;

    public RadiusInterconnectedNetworkGenerator(int n, int radius,int density){
        super(n);
        this.radius = radius;
        this.density = density;
        this.createNodes(n);
    }
    @Override
    protected void createNodes(int n) {
        Random r =new Random();
        double x = r.nextInt(32000);
        double y = r.nextInt(32000);
        int sparse = 1 - this.density;

        while (this.nodes.size()!=n) {
            this.nodes.add(new SpaceNode(this.nodes.size(),x,y));
            x = x + this.generateX(radius*sparse);
            y = y + this.generateY(radius*sparse);
        }
    }

    private double generateX(int radius){
        double a = Math.random()*(2 * Math.PI);
        double r = radius * Math.sqrt(Math.random());

// If you need it in Cartesian coordinates
        double x = r * Math.cos(a);
        double y = r * Math.sin(a);
        return x;
    }

    private double generateY(int radius){
        double a = Math.random()*(2 * Math.PI);
        double r = radius * Math.sqrt(Math.random());

// If you need it in Cartesian coordinates
        double x = r * Math.cos(a);
        double y = r * Math.sin(a);
        return y;
    }


}
