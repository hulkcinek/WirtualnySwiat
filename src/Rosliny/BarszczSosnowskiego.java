package Rosliny;

import Struktury.*;
import Zwierzeta.CyberOwca;

import java.util.Objects;

public class BarszczSosnowskiego extends Roslina {

    public BarszczSosnowskiego(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 10;
        this.symbol = 'b';
    }

    @Override
    protected void akcja() {
        polozenie.znajdzWszystkiePolozeniaDookola(1).stream()
                .map(swiat::wezElementWPolozeniu)
                .filter(Objects::nonNull)
                .filter(Organizm::czyZwierze)
                .filter(organizm -> organizm.getClass() != CyberOwca.class)
                .peek(organizm -> System.out.printf("Barszcz sosnowskiego zabija zwierze %s w polozeniu %s", organizm.getSymbol(), organizm.getPolozenie()))
                .forEach(organizm -> swiat.umieraOrganizm(organizm));
    }

    @Override
    protected void kolizja(Organizm atakujacy) {
        super.kolizja(atakujacy);
        if (!swiat.organizmy.contains(this))
            if (atakujacy.getClass() != CyberOwca.class)
                swiat.umieraOrganizm(atakujacy);
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new BarszczSosnowskiego(swiat, polozenie);
    }
}
