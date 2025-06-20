package Struktury;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Przesuniecie {
    public static final List<Przesuniecie> RUCHY = List.of(
            new Przesuniecie(0, 1),
            new Przesuniecie(0, -1),
            new Przesuniecie(1, 0),
            new Przesuniecie(-1, 0),
            new Przesuniecie(-1, -1),
            new Przesuniecie(-1, 1),
            new Przesuniecie(1, -1),
            new Przesuniecie(1, 1)
    );
    private int dx;
    private int dy;

    public Przesuniecie(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    //zakładamy, że przez "sąsiednie pola" rozumiemy tylko te stykające się ze sobą krawędziami (nie rogami)

    public static Przesuniecie losujPrzesuniecie(){
        Random random = new Random();
        return RUCHY.get(random.nextInt(RUCHY.size()));
    }
    public void pomnozWektor(int n){
        this.dx *= n;
        this.dy *= n;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", dx, dy);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Przesuniecie that = (Przesuniecie) o;
        return dx == that.dx && dy == that.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }
}
