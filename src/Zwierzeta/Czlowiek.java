package Zwierzeta;

import Struktury.*;

import java.util.List;

public class Czlowiek extends Zwierze {

    private String wejscie;

    public Czlowiek(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 5;
        this.inicjatywa = 4;
        this.symbol = 'X';
    }

    @Override
    protected void akcja() {
        Przesuniecie przesuniecie = konwertujWejscieNaPrzesuniecie(wejscie);

        Polozenie sprawdzanePolozenie = polozenie.kopiuj();
        sprawdzanePolozenie.przesun(przesuniecie);
        if (polozenie.equals(sprawdzanePolozenie)){
            System.out.println("Podane wejscie spowodowaloby wyjscie poza plansze");
            return;
        }
        super.ruch(List.of(sprawdzanePolozenie));
    }

    private Przesuniecie konwertujWejscieNaPrzesuniecie(String wejscie) {
        Przesuniecie odczytane;
        switch (wejscie){
            case "w"  -> odczytane = new Przesuniecie(0, -1);
            case "s"  -> odczytane = new Przesuniecie(0, 1);
            case "a"  -> odczytane = new Przesuniecie(-1, 0);
            case "d"  -> odczytane = new Przesuniecie(1, 0);
            case "aw" -> odczytane = new Przesuniecie(-1, -1);
            case "as" -> odczytane = new Przesuniecie(-1, 1);
            case "dw" -> odczytane = new Przesuniecie(1, -1);
            case "ds" -> odczytane = new Przesuniecie(1, 1);
            default -> {
                System.out.println("BLAD ODCZYTU WEJSCIA");
                return null;
            }
        }
        return odczytane;
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return null;
    }

    public void setWejscie(String wejscie) {
        this.wejscie = wejscie;
    }
}
