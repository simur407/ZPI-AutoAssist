package pl.edu.pwr.zpi.autoasystent.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.edu.pwr.zpi.autoasystent.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reports);
//        Button button = (Button) findViewById(R.id.color_picker_id);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ColorPickerDialogBuilder
//                        .with(MainActivity.this)
//                        .setTitle("Wybierz kolor")
//                        .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
//                        .density(12)
//                        .setOnColorSelectedListener(new OnColorSelectedListener() {
//                            @Override
//                            public void onColorSelected(int i) {
//                                Logger.debug("Color: ", Integer.toHexString(i));
//                            }
//                        })
//                        .setPositiveButton("OK", new ColorPickerClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
//                                Toast.makeText(MainActivity.this, "Color: " + Integer.toHexString(i), Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .build()
//                        .show();
//            }
//        });
    }
}
