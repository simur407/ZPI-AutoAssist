package pl.edu.pwr.zpi.autoasystent.presenters;

import android.app.Service;
import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.CarMaintenance;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.model.RefersTo;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.model.Reminder;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;

/**
 * Created by Marcin on 27.04.2016.
 */
public class TransferPresenter {

    private final static String fileDirName = "/pl.edu.pwr.zpi.autoasystent";
    private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isThereExternal() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String doubleComma(String s)
    {
        return s.replace(",", ",,");
    }

    public static String singleComma(String s)
    {
        return s.replace(",,",",");
    }

    public static ArrayList<String> splitData(String s)
    {
        ArrayList<String> result = new ArrayList<String>(Arrays.asList(s.split("(?<!,),(?!,)")));
        for(int i=0; i<result.size(); i++)
        {
            result.set(i, (doubleComma(result.get(i))));
        }
        return result;
    }

    public static String dateToString(Date d)
    {
        return df.format(d);
    }

    public static Date stringToDate(String s)
    {
        try
        {
            return df.parse(s);
        }
        catch (java.text.ParseException e)
        {
            e.printStackTrace();
        }

        return Calendar.getInstance().getTime();
    }

    public static String joinData(ArrayList<String> list)
    {
        StringBuilder sb=new StringBuilder();
        for(String s: list) {
            sb.append(singleComma(s));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void loadFile(Context context, boolean external, String filename, boolean overwrite)
    {
        try
        {
            if (!external || isThereExternal()) {

                File file;
                if (external) {
                    file = new File(context.getExternalFilesDir(null), fileDirName);
                } else {
                    file = new File(context.getFilesDir(), fileDirName);
                }
                file = new File(file, filename);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                if (overwrite)
                {
                    RefersTo.deleteAll(RefersTo.class);
                    ServiceJobs.deleteAll(ServiceJobs.class);
                    CarMaintenance.deleteAll(CarMaintenance.class);
                    Reminder.deleteAll(Reminder.class);
                    Mot.deleteAll(Mot.class);
                    Refueling.deleteAll(Refueling.class);
                    Insurance.deleteAll(Insurance.class);
                    Car.deleteAll(Car.class);
                }
                while ((line = reader.readLine()) != null) {

                }



                reader.close();

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void saveFile(Context context, boolean external, String filename)
    {
        try {
            if(!external || isThereExternal())
            {
                File file;
                if(external)
                {
                    file = new File(context.getExternalFilesDir(null), fileDirName);
                }
                else
                {
                    file = new File(context.getFilesDir(), fileDirName);
                }
                file = new File(file, filename);
                PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
                writer.println("car");
                for(Car c:Car.listAll(Car.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(c.getModel().getModelName());
                    temp.add(c.getVIN());
                    temp.add(dateToString(c.getProductionYear()));
                    temp.add(c.getLicencePlate());
                    temp.add(Integer.toString(c.getCapacity()));
                    temp.add(c.getColor());
                    temp.add(Integer.toString(c.getPower()));
                    temp.add(Integer.toString(c.getStartMileage()));
                    temp.add(c.getCarDescription());
                    writer.println(joinData(temp));
                }
                writer.println("mot");
                for(Mot m:Mot.listAll(Mot.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(m.getCar())));
                    temp.add(dateToString(m.getMotDate()));
                    temp.add(m.getMotDescription());
                    writer.println(joinData(temp));
                }
                writer.println("refueling");
                for(Refueling r:Refueling.listAll(Refueling.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(r.getCar())));
                    temp.add(dateToString(r.getRefuelingDate()));
                    temp.add(Integer.toString(r.getRefuelingMileage()));
                    temp.add(Integer.toString(r.getQuantity()));
                    temp.add(Double.toString(r.getRefuelingCost()));
                    temp.add(r.getRefuelingDescription());
                    writer.println(joinData(temp));
                }
                writer.println("insurance");
                for(Insurance i:Insurance.listAll(Insurance.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(i.getCar())));
                    temp.add(dateToString(i.getInsuranceDate()));
                    temp.add(i.getInsuranceDescription());
                    temp.add(Double.toString(i.getInsuranceCost()));
                    writer.println(joinData(temp));
                }
                writer.println("reminder");
                for(Reminder r:Reminder.listAll(Reminder.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(r.getCar())));
                    temp.add(dateToString(r.getReminderDate()));
                    temp.add(r.getReminderDesription());
                    temp.add(r.getTitle());
                    writer.println(joinData(temp));
                }
                writer.println("serviceJobs");
                for(ServiceJobs s:ServiceJobs.listAll(ServiceJobs.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(s.getCar())));
                    temp.add(dateToString(s.getServiceDate()));
                    temp.add(Double.toString(s.getServiceCost()));
                    temp.add(s.getGarage());
                    temp.add(Integer.toString(s.getServiceMileage()));
                    temp.add(s.getServiceDescription());
                    writer.println(joinData(temp));
                }
                writer.println("carMaintenance");
                for(CarMaintenance c:CarMaintenance.listAll(CarMaintenance.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(c.getMaintenanceName());
                    writer.println(joinData(temp));
                }
                writer.println("refersTo");
                for(RefersTo r:RefersTo.listAll(RefersTo.class))
                {
                    ArrayList<String> temp=new ArrayList<String>();
                    temp.add(Integer.toString(ServiceJobs.listAll(ServiceJobs.class).indexOf(r.getService())));
                    temp.add(Integer.toString(CarMaintenance.listAll(CarMaintenance.class).indexOf(r.getMaintenance())));
                    writer.println(joinData(temp));
                }
                writer.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
