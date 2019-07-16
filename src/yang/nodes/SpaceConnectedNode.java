package yang.nodes;

import java.util.ArrayList;

public class SpaceConnectedNode extends SpaceNode{
    private ArrayList<Neighbor> neighbors;

    public SpaceConnectedNode(int id, double x, double y, ArrayList<Neighbor> neighbors) {
        super(id, x, y);
        this.neighbors = neighbors;
    }

    public ArrayList<Neighbor> getNeighbors() {
        return neighbors;
    }
}
