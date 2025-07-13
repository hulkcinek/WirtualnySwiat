package zwierzeta;

import struktury.Gatunek;
import struktury.Polozenie;
import struktury.Swiat;
import struktury.Zwierze;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 9;
        this.inicjatywa = 5;
        this.symbol = 'W';
        this.gatunek = Gatunek.WILK;
    }

    @Override
    protected Zwierze stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Wilk(swiat, polozenie);
    }
}
