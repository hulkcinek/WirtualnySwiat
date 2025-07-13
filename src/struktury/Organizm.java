package struktury;


import java.util.Random;

public abstract class Organizm {
    protected Gatunek gatunek;
    protected int sila;
    protected int inicjatywa;
    protected Polozenie polozenie;
    protected Swiat swiat;
    protected char symbol;
    protected int wiek = 0;
    private boolean zywy = true;
    Random random = new Random();

    protected Organizm(Swiat swiat, Polozenie polozenie) {
        this.swiat = swiat;
        this.polozenie = polozenie;
        swiat.dodajOrganizmNaListe(this);
    }

    protected abstract void akcja();

    public void kolizja(Organizm atakujacy){
        System.out.printf("Zaatakowany %s przez %s na pozycji %s\n", this.symbol, atakujacy.symbol, this.polozenie);
        if (atakujacy.sila >= this.getSila()){
            swiat.umieraOrganizm(this);
        } else {
            swiat.umieraOrganizm(atakujacy);
        }
    }

    protected abstract Organizm stworzDziecko(Swiat swiat, Polozenie polozenie);

    protected void rysowanie(){
        System.out.print(" " + symbol + " ");
    }

    protected boolean losujSzanse(int a, int omega) {
        int wylosowana = random.nextInt(omega);
        return wylosowana < a;
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

    public void zwiekszSile(int n){
        System.out.printf("Sila %s w polozeniu %s zwieksza sie o %s\n", symbol, polozenie, n);
        this.sila += n;
    }

    public void smierc() {
        this.zywy = false;
    }

    public boolean czyMartwy() {
        return !zywy;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }
}

