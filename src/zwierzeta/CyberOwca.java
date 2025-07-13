package zwierzeta;

import struktury.*;

import java.util.Comparator;
import java.util.List;

public class CyberOwca extends Zwierze {

    public CyberOwca(Swiat swiat, Polozenie polozenie) {
        super(swiat, polozenie);
        this.sila = 11;
        this.inicjatywa = 4;
        this.symbol = 'C';
        this.gatunek = Gatunek.CYBEROWCA;
    }

    @Override
    protected void akcja() {
        List<Organizm> barszczeSosnowskiego = swiat.znajdzWszystkieOrganizmyGatunku(Gatunek.BARSZCZSOSNOWSKIEGO);
        if (barszczeSosnowskiego.isEmpty()) {
            super.akcja();
            System.out.printf("Cyber-owca w polozeniu %s nie znalazla barszczu sosnowskiego\n", polozenie);
            return;
        }

        Polozenie najblizszyBarszcz = getNajblizszyBarszcz(barszczeSosnowskiego);
        System.out.printf("Cyber-owca w polozeniu %s wybrala barszcz sosnowskiego w polozeniu %s jako najblizszy\n", polozenie, najblizszyBarszcz);

        Polozenie wlasciwyRuch = this.polozenie.znajdzWszystkiePolozeniaDookola(zasiegRuchu).stream()
                .min(Comparator.comparingDouble(polozenie -> polozenie.znajdzOdlegloscDo(najblizszyBarszcz)))
                .orElse(null);

        assert wlasciwyRuch != null;
        super.ruch(List.of(wlasciwyRuch));
    }

    private Polozenie getNajblizszyBarszcz(List<Organizm> barszczeSosnowskiego) {
        return barszczeSosnowskiego.stream()
                .map(Organizm::getPolozenie)
                .min(Comparator.comparingDouble(polozenie -> polozenie.znajdzOdlegloscDo(this.polozenie)))
                .orElse(null);
    }

    @Override
    protected Organizm stworzDziecko(Swiat swiat, Polozenie polozenie) {
        return new CyberOwca(swiat, polozenie);
    }
}