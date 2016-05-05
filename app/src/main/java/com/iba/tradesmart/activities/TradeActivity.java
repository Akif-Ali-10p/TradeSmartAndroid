package com.iba.tradesmart.activities;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.iba.tradesmart.*;
import com.iba.tradesmart.activities.base.BaseActivity;
import com.iba.tradesmart.views.TradeView;
import com.tenpearls.android.components.Button;
import com.tenpearls.android.components.EditText;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.service.*;
import com.tenpearls.android.utilities.StringUtility;
import com.tenpearls.android.views.BaseView;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class TradeActivity extends BaseActivity implements OnItemSelectedListener{
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private Button btnProceed;
    private EditText etQuantity;

    @Override
    public BaseView getViewForController(Controller controller) {
        return new TradeView(controller);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity_trade);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        etQuantity = (EditText) findViewById(R.id.etQuantity);

        radioGroup=(RadioGroup)findViewById(R.id.rgTrade);

        btnProceed =(Button)findViewById(R.id.btnProceed);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selectedId);

                if(!StringUtility.isEmptyOrNull(etQuantity.getText().toString()) && radioButton != null){
                    makeTrade();
                }
//                    Toast.makeText(TradeActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }

            private void makeTrade() {
                ((com.iba.tradesmart.services.ServiceFactory)serviceFactory).loadTradeService();
                ((com.iba.tradesmart.services.ServiceFactory)serviceFactory).tradeService.makeTrade("makeTrade", spinner.getSelectedItem().toString(), radioButton.getText().toString(), etQuantity.getText().toString(),"hasan").enqueue(new ServiceCallback(getBaseActivity(), new ServiceSecondaryEventHandler() {
                    @Override
                    public void willStartCall() {
                        showLoader();
                    }

                    @Override
                    public void didFinishCall(boolean isSuccess) {
                        hideLoader();
                    }
                }) {
                    @Override
                    protected void onSuccess(Object response, int code) {
                        showToast(response.toString());
                    }

                    @Override
                    protected void onFailure(String errorMessage, int code) {
                        showToast(String.valueOf(code) + " " + errorMessage);
                        Log.d("Login Exception", String.valueOf(code) + " " + errorMessage);
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}