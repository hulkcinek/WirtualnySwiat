package Struktury;

public abstract class Zwierze extends Organizm{

    protected int zasiegRuchu = 1;

    protected Zwierze(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
    }


    protected void akcja(){
        //przesuwanie na losowe sasiednie pole
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
