package Rosliny;

import Struktury.Organizm;
import Struktury.Polozenie;
import Struktury.Roslina;
import Struktury.Swiat;

public class Guarana extends Roslina {

    public Guarana(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 0;
        this.symbol = 'g';
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
