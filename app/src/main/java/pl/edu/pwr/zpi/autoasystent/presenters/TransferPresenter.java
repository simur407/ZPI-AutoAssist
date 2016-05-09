package pl.edu.pwr.zpi.autoasystent.presenters;

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
import pl.edu.pwr.zpi.autoasystent.model.Model;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.model.RefersTo;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.model.Reminder;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;

/**
 * Created by Marcin on 27.04.2016.
 */
public class TransferPresenter {

    private final static String fileDirName = "/dane";
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
                String line;
                ArrayList<String> list=new ArrayList<>();
                ArrayList<Car> carList=new ArrayList<>();
                ArrayList<ServiceJobs> serviceJobsList=new ArrayList<>();
                ArrayList<CarMaintenance> carMaintenanceList=new ArrayList<>();
                if((line=reader.readLine())!=null && line.equals("car"))
                {
                    while (!(line = reader.readLine()).equals("mot"))
                    {
                        list=splitData(line);
                        Car car=new Car();
                        car.setModel(Model.findById(Model.class, Long.parseLong(list.get(0))));
                        car.setVIN(list.get(1));
                        car.setProductionYear(stringToDate(list.get(2)));
                        car.setLicencePlate(list.get(3));
                        car.setCapacity(Integer.parseInt(list.get(4)));
                        car.setColor(list.get(5));
                        car.setPower(Integer.parseInt(list.get(6)));
                        car.setStartMileage(Integer.parseInt(list.get(7)));
                        car.setCarDescription(list.get(8));
                        carList.add(car);
                    }

                    while (!(line = reader.readLine()).equals("refueling"))
                    {
                        list=splitData(line);
                        Mot mot=new Mot();
                        mot.setCar(carList.get(Integer.parseInt(list.get(0))));
                        mot.setMotDate(stringToDate(list.get(1)));
                        mot.setMotDescription(list.get(2));
                    }

                    while (!(line = reader.readLine()).equals("insurance"))
                    {
                        list=splitData(line);
                        Refueling refueling=new Refueling();
                        refueling.setCar(carList.get(Integer.parseInt(list.get(0))));
                        refueling.setRefuelingDate(stringToDate(list.get(1)));
                        refueling.setRefuelingMileage(Integer.parseInt(list.get(2)));
                        refueling.setQuantity(Double.parseDouble(list.get(3)));
                        refueling.setRefuelingCost(Double.parseDouble(list.get(4)));
                        refueling.setRefuelingDescription(list.get(5));
                    }

                    while (!(line = reader.readLine()).equals("reminder"))
                    {
                        list=splitData(line);
                        Insurance insurance=new Insurance();
                        insurance.setCar(carList.get(Integer.parseInt(list.get(0))));
                        insurance.setInsuranceDate(stringToDate(list.get(1)));
                        insurance.setInsuranceDescription(list.get(2));
                        insurance.setInsuranceCost(Double.parseDouble(list.get(3)));
                    }

                    while (!(line = reader.readLine()).equals("serviceJobs"))
                    {
                        list=splitData(line);
                        Reminder reminder=new Reminder();
                        reminder.setCar(carList.get(Integer.parseInt(list.get(0))));
                        reminder.setReminderDate(stringToDate(list.get(1)));
                        reminder.setReminderDesription(list.get(2));
                        reminder.setTitle(list.get(3));
                    }

                    while (!(line = reader.readLine()).equals("carMaintenance"))
                    {
                        list=splitData(line);
                        ServiceJobs serviceJobs=new ServiceJobs();
                        serviceJobs.setCar(carList.get(Integer.parseInt(list.get(0))));
                        serviceJobs.setServiceDate(stringToDate(list.get(1)));
                        serviceJobs.setServiceCost(Double.parseDouble(list.get(2)));
                        serviceJobs.setServiceGarage(list.get(3));
                        serviceJobs.setServiceMileage(Integer.parseInt(list.get(4)));
                        serviceJobs.setServiceDescription(list.get(5));
                        serviceJobsList.add(serviceJobs);
                    }

                    while (!(line = reader.readLine()).equals("refersTo"))
                    {
                        list=splitData(line);
                        CarMaintenance carMaintenance=new CarMaintenance();
                        carMaintenance.setMaintenanceName(list.get(0));
                        carMaintenanceList.add(carMaintenance);
                    }

                    while ((line = reader.readLine()) != null)
                    {
                        list=splitData(line);
                        RefersTo refersTo=new RefersTo();
                        refersTo.setService(serviceJobsList.get(Integer.parseInt(list.get(0))));
                        refersTo.setMaintenance(carMaintenanceList.get(Integer.parseInt(list.get(1))));
                    }
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
                if(!file.exists()) file.mkdirs();
                file = new File(file, filename);
                if(!file.exists()) file.createNewFile();
                PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
                writer.println("car");
                for(Car c:Car.listAll(Car.class))
                {
                    ArrayList<String> temp=new ArrayList<>();
                    temp.add(Long.toString(c.getModel().getId()));
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
                    ArrayList<String> temp=new ArrayList<>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(m.getCar())));
                    temp.add(dateToString(m.getMotDate()));
                    temp.add(m.getMotDescription());
                    writer.println(joinData(temp));
                }
                writer.println("refueling");
                for(Refueling r:Refueling.listAll(Refueling.class))
                {
                    ArrayList<String> temp=new ArrayList<>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(r.getCar())));
                    temp.add(dateToString(r.getRefuelingDate()));
                    temp.add(Integer.toString(r.getRefuelingMileage()));
                    temp.add(Double.toString(r.getQuantity()));
                    temp.add(Double.toString(r.getRefuelingCost()));
                    temp.add(r.getRefuelingDescription());
                    writer.println(joinData(temp));
                }
                writer.println("insurance");
                for(Insurance i:Insurance.listAll(Insurance.class))
                {
                    ArrayList<String> temp=new ArrayList<>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(i.getCar())));
                    temp.add(dateToString(i.getInsuranceDate()));
                    temp.add(i.getInsuranceDescription());
                    temp.add(Double.toString(i.getInsuranceCost()));
                    writer.println(joinData(temp));
                }
                writer.println("reminder");
                for(Reminder r:Reminder.listAll(Reminder.class))
                {
                    ArrayList<String> temp=new ArrayList<>();
                    temp.add(Integer.toString(Car.listAll(Car.class).indexOf(r.getCar())));
                    temp.add(dateToString(r.getReminderDate()));
                    temp.add(r.getReminderDesription());
                    temp.add(r.getTitle());
                    writer.println(joinData(temp));
                }
                writer.println("serviceJobs");
                for(ServiceJobs s:ServiceJobs.listAll(ServiceJobs.class))
                {
                    ArrayList<String> temp=new ArrayList<>();
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
                    ArrayList<String> temp=new ArrayList<>();
                    temp.add(c.getMaintenanceName());
                    writer.println(joinData(temp));
                }
                writer.println("refersTo");
                for(RefersTo r:RefersTo.listAll(RefersTo.class))
                {
                    ArrayList<String> temp=new ArrayList<>();
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
