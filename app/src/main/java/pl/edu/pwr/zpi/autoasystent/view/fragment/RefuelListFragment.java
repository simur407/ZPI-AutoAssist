package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Refueling;
import pl.edu.pwr.zpi.autoasystent.presenters.RefuelPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.StringUtils;
import pl.edu.pwr.zpi.autoasystent.view.RefuelPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;
import pl.edu.pwr.zpi.autoasystent.view.adapter.RefuelAdapter;

/**
 * Created by Marek on 18.04.2016.
 */
public class RefuelListFragment extends Fragment implements RefuelPanel, TabFragment {
    private RefuelPresenter presenter;
    private RefuelAdapter adapter;
    private long carId;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refuel_list, container, false);

        carId = getArguments().getLong(CarActivity.ID_KEY);

        presenter = new RefuelPresenter(this, carId);
        adapter = new RefuelAdapter(this.getActivity());

        ListView listView = (ListView) view.findViewById(R.id.refuel_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onListItemLongClick(parent, view, position, id);
                return true;
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_refuel);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClick(v);
            }
        });

        return view;

    }


    public void setRefuelList(List<Refueling> refuelingList) {
        adapter.setItems(refuelingList);
    }


    public void onResume() {
        super.onResume();
        presenter.setList();
    }


    public String getTabName(Context context) {
        return StringUtils.getStringFromId(context, R.string.refuel_tab_name);
    }


    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onListItemClick(parent, view, position, id);
        }
    };

    @Override
    public void startActivity(Class<?> clazz, Uri additionalData) {
        Intent intent = new Intent(this.getContext(), clazz);
        if (additionalData != null) {
            intent.setData(additionalData);
        }
        startActivity(intent);
    }

    @Override
    public void showDeleteMenu(final Refueling refueling) {
        View view = getLayoutInflater(null).inflate( R.layout.menu_car_edit, null);
        Button editButton = (Button) view.findViewById(R.id.edit_button);
        editButton.setVisibility(View.GONE);
        Button deleteButton = (Button) view.findViewById(R.id.delete_button);
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).setView(view).show();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteRefueling(refueling);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void refreshRefuelList() {

    }
}

