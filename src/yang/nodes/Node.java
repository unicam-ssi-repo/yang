package yang.nodes;

import java.util.ArrayList;

public class Node {


    private int id;

    public int getId() {
        return id;
    }

    ;
    public Node(int id) {
        this.id = id;
    }

    public String toString(){
        String result = ""+this.id+",";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node){
            Node element = (Node) obj;
            if ( element != null && this.id == element.getId()){
                return true;
            }
        }
        return false;
    }
}
