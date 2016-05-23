package pl.edu.pwr.zpi.autoasystent.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.model.Achievement;

/**
 * Created by Marcin on 23.05.2016.
 */
public class AchievementUtils
{

    Context context;
    SharedPreferences sharedPreferences;
    public AchievementUtils(Context context)
    {
        this.context=context;
        sharedPreferences =context.getSharedPreferences("pl.edu.pwr.zpi.autoasystent", 0);
    }

    //TODO: Dodać brakujący acziwów
    //TODO: Dodać zapisywane acziwów
    //TODO: Dodać wyświetlanie acziwów

    public void checkCar()
    {
        if (!Achievement.FIRST_CAR_ADDED.isEarned())
        {
            Achievement.FIRST_CAR_ADDED.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.FIRST_CAR_ADDED.getId()) ,Toast.LENGTH_LONG).show();
        }
    }

    public void checkService()
    {

        int count = sharedPreferences.getInt("serviceCount", 0) + 1;

        if (!Achievement.FIRST_SERVICE.isEarned() && count >=1)
        {
            Achievement.FIRST_SERVICE.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.FIRST_SERVICE.getId()) ,Toast.LENGTH_LONG).show();
        }

        if (!Achievement.FIFTH_SERVICE.isEarned() && count >=5)
        {
            Achievement.FIFTH_SERVICE.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.FIFTH_SERVICE.getId()) ,Toast.LENGTH_LONG).show();
        }

        if (!Achievement.TENTH_SERVICE.isEarned() && count >=10)
        {
            Achievement.TENTH_SERVICE.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.TENTH_SERVICE.getId()) ,Toast.LENGTH_LONG).show();
        }

        if (!Achievement.TWENTIETH_SERVICE.isEarned() && count >=20)
        {
            Achievement.TWENTIETH_SERVICE.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.TWENTIETH_SERVICE.getId()) ,Toast.LENGTH_LONG).show();
        }

        sharedPreferences.edit().putInt("serviceCount", count).apply();

    }

    public void checkRefueling()
    {
        int count = sharedPreferences.getInt("refuellingCount", 0) + 1;

        if (!Achievement.FIRST_REFUELLING.isEarned() && count >=1)
        {
            Achievement.FIRST_REFUELLING.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.FIRST_REFUELLING.getId()) ,Toast.LENGTH_LONG).show();
        }

        if (!Achievement.FIFTH_REFUELLING.isEarned() && count >=5)
        {
            Achievement.FIFTH_REFUELLING.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.FIFTH_REFUELLING.getId()) ,Toast.LENGTH_LONG).show();
        }

        if (!Achievement.TWENTIETH_REFUELLING.isEarned() && count >=20)
        {
            Achievement.TWENTIETH_REFUELLING.makeEarned();
            Toast.makeText(context, context.getResources().getString(Achievement.TWENTIETH_REFUELLING.getId()) ,Toast.LENGTH_LONG).show();
        }

        sharedPreferences.edit().putInt("refuellingCount", count).apply();
    }


    public void checkTime()
    {
        long time = System.currentTimeMillis();
        if(sharedPreferences.contains("installationTime"))
        {
            long installationTime= sharedPreferences.getLong("installationTime", 0);
            Date installationDate=new Date(installationTime);
            Date date=new Date(time);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
            int installationYear=Integer.parseInt(simpleDateFormat.format(installationDate));
            int year=Integer.parseInt(simpleDateFormat.format(date));
            int yearDiff=year-installationYear;
            if (!Achievement.TWO_YEARS_USING.isEarned() && yearDiff>=2)
            {
                Achievement.TWO_YEARS_USING.makeEarned();
                Toast.makeText(context, context.getResources().getString(Achievement.TWO_YEARS_USING.getId()) ,Toast.LENGTH_LONG).show();
            }
            if (!Achievement.ONE_YEAR_USING.isEarned() && yearDiff>=1)
            {
                Achievement.ONE_YEAR_USING.makeEarned();
                Toast.makeText(context, context.getResources().getString(Achievement.ONE_YEAR_USING.getId()) ,Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            sharedPreferences.edit().putLong("installationTime", time).apply();
        }
    }

}
