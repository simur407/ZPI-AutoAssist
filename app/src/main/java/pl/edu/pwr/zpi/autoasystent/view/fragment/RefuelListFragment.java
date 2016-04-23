package pl.edu.pwr.zpi.autoasystent.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.edu.pwr.zpi.autoasystent.R;

/**
 * Created by Marek on 18.04.2016.
 */
public class RefuelListFragment extends Fragment implements TabFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_refuel_list, container, false);
    }

    @Override
    public String getTabName() {
        return "Tankowania";
    }
}
