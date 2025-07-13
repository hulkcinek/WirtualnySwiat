package zwierzeta;

import struktury.Gatunek;
import struktury.Polozenie;
import struktury.Swiat;
import struktury.Zwierze;

public class Owca extends Zwierze {
    public Owca(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 4;
        this.inicjatywa = 4;
        this.symbol = 'O';
        this.gatunek = Gatunek.OWCA;
    }

    @Override
    protected Zwierze stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Owca(swiat, polozenie);
    }
}
