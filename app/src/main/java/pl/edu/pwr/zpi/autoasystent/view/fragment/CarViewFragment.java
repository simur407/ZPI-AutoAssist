package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Insurance;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.presenters.CarViewPresenter;
import pl.edu.pwr.zpi.autoasystent.service.InsuranceService;
import pl.edu.pwr.zpi.autoasystent.service.MotService;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.utils.StringUtils;
import pl.edu.pwr.zpi.autoasystent.view.CarViewPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;


/**
 * Created by Marcin on 25.04.2016.
 */
public class CarViewFragment extends Fragment implements TabFragment, CarViewPanel {

    private static final String TINT = "#99";

    private CarViewPresenter presenter;

    protected TextView makeField;
    protected TextView modelField;
    protected TextView yearField;
    protected TextView powerField;
    protected TextView plateField;
    protected TextView vinField;
    protected TextView capacityField;
    protected TextView motField;
    protected TextView insuranceField;
    private GradientDrawable colorView;
    protected long carId;

    @Override
    public String getTabName(Context context) {
        return StringUtils.getStringFromId(context, R.string.car_view_tab_name);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_view, container, false);

        carId = getArguments().getLong(CarActivity.ID_KEY);
        presenter = new CarViewPresenter(this, carId);
        colorView = (GradientDrawable) view.findViewById(R.id.color_viewer).getBackground();
        makeField = (TextView) view.findViewById(R.id.make_field);
        modelField = (TextView) view.findViewById(R.id.model_field);
        yearField = (TextView) view.findViewById(R.id.year_field);
        powerField = (TextView) view.findViewById(R.id.power_field);
        plateField = (TextView) view.findViewById(R.id.plate_field);
        vinField = (TextView) view.findViewById(R.id.vin_field);
        capacityField = (TextView) view.findViewById(R.id.capacity_field);
        motField = (TextView) view.findViewById(R.id.mot_field);
        insuranceField = (TextView) view.findViewById(R.id.insurance_field);
        view.findViewById(R.id.add_insurance_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onInsuranceButtonClick(v);
            }
        });
        view.findViewById(R.id.add_mot_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onMotButtonClick(v);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setCarData(carId);
    }

    @Override
    public void setCarData(Car car) {
        colorView.setColor(Color.parseColor("#" + car.getColor()));
        makeField.setText(car.getModel().getMake().getMakeName());
        modelField.setText(car.getModel().getModelName());
        yearField.setText(DateUtils.dateToString(car.getProductionYear(), DateUtils.YEAR_FORMAT));
        powerField.setText(String.valueOf(car.getPower()));
        plateField.setText(car.getLicencePlate());
        vinField.setText(car.getVIN());
        capacityField.setText(String.valueOf(car.getCapacity()));
        Mot latestMot = MotService.getInstance().getLatest(car);
        if(latestMot == null) {
            motField.setText("-");
        } else {
            motField.setText(DateUtils.dateToString(latestMot.getMotDate()));//TODO Not a pattern
        }
        Insurance latestService = InsuranceService.getInstance().getLatest(car);
        if(latestService == null) {
            insuranceField.setText("-");
        } else {
            insuranceField.setText(DateUtils.dateToString(latestService.getInsuranceDate()));
        }
    }

    @Override
    public void startActivity(Class<?> clazz, Uri additionalData) {
        Intent intent = new Intent(this.getContext(), clazz);
        if (additionalData != null) {
            intent.setData(additionalData);
        }
        startActivity(intent);
    }
}
