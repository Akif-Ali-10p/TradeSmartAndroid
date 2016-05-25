package com.iba.tradesmart.views;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.iba.tradesmart.R;
import com.iba.tradesmart.fragments.TradeFragment;
import com.tenpearls.android.components.Button;
import com.tenpearls.android.components.EditText;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.utilities.StringUtility;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 5/5/2016.
 */
public class TradeFragmentView extends BaseView implements AdapterView.OnItemSelectedListener {

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private Button btnProceed;
    private EditText etQuantity;

    private Spinner spinner;

    public TradeFragmentView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.view_trade_fragment;
    }

    @Override
    protected void onCreate() {
        spinner = findViewById(R.id.spinner);
        etQuantity = findViewById(R.id.etQuantity);
        radioGroup = findViewById(R.id.rgTrade);
        btnProceed = findViewById(R.id.btnProceed);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        fillSpinnerItems();


    }

    private void fillSpinnerItems() {
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("AHCL");
        categories.add("ACPL");
        categories.add("BYCO");
        categories.add("COLG");
        categories.add("DAWH");
        categories.add("DCL");
        categories.add("DYNO");
        categories.add("ENGRO");
        categories.add("EXIDE");
        categories.add("FCCL");
        categories.add("FFBL");
        categories.add("GLAXO");
        categories.add("HASCOL");
        categories.add("HBL");
        categories.add("ICI");
        categories.add("KAPCO");
        categories.add("KEL");
        categories.add("NETSOL");
        categories.add("OGDC");
        categories.add("PSO");
        categories.add("SSGC");
        categories.add("UBL");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    protected void setActionListeners() {

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);

                if (!StringUtility.isEmptyOrNull(etQuantity.getText().toString()) && (radioButton != null)) {
                    ((TradeFragment) controller).makeTrade(spinner.getSelectedItem().toString(), radioButton.getText().toString(), etQuantity.getText().toString(), "Akif");
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}
