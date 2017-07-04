package com.example.asinck.filedatestamper;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    EditText filenameInput, output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filenameInput = (EditText) findViewById(R.id.FileNameInput);
        output = (EditText) findViewById(R.id.OutputEditText);

    }
    public String getDate(Boolean time) {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df;
        if (time) {
            df = new SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.US);
        }
        else {
            df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        }

        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
    public void datestamp(View view) {
        String filename = getInput() + "_" + getDate(false) + ".txt";
        output.setText(filename);
    }

    public void timestamp(View view) {
        String filename = getInput() + "_" + getDate(true) + ".txt";
        output.setText(filename);
    }

    public String getInput() {
        return filenameInput.getText().toString().replace(' ', '_');
    }

    public void CopyToClipboard(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("filename", output.getText().toString().trim());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Copied!", Toast.LENGTH_SHORT).show();
    }
}
