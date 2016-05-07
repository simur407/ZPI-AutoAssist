package pl.edu.pwr.zpi.autoasystent.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.model.Mot;
import pl.edu.pwr.zpi.autoasystent.presenters.MotAddPresenter;
import pl.edu.pwr.zpi.autoasystent.view.MotAddPanel;
import pl.edu.pwr.zpi.autoasystent.view.fragment.TabFragment;

/**
 * Created by argo on 07 maj.
 */
public class MotAddDialog extends CustomViewDialog implements MotAddPanel, TabFragment{

    private EditText date, description;
    protected MotAddPresenter presenter;

    public MotAddDialog (Context context) { super(context); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_mot_add);

        presenter = new MotAddPresenter(this);

        date = (EditText) findViewById(R.id.mot_date);
        description = (EditText) findViewById(R.id.mot_description);
        setTitle("MOT");
        setPositiveButton("Zapisz", new OnClickListener() {//TODO dać tu referencje do strings.xml
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveMot();
                Toast.makeText(MotAddDialog.this.getContext(), "Zapisano!", Toast.LENGTH_SHORT).show();
            }
        });
        setNegativeButton("Anuluj", null);
    }

    private void saveMot() {
        Mot mot = new Mot();

//        mot.setMotDate(Double.parseDouble(date.getText().toString()));
        mot.setMotDescription(description.getText().toString());

        presenter.saveMot(mot);
    }

    public String getTabName() {
        return "MOT";
    }
}
