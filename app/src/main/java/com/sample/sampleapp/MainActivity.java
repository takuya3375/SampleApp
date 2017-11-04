package com.sample.sampleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;

import com.sample.sampleapp.R;
import com.sample.sampleapp.SubActivity;

public class MainActivity extends Activity {

    private TextView textView;
    private boolean flag = false;

    // spinnerの選択肢
    private String spinnerItems[] = {"Android", "Spinner", "Apple", "Windows"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testSpinner();
    }

    /**
     * ボタンの確認
     */
    public void testButton(View view) {

        switch (view.getId()) {
            case R.id.button_1:

                Log.d("debug","button1, Perform action on click");

                // flagがtrueの時
                if (flag) {
                    textView.setText("Hello");
                    flag = false;
                }
                // flagがfalseの時
                else {
                    textView.setText("World");
                    flag = true;
                }
                break;
        }
    }


    /**
     * 画面遷移の確認
     */
    public void testMove(View view) {

        Intent intent = new Intent(getApplication(), SubActivity.class);
        startActivity(intent);
    }

    /**
     * Spinnerの確認
     */
    public void testSpinner() {

        textView = (TextView) findViewById(R.id.text_view);
        Spinner spinner = findViewById(R.id.spinner);

        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinnerにadapter をセット
        spinner.setAdapter(adapter);

        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // アイテムが選択された時
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                textView.setText(item);
            }

            // アイテムが選択されなかった場合
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}