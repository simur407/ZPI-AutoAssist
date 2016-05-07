package pl.edu.pwr.zpi.autoasystent.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import pl.edu.pwr.zpi.autoasystent.R;


/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-05-07
 */
public class CustomViewDialog extends AlertDialog {

    private TextView title;
    private Button positiveButton, negativeButton;


    public CustomViewDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
        ViewGroup outerView = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_custom,
                (ViewGroup) getWindow().getDecorView(), false);
        title = (TextView) outerView.findViewById(R.id.title);
        positiveButton = (Button) outerView.findViewById(R.id.positive_button);
        negativeButton = (Button) outerView.findViewById(R.id.negative_button);
        ViewGroup contentHolder = (ViewGroup) outerView.findViewById(R.id.content);

        if(params != null ) {
            contentHolder.addView(view, params);
        } else {
            contentHolder.addView(view);
        }

        super.setContentView(outerView);
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        this.title.setText(titleId);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        this.title.setText(title);
    }

    public void setPositiveButton(CharSequence text, @Nullable final DialogInterface.OnClickListener listener) {
        positiveButton.setText(text);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(CustomViewDialog.this, DialogInterface.BUTTON_POSITIVE);
                }
                CustomViewDialog.this.dismiss();
            }
        });
    }

    public void setNegativeButton(CharSequence text, @Nullable final DialogInterface.OnClickListener listener) {
        negativeButton.setText(text);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onClick(CustomViewDialog.this, DialogInterface.BUTTON_NEGATIVE);
                }
                CustomViewDialog.this.dismiss();
            }
        });
    }
}
