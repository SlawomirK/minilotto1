package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Statystyka {

    private final int liczbaLosowan;
    private Set<Liczba> liczbyDoWyboru;

    public Statystyka(List<String> listaLosowan) {
        liczbaLosowan = listaLosowan.size();
        resetDanych();
        zaktualizujLiczby(zamianaNaLiczby(listaLosowan));
        List<Liczba> sorted = getStatystyki().stream().sorted().collect(Collectors.toList());
        zaproponujNajpewniejszeSzostki(sorted);
        System.out.println();
        print(sorted);
    }

    private void print(List<Liczba> sorted) {
        sorted.stream().forEach(System.out::println);
    }

    private void zaproponujNajpewniejszeSzostki(List<Liczba> sorted) {

        int licznik = 0;
        for (Liczba l : sorted) {

            if (licznik == 5) {
                System.out.println();
                licznik = 0;
            }
            System.out.print(l.getNumer() + ", ");
            licznik++;
        }
    }

    public Set<Liczba> getStatystyki() {
        return liczbyDoWyboru;
    }

    /*Aktualizacja danych w liscie która później wyślę do bazy. Jeżeli wylosowano zwieksza się licznik
     * czestość i zeruje licznik jak dawno od ostatniego losowania*/
    private void zaktualizujLiczby(Set<Integer[]> losowania) {
        Liczba licz = null;
        for (Integer[] los : losowania) {
            for (Integer liczba : los) {
                licz = liczbyDoWyboru.stream().filter(l -> liczba == l.getNumer()).findFirst().get();
                licz.setCzestosc(licz.getCzestosc() + 1);
            }
            zaktualizujKiedyOstatnio(los);
        }
    }

    private void zaktualizujKiedyOstatnio(Integer[] licz) {
        for (Liczba l : liczbyDoWyboru) {
            if (Arrays.stream(licz).anyMatch(n -> n == l.getNumer())) {
                l.setKiedyOstatnio(0);
                l.setOstatnioPoKolei(l.getOstatnioPoKolei() + 1);
            } else {
                l.setKiedyOstatnio(l.getKiedyOstatnio() + 1);
                l.setOstatnioPoKolei(0);
            }
        }
    }

    private Set<Integer[]> zamianaNaLiczby(List<String> listOfDraws) {
        Integer[] losowanie;
        Set<Integer[]> losowania = new LinkedHashSet<>();
        String[] str;
        for (String a : listOfDraws) {
            str = a.split(",");
            losowanie = new Integer[5];
            for (int i = 0; i < 5; i++) {
                losowanie[i] = Integer.parseInt(str[i]);
            }
            losowania.add(losowanie);
        }
        //  losowania.stream().forEach(s->Arrays.stream(s).forEach(a->System.out.println(a)));
        return losowania;
    }

    private void resetDanych() {
        int i = 1;
        liczbyDoWyboru = new HashSet<>();
        while (i <= 42) {
            Liczba liczba = new Liczba(getLiczbaLosowan());
            liczba.setCzestosc(0);
            liczba.setKiedyOstatnio(0);
            liczba.setNumer(i);
            liczbyDoWyboru.add(liczba);
            i++;
        }
    }

    public int getLiczbaLosowan() {
        return liczbaLosowan;
    }

}
