package pl.edu.pwr.zpi.autoasystent.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Marek on 08.04.2016.
 */
public class Reminder extends SugarRecord {

    private Car car;
    private Date reminderDate;
    private String reminderDesription;
    private String title;

    public Reminder() {

    }

    public Reminder(Car car, Date reminderDate, String reminderDesription, String title) {
        this.car = car;
        this.reminderDate = reminderDate;
        this.reminderDesription = reminderDesription;
        this.title = title;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getReminderDesription() {
        return reminderDesription;
    }

    public void setReminderDesription(String reminderDesription) {
        this.reminderDesription = reminderDesription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
