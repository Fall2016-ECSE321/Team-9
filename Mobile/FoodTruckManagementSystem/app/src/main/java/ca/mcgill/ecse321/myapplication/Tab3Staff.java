package ca.mcgill.ecse321.myapplication;

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

    Manager m = Manager.getInstance();
    Spinner roleSpinner;
    ArrayAdapter<CharSequence> adapter;
    Spinner nameSpinner;
    ArrayAdapter<String> nameAdapter;
    HashMap<Integer, StaffMember> staffmembers;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3staff, container, false);
        roleSpinner = (Spinner) rootView.findViewById(R.id.role_spinner);
        adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);


        nameSpinner = (Spinner) rootView.findViewById(R.id.staffmember_spinner);
        nameAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.staffmembers = new HashMap<Integer, StaffMember>();

        int i = 0;
        for (Iterator<StaffMember> staffmembers = m.getStaffmembers().iterator(); staffmembers.hasNext(); i++){
            StaffMember sM = staffmembers.next();
            nameAdapter.add(sM.getName());
            this.staffmembers.put(i, sM);
        }
        nameSpinner.setAdapter(nameAdapter);

        return rootView;
    }
}