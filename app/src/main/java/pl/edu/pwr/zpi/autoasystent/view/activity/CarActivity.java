package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.CarPresenter;
import pl.edu.pwr.zpi.autoasystent.view.CarPanel;
import pl.edu.pwr.zpi.autoasystent.view.adapter.FragmentAdapter;

/**
 * Created by Marek on 18.04.2016.
 */
public class CarActivity extends BaseActivity implements CarPanel {
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    private CarPresenter presenter;

    public static String ID_KEY = "id";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        long carId = Long.valueOf(getIntent().getData().toString());
        presenter = new CarPresenter(this);
        presenter.setFragments(carId);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(fragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        presenter.setToolbarTitle(carId);
    }

    @Override
    public void setFragments(List<Fragment> fragments) {
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
    }

    @Override
    public void setTitle(String title) {
        setToolbarTitle(title);
    }


}
