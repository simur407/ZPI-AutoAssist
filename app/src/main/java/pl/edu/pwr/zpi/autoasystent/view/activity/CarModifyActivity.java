package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.rafalzajfert.androidlogger.Logger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;
import pl.edu.pwr.zpi.autoasystent.presenters.CarModifyPresenter;
import pl.edu.pwr.zpi.autoasystent.view.CarModifyPanel;

/**
 * Dodaje lub edytuje samochód w zalezności od tego czy zostało przeakzane ID samochodu przez Uri.
 *
 * @author Szymon Bartczak
 * @date 2016-04-15
 */
public class CarModifyActivity extends BaseActivity implements CarModifyPanel {

    private GradientDrawable carColorDrawable;
    private CarModifyPresenter presenter;
    private AutoCompleteTextView make, model;
    private EditText plate, vin, power, year, capacity, mileage, description;
    private int color;
    private ArrayAdapter<Make> makeAdapter;
    private ArrayAdapter<Model> modelAdapter;

    private Make selectedMake;
    private Model selectedModel;

    //Używany przy edycji.
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);
        setToolbarTitle(R.string.app_name);
        View color = findViewById(R.id.car_color);
        carColorDrawable = (GradientDrawable) color.getBackground();
        ImageView colorPicker = (ImageView) findViewById(R.id.color_picker);
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onColorPickerClick();
            }
        });

        make = (AutoCompleteTextView) findViewById(R.id.make_edittext);
        makeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        make.setAdapter(makeAdapter);
        make.setOnItemClickListener(selectMakeListener);

        model = (AutoCompleteTextView) findViewById(R.id.model_edittext);
        model.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedModel = (Model) parent.getItemAtPosition(position);
            }
        });
        modelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        model.setAdapter(modelAdapter);
        plate = (EditText) findViewById(R.id.licence_plate);
        vin = (EditText) findViewById(R.id.VIN);
        power = (EditText) findViewById(R.id.power);
        year = (EditText) findViewById(R.id.production_year);
        capacity = (EditText) findViewById(R.id.capacity);
        mileage = (EditText) findViewById(R.id.start_mileage);
        description = (EditText) findViewById(R.id.car_description);

        presenter = new CarModifyPresenter(this);
        presenter.setMakeSpinner();
        if (getIntent().getData() != null) {
            presenter.setInitialData(Long.valueOf(getIntent().getData().toString()));
        }
        this.color = 0xff000000;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveCar();
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void saveCar() {
        boolean error = false;
        if (car == null) {
            car = new Car();
        }
        car.setLicencePlate(plate.getText().toString());
        car.setVIN(vin.getText().toString());
        if (capacity.length() > 1) {
            if (Integer.valueOf(capacity.getText().toString()) < 200) {
                error = true;
                capacity.setError(getString(R.string.error_value));
            } else {
                car.setCapacity(Integer.valueOf(capacity.getText().toString()));
            }
        }
        car.setCarDescription(description.getText().toString());
        car.setColor(Integer.toHexString(color));
        if (power.length() > 1) {
            if (Integer.valueOf(power.getText().toString()) < 5) {
                error = true;
                power.setError(getString(R.string.error_value));
            } else {
                car.setPower(Integer.valueOf(power.getText().toString()));
            }
        }
        Calendar calendar = Calendar.getInstance();
        if (year.length() < 1) {
            error = true;
            year.setError(getString(R.string.error));
        } else if (year.length() > 4 || Integer.valueOf(year.getText().toString()) > calendar.get(Calendar.YEAR) || Integer.valueOf(year.getText().toString()) < 1800) {
            error = true;
            year.setError(getString(R.string.error_value));
        } else {
            car.setProductionYear(new GregorianCalendar(Integer.valueOf(year.getText().toString()), Calendar.getInstance().get(Calendar
                    .MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).getTime());
        }
        if (mileage.length() < 1) {
            error = true;
            mileage.setError(getString(R.string.error));
        } else if (mileage.length() > 9) {
            error = true;
            mileage.setError(getString(R.string.error_value));
        } else {
            car.setStartMileage(Integer.valueOf(mileage.getText().toString()));
        }


        if (selectedMake == null) {
            if (make.length() < 1) {
                error = true;
                make.setError(getString(R.string.error));
            } else {
                selectedMake = new Make();
                selectedMake.setMakeName(make.getText().toString().toUpperCase());
            }
        }

        if (selectedModel == null) {

            if (model.length() < 1) {
                error = true;
                model.setError(getString(R.string.error));
            } else {
                selectedModel = new Model();
                selectedModel.setModelName(model.getText().toString().toUpperCase());
            }
        }
        if (!error) {
            presenter.saveCar(car, selectedMake, selectedModel);
            finish();
        }
    }

    @Override
    public void showColorPicker() {
        ColorPickerDialogBuilder
                .with(CarModifyActivity.this)
                .setTitle("Wybierz kolor")
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setPositiveButton("OK", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        presenter.onColorSelected(i);
                        Logger.debug(i, Integer.toHexString(i));
                    }
                })
                .build()
                .show();
    }

    @Override
    public void setMakeSpinner(List<Make> makeList) {
        makeAdapter.addAll(makeList);
        makeAdapter.notifyDataSetChanged();
    }

    @Override
    public void setModelSpinner(List<Model> modelList) {
        modelAdapter.clear();
        modelAdapter.addAll(modelList);
        modelAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPhotoDialog() {

    }

    @Override
    public void setColor(int color) {
        carColorDrawable.setColor(color);
        this.color = color;
    }

    private AdapterView.OnItemClickListener selectMakeListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onMakeSelected(parent, view, position, id);
            selectedMake = (Make) parent.getItemAtPosition(position);
        }
    };

    @Override
    public void setInitialData(Car car) {
        this.car = car;
        if (car.getModel() != null) {
            if (car.getModel().getMake() != null) {
                make.setText(car.getModel().getMake().getMakeName());
            }
            model.setText(car.getModel().getModelName());
        }

        plate.setText(car.getLicencePlate());
        vin.setText(car.getVIN());
        power.setText(String.valueOf(car.getPower()));
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        calendar.setTime(car.getProductionYear());
        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        capacity.setText(String.valueOf(car.getCapacity()));
        mileage.setText(String.valueOf(car.getStartMileage()));
        description.setText(car.getCarDescription());
    }
}
