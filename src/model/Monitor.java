package model;

import javax.persistence.Entity;
import javax.persistence.Column;

/**
 * @author rsamoylov
 */
@Entity
public class Monitor extends Resource implements Inventarable
{
    @Column(name = "Inventarno", unique = true, length = 10)
    private String inventarno;
    public Monitor()
    {
        super();
    }

    public Monitor(String name,String inventarno) {
        super(name);
        this.inventarno = inventarno;
    }

    public void setInvenarno(String inventarno) {
        this.inventarno = inventarno;
    }

    public String getInvenarno() {
        return inventarno;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        final Monitor mon = (Monitor) o;
        if ( !inventarno.equals( mon.inventarno ) ) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode()+5*inventarno.hashCode();
    }

    @Override
    public String toString() {
        return super.toString()+" "+inventarno;
    }

}
