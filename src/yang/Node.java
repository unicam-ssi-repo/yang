package yang;

import java.util.ArrayList;

public class Node {
    private long x;
    private long y;
    private long id;

    public Node(long x, long y, long id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getId() {
        return id;
    }

    public int getNeighborNumber(ArrayList<Node> list, double radius) {
        int in; // Index node.
        int in_ok = 0;
        for ( in = 0; in < list.size();in++){
            if (this.isNeighborn(list.get(in),radius)){
                in_ok++;
            }
        }
        return in_ok;
    }

    public boolean isNeighborn(Node node, double radius) {
        long x2 = node.getX();
        long y2 = node.getY();

        long eq = (this.x*this.x+x2*x2-2*this.x*x2) + (this.y*this.y+y2*y2-2*this.y*y2);

        if (eq <= radius*radius){
            return true;
        }else{
            return false;
        }
    }
    public boolean equalsPosition(long x2,long y2){
        return x==x2 && y==y2;
    }

    public String toString(){
        return this.y+" "+this.x+" "+this.id;
    }
}
