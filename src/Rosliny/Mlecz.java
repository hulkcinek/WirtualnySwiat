package Rosliny;

import Struktury.Organizm;
import Struktury.Polozenie;
import Struktury.Roslina;
import Struktury.Swiat;

public class Mlecz extends Roslina {

    public Mlecz(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 0;
        this.symbol = 'm';
    }

    @Override
    protected void akcja() {
        for (int i = 0; i < 3; i++) {
            super.akcja();
        }
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Mlecz(swiat, polozenie);
    }

}