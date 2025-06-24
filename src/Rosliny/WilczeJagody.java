package Rosliny;

import Struktury.Organizm;
import Struktury.Polozenie;
import Struktury.Roslina;
import Struktury.Swiat;
import Zwierzeta.CyberOwca;

public class WilczeJagody extends Roslina {

    public WilczeJagody(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 99;
        this.symbol = 'j';
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
        return new WilczeJagody(swiat, polozenie);
    }
}
