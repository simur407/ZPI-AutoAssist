package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.rafalzajfert.androidlogger.Logger;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
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

    private Drawable background;
    private CarAddPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);
        setToolbarTitle(R.string.app_name);
        View color = findViewById(R.id.car_color);
        background = color.getBackground();
        ImageView colorPicker = (ImageView) findViewById(R.id.color_picker);
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onColorPickerClick();
            }
        });

        presenter = new CarAddPresenter(this);
        presenter.setSpinners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO obsluzyc zapis
        return super.onOptionsItemSelected(item);
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
                        //TODO Set color
                        presenter.onColorSelected(i);
                    }
                })
                .build()
                .show();
    }

    @Override
    public void setMakeSpinner(List<Make> makeList) {

    }

    @Override
    public void setModelSpinner(List<Model> modelList) {

    }

    @Override
    public void showPhotoDialog() {

    }

    @Override
    public void setColor(int color) {
        if (background instanceof ShapeDrawable) {
            Logger.debug("Jest spoko");
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(color);
        } else if (background instanceof GradientDrawable) {
            Logger.debug("Jest spoko");
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable)background;
            gradientDrawable.setColor(color);
        }
    }
}
