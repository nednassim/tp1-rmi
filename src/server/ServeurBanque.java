package server;

import shared.BanqueInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServeurBanque {
    public static void main(String[] args) {
        try {
            // Création du registre RMI
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Création de l'implémentation
            BanqueInterface banque = new BanqueImpl();
            
            // Enregistrement
            registry.rebind("BanqueService", banque);
            
            System.out.println("Serveur bancaire prêt...");
        } catch (Exception e) {
            System.err.println("Erreur serveur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}