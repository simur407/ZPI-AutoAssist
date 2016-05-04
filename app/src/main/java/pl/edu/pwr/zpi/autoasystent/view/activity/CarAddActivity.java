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
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.model.Make;
import pl.edu.pwr.zpi.autoasystent.model.Model;
import pl.edu.pwr.zpi.autoasystent.presenters.CarAddPresenter;
import pl.edu.pwr.zpi.autoasystent.view.CarAddPanel;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-15
 */
public class CarAddActivity extends BaseActivity implements CarAddPanel {

    private GradientDrawable carColorDrawable;
    private CarAddPresenter presenter;
    private AutoCompleteTextView make, model;
    private EditText plate, vin, power, year, capacity, mileage, description;
    private int color;
    private ArrayAdapter<Make> makeAdapter;
    private ArrayAdapter<Model> modelAdapter;

    private Make selectedMake;
    private Model selectedModel;



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

        presenter = new CarAddPresenter(this);
        presenter.setMakeSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_save: saveCar();
            default: super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void saveCar() {
        Car car = new Car();
        car.setLicencePlate(plate.getText().toString());
        car.setVIN(vin.getText().toString());
        car.setCapacity(Integer.valueOf(capacity.getText().toString()));
        car.setCarDescription(description.getText().toString());
        car.setColor(Integer.toHexString(color));
        car.setPower(Integer.valueOf(power.getText().toString()));
        car.setProductionYear(new GregorianCalendar(Integer.valueOf(year.getText().toString()), Calendar.getInstance().get(Calendar
                .MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).getTime());
        car.setStartMileage(Integer.valueOf(mileage.getText().toString()));


        if(selectedMake == null) {
             selectedMake = new Make();
             selectedMake.setMakeName(make.getText().toString().toUpperCase());
        }

        if(selectedModel == null) {
            selectedModel = new Model();
            selectedModel.setModelName(model.getText().toString().toUpperCase());
        }

        presenter.saveCar(car, selectedMake, selectedModel);
        finish();
    }

    @Override
    public void showColorPicker() {
        ColorPickerDialogBuilder
                .with(CarAddActivity.this)
                .setTitle("Wybierz kolor")
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setPositiveButton("OK", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        Toast.makeText(CarAddActivity.this, "Color: " + Integer.toHexString(i), Toast.LENGTH_SHORT).show();
                        presenter.onColorSelected(i);
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
    }

    private AdapterView.OnItemClickListener selectMakeListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onMakeSelected(parent, view, position, id);
            selectedMake = (Make) parent.getItemAtPosition(position);
        }
    };
}
