package zwierzeta;

import struktury.*;

import java.util.List;
import java.util.Objects;

public class Czlowiek extends Zwierze {

    private String wejscie;
    private int czasTrwaniaUmiejetnosci = 0;
    private SpecjalnaUmiejetnosc aktywnaUmiejetnosc = SpecjalnaUmiejetnosc.BRAK;

    public Czlowiek(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 5;
        this.inicjatywa = 4;
        this.symbol = 'X';
    }

    @Override
    protected void akcja() {
        if (Character.isDigit(wejscie.charAt(0))){
            odczytajSpecjalnaUmiejetnosc();
            return;
        }
        Przesuniecie przesuniecie = konwertujWejscieNaPrzesuniecie(wejscie);

        if (aktywnaUmiejetnosc == SpecjalnaUmiejetnosc.SZYBKOSCANTYLOPY) {
            if (czasTrwaniaUmiejetnosci > 2 || losujSzanse(1, 2)) {
                przesuniecie.pomnozWektor(2);
            }
        }

        Polozenie sprawdzanePolozenie = polozenie.kopiuj();
        sprawdzanePolozenie.przesun(przesuniecie);
        if (polozenie.equals(sprawdzanePolozenie)) {
            System.out.println("Podane wejscie spowodowaloby wyjscie poza plansze");
            return;
        }
        super.ruch(List.of(sprawdzanePolozenie));
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        switch(aktywnaUmiejetnosc){
            case NIESMIERTELNOSC -> {
                if (atakujacy.getSila() >= this.sila){
                    List<Polozenie> wolnePolozenia = polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat);
                    super.ruch(wolnePolozenia);
                } else {
                    super.kolizja(atakujacy);
                }
            }
            case TARCZAALZURA -> {
                if (atakujacy instanceof Zwierze zwierze) {
                    List<Polozenie> sasiednieWolnePola = polozenie.znajdzWszystkieWolnePolozeniaDookola(swiat);
                    zwierze.ruch(sasiednieWolnePola);
                    System.out.printf("Tarcza alzura przesuwa zwierze %s\n", zwierze.getSymbol());
                }
            }
            default -> super.kolizja(atakujacy);
        }
    }


    private void odczytajSpecjalnaUmiejetnosc() {
        if (aktywnaUmiejetnosc != SpecjalnaUmiejetnosc.BRAK) {
            System.out.println("Specjalna umiejetnosc juz jest uruchomiona");
            return;
        }

        switch (wejscie){
            case "1" -> uruchomSpecjalnaUmiejetnosc(SpecjalnaUmiejetnosc.NIESMIERTELNOSC);
            case "2" -> {
                uruchomSpecjalnaUmiejetnosc(SpecjalnaUmiejetnosc.MAGICZNYELIKSIR);
                zwiekszSile(5);
            }
            case "3" -> uruchomSpecjalnaUmiejetnosc(SpecjalnaUmiejetnosc.SZYBKOSCANTYLOPY);
            case "4" -> uruchomSpecjalnaUmiejetnosc(SpecjalnaUmiejetnosc.TARCZAALZURA);
            case "5" -> {
                uruchomSpecjalnaUmiejetnosc(SpecjalnaUmiejetnosc.CALOPALENIE);
                polozenie.znajdzWszystkiePolozeniaDookola(1).stream()
                        .map(swiat::wezElementWPolozeniu)
                        .filter(Objects::nonNull)
                        .peek(organizm -> System.out.printf("Calopalenie zabija zwierze %s w polozeniu %s\n", organizm.getSymbol(), organizm.getPolozenie()))
                        .forEach(organizm -> swiat.umieraOrganizm(organizm));
            }
            default -> throw new IllegalArgumentException("Blad odczytu wejscia ruchu czlowieka");
        }
    }

    private void uruchomSpecjalnaUmiejetnosc(SpecjalnaUmiejetnosc specjalnaUmiejetnosc) {
        aktywnaUmiejetnosc = specjalnaUmiejetnosc;
        czasTrwaniaUmiejetnosci = 5;
        System.out.printf("Uruchomiona zostaje umiejetnosc %s\n", specjalnaUmiejetnosc.name());
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
            default -> throw new IllegalArgumentException("Blad odczytu wejscia ruchu czlowieka");
        }
        return odczytane;
    }

    @Override
    public void zwiekszWiek() {
        super.zwiekszWiek();
        if (aktywnaUmiejetnosc == SpecjalnaUmiejetnosc.MAGICZNYELIKSIR)
            zwiekszSile(-1);
        if (czasTrwaniaUmiejetnosci > 0) {
            czasTrwaniaUmiejetnosci--;
            if (czasTrwaniaUmiejetnosci == 0) {
                wylaczUmiejetnosc();
            }
        }
    }

    private void wylaczUmiejetnosc() {
        aktywnaUmiejetnosc = SpecjalnaUmiejetnosc.BRAK;
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return null;
    }

    public void setWejscie(String wejscie) {
        this.wejscie = wejscie;
    }

    public int getCzasTrwaniaUmiejetnosci() {
        return czasTrwaniaUmiejetnosci;
    }

    public String getAktywnaUmiejetnosc() {
        return aktywnaUmiejetnosc.name();
    }

}
enum SpecjalnaUmiejetnosc {
    NIESMIERTELNOSC,
    MAGICZNYELIKSIR,
    SZYBKOSCANTYLOPY,
    TARCZAALZURA,
    CALOPALENIE,
    BRAK
}

