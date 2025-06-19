package Zwierzeta;

import Struktury.Polozenie;
import Struktury.Swiat;
import Struktury.Zwierze;

public class Antylopa extends Zwierze {
    public Antylopa(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 4;
        this.inicjatywa = 4;
        this.symbol = 'A';
        this.zasiegRuchu = 2;
    }



    @Override
    protected Zwierze stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Wilk(swiat, polozenie);
    }
}
