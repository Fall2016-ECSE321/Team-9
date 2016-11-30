package ca.mcgill.ecse321.foodtruckmanagementsystem;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

/**
 * Created by Evan on 20/11/2016.
 */

public class Tab3Staff extends Fragment{

    Spinner roleSpinner;
    ArrayAdapter<CharSequence> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3staff, container, false);
        roleSpinner = (Spinner) rootView.findViewById(R.id.role_spinner);
        adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        return rootView;
    }
}