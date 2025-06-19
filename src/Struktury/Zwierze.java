package Struktury;

import java.lang.reflect.Constructor;
import java.util.*;

public abstract class Zwierze extends Organizm{

    protected Zwierze(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
    }

    protected abstract Zwierze stworzDziecko(Swiat swiat, Polozenie polozenie);

    protected void akcja(){
        Random random = new Random();
        List<Polozenie> dostepnePolozenia = polozenie.znajdzWszystkiePolozeniaDookola();
        Polozenie wylosowanePolozenie = dostepnePolozenia.get(random.nextInt(dostepnePolozenia.size()));
        Organizm zawartoscWylosowanegoPolozenia = swiat.wezElementZPlanszyWPolozeniu(wylosowanePolozenie);

        System.out.printf("Sprawdzanie ruchu %s, z polozenia %s na polozenie %s, gdzie znajduje sie element %s\n",
                this.symbol, polozenie, wylosowanePolozenie,
                zawartoscWylosowanegoPolozenia == null ? "." : zawartoscWylosowanegoPolozenia.symbol);
        if (zawartoscWylosowanegoPolozenia != null) {
            kolizja(zawartoscWylosowanegoPolozenia);
        } else {
            this.polozenie = wylosowanePolozenie;
        }
        swiat.zaktualizujPlansze();
        if (swiat.debug)
            swiat.rysujSwiat();
    }

    @Override
    protected void kolizja(Organizm atakowany) {
        if (atakowany.getClass() == this.getClass()){
            System.out.printf("Rozmnazanie %s z %s\n", atakowany.symbol, this.symbol);

            List<Polozenie> mozliweMiejscaDlaDziecka = new ArrayList<>();
            mozliweMiejscaDlaDziecka.addAll(this.polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat));
            mozliweMiejscaDlaDziecka.addAll(atakowany.polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat));
            mozliweMiejscaDlaDziecka = mozliweMiejscaDlaDziecka.stream().distinct().toList();

            if (mozliweMiejscaDlaDziecka.isEmpty()){
                System.out.println("Nie znaleziono miejsca dla dziecka");
                return;
            }

            Random random = new Random();

            Polozenie wylosowanePolozenieDlaDziecka = mozliweMiejscaDlaDziecka.get(random.nextInt(mozliweMiejscaDlaDziecka.size()));
            Zwierze dziecko = this.stworzDziecko(swiat, wylosowanePolozenieDlaDziecka);
            swiat.rodziSieOrganizm(dziecko);
        }else {
            System.out.printf("Zaatakowany %s przez %s na pozycji %s\n", atakowany.symbol, this.symbol, this.polozenie);
            super.kolizja(atakowany);
        }
    }
}
