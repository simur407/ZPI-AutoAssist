package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.presenters.CarListPresenter;
import pl.edu.pwr.zpi.autoasystent.view.CarListPanel;
import pl.edu.pwr.zpi.autoasystent.view.adapter.CarAdapter;

public class MainActivity extends AppCompatActivity implements CarListPanel {

    private CarListPresenter presenter;
    private CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_car_list);
        ListView listView = (ListView) findViewById(R.id.car_list);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_main);
        presenter = new CarListPresenter(this);
        adapter = new CarAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        presenter.setList();
//        Button button = (Button) findViewById(R.id.color_picker_id);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ColorPickerDialogBuilder
//                        .with(MainActivity.this)
//                        .setTitle("Wybierz kolor")
//                        .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
//                        .density(12)
//                        .setOnColorSelectedListener(new OnColorSelectedListener() {
//                            @Override
//                            public void onColorSelected(int i) {
//                                Logger.debug("Color: ", Integer.toHexString(i));
//                            }
//                        })
//                        .setPositiveButton("OK", new ColorPickerClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
//                                Toast.makeText(MainActivity.this, "Color: " + Integer.toHexString(i), Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .build()
//                        .show();
//            }
//        });
    }

    @Override
    public void setCarList(List<Car> carList) {
        adapter.setItems(carList);
    }

    @Override
    public void startActivity(Class<?> clazz, Uri additionalData) {

    }

    @Override
    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onListItemClick(parent, view, position, id);
        }
    };
}
