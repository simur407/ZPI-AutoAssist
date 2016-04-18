package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.LinkedList;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.view.adapter.FragmentAdapter;
import pl.edu.pwr.zpi.autoasystent.view.fragment.RefuelListFragment;
import pl.edu.pwr.zpi.autoasystent.view.fragment.RefuelViewFragment;

/**
 * Created by Marek on 18.04.2016.
 */
public class CarActivity extends FragmentActivity {
    FragmentAdapter fragmentAdapter;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        LinkedList<Fragment> fragmentLinkedList = new LinkedList<Fragment>();
        fragmentLinkedList.add(new RefuelListFragment());
        fragmentLinkedList.add(new RefuelViewFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentLinkedList);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(fragmentAdapter);

    }

}
