package pl.edu.pwr.zpi.autoasystent.service;

import pl.edu.pwr.zpi.autoasystent.model.Reminder;

/**
 * Created by Marek on 10.04.2016.
 */
public class ReminderService {

    private ReminderService instance = null;

    public ReminderService getInstance() {
        if (instance == null) {
            instance = new ReminderService();
        }
        return instance;
    }

    private ReminderService() {
    }

    public void saveReminder(Reminder reminder) {
        Reminder.save(reminder);
    }

    ;

    public void deleteReminder(Reminder reminder) {
        Reminder.delete(reminder);
    }
}
