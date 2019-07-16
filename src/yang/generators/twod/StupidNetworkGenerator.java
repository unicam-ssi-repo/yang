package yang.generators.twod;

import yang.nodes.SpaceNode;

import java.util.Random;

public class StupidNetworkGenerator extends NetworkGenerator2DCore {


    public StupidNetworkGenerator(int n) {
        super(n); this.createNodes(n);
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


}
