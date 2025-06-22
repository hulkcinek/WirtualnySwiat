package Zwierzeta;

import Struktury.Organizm;
import Struktury.Polozenie;
import Struktury.Swiat;
import Struktury.Zwierze;

public class Zolw extends Zwierze {

    public Zolw(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 2;
        this.inicjatywa = 1;
        this.symbol = 'Z';
    }

    @Override
    protected void akcja() {
        if (losujSzanse(1, 4)) super.akcja();
        else System.out.printf("Proba losowania dla ruchu Z w polozeniu %s zakonczona niepowodzeniem\n", polozenie);
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Zolw(swiat, polozenie);
    }
}
