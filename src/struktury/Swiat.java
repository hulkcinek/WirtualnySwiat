package struktury;

import zwierzeta.*;

import java.util.*;

public class Swiat {

    public static final boolean DEBUG = false;
    public static final int WYSOKOSC = 25;
    public static final int SZEROKOSC= 25;

    private final List<Organizm> organizmy = new ArrayList<>();
    private Organizm[][] plansza = new Organizm[WYSOKOSC][SZEROKOSC];
    private Czlowiek czlowiek;

    public static void main(String[] args) {
        new Swiat().start();
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        czlowiek = new Czlowiek(this, Polozenie.losujPolozenie(this));
        stworzOrganizmy();
        zaktualizujWiekOrganizmow();
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

    private void wykonajTure(){
        uporzadkujOrganizmyNaLiscie();
        zaktualizujPlansze();
        List<Organizm> organizmyNaPoczatkuTury = organizmy.stream().toList();
        organizmyNaPoczatkuTury.stream()
                .filter(organizm -> organizm.getWiek() != 0)
                .filter(organizm -> !organizm.czyMartwy())
                .forEach(Organizm::akcja);
        zaktualizujWiekOrganizmow();
        if (!DEBUG) rysujSwiat();
        System.out.printf("Na swiecie pozostaje %s organizmow\n", organizmy.size());
        System.out.printf("Sila czlowieka wynosi teraz %s\n", czlowiek.getSila());
        System.out.printf("Obecnie aktywna specjalna umiejetnosc to %s i bedzie trwala jeszcze przez %s tury\n", czlowiek.getAktywnaUmiejetnosc(), czlowiek.getCzasTrwaniaUmiejetnosci());
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
            for (Organizm pole : wiersz) {
                if (pole == null) System.out.print(" . ");
                else pole.rysowanie();
            }
            System.out.println();
        }
    }

    public Organizm wezElementWPolozeniu(Polozenie polozenie){
        return plansza[polozenie.getY()][polozenie.getX()];
    }

    public void dodajOrganizmNaListe(Organizm organizm){
        organizmy.add(organizm);
        uporzadkujOrganizmyNaLiscie();
        zaktualizujPlansze();
    }

    public List<Organizm> znajdzWszystkieOrganizmyGatunku(Gatunek gatunek){
        return organizmy.stream()
                .filter(organizm -> organizm.gatunek == gatunek)
                .toList();
    }

    public boolean czyPolozenieWolne(Polozenie polozenie){
        return plansza[polozenie.getY()][polozenie.getX()] == null;
    }

    public static boolean czyPoleNaPlanszy(Polozenie polozenie, Przesuniecie przesuniecie){
        int sprawdzaneY = polozenie.getY() + przesuniecie.getDy();
        int sprawdzaneX = polozenie.getX() + przesuniecie.getDx();
        return 0 <= sprawdzaneY && 0 <= sprawdzaneX && sprawdzaneY < WYSOKOSC && sprawdzaneX < SZEROKOSC;
    }

    public void rodziSieOrganizm(Organizm organizm) {
        System.out.printf("Powstaje organizm %s w polozeniu %s\n", organizm.symbol, organizm.polozenie);
        zaktualizujPlansze();
    }

    public void umieraOrganizm(Organizm umierajacy){
        if (umierajacy instanceof Czlowiek cz && cz.getAktywnaUmiejetnosc().equals("NIESMIERTELNOSC")) return;
        organizmy.remove(umierajacy);
        umierajacy.smierc();
        System.out.printf("Umiera organizm: %s, ktory znajdowal sie na pozycji: %s\n", umierajacy.symbol, umierajacy.getPolozenie());
        zaktualizujPlansze();
    }

    private void stworzOrganizmy() {
        final int iloscOrganizmowDodawanych = SZEROKOSC*WYSOKOSC/10;
        for (int i = 0; i < iloscOrganizmowDodawanych; i++) {
            Gatunek.losujOrganizm()
                    .getKonstruktor()
                    .apply(this, Polozenie.losujPolozenie(this));
        }
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
                "ds",
                "1",
                "2",
                "3",
                "4",
                "5"
        );
        return poprawneWejscia.contains(wejscie);
    }

    private String ujednolicWejscie(String wejscie) {
        wejscie = wejscie.toLowerCase();
        char[] litery = wejscie.toCharArray();
        Arrays.sort(litery);
        return new String(litery);
    }

    private void uporzadkujOrganizmyNaLiscie() {
        organizmy.sort(Comparator
                .comparingInt(Organizm::getInicjatywa)
                .thenComparing(Organizm::getWiek).reversed());
    }

    private void zaktualizujWiekOrganizmow() {
        organizmy.forEach(Organizm::zwiekszWiek);
    }
}
