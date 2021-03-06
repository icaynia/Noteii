package com.icaynia.noteii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {
    EditText textContentView;
    Toolbar toolbar;
    memo tmemo = new memo();
    String filename = "note.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(android.R.drawable.ic_menu_sort_by_size));
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        textContentView = (EditText) findViewById(R.id.textContentView);
        /*
        textContentView.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                String getT[] = textContentView.getText().toString().split("\n");
                setTitle(getT[0]);
                Log.e("Noteii", "File Saved : ");
            }
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }
        });
        */
        setContentText(loadmemo());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*
        if (id == R.id.action_settings) {
            return true;
        } else */if (id == R.id.action_save) {
            savememo();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTitleText(String text) {
        setTitle(text);
    }

    public void setContentText(String textContent) {
        textContentView.setText(textContent);
        String getT[] = textContent.split("\\n");
        setTitleText(getT[0]);
    }



    public String loadmemo() {
        tmemo.load("note.txt");
        return tmemo.get();
    }



    public void savememo() {
        String str = textContentView.getText().toString().replace(System.getProperty("line.separator"), "\\n");
        tmemo.set(str);
        tmemo.save(filename);
        Toast.makeText(getApplicationContext(), "저장됨", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        savememo();
    }
}
