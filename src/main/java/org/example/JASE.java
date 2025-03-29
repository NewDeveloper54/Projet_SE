package org.example;

import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class JASE {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

Config config1 =Config.chargerConfig("Config/Config.txt");
System.out.println("Configuration chargée !");


        Queue<Processus> processuses = DonneesChargeur.chargerProcessus("Config/Donnees.txt");
        System.out.println("processus charge");

        Scheduler scheduler=new Scheduler();
        Simulation simulation= new Simulation(processuses, config1, scheduler);
        simulation.executer();
        simulation.afficherResultat();
    }
}