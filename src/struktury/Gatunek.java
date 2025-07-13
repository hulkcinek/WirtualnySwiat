package struktury;

import rosliny.*;
import zwierzeta.*;

import java.util.Random;
import java.util.function.BiFunction;

public enum Gatunek {
    WILK(Wilk::new, Wilk.class),
    OWCA(Owca::new, Owca.class),
    LIS(Lis::new, Lis.class),
    ZOLW(Zolw::new, Zolw.class),
    ANTYLOPA(Antylopa::new, Antylopa.class),
    CYBEROWCA(CyberOwca::new, CyberOwca.class),
    TRAWA(Trawa::new, Trawa.class),
    MLECZ(Mlecz::new, Mlecz.class),
    GUARANA(Guarana::new, Guarana.class),
    WILCZEJAGODY(WilczeJagody::new, WilczeJagody.class),
    BARSZCZSOSNOWSKIEGO(BarszczSosnowskiego::new, BarszczSosnowskiego.class);

    final BiFunction<Swiat, Polozenie, Organizm> konstruktor;
    final Class<? extends Organizm> klasa;
    static final Random random = new Random();

    Gatunek(BiFunction<Swiat, Polozenie, Organizm> konstruktor, Class<? extends Organizm> klasa) {
            this.konstruktor = konstruktor;
            this.klasa = klasa;
        }

    public static Gatunek losujOrganizm(){
        Gatunek[] gatunek = Gatunek.values();
        return gatunek[random.nextInt(gatunek.length)];
    }

    public Class<? extends Organizm> getKlasa() {
        return klasa;
    }

    public BiFunction<Swiat, Polozenie, Organizm> getKonstruktor() {
        return konstruktor;
    }
}
