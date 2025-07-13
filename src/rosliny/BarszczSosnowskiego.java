package rosliny;

import struktury.*;

import java.util.Objects;

public class BarszczSosnowskiego extends Roslina {

    public BarszczSosnowskiego(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 10;
        this.symbol = 'b';
        this.gatunek = Gatunek.BARSZCZSOSNOWSKIEGO;
    }

    @Override
    protected void akcja() {
        polozenie.znajdzWszystkiePolozeniaDookola(1).stream()
                .map(swiat::wezElementWPolozeniu)
                .filter(Objects::nonNull)
                .filter(Zwierze.class::isInstance)
                .filter(organizm -> organizm.getGatunek() != Gatunek.CYBEROWCA)
                .peek(organizm -> System.out.printf("Barszcz sosnowskiego zabija zwierze %s w polozeniu %s\n", organizm.getSymbol(), organizm.getPolozenie()))
                .forEach(organizm -> swiat.umieraOrganizm(organizm));
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        super.kolizja(atakujacy);
        if (this.czyMartwy() && atakujacy.getGatunek() != Gatunek.CYBEROWCA) {
            swiat.umieraOrganizm(atakujacy);
        }
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new BarszczSosnowskiego(swiat, polozenie);
    }
}
