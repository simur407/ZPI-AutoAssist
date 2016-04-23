package pl.edu.pwr.zpi.autoasystent.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rafalzajfert.androidlogger.Logger;

import java.util.List;

import pl.edu.pwr.zpi.autoasystent.view.fragment.TabFragment;

/**
 * Created by Marek on 18.04.2016.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        Logger.trace();
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(fragmentList.get(position) instanceof TabFragment) {
            return ((TabFragment) fragmentList.get(position)).getTabName();
        } else {
            throw new IllegalStateException("Fragment must implement " + TabFragment.class.getName());
        }
    }

    public void setItemsList(List<Fragment> items) {
        fragmentList = items;
        notifyDataSetChanged();
    }
}
