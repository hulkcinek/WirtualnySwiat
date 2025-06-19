package Zwierzeta;

import Struktury.Polozenie;
import Struktury.Swiat;
import Struktury.Zwierze;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 9;
        this.inicjatywa = 5;
        this.symbol = 'W';
    }

    @Override
    protected Zwierze stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Wilk(swiat, polozenie);
    }
}
