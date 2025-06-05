package Zwierzeta;

import Struktury.Polozenie;
import Struktury.Swiat;
import Struktury.Zwierze;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat, Polozenie polozenie) {
        this.swiat = swiat;
        this.sila = 9;
        this.inicjatywa = 4;
        this.polozenie = polozenie;
    }
}
