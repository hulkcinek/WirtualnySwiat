package Struktury;

import Zwierzeta.*;
import Rosliny.*;

import java.util.*;
import java.util.function.BiFunction;

public class Swiat {

    public boolean DEBUG = false;

    public static void main(String[] args) {
        new Swiat().start();
    }

    private static final int WYSOKOSC = 25;
    private static final int SZEROKOSC= 25;
    public List<Organizm> organizmy = new ArrayList<>();
    List<Organizm> organizmyPowstaleWTejTurze = new ArrayList<>();
    Organizm[][] plansza = new Organizm[WYSOKOSC][SZEROKOSC];
    Czlowiek czlowiek;


    private void start() {
        Scanner scanner = new Scanner(System.in);
        stworzOrganizmy();
        czlowiek = new Czlowiek(this, Polozenie.losujPolozenie(this));
        rysujSwiat();
        while (organizmy.contains(czlowiek)){
            String wejscie = scanner.next();
            wejscie = ujednolicWejscie(wejscie);
            while (!czyPoprawneWejscie(wejscie)) {
                System.out.println("Podaj poprawne wejscie");
                wejscie = scanner.next();
                wejscie = ujednolicWejscie(wejscie);
            }
            czlowiek.setWejscie(wejscie);
            wykonajTure();
        }
        System.out.println("Gra sie zakonczyla bo umarl czlowiek");
        System.out.printf("Trwala %s tur\n", czlowiek.getWiek());
    }

    private boolean czyPoprawneWejscie(String wejscie) {
        List<String> poprawneWejscia = List.of(
                "w",
                "s",
                "a",
                "d",
                "aw",
                "as",
                "dw",
                "ds"
        );
        return poprawneWejscia.contains(wejscie);
    }

    private String ujednolicWejscie(String wejscie) {
        wejscie = wejscie.toLowerCase();
        char[] litery = wejscie.toCharArray();
        Arrays.sort(litery);
        return new String(litery);
    }

    private void stworzOrganizmy() {
        final int iloscOrganizmowDodawanych = SZEROKOSC*WYSOKOSC/10;
        List<BiFunction<Swiat, Polozenie, Organizm>> konstruktory = List.of(
                Wilk::new
                ,Owca::new
                ,Lis::new
                ,Zolw::new
                ,Antylopa::new
                ,CyberOwca::new
                ,Trawa::new
                ,Mlecz::new
                ,Guarana::new
                ,WilczeJagody::new
                ,BarszczSosnowskiego::new
        );
        Random random = new Random();
        for (int i = 0; i < iloscOrganizmowDodawanych; i++) {
            konstruktory.get(random.nextInt(konstruktory.size()))
                        .apply(this, Polozenie.losujPolozenie(this));
        }
    }

    private void wykonajTure(){
        uporzadkujOrganizmyNaLiscie();
        zaktualizujPlansze();
        for (int i = 0; i < organizmy.size(); i++) {
            Organizm organizm = organizmy.get(i);
            organizm.akcja();
        }
        przeniesNoweOrganizmyNaDocelowaListe();
        zaktualizujWiekOrganizmow();
        if (!DEBUG) rysujSwiat();
        System.out.printf("Na swiecie pozostaje %s organizmow\n", organizmy.size());
        System.out.printf("Sila czlowieka wynosi teraz %s\n", czlowiek.getSila());
    }

    private void przeniesNoweOrganizmyNaDocelowaListe() {
        organizmy.addAll(organizmyPowstaleWTejTurze);
        organizmyPowstaleWTejTurze.clear();
        uporzadkujOrganizmyNaLiscie();
    }

    public void zaktualizujPlansze() {
        plansza = new Organizm[WYSOKOSC][SZEROKOSC];
        organizmy.forEach(organizm -> {
            Polozenie polozenie = organizm.getPolozenie();
            plansza[polozenie.getY()][polozenie.getX()] = organizm;
        });
        organizmyPowstaleWTejTurze.forEach(organizm -> {
            Polozenie polozenie = organizm.getPolozenie();
            plansza[polozenie.getY()][polozenie.getX()] = organizm;
        });
        long iloscOrganizmow = organizmy.size();
        long iloscZajetychPol = organizmy.stream().map(Organizm::getPolozenie).distinct().count();
        if (iloscOrganizmow != iloscZajetychPol) System.out.println("Niezgodnosc: " + iloscZajetychPol + " " + iloscOrganizmow);
    }

    public Organizm wezElementWPolozeniu(Polozenie polozenie){
        return plansza[polozenie.getY()][polozenie.getX()];
    }

    public void umieraOrganizm(Organizm umierajacy){
        organizmy.remove(umierajacy);
        organizmyPowstaleWTejTurze.remove(umierajacy);
        System.out.printf("Umiera organizm: %s, ktory znajdowal sie na pozycji: %s\n", umierajacy.symbol, umierajacy.getPolozenie());
        zaktualizujPlansze();
    }

    private void uporzadkujOrganizmyNaLiscie() {
        organizmy.sort(Comparator
                .comparingInt(Organizm::getInicjatywa)
                .thenComparing(Organizm::getWiek).reversed());
    }

    public void rysujSwiat(){
        System.out.print("   ");
        for (int i = 0; i < SZEROKOSC; i++) {
            if (i < 10) {
                System.out.print(" " + i + " ");
            }else {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        for (int y = 0; y < plansza.length; y++) {
            Organizm[] wiersz = plansza[y];
            if (y < 10) {
                System.out.print(" " + y + " ");
            }else {
                System.out.print(y + " ");
            }
            for (int x = 0; x < wiersz.length; x++) {
                Organizm pole = wiersz[x];
                if (pole == null) System.out.print(" . ");
                else pole.rysowanie();
            }
            System.out.println();
        }
    }

    private void zaktualizujWiekOrganizmow() {
        organizmy.forEach(Organizm::zwiekszWiek);
    }

    public void dodajOrganizmNaListe(Organizm organizm){
        organizmy.add(organizm);
        uporzadkujOrganizmyNaLiscie();
        zaktualizujPlansze();
    }

    public boolean czyPolozenieWolne(Polozenie polozenie){
        return plansza[polozenie.getY()][polozenie.getX()] == null;
    }

    public static int getWYSOKOSC() {
        return WYSOKOSC;
    }

    public static int getSZEROKOSC() {
        return SZEROKOSC;
    }

    public static boolean czyPoleNaPlanszy(Polozenie polozenie, Przesuniecie przesuniecie){
        int sprawdzaneY = polozenie.getY() + przesuniecie.getDy();
        int sprawdzaneX = polozenie.getX() + przesuniecie.getDx();
        return 0 <= sprawdzaneY && 0 <= sprawdzaneX && sprawdzaneY < WYSOKOSC && sprawdzaneX < SZEROKOSC;
    }

    public void rodziSieOrganizm(Organizm organizm) {
        organizmy.remove(organizm);
        System.out.printf("Powstaje organizm %s w polozeniu %s\n", organizm.symbol, organizm.polozenie);
        organizmyPowstaleWTejTurze.add(organizm);
        zaktualizujPlansze();
    }
}
