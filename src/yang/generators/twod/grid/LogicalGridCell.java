package yang.generators.twod.grid;

import yang.nodes.SpaceNode;

import java.util.Objects;

public class LogicalGridCell {
    public long x;
    public long y;

    public LogicalGridCell(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogicalGridCell)) return false;
        LogicalGridCell that = (LogicalGridCell) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
