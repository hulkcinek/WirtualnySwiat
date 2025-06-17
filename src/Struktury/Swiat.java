package Struktury;

import Zwierzeta.Owca;
import Zwierzeta.Wilk;

import java.util.*;
import java.util.function.BiFunction;

public class Swiat {

    public static void main(String[] args) {
        new Swiat().start();
    }

    private static final int WYSOKOSC = 10;
    private static final int SZEROKOSC= 10;
    List<Organizm> organizmy = new ArrayList<>();
    Organizm[][] plansza = new Organizm[WYSOKOSC][SZEROKOSC];


    private void start() {
        stworzOrganizmy();
        Scanner scanner = new Scanner(System.in);
        while (true){
            wykonajTure();
            scanner.next();
        }
    }

    private void stworzOrganizmy() {
        List<BiFunction<Swiat, Polozenie, Organizm>> konstruktory = List.of(
                Wilk::new,
                Owca::new
        );
        Random random = new Random();
        final int iloscZwierzatDodawanych = 10;
        for (int i = 0; i < iloscZwierzatDodawanych; i++) {
            konstruktory.get(random.nextInt(konstruktory.size()))
                    .apply(this, Polozenie.losujPolozenie(this));
        }
    }

    private void wykonajTure(){
        rysujSwiat();
        uporzadkujOrganizmyNaLiscie();
        zaktualizujPlansze();
        for (Organizm organizm : organizmy) {
            organizm.akcja();
        }
        System.out.printf("Na swiecie pozostaje %s organizmow", organizmy.size());
    }

    public void zaktualizujPlansze() {
        plansza = new Organizm[WYSOKOSC][SZEROKOSC];
        organizmy.forEach(organizm -> {
            Polozenie polozenie = organizm.getPolozenie();
            plansza[polozenie.getY()][polozenie.getX()] = organizm;
        });
        long iloscOrganizmow = organizmy.size();
        long iloscZajetychPol = organizmy.stream().map(Organizm::getPolozenie).distinct().count();
        if (iloscOrganizmow != iloscZajetychPol) System.out.println("Niezgodnosc: " + iloscZajetychPol + " " + iloscOrganizmow);
    }

    public Organizm wezElementZPlanszyWPolozeniu(Polozenie polozenie){
        return plansza[polozenie.getY()][polozenie.getX()];
    }

    public void umieraOrganizm(Organizm umierajacy){
        organizmy.remove(umierajacy);
        System.out.printf("Umiera organizm: %s, ktory znajdowal sie na pozycji: %s\n", umierajacy.symbol, umierajacy.getPolozenie());
        zaktualizujPlansze();
    }

    private void uporzadkujOrganizmyNaLiscie() {
        organizmy.sort(Comparator.comparingInt(Organizm::getInicjatywa).reversed());
    }

    public void rysujSwiat(){
        for (Organizm[] wiersz : plansza){
            for (Organizm pole : wiersz){
                if (pole == null) System.out.print(". ");
                else pole.rysowanie();
            }
            System.out.println();
        }
    }

    public void dodajOrganizmNaListe(Organizm organizm){
        organizmy.add(organizm);
        uporzadkujOrganizmyNaLiscie();
        zaktualizujPlansze();
    }

    public boolean czyPolozenieWolne(Polozenie polozenie){
        return plansza[polozenie.getY()][polozenie.getX()] != null;
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
}
