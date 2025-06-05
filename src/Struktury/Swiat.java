package Struktury;

import java.util.ArrayList;
import java.util.List;

public class Swiat {

    public static void main(String[] args) {
        new Swiat().start();
    }

    List<Organizm> organizmy = new ArrayList<>();


    private void start() {

        while (true){
            wykonajTure();
        }
    }

    private void wykonajTure(){
        rysujSwiat();
        //akcje wszystkich organizmow
    }

    private void rysujSwiat(){
        //narysuj plansze
    }
}
