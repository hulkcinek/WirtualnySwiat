package Zwierzeta;

import Struktury.Polozenie;
import Struktury.Swiat;
import Struktury.Zwierze;

public class Owca extends Zwierze {
    public Owca(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 4;
        this.inicjatywa = 4;
        this.symbol = 'O';
    }

    @Override
    protected Zwierze stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Owca(swiat, polozenie);
    }
}
