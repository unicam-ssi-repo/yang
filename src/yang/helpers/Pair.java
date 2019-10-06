package yang.helpers;

import yang.nodes.Node;
import yang.nodes.SpaceConnectedNode;

import java.util.Objects;

public class Pair {
    private final SpaceConnectedNode from;
    private final SpaceConnectedNode to;
    private final double distance;

    public Pair(SpaceConnectedNode from, SpaceConnectedNode to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public SpaceConnectedNode getFrom() {
        return from;
    }

    public SpaceConnectedNode getTo() {
        return to;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return (getFrom() == pair.getFrom() || getFrom() == pair.getTo())&&
                (getTo() == pair.getTo() || getTo() == pair.getFrom()) &&
                Double.compare(pair.getDistance(), getDistance()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getDistance());
    }
}
