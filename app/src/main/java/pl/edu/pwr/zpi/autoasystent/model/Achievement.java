package pl.edu.pwr.zpi.autoasystent.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.edu.pwr.zpi.autoasystent.R;

/**
 * Created by Marek on 16.05.2016.
 */
public enum Achievement {
    FIRST_CAR_ADDED(R.string.first_car_added, R.drawable.first_car),
    MOT_INSURANCE_ADDED(R.string.mot_insurance_added, R.drawable.insurance_mot),
    ONE_YEAR_USING(R.string.one_year_using, R.drawable.year1),
    TWO_YEARS_USING(R.string.two_years_using, R.drawable.years2),
    FIRST_REFUELLING(R.string.first_refuelling, R.drawable.refueling1),
    FIFTH_REFUELLING(R.string.fifth_refuelling, R.drawable.refueling5),
    TWENTIETH_REFUELLING(R.string.twentieth_refuelling, R.drawable.refueling20),
    FIRST_SERVICE(R.string.first_service, R.drawable.service1),
    FIFTH_SERVICE(R.string.fifth_service, R.drawable.service5),
    TENTH_SERVICE(R.string.tenth_service, R.drawable.service10),
    TWENTIETH_SERVICE(R.string.twentieth_service, R.drawable.service20);


    Achievement(@StringRes int id, @DrawableRes int drawable) {
        this.id = id;
        this.drawable = drawable;
        this.earned = false;
    }

    private int id, drawable;

    private boolean earned;

    public boolean isEarned() {
        return earned;
    }

    public void setEarned(boolean earned) {
        this.earned = earned;
    }

    public void makeEarned() {
        earned = true;
    }

    public int getId() {
        return id;
    }

    public int getDrawable() {
        return drawable;
    }
}
