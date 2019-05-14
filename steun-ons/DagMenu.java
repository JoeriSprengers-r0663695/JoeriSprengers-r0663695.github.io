package be.ucll.gerecht.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
public class DagMenu {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)

    private Gerecht soep;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Gerecht dagschotel;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Gerecht veggie;

    @Id
    @NotNull
    private Date datum;

    @NotNull
    private String datumGegevens;

    public DagMenu() {

    }

    public DagMenu(Date datum, Gerecht soep, Gerecht dagschotel, Gerecht veggie) {
        setDatum(datum);
        setSoep(soep);
        setDagschotel(dagschotel);
        setVeg(veggie);
    }

    public String getDatumString() {

        return datumGegevens;
    }

    public Gerecht getDagschotel() {

        return dagschotel;
    }

    public Gerecht getSoep() {

        return soep;
    }

    public Gerecht getVeggie() {

        return veggie;
    }

    public Date getDatum() {

        return datum;
    }



    public void setDagschotel(Gerecht dagschotel) {
        if(!dagschotel.getType().equals(Type.Dagschotel)) throw new IllegalArgumentException("Dit is geen dagschotel");
        this.dagschotel = dagschotel;
    }

    public void setSoep(Gerecht soep) {
        if(!soep.getType().equals(Type.Soep)) throw new IllegalArgumentException("Dit is geen soep gerecht");
        this.soep = soep;
    }

    public void setVeg(Gerecht veggie) {
        if(!veggie.getType().equals(Type.Veggie)) throw new IllegalArgumentException("dit is geen vegitarisch gerecht");
        this.veggie = veggie;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
        SetDatePeriod(datum);
    }

    private void SetDatePeriod(Date datum) {
        Calendar c = Calendar.getInstance();
        c.setTime(datum);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case 1:
                this.datumGegevens = "Maandag";
                break;
            case 2:
                this.datumGegevens = "Dinsdag";
                break;
            case 3:
                this.datumGegevens = "Woensdag";
                break;
            case 4:
                this.datumGegevens = "Donderdag";
                break;
            case 5:
                this.datumGegevens = "Vrijdag";
                break;
            case 6:
                this.datumGegevens = "Zaterdag";
                break;
            case 7:
                this.datumGegevens = "Zondag";
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof DagMenu) {
            DagMenu dagMenu = (DagMenu) o;
            if(this.getDatum().equals(dagMenu.getDatum())) return true;
        }
        return false;
    }

}
