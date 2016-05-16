package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Car;
import pl.edu.pwr.zpi.autoasystent.presenters.CarListPresenter;
import pl.edu.pwr.zpi.autoasystent.presenters.TransferPresenter;
import pl.edu.pwr.zpi.autoasystent.view.CarListPanel;
import pl.edu.pwr.zpi.autoasystent.view.adapter.CarAdapter;

public class MainActivity extends BaseActivity implements CarListPanel {

    private CarListPresenter presenter;
    private CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CarListPresenter(this);
        adapter = new CarAdapter(this);

        setContentView(R.layout.fragment_car_list);

        setToolbarTitle(R.string.app_name);

        ListView listView = (ListView) findViewById(R.id.car_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(onItemLongClickListener);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_main);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClick(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TransferPresenter.saveFile(this, true, "lel.csv");
        //TransferPresenter.loadFile(this, true, "lel.csv", true);
        //List<Car> kurczak=Car.listAll(Car.class);
        //Toast.makeText(this, "Kurczak", Toast.LENGTH_SHORT).show();
        presenter.setList();
    }

    @Override
    public void setCarList(List<Car> carList) {
        adapter.setItems(carList);
    }

    @Override
    public void startActivity(Class<?> clazz, Uri additionalData) {
        Intent intent = new Intent(this, clazz);
        if(additionalData != null) {
            intent.setData(additionalData);
        }
        startActivity(intent);
    }

    @Override
    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDeleteMenu(final Car car) {
        View view = getLayoutInflater().inflate( R.layout.menu_car_edit, null);
        Button editButton = (Button) view.findViewById(R.id.edit_button);
        Button deleteButton = (Button) view.findViewById(R.id.delete_button);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).show();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editCar(car);
                dialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteCar(car);
                dialog.dismiss();
            }
        });


    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onListItemClick(parent, view, position, id);
        }
    };

    private AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onListItemLongClick(parent, view, position, id);
            return true;
        }
    };
}
