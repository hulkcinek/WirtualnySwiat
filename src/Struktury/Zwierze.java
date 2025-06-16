package Struktury;

public abstract class Zwierze extends Organizm{

    protected int zasiegRuchu = 1;

    protected Zwierze(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
    }


    protected void akcja(){
        Przesuniecie wylosowane = Przesuniecie.losujPrzesuniecie();
        wylosowane.pomnozWektor(zasiegRuchu);
        //sprawdzenie, czy nastąpi kolizja
        this.polozenie.przesun(wylosowane);
        swiat.zaktualizujPlansze();
    }

    @Override
    protected void kolizja(Organizm organizm) {
        if (organizm.getClass() == this.getClass()){
            //rozmnóż
        }else {
            super.kolizja(organizm);
        }
    }
}
