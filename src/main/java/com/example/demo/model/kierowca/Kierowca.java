package com.example.demo.model.kierowca;


import com.example.demo.model.Pogoda;
import com.example.demo.model.samochod.Samochod;
import com.example.demo.utils.Utils;

import java.util.UUID;

public abstract class Kierowca {
    private TypKierowcy typKierowcy;
    private Integer znajomoscTrasy;
    private Integer stanTrzezwosci;
    private Integer szybkoscReakcji;
    private Integer zycieKierowcy;
    private final UUID uuid;


    public Kierowca(TypKierowcy typKierowcy, Integer znajomoscTrasy, Integer stanTrzezwosci, Integer szybkoscReakcji, Integer zycieKierowcy,  UUID uuid) {
        this.typKierowcy = typKierowcy;
        this.znajomoscTrasy = znajomoscTrasy;
        this.stanTrzezwosci = stanTrzezwosci;
        this.szybkoscReakcji = szybkoscReakcji;
        this.zycieKierowcy = zycieKierowcy;
        this.uuid = uuid;
    }

    public void setZycieKierowcy(Integer zycieKierowcy) {
        this.zycieKierowcy = zycieKierowcy;
    }

    public TypKierowcy getTypKierowcy() {

        return typKierowcy;
    }

    public Integer getZnajomoscTrasy() {

        return znajomoscTrasy;
    }

    public Integer getStanTrzezwosci() {

        return stanTrzezwosci;
    }

    public Integer getSzybkoscReakcji() {

        return szybkoscReakcji;
    }

    public Integer getZycieKierowcy() {
        return  zycieKierowcy; }



    public void setSzybkoscReakcji(Integer szybkoscReakcji) {

        this.szybkoscReakcji = szybkoscReakcji;
    }

    private Integer ryzyko;

    public Integer getRyzyko() {
        return ryzyko;
    }

    public void setRyzyko(Integer ryzyko) {
        this.ryzyko = ryzyko;
    }

    public UUID getUuid() {
        return uuid;
    }



    public void nietrzezwoscZmniejszenieReakcji() {
        if (!czyTrzezwy()) {
            if (getSzybkoscReakcji() <= 1) {
                setSzybkoscReakcji(0);
            } else {
                Integer nowaSzybkoscReakcji = getSzybkoscReakcji() - 1;
                setSzybkoscReakcji(nowaSzybkoscReakcji);
            }
        }
    }

    private boolean czyTrzezwy() {
        boolean trzezwosc = true;
        if (getStanTrzezwosci() >= 4) {
            trzezwosc = false;
        }
        return trzezwosc;
    }

    public void aktualizacjaReakcjiOdPogody( Pogoda pogoda) {
        Integer zmianaSzybkosciReakcji = getSzybkoscReakcji() + pogoda.getZmianaSzybkosciReakcjiKierowcy();
        setSzybkoscReakcji(zmianaSzybkosciReakcji);
        if (getSzybkoscReakcji() < 0){
            setSzybkoscReakcji(0);
        }
        System.out.println("Ze wzgl??du na pogod?? reakcja kierowcy " + getTypKierowcy() + " to: " + getSzybkoscReakcji());
    }

    public int aktualizacjaZycia (int minZycieKierowca, int maxZycieKierowca) {
        Integer punktyZyciaDoZmniejszenia = Utils.losuj(minZycieKierowca, maxZycieKierowca);
        Integer zmniejszeniePunktowZycia = getZycieKierowcy() - punktyZyciaDoZmniejszenia;

        setZycieKierowcy(zmniejszeniePunktowZycia);
        return punktyZyciaDoZmniejszenia;
    }
    public void znajomoscTrasyPredkosc (Samochod samochod){
        if(getZnajomoscTrasy() >= 9){
            samochod.setSzybkosc(samochod.getSzybkosc() + 15);
            System.out.println("Ze wzgl??du na bardzo dobr?? znajomo???? trasy kierowcy " + getTypKierowcy() +
                    " pr??dko???? na tym odcinku (prostym) zostaje zwi??kszona o 15 km/h i wynosi " + samochod.getSzybkosc());
        } else if ( getZnajomoscTrasy() >= 7 ){
            samochod.setSzybkosc(samochod.getSzybkosc() + 5);
            System.out.println("Ze wzgl??du na dobr?? znajomo???? trasy kierowcy " + getTypKierowcy() +
                    " pr??dko???? na tym odcinku (prostym) zostaje zwi??kszona o 5 km/h i wynosi " + samochod.getSzybkosc());
        }
    }

    public void ustawienieRyzyka (){
        Integer ryzyko = 20 - (getZnajomoscTrasy() + getSzybkoscReakcji());
        if (ryzyko >= 4 && czyTrzezwy()) {
 /*           Integer zmniejszenieRyzyka = ryzyko - 4;
            ryzyko = zmniejszenieRyzyka;*/
            ryzyko -= 4;
            setRyzyko(ryzyko);
        } else if (ryzyko < 4 && czyTrzezwy()) {
            setRyzyko(0);
        } else if (!czyTrzezwy() && ryzyko <= 16) {
           ryzyko += 4;
           setRyzyko(ryzyko);
        } else {
            setRyzyko(20);
        }
    }

    public int aktualizacjaZyciaPitsop(Kierowca kierowca){
        int zwiekszenieZyciaKierowcy = kierowca.getZycieKierowcy() + 10;
        kierowca.setZycieKierowcy(zwiekszenieZyciaKierowcy);
        System.out.println("Dzi??ki zjechaniu do pitstopu  uczestnik " + kierowca.getTypKierowcy() + " zyskuje 10 punkt??w ??ycia " +
                "i teraz jego ??ycie wynosi: " + kierowca.getZycieKierowcy() +".");
        return zwiekszenieZyciaKierowcy;
    }

    @Override
    public String toString() {
        return getTypKierowcy() + " znajomosc trasy: " + getZnajomoscTrasy() + " poziom trzezwosci: " + getStanTrzezwosci() + " stan trzezwosci: "
                + czyTrzezwy() + " szybkosc reakcji: " + getSzybkoscReakcji() + " ryzyko: " + getRyzyko() + " ID: " + getUuid();
    }


}


