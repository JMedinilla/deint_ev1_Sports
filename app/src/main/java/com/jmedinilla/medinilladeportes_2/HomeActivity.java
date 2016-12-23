package com.jmedinilla.medinilladeportes_2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Preferences preferences;
    private Adapter adapter;

    private boolean[] checked_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = Preferences.getInstance(HomeActivity.this);

        Button btnAccept = (Button) findViewById(R.id.activity_home_btnAccept);
        ListView list = (ListView) findViewById(R.id.activity_home_list);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.saveSports();
            }
        });

        preferences.loadSports();
        adapter = new Adapter(HomeActivity.this);
        list.setAdapter(adapter);
        list.setDivider(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.home_menu_filter:
                showFilterDialog();
                break;
            default:
                break;
        }
        return true;
    }

    private void showFilterDialog() {
        final EditText editText = new EditText(HomeActivity.this);
        editText.setEms(5);
        editText.setMaxEms(1);
        editText.setLines(1);
        editText.setMaxLines(1);
        editText.setGravity(Gravity.CENTER);
        AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
        dialog.setTitle(R.string.title_dialog);
        dialog.setMessage(R.string.message_dialog);
        dialog.setView(editText);
        dialog.setPositiveButton(R.string.button_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String character = editText.getText().toString();
                if (character.length() == 0) {
                    adapter.reloadSports();
                } else if (character.length() > 1) {
                    Toast.makeText(HomeActivity.this, R.string.toast_dialog, Toast.LENGTH_LONG).show();
                    adapter.reloadSports();
                } else {
                    adapter.loadCharacter(character);
                }
            }
        });
        dialog.show();
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
