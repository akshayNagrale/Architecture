package com.example.architecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Add_Items extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.architecture.EXTRA_TITLE";
    public static final String EXTRA_ID = "com.example.architecture.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION = "com.example.architecture.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.architecture.EXTRA_PRIORITY";
    private EditText edTitle, edDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__items);
        edTitle = findViewById(R.id.edTitle);
        edDescription = findViewById(R.id.edDescription);
        numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            edTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            edDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else {
            setTitle("Add Note");
        }
       // saveNote();
    }
    private void saveNote(){
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
        int priority = numberPicker.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();

            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);
        int id = getIntent().getIntExtra(Add_Items.EXTRA_ID,-1);
        if(id !=-1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSave:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
}