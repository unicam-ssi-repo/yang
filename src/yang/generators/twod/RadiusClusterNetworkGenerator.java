package yang.generators.twod;

import yang.nodes.SpaceNode;

import java.util.Random;

public class RadiusClusterNetworkGenerator extends NetworkGenerator2DCore {
    private int radius;
    private double density;
    private int basex;



    private int basey;
    private Random random = new Random();

    public RadiusClusterNetworkGenerator(int n, int radius,double density){
        super(n);
        this.radius = radius;
        this.density = density;
        Random r =new Random();
        this.basex = r.nextInt(32000);
        this.basey = r.nextInt(32000);
        this.createNodes(n);
    }
    public RadiusClusterNetworkGenerator(int n, int radius,double density,int basex,int basey){
        super(n);
        this.radius = radius;
        this.density = density;
        this.basex = basex;
        this.basey = basey;
        this.createNodes(n);
    }
    @Override
    protected void createNodes(int n) {
        Random r =new Random();
        double x,y;
        double sparse = 1 - this.density;
        // add Master node.
        this.nodes.add(new SpaceNode(this.nodes.size(),this.basex,this.basey));

        while (this.nodes.size()!=n) {
            x = this.basex + this.generateX(this.radius*sparse);
            y = this.basey + this.generateY(this.radius*sparse);
            if (this.basex != x || this.basey != y){ // cannot stay over master.
                this.nodes.add(new SpaceNode(this.nodes.size(),x,y));
            }
        }
    }

    private double generateX(double radius){

        double a = this.random.nextDouble()*(2 * Math.PI);
        double r = radius * Math.sqrt(this.random.nextDouble());

// If you need it in Cartesian coordinates
        double x = (r * Math.cos(a));
        double y = (r * Math.sin(a));
        return x;
    }

    private double generateY(double radius){
        double a = this.random.nextDouble()*(2 * Math.PI);
        double r = radius * Math.sqrt(this.random.nextDouble());

// If you need it in Cartesian coordinates
        double x = (r * Math.cos(a));
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

