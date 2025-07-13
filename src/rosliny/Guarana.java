package rosliny;

import struktury.*;

public class Guarana extends Roslina {

    public Guarana(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 0;
        this.symbol = 'g';
        this.gatunek = Gatunek.GUARANA;
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        super.kolizja(atakujacy);
        atakujacy.zwiekszSile(3);
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Guarana(swiat, polozenie);
    }
}
