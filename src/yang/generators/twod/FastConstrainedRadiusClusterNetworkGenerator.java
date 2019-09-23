package yang.generators.twod;

import yang.nodes.SpaceNode;

import java.util.Random;

public class FastConstrainedRadiusClusterNetworkGenerator extends NetworkGenerator2DCore  {
    private int minRadius, maxRadius ;
    private double density;
    private int basex;



    private int basey;
    private Random random = new Random();
    private int networkRange;

    public FastConstrainedRadiusClusterNetworkGenerator(int n, int networkRange, int minRadius,int maxRadius){
        super(n);
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        Random r =new Random();
        this.basex = r.nextInt(32000);
        this.basey = r.nextInt(32000);
        this.networkRange = networkRange;
        this.createNodes(n);
    }
    public FastConstrainedRadiusClusterNetworkGenerator(int n, int networkRange, int nodeMinRadius,int nodeMaxRadius,int basex,int basey){
        super(n);
        this.minRadius = nodeMinRadius;
        this.maxRadius = nodeMaxRadius;
        this.networkRange = networkRange;
        this.basex = basex;
        this.basey = basey;
        this.createNodes(n);
    }
    @Override
    protected void createNodes(int n) {
        Random r =new Random();
        double x,y;
        // add Master node.
        this.nodes.add(new SpaceNode(this.nodes.size(),this.basex,this.basey));
        boolean insertFail;
        while (this.nodes.size()!=n) {
            double radius = this.minRadius + (this.maxRadius - this.minRadius)* r.nextDouble();
            SpaceNode node = (SpaceNode) this.nodes.get(r.nextInt(this.nodes.size()));

            double basex = node.getX();
            double basey = node.getY();

            x = basex + this.generateX(radius);
            y = basey + this.generateY(radius);

            if (x < 0 || x > this.networkRange*2) {
               continue;
            }
            if (y < 0 || y > this.networkRange*2) {
                continue;
            }

            SpaceNode newNode = new SpaceNode(this.nodes.size(),x,y);
            insertFail = false;

            for (int nodesIndex = 0; nodesIndex < this.nodes.size(); nodesIndex++) {
                SpaceNode ns = (SpaceNode) this.nodes.get(nodesIndex);
                double distance = ns.getDistance(newNode);
                if (distance < this.minRadius || distance > this.maxRadius ){
                    insertFail = true;
                    break;
                }
            }

            if (!insertFail){
                this.nodes.add(newNode);
            }
        }
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
