package rosliny;

import struktury.*;

public class Trawa extends Roslina {

    public Trawa(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 0;
        this.symbol = 't';
        this.gatunek = Gatunek.TRAWA;
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Trawa(swiat, polozenie);
    }
}
