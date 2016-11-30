package ca.mcgill.ecse321.foodtruckmanagementsystem;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Manager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.StaffMember;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Supply;

/**
 * Created by Evan on 20/11/2016.
 */

public class Tab4Report extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4report, container, false);

        staffTableSetup(rootView);
        equipmentTableSetup(rootView);
        supplyTableSetup(rootView);
        popularityTableSetup(rootView);
        return rootView;
    }

    private void staffTableSetup(View inputView) {
        Manager m = Manager.getInstance();

        TextView staffRoleEntry = (TextView) inputView.findViewById(R.id.staffmemberroleentry);
        TableLayout staffMemberTable = (TableLayout) inputView.findViewById(R.id.staffmember_table);
        TableRow staffMemberTableRow1 = (TableRow) inputView.findViewById(R.id.staffmember_row1);
        TableRow staffMemberTableRow2 = (TableRow) inputView.findViewById(R.id.staffmember_row2);

        staffMemberTable.removeAllViewsInLayout();
        staffMemberTable.addView(staffMemberTableRow1);
        staffMemberTable.addView(staffMemberTableRow2);


        staffMemberTable.setStretchAllColumns(true);
        staffMemberTable.bringToFront();

        int i = 0;
        for (Iterator<StaffMember> staffMembers = m.getStaffmembers().iterator(); staffMembers.hasNext(); i++) {
            StaffMember sM = staffMembers.next();
            TableRow tr = new TableRow(this.getContext());
            TextView c1 = new TextView(this.getContext());
            c1.setText(sM.getName());
            TextView c2 = new TextView(this.getContext());
            c2.setText(sM.getRole());
            c2.setLayoutParams(staffRoleEntry.getLayoutParams());
            tr.addView(c1);
            tr.addView(c2);
            staffMemberTable.addView(tr);
        }
    }

    private void equipmentTableSetup(View inputView) {
        Manager m = Manager.getInstance();

        TableLayout equipmentTable = (TableLayout) inputView.findViewById(R.id.equipment_table);
        equipmentTable.setStretchAllColumns(true);
        equipmentTable.bringToFront();
        int i = 0;
        for (Iterator<Equipment> equipments = m.getEquipments().iterator(); equipments.hasNext(); i++) {
            Equipment e = equipments.next();
            TableRow tr = new TableRow(this.getContext());
            TextView c1 = new TextView(this.getContext());
            c1.setText(e.getName());
            TextView c2 = new TextView(this.getContext());
            c2.setText(Integer.toString(e.getQuantity()));
            tr.addView(c1);
            tr.addView(c2);
            equipmentTable.addView(tr);
        }
    }

    private void supplyTableSetup(View inputView) {

        Manager m = Manager.getInstance();

        TableLayout suppliesTable = (TableLayout) inputView.findViewById(R.id.supply_table);
        suppliesTable.setStretchAllColumns(true);
        suppliesTable.bringToFront();
        int i = 0;
        for (Iterator<Supply> supplies = m.getSupplies().iterator(); supplies.hasNext(); i++) {
            Supply s = supplies.next();
            TableRow tr = new TableRow(this.getContext());
            TextView c1 = new TextView(this.getContext());
            c1.setText(s.getName());
            TextView c2 = new TextView(this.getContext());
            c2.setText(s.getName());
            c2.setText(Double.toString(s.getQuantity()));
            TextView c3 = new TextView(this.getContext());
            c3.setText(s.getUnit());
            tr.addView(c1);
            tr.addView(c2);
            tr.addView(c3);
            suppliesTable.addView(tr);

        }
    }

    private void popularityTableSetup(View inputView){
        Manager m = Manager.getInstance();

        TextView staffRoleEntry = (TextView) inputView.findViewById(R.id.staffmemberroleentry);
        TableLayout popularityTable = (TableLayout) inputView.findViewById(R.id.popularity_table);
        popularityTable.setStretchAllColumns(true);
        popularityTable.bringToFront();
        int i =0;
        for(Iterator<ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem> menuItems = m.getMenus().iterator(); menuItems.hasNext(); i++){
            ca.mcgill.ecse321.foodtruckmanagementsystem.model.MenuItem mI = menuItems.next();
            TableRow tr = new TableRow(this.getContext());
            TextView c1 = new TextView(this.getContext());
            c1.setText(mI.getName());
            TextView c2 = new TextView(this.getContext());
            c2.setText(Integer.toString(mI.getPopularityCounter()));
            //c2.setLayoutParams(staffRoleEntry.getLayoutParams());
            tr.addView(c1);
            tr.addView(c2);
            popularityTable.addView(tr);
        }
    }

}