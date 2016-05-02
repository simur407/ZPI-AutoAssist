package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.ServiceJobs;
import pl.edu.pwr.zpi.autoasystent.presenters.ServiceJobsListPresenter;
import pl.edu.pwr.zpi.autoasystent.view.ServiceJobsPanel;
import pl.edu.pwr.zpi.autoasystent.view.adapter.ServiceJobsAdapter;

/**
 * Created by Marek on 25.04.2016.
 */
public class ServiceJobsListFragment extends Fragment implements ServiceJobsPanel, TabFragment {
    private ServiceJobsListPresenter presenter;
    private ServiceJobsAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_list, container, false);

        presenter = new ServiceJobsListPresenter(this);
        adapter = new ServiceJobsAdapter(this.getActivity());

        ListView listView = (ListView) view.findViewById(R.id.list_service);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);

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

    public String getTabName() {
        return "Serwisy";
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
}
