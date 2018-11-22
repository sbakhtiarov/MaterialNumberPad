package com.basv.materialnumberpad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basv.materialnumpad.MaterialNumberpad;

public class MainActivity extends AppCompatActivity implements MaterialNumberpad.OnNumpadClickListener {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);

        final MaterialNumberpad numPad = (MaterialNumberpad) findViewById(R.id.numpad);
        numPad.setEditText(editText);
        numPad.setOnNumpadClickListener(this);
    }

    @Override
    public void onNumpadClick(@MaterialNumberpad.NumpadAction int action, String val) {
        Log.i("TAGG", "onNumpadClick: " + action);
    }

    @Override
    public void onDoneClick(View view) {
        Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(View view) {
        Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();

    }
}
