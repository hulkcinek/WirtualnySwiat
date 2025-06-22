package Struktury;

import java.util.*;

public abstract class Zwierze extends Organizm{

    protected int zasiegRuchu = 1;

    protected Zwierze(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
    }


    protected void akcja(){
        List<Polozenie> dostepnePolozenia = polozenie.znajdzWszystkiePolozeniaDookola(zasiegRuchu);
        ruch(dostepnePolozenia);
    }

    protected void ruch(List<Polozenie> dostepnePolozenia) {
        if (dostepnePolozenia.isEmpty()){
            System.out.printf("%s nie znalazl zadnego mozliwego ruchu do wykonania z polozenia %s\n", symbol, polozenie);
            return;
        }
        Random random = new Random();
        Polozenie wylosowanePolozenie = dostepnePolozenia.get(random.nextInt(dostepnePolozenia.size()));
        Organizm zawartoscWylosowanegoPolozenia = swiat.wezElementWPolozeniu(wylosowanePolozenie);

        System.out.printf("Ruch %s z polozenia %s na polozenie %s, gdzie znajduje sie element %s\n",
                this.symbol, polozenie, wylosowanePolozenie,
                zawartoscWylosowanegoPolozenia == null ? "." : zawartoscWylosowanegoPolozenia.symbol);
        if (zawartoscWylosowanegoPolozenia != null) {
            kolizja(zawartoscWylosowanegoPolozenia);
            if (swiat.wezElementWPolozeniu(wylosowanePolozenie) == null)
                this.polozenie = wylosowanePolozenie;
        } else {
            this.polozenie = wylosowanePolozenie;
        }
        swiat.zaktualizujPlansze();
        if (swiat.DEBUG) swiat.rysujSwiat();
    }

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
            Organizm dziecko = this.stworzDziecko(swiat, wylosowanePolozenieDlaDziecka);
            swiat.rodziSieOrganizm(dziecko);
        }else {
            System.out.printf("Zaatakowany %s przez %s na pozycji %s\n", atakowany.symbol, this.symbol, this.polozenie);
            super.kolizja(atakowany);
        }
    }

    @Override
    public boolean czyZwierze() {
        return true;
    }
}
