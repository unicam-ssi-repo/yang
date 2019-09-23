package yang.generators.twod.grid;

import yang.nodes.SpaceNode;

import java.util.Objects;

public class GridCell {
    public double x;
    public double y;
    public SpaceNode node = null;

    public GridCell(double x, double y, SpaceNode node) {
        this.x = x;
        this.y = y;
        this.node = node;
    }

    public GridCell(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public boolean isBusy(){
        return node != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GridCell)) return false;
        GridCell gridCell = (GridCell) o;
        return Double.compare(gridCell.x, x) == 0 &&
                Double.compare(gridCell.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
