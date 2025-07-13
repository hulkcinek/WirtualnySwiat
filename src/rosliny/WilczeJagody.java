package rosliny;

import struktury.*;

public class WilczeJagody extends Roslina {

    public WilczeJagody(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 99;
        this.symbol = 'w';
        this.gatunek = Gatunek.WILCZEJAGODY;
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        super.kolizja(atakujacy);
        if (this.czyMartwy() && atakujacy.getGatunek() != Gatunek.CYBEROWCA)
                swiat.umieraOrganizm(atakujacy);
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new WilczeJagody(swiat, polozenie);
    }
}
