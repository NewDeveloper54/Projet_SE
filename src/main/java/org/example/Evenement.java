package org.example;

public class Evenement {

    public String type; // le type peut etre un calcul, une ecriture, une lecture, dormir et fin
    public int parametre;


    public Evenement(String type, int parametre){
        this.type=type;
        this.parametre=parametre;
    }

    public String toString(){
        return ("l'evenement de type: "+this.type+" et de paramaetre: "+this.parametre);
    }

    public String getType() {
        return type;
    }

    public int getParametre() {
        return parametre;
    }


}
