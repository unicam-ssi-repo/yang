package yang.nodes;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpaceConnectedNode extends SpaceNode{
    private ArrayList<Neighbor> neighbors;

    public SpaceConnectedNode(int id, double x, double y, ArrayList<Neighbor> neighbors) {
        super(id, x, y);
        this.neighbors = neighbors;
    }

    public static SpaceConnectedNode toSpaceConnectedNode(String nodeString) {
        String[] list = nodeString.split(" ");
        String neighborn = list[3];

        SpaceConnectedNode nsn = new SpaceConnectedNode(
                Integer.parseInt(list[0]), Double.parseDouble(list[1]), Double.parseDouble(list[2]),new ArrayList<Neighbor>());
        return nsn;
    }

    public ArrayList<Neighbor> getNeighbors() {
        return neighbors;
    }

    public String toString(){
        String connected = "";
        for (int i = 0; i < this.neighbors.size(); i++) {
            if (i+1 != this.neighbors.size()){
                connected = connected + this.neighbors.get(i).nodeID+"("+this.neighbors.get(i).distance+")"+",";
            }else{
                connected = connected + this.neighbors.get(i).nodeID+"("+this.neighbors.get(i).distance+")";
            }
        }
        if (connected.equals("")){
            connected = ",";
        }
        return super.toString()+" "+ connected;
    }

}
