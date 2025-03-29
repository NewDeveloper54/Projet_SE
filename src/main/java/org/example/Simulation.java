package org.example;

import java.util.Queue;

public class Simulation {
    public Queue<Processus> processuses;
    public int tempsSimulation;
    Config c;
    Scheduler s;


    public Simulation(Queue<Processus> processuses, Config c,Scheduler s){
        this.processuses=processuses;
        this.c=c;
        this.tempsSimulation=0;
        this.s=s;
    }

    public void executer(){
while (!processuses.isEmpty()){
     Processus p =s.choisirProcessus(processuses,tempsSimulation,c);

     if(p==null){
         System.out.println("aucun processus identifie");
         break;
     }
     p.etat="EN COURS";
System.out.println("processus en cours: "+p.id+" avec la priorite: "+p.priorite);
 tempsSimulation+=p.tempsExecution;
 p.etat="TERMINEE";
 System.out.println("processus termine: "+p.id+" avec la priorite: "+p.priorite);
}
System.out.println("la simulation est finie, le temps de simulation est: "+tempsSimulation +" s");
    }


    public void afficherResultat(){
for(Processus processus: processuses){
    System.out.println("le processus: "+processus.id+" avec l'etat: "+processus.etat+ " a pris "+processus.tempsExecution+" temps d'execution");
}
        System.out.println("le temps total de simulation est: "+tempsSimulation+ " s");

    }


}
