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

    private static final int WYSOKOSC = 20;
    private static final int SZEROKOSC= 20;
    public List<Organizm> organizmy = new ArrayList<>();
    List<Organizm> organizmyPowstaleWTejTurze = new ArrayList<>();
    Organizm[][] plansza = new Organizm[WYSOKOSC][SZEROKOSC];


    private void start() {
        Scanner scanner = new Scanner(System.in);
        stworzOrganizmy();
        rysujSwiat();
        while (true){
            scanner.next();
            wykonajTure();
        }
    }

    private void stworzOrganizmy() {
        final int iloscZwierzatDodawanych = 40;
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
        for (int i = 0; i < iloscZwierzatDodawanych; i++) {
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
