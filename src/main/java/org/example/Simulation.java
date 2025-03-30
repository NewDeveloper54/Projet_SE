package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
    private Queue<Processus> processuses;
    private int tempsSimulation;
    private Config c;
    private Scheduler s;

    public Simulation(Queue<Processus> processuses, Config c, Scheduler s) {
        this.processuses = new LinkedList<>(processuses);
        this.c = c;
        this.tempsSimulation = 0;
        this.s = s;
    }

    public void executer() {
        while (!processuses.isEmpty()) {
            Processus p = s.choisirProcessus(processuses, tempsSimulation, c);

            if (p == null) {
                System.out.println("Aucun processus identifié");
                break;
            }

            p.etat = "EN COURS";
            System.out.println("Processus en cours: " + p.id + " avec la priorité: " + p.priorite);

            // Si l'événement est LECTURE, ECRITURE ou DORMIR, on va ajuster le temps d'exécution
            for (Evenement evenement : p.e) {
                if (evenement.type.equals("LECTURE") || evenement.type.equals("ECRITURE") || evenement.type.equals("DORMIR")) {
                    System.out.println("Traitement : " + evenement.type + " pendant " + evenement.duree + " s");
                    tempsSimulation += evenement.duree;
                }
            }

            // Si le processus est terminé après le traitement de l'événement
            tempsSimulation += p.tempsExecution;
            p.etat = "TERMINEE";
            System.out.println("Processus terminé: " + p.id + " avec la priorité: " + p.priorite);
        }
        System.out.println("La simulation est finie, le temps total de simulation est: " + tempsSimulation + " s");
    }

    public void afficherResultat() {
        for (Processus processus : processuses) {
            System.out.println("Le processus: " + processus.id + " avec l'état: " + processus.etat +
                    " a pris " + processus.tempsExecution + " temps d'exécution");
        }
        System.out.println("Le temps total de simulation est: " + tempsSimulation + " s");
    }
}
