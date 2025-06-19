package Rosliny;

import Struktury.Polozenie;
import Struktury.Roslina;
import Struktury.Swiat;

public class Trawa extends Roslina {

    public Trawa(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 0;
        this.symbol = 't';
    }

    @Override
    protected void akcja(){}
}
