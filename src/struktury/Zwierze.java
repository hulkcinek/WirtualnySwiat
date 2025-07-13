package struktury;

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

    public void ruch(List<Polozenie> dostepnePolozenia) {
        if (dostepnePolozenia.isEmpty()){
            System.out.printf("%s nie znalazl zadnego mozliwego ruchu do wykonania z polozenia %s\n", symbol, polozenie);
            return;
        }
        Polozenie wylosowanePolozenie = dostepnePolozenia.get(random.nextInt(dostepnePolozenia.size()));
        Organizm zawartoscWylosowanegoPolozenia = swiat.wezElementWPolozeniu(wylosowanePolozenie);

        System.out.printf("Ruch %s z polozenia %s na polozenie %s, gdzie znajduje sie element %s\n",
                this.symbol, polozenie, wylosowanePolozenie,
                zawartoscWylosowanegoPolozenia == null ? "." : zawartoscWylosowanegoPolozenia.symbol);
        if (zawartoscWylosowanegoPolozenia != null) {
            zawartoscWylosowanegoPolozenia.kolizja(this);
            if (swiat.wezElementWPolozeniu(wylosowanePolozenie) == null) this.polozenie = wylosowanePolozenie;
        } else {
            this.polozenie = wylosowanePolozenie;
        }
        swiat.zaktualizujPlansze();
        if (Swiat.DEBUG) swiat.rysujSwiat();
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        if (atakujacy.gatunek == this.gatunek){
            rozmnazanie(atakujacy);
        }else {
            super.kolizja(atakujacy);
        }
    }

    private void rozmnazanie(Organizm atakujacy) {
        System.out.printf("Rozmnazanie %s z %s\n", atakujacy.symbol, this.symbol);

        List<Polozenie> mozliweMiejscaDlaDziecka = new ArrayList<>();
        mozliweMiejscaDlaDziecka.addAll(this.polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat));
        mozliweMiejscaDlaDziecka.addAll(atakujacy.polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat));
        mozliweMiejscaDlaDziecka = mozliweMiejscaDlaDziecka.stream().distinct().toList();

        if (mozliweMiejscaDlaDziecka.isEmpty()){
            System.out.println("Nie znaleziono miejsca dla dziecka");
            return;
        }

        Polozenie wylosowanePolozenieDlaDziecka = mozliweMiejscaDlaDziecka.get(random.nextInt(mozliweMiejscaDlaDziecka.size()));
        Organizm dziecko = this.stworzDziecko(swiat, wylosowanePolozenieDlaDziecka);
        swiat.rodziSieOrganizm(dziecko);
    }
}
