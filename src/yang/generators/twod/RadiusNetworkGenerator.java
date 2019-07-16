package yang.generators.twod;

import yang.nodes.SpaceNode;

import java.util.Random;

public class RadiusNetworkGenerator extends NetworkGenerator2DCore {
    private int radius;

    public RadiusNetworkGenerator(int n, int radius){
        super(n);
        this.radius = radius;
        this.createNodes(n);
    }
    @Override
    protected void createNodes(int n) {
        Random r =new Random();
        for (int ni = 0; ni < n; ni++) {
            int x = r.nextInt();
            int y = r.nextInt();
            this.nodes.add(new SpaceNode(ni,x,y));
        }
    }

    /**
     * Check if @to node is inside the range of from node.
     * @param from
     * @param to
     * @return
     */
    public boolean isReachable(SpaceNode from,SpaceNode to){
        double x1 = from.getX();
        double y1 = from.getY();
        double x2 = to.getX();
        double y2 = to.getY();
        return this.isAPointInACirconference(x2,y2,x1,y1,this.radius);
    }

    public boolean isAPointInACirconference(double x, double y, double center_x,double center_y, double radius){
        return (((x - center_x)*(x - center_x)) + ((y - center_y)*(y - center_y))) < (radius*radius);
    }

    public boolean isReachable(int from,int to){
        return this.isReachable((SpaceNode) this.nodes.get(from),(SpaceNode) this.nodes.get(to));
    }
}
