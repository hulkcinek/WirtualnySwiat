package Struktury;

import java.util.List;
import java.util.Random;

public abstract class Roslina extends Organizm{

    protected Roslina(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.inicjatywa = 0;
    }

    protected void akcja() {
        Random random = new Random();
        List<Polozenie> dostepnePolozenia = polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat);
        if (dostepnePolozenia.isEmpty()) {
            System.out.printf("Brak dostepnych miejsc do rozprzestrzeniania sie dla %s w polozeniu %s\n", symbol, polozenie);
            return;
        }

        if (!losujSzanse(1, 6)){
            System.out.printf("Proba losowania dla rozprzestrzeniania zakonczona niepowodzeniam dla %s w polozeniu %s\n",
                    symbol, polozenie);
            return;
        }

        Polozenie wylosowanePolozenie = dostepnePolozenia.get(random.nextInt(dostepnePolozenia.size()));

        System.out.printf("Rozprzestrzenianie sie %s, z polozenia %s na polozenie %s\n",
                symbol, polozenie, wylosowanePolozenie);

        Organizm dziecko = this.stworzDziecko(swiat, wylosowanePolozenie);
        swiat.rodziSieOrganizm(dziecko);
        if (swiat.DEBUG) swiat.rysujSwiat();
    }

    @Override
    public boolean czyZwierze() {
        return false;
    }
}
