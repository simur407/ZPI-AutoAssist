package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.rafalzajfert.androidlogger.Logger;

import pl.edu.pwr.zpi.autoasystent.R;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-04-15
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View addedView = getLayoutInflater().inflate(layoutResID, null, false);
        setContentView(addedView);
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        ViewGroup outerView = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_base,
                (ViewGroup) getWindow().getDecorView(), false);
        ViewGroup contentHolder = (ViewGroup) outerView.findViewById(R.id.content);

        if(params != null ) {
            contentHolder.addView(view, params);
        } else {
            contentHolder.addView(view);
        }

        super.setContentView(outerView);
        initToolbar();
    }

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.action_bar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void setToolbarTitle(@StringRes int stringRes) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(stringRes);
        } else {
            Logger.debug("Cos nie dziala");
        }
    }

    public void setToolbarTitle(CharSequence title) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        } else {
            Logger.debug("Cos nie dziala");
        }
    }
}
