package pl.edu.pwr.zpi.autoasystent.view.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;

/**
 * Created by argo on 07 maj.
 */
public class DateDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    private Date date;

    public DateDialog() {
        date = new Date();
        listener = null;
    }

    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the dialog
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }

    public static String convertToString(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return DateUtils.dateToString(calendar.getTime());
    }
}