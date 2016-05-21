package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import pl.edu.pwr.zpi.autoasystent.R;
import pl.edu.pwr.zpi.autoasystent.presenters.TransferPresenter;

public class TransferActivity extends AppCompatActivity {

    private EditText editText;
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        editText=(EditText)findViewById(R.id.transfer_editText);
        switch1=(Switch)findViewById(R.id.transfer_switch);
    }

    public void saveToFile(View view)
    {
        String filename=editText.getText().toString();
        Boolean checked=switch1.isChecked();
        TransferPresenter.saveFile(this, checked, filename);
    }

    public void loadFromFile(View view)
    {
        String filename=editText.getText().toString();
        Boolean checked=switch1.isChecked();
        TransferPresenter.loadFile(this, checked, filename, false);
    }

    public void deleteDatabase(View view)
    {
        TransferPresenter.deleteDatabase();
    }


}
