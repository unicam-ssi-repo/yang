package yang.generators.twod;

public class GridClusterNetworkGenerator extends NetworkGenerator2DCore {


    private int layers;
    private int squareSize = 50;

    public GridClusterNetworkGenerator(int n) {
        super(n);
        this.layers = this.getNumberOfLayers(n-1);

        this.createNodes(n);
    }



    @Override
    protected void createNodes(int n) {
        this.createLayers(this.layers,this.squareSize);
    }
    public int getNumberOfLayers(int n){
        int nmax = n;
        int size = 1;
        for (int i = 0; i < n; i++) {
            if (nmax < size*4){
                return i;
            }else{
                nmax-=(size*4);
            }
            size+=2;
        }
        return 0;
    }

    private void createLayers(int layers,int squareUnit) {
        /* TODO
        *   Create layer. */
    }
}
