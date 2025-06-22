package Zwierzeta;

import Struktury.Organizm;
import Struktury.Polozenie;
import Struktury.Swiat;
import Struktury.Zwierze;

import java.util.List;

public class Lis extends Zwierze {

    public Lis(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 3;
        this.inicjatywa = 7;
        this.symbol = 'L';
    }

    @Override
    protected void akcja() {
        List<Polozenie> dostepnePolozenia = polozenie.znajdzWszystkiePolozeniaDookola(zasiegRuchu);
        dostepnePolozenia = dostepnePolozenia.stream()
                .filter(p -> swiat.czyPolozenieWolne(p) || swiat.wezElementWPolozeniu(p).getSila() <= this.sila)
                .toList();
        super.ruch(dostepnePolozenia);
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new Lis(swiat, polozenie);
    }
}
