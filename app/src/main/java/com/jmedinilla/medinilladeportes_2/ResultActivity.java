package com.jmedinilla.medinilladeportes_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ResultActivity extends AppCompatActivity {
    private Preferences preferences;
    private Adapter adapter;

    private boolean[] checked_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        preferences = Preferences.getInstance(ResultActivity.this);

        Button btnAccept = (Button) findViewById(R.id.activity_result_btnAccept);
        ListView list = (ListView) findViewById(R.id.activity_result_list);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.saveSports();
            }
        });

        preferences.loadSports();
        adapter = new Adapter(ResultActivity.this, getIntent().getExtras().getParcelableArrayList("filter_list"));
        list.setAdapter(adapter);
        list.setDivider(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        checked_state = adapter.getCheckedItems();
        outState.putBooleanArray("key_boolean_status", checked_state);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        checked_state = savedInstanceState.getBooleanArray("key_boolean_status");
        if (checked_state != null) {
            adapter.setCheckedItems(checked_state);
        }
    }
}
