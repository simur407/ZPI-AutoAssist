package pl.edu.pwr.zpi.autoasystent.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import pl.edu.pwr.zpi.autoasystent.R;

/**
 * Created by Marek on 16.05.2016.
 */
public enum Achievement {
    FIRST_CAR_ADDED(R.string.first_car_added, R.drawable.ic_car_24dp),
    MOT_INSURANCE_ADDED(R.string.mot_insurance_added, R.drawable.ic_car_24dp),
    ONE_YEAR_USING(R.string.one_year_using, R.drawable.ic_car_24dp),
    TWO_YEARS_USING(R.string.two_years_using, R.drawable.ic_car_24dp),
    FIRST_REFUELLING(R.string.first_refuelling, R.drawable.pump_icon),
    FIFTH_REFUELLING(R.string.fifth_refuelling, R.drawable.pump_icon),
    TWENTIETH_REFUELLING(R.string.twentieth_refuelling, R.drawable.pump_icon),
    FIRST_SERVICE(R.string.first_service, R.drawable.pump_icon),
    FIFTH_SERVICE(R.string.fifth_service, R.drawable.pump_icon),
    TENTH_SERVICE(R.string.tenth_service, R.drawable.pump_icon),
    TWENTIETH_SERVICE(R.string.twentieth_service, R.drawable.pump_icon);

    Achievement(@StringRes int id, @DrawableRes int drawable) {
        this.id = id;
        this.drawable = drawable;
    }

    public int id, drawable;


}
