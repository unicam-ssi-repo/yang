package yang.generators.twod;

import yang.nodes.SpaceNode;

import java.util.Random;

public class StupidNetworkSizeGenerator extends NetworkGenerator2DCore {


    private final int network_size;

    public StupidNetworkSizeGenerator(int n, int size) {
        super(n);
        this.network_size = size;
        this.createNodes(n);
    }

    @Override
    protected void createNodes(int n) {
        Random r =new Random();
        for (int ni = 0; ni < n; ni++) {
            int x = r.nextInt(this.network_size);
            int y = r.nextInt(this.network_size);
            this.nodes.add(new SpaceNode(ni,x,y));
        }
    }


}
