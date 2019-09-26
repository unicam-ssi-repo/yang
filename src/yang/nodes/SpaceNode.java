package yang.nodes;

import java.awt.*;
import java.util.ArrayList;

public class SpaceNode extends Node {

    private double x;
    private double  y;

    public SpaceNode(int id, double x, double y) {
        super(id);
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getNeighborNumber(ArrayList<SpaceNode> list, double radius) {
        int in; // Index node.
        int in_ok = 0;
        for ( in = 0; in < list.size();in++){
            if (this.isNeighborn(list.get(in),radius)){
                in_ok++;
            }
        }
        return in_ok;
    }

    public boolean isNeighborn(SpaceNode node, double radius) {
        double x2 = node.getX();
        double y2 = node.getY();

        double eq = (this.x*this.x+x2*x2-2*this.x*x2) + (this.y*this.y+y2*y2-2*this.y*y2);

        if (eq <= radius*radius){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        return super.toString()+" "+this.x+" "+this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SpaceNode){
            SpaceNode element = (SpaceNode) obj;
            if(element != null  && this.getX() == element.getX() && this.getY() == element.getY() ){
                return true;
            }
        }
        return false;
    }

    public double getDistance(SpaceNode spaceNode) {
        return this._getDistanceXY(this.x,this.y,spaceNode.getX(),spaceNode.getY());
    }

    public double _getDistanceXY( double x1, double y1, double x2, double y2) {
        double firstTerm = (x2-x1);
        double secondTerm = (y2-y1);
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public SpaceConnectedNode toSpaceInterConnectedNode() {
        return new SpaceConnectedNode(this.getId(),this.getX(),this.getY(),new ArrayList<>());
    }

    public void draw(Graphics2D g) {
        if (this.getId() == 0){
            g.setColor(Color.red);
        } else{
            g.setColor(Color.orange);
        }

        int nodeRadius = 10;
        g.fillOval((int) this.getX() - nodeRadius, (int) this.getY() - nodeRadius,
                nodeRadius * 2, nodeRadius * 2);

        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.setColor(Color.black);
        String s = String.valueOf(this.getId());
        FontMetrics fm = g.getFontMetrics();

        double textWidth = fm.getStringBounds(s, g).getWidth();
        g.setColor(Color.gray);
        g.drawString(s, (int) ((int) this.getX() - textWidth/2), ((int) this.getY() + fm.getMaxAscent() / 2));
    }

    public void drawBackground(Graphics2D g, int nodeRadius) {
        g.setColor(Color.gray);
        float alpha = 5 * 0.1f;
        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g.setComposite(alcom);
        g.fillOval((int) this.getX() - nodeRadius, (int)this.getY() - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
    }
}
