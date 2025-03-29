package org.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class DonneesChargeur {

    public static Queue<Processus> chargerProcessus(String fichier) {
        Queue<Processus> processusQueue = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;

            while ((ligne = br.readLine()) != null) {
                Processus p = parserLigneProcessus(ligne);
                if (p != null) {
                    processusQueue.add(p);
                }
            }

        } catch (IOException e) {
            System.out.println("il ya une erreur lors du chargement du fichier : " + e.getMessage());
        }

        return processusQueue;
    }

    private static Processus parserLigneProcessus(String ligne) {
        int id = -1, tempsArrivee = -1, priorite = -1, tempsExecution = -1;
        Queue<Evenement> evenements = new LinkedList<>();

        String[] parts = ligne.split(",");
        for (String part : parts) {
            String[] keyValue = part.split("=");

            if (keyValue.length < 2) {
                System.out.println("il ya une erreur : la ligne est mal formatée => " + part);
                continue;
            }

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "id":
                    id = Integer.parseInt(value);
                    break;
                case "tempsArrivee":
                    tempsArrivee = Integer.parseInt(value);
                    break;
                case "priorite":
                    priorite = Integer.parseInt(value);
                    break;
                case "tempsExecution":
                    tempsExecution = Integer.parseInt(value);
                    break;
                case "evenements":
                    evenements = parserEvenements(value);
                    break;
                default:
                    System.out.println("il ya une erreur : le champ inconnu '" + key + "'");
            }
        }

        if (id == -1 || tempsArrivee == -1 || priorite == -1 || tempsExecution == -1) {
            System.out.println("il ya une erreur : le processus est incomplet => " + ligne);
            return null;
        }

        return new Processus(id, tempsArrivee, priorite, tempsExecution, evenements);
    }

    private static Queue<Evenement> parserEvenements(String valeur) {
        Queue<Evenement> evenements = new LinkedList<>();
        String[] events = valeur.split(",");

        for (String event : events) {
            event = event.trim(); // ici on enleve les espaces inutiles

            if (!event.contains(":")) {
                System.out.println("il ya une erreur : le format est incorrect pour l'événement, ':' manquant => " + event);
                continue;
            }

            String[] eventParts = event.split(":");
            if (eventParts.length != 2) {
                System.out.println("il ya une erreur : le format est incorrect pour l'événement, trop de paramètres => " + event);
                continue;
            }

            String type = eventParts[0].trim().toUpperCase(); // Conversion en majuscule pour eviter les erreur
            int parametre;

            try {
                parametre = Integer.parseInt(eventParts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("il ya une erreur : Le temps est invalide dans l'événement '" + event + "'");
                continue;
            }

            // on va verifier les evenements possibles
            if (type.equals("CALCUL") || type.equals("ECRITURE") || type.equals("LECTURE") ||
                    type.equals("DORMIR") || type.equals("FIN")) {
                evenements.add(new Evenement(type, parametre));
            } else {
                System.out.println("il ya une erreur : Le type d'événement est inconnu => " + type);
            }
        }

        return evenements;
    }

}
