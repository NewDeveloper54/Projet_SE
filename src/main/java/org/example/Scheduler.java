package org.example;

import java.io.ObjectInputFilter;
import java.util.Queue;

public class Scheduler {


    public Processus choisirProcessus(Queue<Processus> p, int tempsActuelSimulation,Config config) {
        if (p.isEmpty()) {
            return null;
        }

        String politiqueOrdonnancement =config.processusOrdonnancement;
        if(politiqueOrdonnancement.equals("FIFO")) {
            return p.poll();
        } else if (politiqueOrdonnancement.equals("tourniquet")) {
            Processus processus = p.poll();
            if (processus!= null && processus.tempsArrivee > tempsActuelSimulation) {
                p.offer(processus);
            }
            return processus;
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
