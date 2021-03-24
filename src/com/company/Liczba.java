package com.company;

import java.math.BigDecimal;


public class Liczba implements Comparable<Liczba> {

    public static double liczbaLosowan;
    private int numer;
    private double czestosc = 0;
    private int kiedyOstatnio = 0;
    private int ileRazyPodRzadOstatnio = 0;//dodac do bazy danej tą kolumne

    public Liczba(double liczbaLosowan) {
        Liczba.liczbaLosowan = liczbaLosowan;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Liczba ");
        sb.append(numer);
        sb.append(", czestosc=").append(czestosc);
        sb.append(", kiedyOstatnio=").append(kiedyOstatnio);
        sb.append(", ileRazyPodRzadOstatnio=").append(ileRazyPodRzadOstatnio);
        sb.append(", prawdopodobienstwo=").append(getPrawdopodobienstwo());

        return sb.toString();
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public double getCzestosc() {
        return czestosc;
    }

    public void setCzestosc(double czestosc) {
        this.czestosc = czestosc;
    }

    public int getKiedyOstatnio() {
        return kiedyOstatnio;
    }

    public void setKiedyOstatnio(int kiedyOstatnio) {
        this.kiedyOstatnio = kiedyOstatnio;
    }

    public int getOstatnioPoKolei() {
        return ileRazyPodRzadOstatnio;
    }

    public void setOstatnioPoKolei(int ileRazyPodRzadOstatnio) {
        this.ileRazyPodRzadOstatnio = ileRazyPodRzadOstatnio;
    }


    public double getPrawdopodobienstwo() {//każda liczba jest równie prawdopodobna
        double kiedyOstatnio = prawdopodobienstwoJakDawnoOstatnioLosowano();
        BigDecimal kolejno = obliczPrawdopodobienstwoPoKolejnosci();
        double wynik = obliczPrawdopodobienstwo();
        return wynik;
    }

    private double obliczPrawdopodobienstwo() {
        double cz = 1 - getCzestosc() / liczbaLosowan;
        return cz * 100 / 42;
    }

    private double prawdopodobienstwoJakDawnoOstatnioLosowano() {
        return 0;
    }

    private BigDecimal obliczPrawdopodobienstwoPoKolejnosci() {
        int i = ileRazyPodRzadOstatnio;
        double PROB = 1 / 42.0;
        BigDecimal wynik = BigDecimal.valueOf(1 / 42.0);
        while (i > 0) {
            wynik = wynik.multiply(BigDecimal.valueOf(PROB));
            i--;
        }
        return wynik;
    }

    @Override
    public int compareTo(Liczba o) {
        if (this.getPrawdopodobienstwo() < o.getPrawdopodobienstwo()) return 1;
        if (this.getPrawdopodobienstwo() > o.getPrawdopodobienstwo()) return -1;
        else return 0;
    }
}
