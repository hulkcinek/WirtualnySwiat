package struktury;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Przesuniecie {
    private int dx;
    private int dy;
    private static final Random random = new Random();

    public Przesuniecie(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static List<Przesuniecie> stworzRuchy(int zasiegRuchu){
        List<Przesuniecie> ruchy = new ArrayList<>();
        for (int y = -zasiegRuchu; y <= zasiegRuchu; y++) {
            for (int x = -zasiegRuchu; x <= zasiegRuchu; x++) {
                ruchy.add(new Przesuniecie(x, y));
            }
        }
        ruchy.remove(new Przesuniecie(0, 0));
        return ruchy;
    }
    
    public static Przesuniecie losujPrzesuniecie(){
        List<Przesuniecie> ruchy = stworzRuchy(1);
        return ruchy.get(random.nextInt(ruchy.size()));
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
