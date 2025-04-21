package shared;

import java.util.*;

public class CompteCourant extends Compte {
    private double decouvert = 0.0;

    public CompteCourant(int numero, String client, double soldeInitial, Date date_creation, double decouvert) {
        super(numero, client, soldeInitial, date_creation);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
       return decouvert;
    }
    public void setDecouvert(double decouvert) {
       this.decouvert = decouvert;
    }
    @Override
    public void effectuerOperation(Operation operation) {
        if (operation.getType() == TypeOperation.RETRAIT) {
            if (getSolde() - operation.getMontant() < -decouvert) {
                throw new IllegalArgumentException("Retrait non autorisé : découvert dépassé");
            }
        }
        super.effectuerOperation(operation);
    }
    @Override
    public String toString() {
        return "CompteCourant{" +
                "numero=" + getNumero() +
                ", client='" + getClient() + '\'' +
                ", solde=" + getSolde() +
                ", date_creation=" + getDateCreation() +
                ", decouvert=" + decouvert +
                '}';
    }    
}
