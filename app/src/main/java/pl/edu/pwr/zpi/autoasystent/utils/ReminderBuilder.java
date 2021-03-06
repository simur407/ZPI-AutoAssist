package pl.edu.pwr.zpi.autoasystent.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;

import com.orm.dsl.NotNull;

import java.util.Calendar;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.view.service.ReminderNotificationService;

/**
 * Klasa budująca przypomnienie uruchamiająca je i dodająca do bazy.
 *
 * @author Szymon Bartczak
 * @date 2016-05-10
 */
public class ReminderBuilder {

    private Context context;
    private String description;
    private String title;
    private Date date;


    public ReminderBuilder(@NotNull Context context) {
        this.context = context;
    }

    public ReminderBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public ReminderBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ReminderBuilder setTitle(@StringRes int title) {
        this.title = StringUtils.getStringFromId(context, title);
        return this;
    }

    public ReminderBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReminderBuilder setDescription(@StringRes int description) {
        this.description = StringUtils.getStringFromId(context, description);
        return this;
    }

    /**
     * Ustawia przypomnienie. Każda metoda z {@link ReminderBuilder} wywołana po tym nie odniesie skutku.
     * Przed użyciem należy wywołać
     * {@link ReminderBuilder#setDate(Date)}, {@link ReminderBuilder#setTitle(String)} i {@link ReminderBuilder#setDescription(String)}.
     */
    public void set() {
        if(title == null || description == null || date == null) {
            throw new IllegalArgumentException("Not all required params set.");
        }
        Intent intent = new Intent(context, ReminderNotificationService.class);
        Bundle args = new Bundle();
        args.putString(ReminderNotificationService.ARGS_TITLE, title);
        args.putString(ReminderNotificationService.ARGS_DESCRIPTION, description);
        intent.putExtras(args);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pIntent = PendingIntent.getService(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int time = SettingsUtils.getReminderTime(context);
        calendar.set(Calendar.HOUR_OF_DAY, time / 100);
        calendar.set(Calendar.MINUTE, time % 100);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);

    }
}
