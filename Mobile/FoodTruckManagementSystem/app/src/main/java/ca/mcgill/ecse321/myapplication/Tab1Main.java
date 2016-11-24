package ca.mcgill.ecse321.myapplication;

/**
 * Created by Evan on 20/11/2016.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem;

public class Tab1Main extends Fragment{

    Spinner orderSpinner;
    ArrayAdapter<String> orderAdapter;
    HashMap<Integer, MenuItem> menuItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1main, container, false);

        Manager m = Manager.getInstance();

        orderSpinner = (Spinner) rootView.findViewById(R.id.addorder_spinner);
        orderAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.menuItems = new HashMap<Integer, MenuItem>();

        int i = 0;
        for (Iterator<MenuItem> menuItems = m.getMenus().iterator(); menuItems.hasNext(); i++){
            MenuItem sM = menuItems.next();
            orderAdapter.add(sM.getName());
            this.menuItems.put(i, sM);
        }
        orderSpinner.setAdapter(orderAdapter);

        return rootView;
    }
}

