package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.presenters.CarViewPresenter;
import pl.edu.pwr.zpi.autoasystent.service.InsuranceService;
import pl.edu.pwr.zpi.autoasystent.service.MotService;
import pl.edu.pwr.zpi.autoasystent.view.CarViewPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.InsuranceAddDialog;
import pl.edu.pwr.zpi.autoasystent.view.dialog.MotAddDialog;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;

/**
 * Created by Marcin on 25.04.2016.
 */
public class CarViewFragment extends Fragment implements TabFragment, CarViewPanel {

    protected TextView makeField;
    protected TextView modelField;
    protected TextView yearField;
    protected TextView powerField;
    protected TextView plateField;
    protected TextView vinField;
    protected TextView capacityField;
    protected TextView motField;
    protected TextView insuranceField;
    protected long carId;

    @Override
    public String getTabName() {
        return "Szczegóły samochodu";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_view, container, false);

        //TODO wczytać carId
        carId = getArguments().getLong(CarActivity.ID_KEY);
        CarViewPresenter presenter=new CarViewPresenter(this);

        makeField = (TextView)view.findViewById(R.id.make_field);
        modelField = (TextView)view.findViewById(R.id.model_field);
        yearField = (TextView)view.findViewById(R.id.year_field);
        powerField = (TextView)view.findViewById(R.id.power_field);
        plateField = (TextView)view.findViewById(R.id.plate_field);
        vinField = (TextView)view.findViewById(R.id.vin_field);
        capacityField = (TextView)view.findViewById(R.id.capacity_field);
        motField = (TextView)view.findViewById(R.id.mot_field);
        insuranceField = (TextView)view.findViewById(R.id.insurance_field);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsuranceAddDialog(getActivity()).show();
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MotAddDialog(getActivity()).show();
            }
        });

        presenter.setCarData(carId);

        return view;
    }

    @Override
    public void setCarData(Car car) {

        makeField.setText(car.getModel().getMake().getMakeName());
        modelField.setText(car.getModel().getModelName());
        yearField.setText((new SimpleDateFormat("yyyy")).format(car.getProductionYear()));
        powerField.setText(Integer.toString(car.getPower()));
        plateField.setText(car.getLicencePlate());
        vinField.setText(car.getVIN());
        capacityField.setText(Integer.toString(car.getCapacity()));
        motField.setText((new SimpleDateFormat("yyyy")).format(MotService.getInstance().getLatest(car)));
        insuranceField.setText((new SimpleDateFormat("yyyy")).format(InsuranceService.getInstance().getLatest(car)));

    }
}
