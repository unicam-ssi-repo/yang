package yang.nodes;

import java.util.ArrayList;

public class NoSpaceNode extends Node {

    private ArrayList<Integer> neighbors;

    public ArrayList<Integer> getNeighbors() {
        return neighbors;
    }

    public NoSpaceNode(int id) {
        super(id);
        this.neighbors = new ArrayList<Integer>();
    }

    public String toString(){
        String result = super.toString();
        for (int i = 0; i < this.neighbors.size() ; i++) {
            result+= this.neighbors.get(i);
            if (i+1 < this.neighbors.size()){
                result+=",";
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SpaceNode){
            NoSpaceNode element = (NoSpaceNode) obj;
            if(element != null && super.equals(obj) && this.getNeighbors()== element.getNeighbors()){
                return true;
            }
        }
        return false;
    }
}
