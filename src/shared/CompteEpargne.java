package shared;

import java.util.*;

public class CompteEpargne extends Compte {
    private double taux = 0.0;

    public CompteEpargne(int numero, String client, double soldeInitial, Date date_creation, double taux) {
        super(numero, client, soldeInitial, date_creation);
        this.taux = taux;
    }

    public double getTaux() {
       return taux;
    }
    public void setTaux(double taux) {
       this.taux = taux;
    }
    @Override
    public void effectuerOperation(Operation operation) {
        if (operation.getType() == TypeOperation.RETRAIT) {
            throw new IllegalArgumentException("Retrait non autorisé sur un compte épargne");
        }
        super.effectuerOperation(operation);
    }
    @Override
    public String toString() {
        return "CompteEpargne{" +
                "numero=" + getNumero() +
                ", client='" + getClient() + '\'' +
                ", solde=" + getSolde() +
                ", date_creation=" + getDateCreation() +
                ", taux=" + taux +
                '}';
    }
}