package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;


public interface BanqueInterface extends Remote {
    Compte creerCompteCourant(int numero, String client, double soldeInitial, Date date, double decouvert) throws RemoteException;
    Compte creerCompteEpargne(int numero, String client, double soldeInitial, Date date, double taux) throws RemoteException;
    double obtenirSolde(int numeroCompte) throws RemoteException;
    void effectuerVersement(int numeroCompte, double montant) throws RemoteException;
    void effectuerRetrait(int numeroCompte, double montant) throws RemoteException;
    List<Operation> obtenirOperations(int numeroCompte) throws RemoteException;
    void creerCompteCourant(int numero, String client, double solde, Date date, int i);
}