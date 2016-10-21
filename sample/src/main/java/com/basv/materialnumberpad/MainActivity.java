package com.basv.materialnumberpad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.basv.materialnumpad.MaterialNumberpad;

public class MainActivity extends AppCompatActivity implements MaterialNumberpad.OnNumpadClickListener {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final MaterialNumberpad numPad = (MaterialNumberpad) findViewById(R.id.numpad);
        numPad.setOnNumpadClickListener(this);

        editText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public void onNumpadClick(@MaterialNumberpad.NumpadAction int action) {
        switch (action) {

            case MaterialNumberpad.BUTTON_0:
            case MaterialNumberpad.BUTTON_1:
            case MaterialNumberpad.BUTTON_2:
            case MaterialNumberpad.BUTTON_3:
            case MaterialNumberpad.BUTTON_4:
            case MaterialNumberpad.BUTTON_5:
            case MaterialNumberpad.BUTTON_6:
            case MaterialNumberpad.BUTTON_7:
            case MaterialNumberpad.BUTTON_8:
            case MaterialNumberpad.BUTTON_9:
                editText.append(String.valueOf(action));
                break;
            case MaterialNumberpad.BUTTON_ACTION_DELETE:
                if (editText.length() > 0) {
                    editText.getText().delete(editText.length() - 1, editText.length());
                }
                break;
            case MaterialNumberpad.BUTTON_ACTION_DONE:
                Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
