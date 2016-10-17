package ca.mcgill.ecse321.foodtruckmanagementsystem;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ca.mcgill.ecse321.foodtruckmanagementsystem.application.FoodTruckManagementSystem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //ALL CODE ABOVE IS PRE-RENDERED BY ANDROID STUDIO

    //Calls PersistenceXStream library and saves the file(?)
    public void androidLocationSet(){
        PersistenceXStream.setFilename(getFilesDir().getAbsolutePath() + "main.xml");
    }

    //Visual refresh of Android Application
    private void refreshData() {
        TextView en = (TextView) findViewById(R.id.addequipment_name);
        TextView eq = (TextView) findViewById(R.id.addequipment_quantity);
        en.setText("");
        eq.setText("");

    }

    //Creates an object of type 'Equipment' to the XML file
    public void addEquipment(View v) throws IOException{
//        androidLocationSet();

        TextView ev = (TextView) findViewById(R.id.addequipment_name);
        EditText en = (EditText) findViewById(R.id.addequipment_quantity);
        ItemController pc = new ItemController();
        TextView error = (TextView) findViewById(R.id.errorhandler);
        error.setText("");
        if(en.getText().toString().equals("")){
            if(ev.getText().toString().equals(""))
                error.setText("Equipment name cannot be empty! Equipment quantity cannot be empty!");
            else{
                error.setText("Equipment quantity cannot be empty!");
            }
        }
        else{
        try {
            pc.createEquipment(ev.getText().toString(),Integer.parseInt(en.getText().toString()));
        }  catch (InvalidInputException e) {
            error.setText(e.getMessage());
        }
        }



//        ItemController ic = new ItemController();
//        if(en.getText().toString().equals("")) {
//            if(ev.getText().toString().equals("")){
//                Toast display = Toast.makeText(this, "Equipment name and quantity cannot be empty!", Toast.LENGTH_LONG);
//                display.setGravity(Gravity.TOP| Gravity.LEFT,0,0);
//                display.show();
//            }
//            else {
//                Toast display = Toast.makeText(this, "Equipment quantity cannot be empty!", Toast.LENGTH_LONG);
//                display.setGravity(Gravity.TOP| Gravity.LEFT,0,0);
//                display.show();
//            }
//        }
//
//        else{
//            try {
//                ic.createEquipment(ev.getText().toString(), Integer.valueOf(en.getText().toString()));
//            } catch (InvalidInputException e) {
//                e.printStackTrace();
//                showToast(e);
//            }


        refreshData();
    }

    //Displays a relevant error message at the bottom of the screen to the user
    public void showToast(InvalidInputException error) {
        int duration = Toast.LENGTH_LONG;
        Toast display = Toast.makeText(this, error.getMessage(), duration);
        display.show();
        display.setGravity(Gravity.TOP| Gravity.LEFT,0,0);
    }
}

