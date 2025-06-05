package Struktury;


public abstract class Organizm {
    protected int id;
    protected int sila;
    protected int inicjatywa;
    protected Polozenie polozenie;
    protected Swiat swiat;
    protected char symbol;

    protected Organizm(Swiat swiat, Polozenie polozenie) {
        this.swiat = swiat;
        this.polozenie = polozenie;
    }

    protected abstract void akcja();

    protected void kolizja(Organizm organizm){
        //walka
    }

    protected void rysowanie(){
        System.out.println(symbol + " ");
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
}

