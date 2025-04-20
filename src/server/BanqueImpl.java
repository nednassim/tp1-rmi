// src/server/BanqueImpl.java
package server;

import shared.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BanqueImpl extends UnicastRemoteObject implements BanqueInterface {
    private Map<Integer, Compte> comptes;

    public BanqueImpl() throws RemoteException {
        super();
        comptes = new HashMap<>();
    }

    @Override
    public Compte creerCompteCourant(int numero, String client, double soldeInitial) throws RemoteException {
        Compte compte = new CompteCourant(numero, client, soldeInitial);
        comptes.put(numero, compte);
        System.out.println("[Serveur] Compte courant créé pour " + client + " (n°" + numero + ")");
        return compte;
    }

    @Override
    public Compte creerCompteEpargne(int numero, String client, double soldeInitial) throws RemoteException {
        Compte compte = new CompteEpargne(numero, client, soldeInitial);
        comptes.put(numero, compte);
        System.out.println("[Serveur] Compte épargne créé pour " + client + " (n°" + numero + ")");
        return compte;
    }

    @Override
    public double obtenirSolde(int numeroCompte) throws RemoteException {
        Compte compte = comptes.get(numeroCompte);
        if (compte != null) {
            System.out.println("[Serveur] Solde demandé pour le compte n°" + numeroCompte);
            return compte.getSolde();
        }
        throw new RemoteException("Compte non trouvé");
    }

    @Override
    public void effectuerVersement(int numeroCompte, double montant) throws RemoteException {
        Compte compte = comptes.get(numeroCompte);
        if (compte != null) {
            Operation operation = new Operation(TypeOperation.VERSEMENT, montant, new Date());
            compte.effectuerOperation(operation);
            System.out.println("[Serveur] Versement de " + montant + "€ sur le compte n°" + numeroCompte);
        } else {
            throw new RemoteException("Compte non trouvé");
        }
    }

    @Override
    public void effectuerRetrait(int numeroCompte, double montant) throws RemoteException {
        Compte compte = comptes.get(numeroCompte);
        if (compte != null) {
            if (compte.getSolde() >= montant) {
                Operation operation = new Operation(TypeOperation.RETRAIT, montant, new Date());
                compte.effectuerOperation(operation);
                System.out.println("[Serveur] Retrait de " + montant + "€ sur le compte n°" + numeroCompte);
            } else {
                throw new RemoteException("Solde insuffisant");
            }
        } else {
            throw new RemoteException("Compte non trouvé");
        }
    }

    @Override
    public List<Operation> obtenirOperations(int numeroCompte) throws RemoteException {
        Compte compte = comptes.get(numeroCompte);
        if (compte != null) {
            System.out.println("[Serveur] Opérations demandées pour le compte n°" + numeroCompte);
            return compte.getOperations();
        }
        throw new RemoteException("Compte non trouvé");
    }
}