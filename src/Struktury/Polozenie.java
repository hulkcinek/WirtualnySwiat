package Struktury;

import java.util.Objects;

public class Polozenie {
    private int x;
    private int y;

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Polozenie polozenie = (Polozenie) o;
        return x == polozenie.x && y == polozenie.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}