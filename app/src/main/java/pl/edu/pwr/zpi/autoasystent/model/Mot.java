package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Marcin on 11.04.2016.
 */
public class Mot extends SugarRecord {

    private Car car;
    private Date motDate;
    private String motDescription;

    public Mot()
    {

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getMotDate() {
        return motDate;
    }

    public void setMotDate(Date motDate) {
        this.motDate = motDate;
    }

    public String getMotDescription() {
        return motDescription;
    }

    public void setMotDescription(String motDescription) {
        this.motDescription = motDescription;
    }
}
