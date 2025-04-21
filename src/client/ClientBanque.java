// src/client/ClientBanque.java
package client;

import shared.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.*;

public class ClientBanque {
    public static void main(String[] args) {
        try {
            // Connexion au registre RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Recherche du service
            BanqueInterface banque = (BanqueInterface) registry.lookup("BanqueService");
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\n=== GESTION BANCAIRE ===");
                System.out.println("1. Créer compte courant");
                System.out.println("2. Créer compte épargne");
                System.out.println("3. Consulter solde");
                System.out.println("4. Effectuer versement");
                System.out.println("5. Effectuer retrait");
                System.out.println("6. Quitter");
                System.out.print("Votre choix: ");
                
                int choix = scanner.nextInt();
                scanner.nextLine();
                
                try {
                    switch (choix) {
                        case 1:
                            creerCompte(banque, scanner, "courant");
                            break;
                            
                        case 2:
                            creerCompte(banque, scanner, "épargne");
                            break;
                            
                        case 3:
                            consulterSolde(banque, scanner);
                            break;
                            
                        case 4:
                            effectuerOperation(banque, scanner, "versement");
                            break;
                            
                        case 5:
                            effectuerOperation(banque, scanner, "retrait");
                            break;
                            
                        case 6:
                            System.out.println("Au revoir!");
                            scanner.close();
                            System.exit(0);
                            
                        default:
                            System.out.println("Choix invalide!");
                    }
                } catch (Exception e) {
                    System.err.println("Erreur: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur client: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void creerCompte(BanqueInterface banque, Scanner scanner, String type) throws Exception {
        System.out.print("\nNuméro de compte: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom du client: ");
        String client = scanner.nextLine();
        System.out.print("Solde initial: ");
        double solde = scanner.nextDouble();
        
        if (type.equals("courant")) {
            banque.creerCompteCourant(numero, client, solde, new Date(), 0);
        } else {
            banque.creerCompteEpargne(numero, client, solde, new Date(), 0);
        }
        
        System.out.println("Compte " + type + " créé avec succès!");
    }
    
    private static void consulterSolde(BanqueInterface banque, Scanner scanner) throws Exception {
        System.out.print("\nNuméro de compte: ");
        int numero = scanner.nextInt();
        double solde = banque.obtenirSolde(numero);
        System.out.println("Solde: " + solde + "€");
    }
    
    private static void effectuerOperation(BanqueInterface banque, Scanner scanner, String type) throws Exception {
        System.out.print("\nNuméro de compte: ");
        int numero = scanner.nextInt();
        System.out.print("Montant: ");
        double montant = scanner.nextDouble();
        
        if (type.equals("versement")) {
            banque.effectuerVersement(numero, montant);
        } else {
            banque.effectuerRetrait(numero, montant);
        }
        
        System.out.println("Opération effectuée avec succès!");
    }
}