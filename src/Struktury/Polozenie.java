package Struktury;

import java.util.Objects;
import java.util.Random;

public class Polozenie {
    private int x;
    private int y;

    public Polozenie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Polozenie losujPolozenie(Swiat swiat) {
        Random random = new Random();
        Polozenie polozenie = new Polozenie(-1, -1);
        do {
            polozenie.setY(random.nextInt(Swiat.getWYSOKOSC()));
            polozenie.setX(random.nextInt(Swiat.getSZEROKOSC()));
        } while (swiat.czyPolozenieWolne(polozenie));
        return polozenie;
    }

    //zakładamy, że jeśli wylosowane pole do przesunięcia jest poza planszą to zwierze się nie poruszy
    public void przesun(Przesuniecie przesuniecie){
        if (!Swiat.czyPoleNaPlanszy(this, przesuniecie)) return;

        this.y += przesuniecie.getDy();
        this.x += przesuniecie.getDx();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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