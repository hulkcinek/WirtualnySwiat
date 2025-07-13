package zwierzeta;

import struktury.*;

import java.util.List;

public class Antylopa extends Zwierze {
    public Antylopa(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 4;
        this.inicjatywa = 4;
        this.symbol = 'A';
        this.zasiegRuchu = 2;
        this.gatunek = Gatunek.ANTYLOPA;
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        if (losujSzanse(1, 2)){
            List<Polozenie> wolnePolozenia = polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat);
            if (wolnePolozenia.isEmpty()) {
                System.out.printf("%s w polozeniu %s nie ma miejsca na ucieczke\n", symbol, polozenie);
                super.kolizja(atakujacy);
                return;
            }
            System.out.printf("%s w polozeniu %s udaje sie uciec\n", symbol, polozenie);
            super.ruch(wolnePolozenia);
        } else {
            super.kolizja(atakujacy);
        }
    }

    @Override
    protected Zwierze stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Antylopa(swiat, polozenie);
    }
}
