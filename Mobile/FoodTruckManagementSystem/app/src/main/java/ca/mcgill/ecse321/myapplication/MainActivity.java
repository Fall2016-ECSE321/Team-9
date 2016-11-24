package ca.mcgill.ecse321.myapplication;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.ItemController;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    HashMap<Integer, StaffMember> staffmembers;
    Bundle rtnStartTime = new Bundle();
    Bundle rtnEndTime = new Bundle();
    ArrayList<TextView> startTimeTextViews = new ArrayList<>();
    ArrayList<TextView> endTimeTextViews = new ArrayList<>();
    ArrayList<Time> startTimes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

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

    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Tab1Main tab1 = new Tab1Main();
                    return tab1;
                case 1:
                    Tab2Inventory tab2 = new Tab2Inventory();
                    return tab2;
                case 2:
                    Tab3Staff tab3 = new Tab3Staff();
                    return tab3;
                case 3:
                    Tab4Report tab4 = new Tab4Report();
                    return tab4;
                default:
                    return null;


            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Main";
                case 1:
                    return "Inventory";
                case 2:
                    return "Staff";
                case 3:
                    return "Report";
            }
            return null;
        }
    }

    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }

    public void addEquipment(View v) throws IOException {
        androidLocationSet();

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
        refreshData();
    }

    public void removeEquipment(View v) throws IOException {
        androidLocationSet();

        TextView en = (TextView) findViewById(R.id.addequipment_name);
        EditText eq = (EditText) findViewById(R.id.addequipment_quantity);
        ItemController pc = new ItemController();
        TextView error = (TextView) findViewById(R.id.errorhandler);
        error.setText("");

        if(eq.getText().toString().equals("")){
            if(en.getText().toString().equals(""))
                error.setText("Equipment name cannot be empty! Equipment quantity cannot be empty!");
            else{
                error.setText("Equipment quantity cannot be empty!");
            }
        }else{
            try {
                pc.removeEquipment(en.getText().toString(), Integer.parseInt(eq.getText().toString()));
            } catch (InvalidInputException e) {
                error.setText(e.getMessage());
            }
        }

        refreshData();
    }

    public void addSupply(View v) throws IOException{
        androidLocationSet();

        TextView sn = (TextView) findViewById(R.id.addsupply_name);
        TextView sq = (TextView) findViewById(R.id.addsupply_quantity);
        TextView su = (TextView) findViewById(R.id.addsupply_unit);
        ItemController pc = new ItemController();

        TextView error = (TextView) findViewById(R.id.errorhandler);
        error.setText("");
        if(sq.getText().toString().equals("")){
            if(sn.getText().toString().equals("") && su.getText().toString().equals(""))
                error.setText("Supply name cannot be empty! Supply quantity cannot be empty! Supply unit cannot be empty!");
            else if(sn.getText().toString().equals("")) {
                error.setText("Supply name cannot be empty! Supply quantity cannot be empty!");
            }
            else if(su.getText().toString().equals("")){
                error.setText("Supply quantity cannot be empty! Supply unit cannot be empty!");
            }
            else{
                error.setText("Supply quantity cannot be empty!");
            }
        }
        else{
            try{
                pc.createSupply(sn.getText().toString(), Double.parseDouble(sq.getText().toString()), su.getText().toString());
            } catch(InvalidInputException e){
                error.setText(e.getMessage());
            }
        }
        refreshData();
    }

    public void removeSupply(View v) throws IOException{
        androidLocationSet();

        TextView sn = (TextView) findViewById(R.id.addsupply_name);
        TextView sq = (TextView) findViewById(R.id.addsupply_quantity);
        ItemController pc = new ItemController();

        TextView error = (TextView) findViewById(R.id.errorhandler);
        error.setText("");
        if(sq.getText().toString().equals("")){
            if(sn.getText().toString().equals("")) {
                error.setText("Supply name cannot be empty! Supply quantity cannot be empty!");
            }
            else{
                error.setText("Supply name cannot be empty!");
            }
        }
        else{
            try {
                pc.removeSupply(sn.getText().toString(), Double.parseDouble(sq.getText().toString()));
            } catch(InvalidInputException e){
                error.setText(e.getMessage());
            }
        }

        refreshData();
    }

    public void addStaffMember(View v) throws IOException {
        androidLocationSet();

        TextView sn = (TextView) findViewById(R.id.staffmember_name);
        Spinner spinner = (Spinner) findViewById(R.id.role_spinner);
        String sr = "";
        if(spinner.getSelectedItem() != null){
            sr = spinner.getSelectedItem().toString();
        }

        ItemController pc = new ItemController();
        TextView error = (TextView) findViewById(R.id.stafferrorhandler);
        error.setText("");

        if(sr.equals("") || sr.equals(null)){
            if(sn.getText().toString().equals("") || sn.getText().toString().equals(null)){
                error.setText("Staff member name cannot be empty! Staff member role cannot be empty!");
            }
            else{
                System.out.println(sn.getText());
                error.setText("Staff member role cannot be empty!");
            }
        }
        else{
            try{
                pc.createStaffMember(sn.getText().toString(), sr);
            } catch(InvalidInputException e){
                error.setText(e.getMessage());
            }
        }
    }

    public void addTimeStaffMember(View v) throws IOException{
        androidLocationSet();
        setUpStartTimes();
        setUpEndTimes();
        ItemController ic = new ItemController();

        TextView error = (TextView) findViewById(R.id.stafferrorhandler);
        error.setText("");

        Spinner spinner = (Spinner) findViewById(R.id.staffmember_spinner);
        String sn = "";
        if(spinner.getSelectedItem() != null){
            sn = spinner.getSelectedItem().toString();
        }

        for(int i = 0; i < startTimeTextViews.size(); i++){
            String startTimeString = startTimeTextViews.get(i).getText().toString();
            String endTimeString = endTimeTextViews.get(i).getText().toString();
            System.out.println(startTimeString);
            if(sn.equals("") || sn.equals(null)){
                error.setText("Staff name cannot be empty!");
                if(startTimeString.equals(endTimeString) && !(startTimeString.equals(""))){
                    error.setText("Start time and end time cannot be equal!");
                }
            }
            else{
                String comps[] = startTimeString.split(":");
                String comps2[] = endTimeString.split(":");
                Time startTime = new Time(0000);
                Time endTime = new Time(0000);
                if(comps.length == 2 && comps2.length == 2){
                    startTime.setHours(Integer.parseInt(comps[0]));
                    startTime.setMinutes(Integer.parseInt(comps[1]));
                    endTime.setHours(Integer.parseInt(comps2[0]));
                    endTime.setHours(Integer.parseInt(comps2[1]));
                    try{
                        ic.addTimeStaffMember(sn, startTime, endTime);
                    } catch(InvalidInputException e){
                        error.setText(e.getMessage());
                    }
                }
            }
        }
    }

    public void addMenuItem(View v) throws IOException{
        androidLocationSet();
        TextView mn = (TextView) findViewById(R.id.addmenuitem_name);
        TextView mp = (TextView) findViewById(R.id.addmenuitem_price);
        ItemController ic = new ItemController();

        TextView error = (TextView) findViewById(R.id.menuerrorhandler);
        error.setText("");

        if(mp.getText().toString().equals("")){
            if(mn.getText().toString().equals("")){
                error.setText("Menu item name cannot be empty! Menu item price cannot be empty!");
            }
            else {
                error.setText("Menu item price cannot be empty!");
            }
        }else{
            /*try{
                ic.createMenuItem(mn.getText().toString(), Double.parseDouble(mp.getText().toString()));
            } catch(InvalidInputException e){
                error.setText(e.getMessage());
            }*/
        }

        refreshData();
    }

    public void removeMenuItem(View v) throws IOException{
        androidLocationSet();

        TextView mn = (TextView) findViewById(R.id.addmenuitem_name);
        ItemController ic = new ItemController();
        TextView error = (TextView) findViewById(R.id.menuerrorhandler);
        error.setText("");

        if(mn.getText().toString().equals("")){
            error.setText("Menu item name cannot be empty!");
        }else{
           /* try{
                ic.removeMenuItem(mn.getText().toString());
            } catch(InvalidInputException e){
                error.setText(e.getMessage());
            }*/
        }

    }

    public void addOrder(View v) throws IOException{
        androidLocationSet();
        ItemController ic = new ItemController();

        TextView error = (TextView) findViewById(R.id.menuerrorhandler);
        error.setText("");
        Spinner nameSpinner = (Spinner) findViewById(R.id.addorder_spinner);
        String on = "";
        if(nameSpinner.getSelectedItem() != null){
            on = nameSpinner.getSelectedItem().toString();
        }
        TextView oq = (TextView) findViewById(R.id.addorder_quantity);

        if(oq.getText().toString().equals("")){
            if(on.equals("")){
                error.setText("Order name cannot be empty! Order quantity cannot be empty!");
            } else{
                error.setText("Order quantity cannot be empty!");
            }
        }else{
            /*try{
                ic.menuItemOrdered(on, Integer.parseInt(oq.getText().toString()));
            } catch(InvalidInputException e){
                error.setText(e.getMessage());
            }*/
        }
    }

    private Bundle getStartTimeFromLabel(CharSequence text){
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;
        if (comps.length == 2){
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[0]);
        }
        rtnStartTime.putInt("hour", hour);
        rtnStartTime.putInt("minute", minute);

        Time newTime = new Time(hour + minute);
        startTimes.add(newTime);
        return rtnStartTime;
    }

    public void showStartTimePickerDialog(View v){
        TextView tf = (TextView) v;
        Bundle args = getStartTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showEndTimePickerDialog(View v){
        TextView tf = (TextView) v;
        Bundle args = getEndTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getEndTimeFromLabel(CharSequence text){
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;
        if (comps.length == 2){
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[0]);
        }
        rtnEndTime.putInt("hour", hour);
        rtnEndTime.putInt("minute", minute);
        return rtnEndTime;
    }

    public void androidLocationSet(){
        PersistenceXStream.setFilename(getFilesDir().getAbsolutePath() + "main.xml");
    }

    public void setUpStartTimes(){
        startTimeTextViews.add((TextView) findViewById(R.id.mondaystarttime_hint));
        startTimeTextViews.add((TextView) findViewById(R.id.tuesdaystarttime_hint));
        startTimeTextViews.add((TextView) findViewById(R.id.wednesdaystarttime_hint));
        startTimeTextViews.add((TextView) findViewById(R.id.thursdaystarttime_hint));
        startTimeTextViews.add((TextView) findViewById(R.id.fridaystarttime_hint));
    }

    public void setUpEndTimes(){
        endTimeTextViews.add((TextView) findViewById(R.id.mondayendtime_hint));
        endTimeTextViews.add((TextView) findViewById(R.id.tuesdayendtime_hint));
        endTimeTextViews.add((TextView) findViewById(R.id.wednesdayendtime_hint));
        endTimeTextViews.add((TextView) findViewById(R.id.thursdayendtime_hint));
        endTimeTextViews.add((TextView) findViewById(R.id.fridayendtime_hint));
    }

    private void refreshData() {
        TextView en = (TextView) findViewById(R.id.addequipment_name);
        TextView eq = (TextView) findViewById(R.id.addequipment_quantity);
        en.setText("");
        eq.setText("");
        TextView sn =(TextView) findViewById(R.id.addsupply_name);
        TextView sq = (TextView) findViewById(R.id.addsupply_quantity);
        TextView su = (TextView) findViewById(R.id.addsupply_unit);
        sn.setText("");
        sq.setText("");
        su.setText("");

    }

}
