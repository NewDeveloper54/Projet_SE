package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Config {
    public int tempsSimulation ;
    public int tempsEcritureDisque;
    public int tempsChargePage;
    public int interruptionHorloge;
    public String processusOrdonnancement; // fifo ou tourniquet ou par-priorite
    public int processusQuantum;
    public String paginationPolitiqueAllocation;
    public int paginationNombreCadresLocaux;
    public int getPaginationNombreCadres;
    public String paginationAlgorithme;
    public String disqueOrdonnancement;
    public int disquePositionInitiale;
    public String disqueDirectionInitiale;


public Config(int tempsSimulation, int tempsEcritureDisque, int tempsChargePage, int interruptionHorloge,
              String processusOrdonnancement, int processusQuantum, String paginationPolitiqueAllocation,
              int paginationNombreCadresLocaux, int getPaginationNombreCadres, String paginationAlgorithme,
              String disqueOrdonnancement, int disquePositionInitiale, String disqueDirectionInitiale) {
    this.tempsSimulation = tempsSimulation;
    this.tempsEcritureDisque = tempsEcritureDisque;
    this.tempsChargePage = tempsChargePage;
    this.interruptionHorloge = interruptionHorloge;
    this.processusOrdonnancement = processusOrdonnancement;
    this.processusQuantum = processusQuantum;
    this.paginationPolitiqueAllocation = paginationPolitiqueAllocation;
    this.paginationNombreCadresLocaux = paginationNombreCadresLocaux;
    this.getPaginationNombreCadres = getPaginationNombreCadres;
    this.paginationAlgorithme = paginationAlgorithme;
    this.disqueOrdonnancement = disqueOrdonnancement;
    this.disquePositionInitiale = disquePositionInitiale;
    this.disqueDirectionInitiale = disqueDirectionInitiale;
}

public static Config chargerConfig(String fichierPath){
    int tempsSimulation = 0, tempsEcritureDisque = 0, tempsChargePage = 0, interruptionHorloge = 0;
    String processusOrdonnancement = "";
    int processusQuantum = 0;
    String paginationPolitiqueAllocation = "";
    int paginationNombreCadresLocaux = 0, paginationNombreCadres = 0;
    String paginationAlgorithme = "";
    String disqueOrdonnancement = "";
    int disquePositionInitiale = 0;
    String disqueDirectionInitiale = "";

    try {
        File fichier = new File(fichierPath);
        Scanner scanner = new Scanner(fichier);

        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            if (ligne.contains("=")) {
                String[] parts = ligne.split("=");
                String cle = parts[0].trim();
                String valeur = parts[1].trim();

                switch (cle) {
                    case "tempsSimulation": tempsSimulation = Integer.parseInt(valeur); break;
                    case "tempsEcritureDisque": tempsEcritureDisque = Integer.parseInt(valeur); break;
                    case "tempsChargePage": tempsChargePage = Integer.parseInt(valeur); break;
                    case "interruptionHorloge": interruptionHorloge = Integer.parseInt(valeur); break;
                    case "processusOrdonnancement": processusOrdonnancement = valeur; break;
                    case "processusQuantum": processusQuantum = Integer.parseInt(valeur); break;
                    case "paginationPolitiqueAllocation": paginationPolitiqueAllocation = valeur; break;
                    case "paginationNombreCadresLocaux": paginationNombreCadresLocaux = Integer.parseInt(valeur); break;
                    case "paginationNombreCadres": paginationNombreCadres = Integer.parseInt(valeur); break;
                    case "paginationAlgorithme": paginationAlgorithme = valeur; break;
                    case "disqueOrdonnancement": disqueOrdonnancement = valeur; break;
                    case "disquePositionInitiale": disquePositionInitiale = Integer.parseInt(valeur); break;
                    case "disqueDirectionInitiale": disqueDirectionInitiale = valeur; break;
                }
            }
        }
        scanner.close();

    } catch (FileNotFoundException e) {
        System.out.println("Fichier de configuration non trouvé !");
        e.printStackTrace();
    }

    return new Config(tempsSimulation, tempsEcritureDisque, tempsChargePage, interruptionHorloge,
            processusOrdonnancement, processusQuantum, paginationPolitiqueAllocation,
            paginationNombreCadresLocaux, paginationNombreCadres, paginationAlgorithme,
            disqueOrdonnancement, disquePositionInitiale, disqueDirectionInitiale);
}


    }



