package org.example;

import java.util.Queue;

public class Scheduler {
    public Processus choisirProcessus(Queue<Processus> p, int tempsActuelSimulation, Config config) {
        if (p.isEmpty()) {
            return null;
        }

        String politiqueOrdonnancement = config.processusOrdonnancement;
        if (politiqueOrdonnancement.equals("FIFO")) {
            return p.poll();
        } else if (politiqueOrdonnancement.equals("tourniquet")) {
            Processus processus = p.poll();
            int quantum = config.processusQuantum;  

            if (processus != null && processus.tempsArrivee <= tempsActuelSimulation) {
                // on va appliue le quantum
                if (processus.tempsExecution > quantum) {
                    // Si le processus n'est pas terminé ,on va le remettre dans la queue
                    processus.tempsExecution -= quantum;
                    p.offer(processus);
                    System.out.println("Processus " + processus.id + " a été interrompu après " + quantum + " unités de temps");
                } else {
                    
                    System.out.println("Processus " + processus.id + " terminé après " + processus.tempsExecution + " unités de temps");
                    processus.tempsExecution = 0;
                }
                return processus;
            } else {
                // Si le processus n'est pas encore arrivé, on va le remettre en queue
                p.offer(processus);
            }
        } else if (politiqueOrdonnancement.equals("par-priorites")) {
            Processus processusAMettre = null;
            int prioriteMin = Integer.MAX_VALUE;

            for (Processus processus : p) {
                if (processus.priorite < prioriteMin) {
                    processusAMettre = processus;
                    prioriteMin = processus.priorite;
                }
            }
            if (processusAMettre != null) {
                p.remove(processusAMettre);
            }
            return processusAMettre;
        }
        return null;
    }
}
