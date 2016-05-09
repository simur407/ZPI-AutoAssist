package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.rafalzajfert.androidlogger.Logger;

import java.text.ParseException;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.presenters.MotAddPresenter;
import pl.edu.pwr.zpi.autoasystent.utils.DateUtils;
import pl.edu.pwr.zpi.autoasystent.view.MotAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.dialog.DateDialog;

/**
 * Created by Marek on 09.05.2016.
 */
public class MotAddActivity extends BaseActivity implements MotAddPanel {
    private EditText date, description;
    protected MotAddPresenter presenter;
    private long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carId = Long.valueOf(getIntent().getData().toString());
        setContentView(R.layout.fragment_mot_add);
        presenter = new MotAddPresenter(this, carId);
        date = (EditText) findViewById(R.id.mot_date);
        description = (EditText) findViewById(R.id.mot_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveMot();
            default:
                super.onOptionsItemSelected(item);
        }
        return true;

    }

    private void saveMot() {
        Mot mot = new Mot();
        try {
            mot.setMotDate(DateUtils.stringToDate(date.getText().toString(), DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            Logger.error(e);
        }

        mot.setMotDescription(description.getText().toString());

        presenter.saveMot(mot);
        finish();
    }

    public void onStart() {
        super.onStart();

        EditText txtDate = (EditText) findViewById(R.id.mot_date);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean hasfocus) {
                if (hasfocus) {
                    DateDialog dialog = new DateDialog(view);

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    dialog.show(ft, "Wybierz datę");
                }
            }

        });
    }

}