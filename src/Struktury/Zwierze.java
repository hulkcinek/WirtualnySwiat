package Struktury;

public abstract class Zwierze extends Organizm{

    protected int zasiegRuchu = 1;

    protected Zwierze(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
    }


    protected void akcja(){
        Przesuniecie wylosowane = Przesuniecie.losujPrzesuniecie();
        wylosowane.pomnozWektor(zasiegRuchu);

        Polozenie sprawdzanePolozenie = this.polozenie.kopiuj();
        sprawdzanePolozenie.przesun(wylosowane);
        System.out.printf("Sprawdzanie ruchu %s, z polozenia %s o wektor %s, czyli na polozenie %s, gdzie znajduje sie element %s\n", this.symbol, polozenie, wylosowane, sprawdzanePolozenie, swiat.wezElementZPlanszyWPolozeniu(sprawdzanePolozenie) == null ? "." : swiat.wezElementZPlanszyWPolozeniu(sprawdzanePolozenie).symbol);
        if (swiat.wezElementZPlanszyWPolozeniu(sprawdzanePolozenie) != null){
            kolizja(swiat.wezElementZPlanszyWPolozeniu(sprawdzanePolozenie));
        }else {
            this.polozenie.przesun(wylosowane);
            swiat.zaktualizujPlansze();
        }
        swiat.rysujSwiat();
    }

    @Override
    protected void kolizja(Organizm atakowany) {
        if (atakowany.getClass() == this.getClass()){
            System.out.printf("Rozmnazanie %s z %s\n", atakowany.symbol, this.symbol);
        }else {
            System.out.printf("Zaatakowany %s przez %s na pozycji %s\n", atakowany.symbol, this.symbol, this.polozenie);
            super.kolizja(atakowany);
        }
    }
}
