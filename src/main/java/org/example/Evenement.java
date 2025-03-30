package org.example;

public class Evenement {

    public String type; // le type peut etre un calcul, une ecriture, une lecture, dormir et fin
    public int duree;


    public Evenement(String type, int duree){
        this.type=type;
        this.duree=duree;
    }

    public String toString(){
        return ("l'evenement de type: "+this.type+" et de duree: "+this.duree);
    }

    public String getType() {
        return type;
    }

    public int getDuree() {
        return duree;
    }


}
