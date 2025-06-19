package Struktury;


public abstract class Organizm {
    protected int id;
    protected int sila;
    protected int inicjatywa;
    protected Polozenie polozenie;
    protected Swiat swiat;
    protected char symbol;
    protected int wiek = 0;

    protected Organizm(Swiat swiat, Polozenie polozenie) {
        this.swiat = swiat;
        this.polozenie = polozenie;
        swiat.dodajOrganizmNaListe(this);
    }

    protected abstract void akcja();

    protected void kolizja(Organizm atakowany){
        if (this.sila >= atakowany.getSila()){
            swiat.umieraOrganizm(atakowany);
        } else {
            swiat.umieraOrganizm(this);
        }
    }

    protected void rysowanie(){
        System.out.print(symbol + " ");
    }

    public int getSila() {
        return sila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public Swiat getSwiat() {
        return swiat;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getWiek() {
        return wiek;
    }

    public void zwiekszWiek() {
        this.wiek++;
    }
}

