package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Compte implements Serializable {
    private int numero;
    private String client;
    private double solde;
    private List<Operation> operations;

    public Compte(int numero, String client, double soldeInitial) {
        this.numero = numero;
        this.client = client;
        this.solde = soldeInitial;
        this.operations = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public String getClient() { return client; }
    public double getSolde() { return solde; }

    public void setNumero(int numero) {
       this.numero = numero;
    }
    public void setClient(String client) {
       this.client = client;
    }
    public void setSolde(double solde) {
       this.solde = solde;
    }
    public List<Operation> getOperations() { return operations; }

    public void effectuerOperation(Operation operation) {
        if (operation.getType() == TypeOperation.RETRAIT) {
            solde -= operation.getMontant();
        } else {
            solde += operation.getMontant();
        }
        operations.add(operation);
    }
}