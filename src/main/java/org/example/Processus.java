package org.example;

import java.util.Queue;

public class Processus {
    public int id;
    public int tempsArrivee;
    public String etat; // encours ou termine
    public int tempsReponse = -1;
    public int tempsArret;
    public int tempsExecution;
    public Queue<Evenement> e;
    public int priorite;


    public Processus(int id, int tempsArrivee, int priorite, int tempsExecution, Queue<Evenement> e){
        this.id=id;
        this.tempsArrivee=tempsArrivee;
        this.etat="NOUVEAU";
        this.priorite=priorite;
        this.e=e;
        this.tempsExecution=tempsExecution;
    }

    public String toString(){
        return "le processus: "+this.id+" avec le temps d'arrivee: "+this.tempsArrivee+" avec la priorite: "+this.priorite;
    }


}
