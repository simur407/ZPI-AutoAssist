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
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.ServiceJobsListPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.StringUtils;
import pl.edu.pwr.zpi.autoasystent.view.ServiceJobsPanel;
import pl.edu.pwr.zpi.autoasystent.view.activity.CarActivity;
import pl.edu.pwr.zpi.autoasystent.view.adapter.ServiceJobsAdapter;

/**
 * Created by Marek on 25.04.2016.
 */
public class ServiceJobsListFragment extends Fragment implements ServiceJobsPanel, TabFragment {
    private ServiceJobsListPresenter presenter;
    private ServiceJobsAdapter adapter;
    private long carId;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_list, container, false);

        carId = getArguments().getLong(CarActivity.ID_KEY);
        presenter = new ServiceJobsListPresenter(this, carId);
        adapter = new ServiceJobsAdapter(this.getActivity());

        ListView listView = (ListView) view.findViewById(R.id.list_service);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(onLongItemClickListener);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_service);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddButtonClick(v);
            }
        });

        return view;

    }

    public void setServiceJobsList(List<ServiceJobs> serviceJobsList) {
        adapter.setItems(serviceJobsList);
    }

    public void onResume() {
        super.onResume();
        presenter.setList();
    }


    public String getTabName(Context context) {
        return StringUtils.getStringFromId(context, R.string.service_jobs_tab_name);
    }

    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startActivity(Class<?> clazz, Uri additionalData) {
        Intent intent = new Intent(this.getContext(), clazz);
        if (additionalData != null) {
            intent.setData(additionalData);
        }
        startActivity(intent);
    }

    @Override
    public void showDeleteMenu(final ServiceJobs serviceJob) {
        View view = getLayoutInflater(null).inflate( R.layout.menu_car_edit, null);
        Button editButton = (Button) view.findViewById(R.id.edit_button);
        editButton.setVisibility(View.GONE);
        Button deleteButton = (Button) view.findViewById(R.id.delete_button);
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).setView(view).show();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteServiceJob(serviceJob);
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

    private AdapterView.OnItemLongClickListener onLongItemClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            presenter.onLongListItemClick(parent, view, position, id);
            return true;
        }
    };
}
