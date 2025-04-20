package shared;

import java.io.Serializable;
import java.util.Date;

public class Operation implements Serializable {
    private TypeOperation type;
    private double montant;
    private Date date;

    public Operation(TypeOperation type, double montant, Date date) {
        this.type = type;
        this.montant = montant;
        this.date = date;
    }

    public TypeOperation getType() { return type; }
    public double getMontant() { return montant; }
    public Date getDate() { return date; }
}