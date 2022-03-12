package com.example.parvatiarchana.table;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parvatiarchana.R;

import java.util.List;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button add, yes, no;
    private Holidays holiday = null;
    private List<Object> holidays = null;
    private EditText t = null;
    EditText t1 = null;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    public CustomDialogClass(Activity a, Holidays holiday, List<Object> holidays) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.holiday = holiday;
        this.holidays = holidays;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        add = (Button) findViewById(R.id.btn_add);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        add.setOnClickListener(this);
        this.t = findViewById(R.id.editTextTextPersonName5);
        this.t.setText(holiday.getName());
        this.t1 = findViewById(R.id.editTextTextPersonName6);
        this.t1.setText(holiday.getDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                // Update record
// add

                System.out.println(" sai table data size holidys before add :" + holidays.size());

                holidays.add(new Holidays("" + t.getText(), "" + t1.getText()));
                System.out.println(" sai table data size holidys Afrer  add :" + holidays.size());

                //...   c.finish();
                break;

            case R.id.btn_yes:
                // Update record
// add

                System.out.println(" sai table data size holidys before add :" + holidays.size());
                holiday.setName("" + t.getText());
                holiday.setDate("" + t1.getText());
                updateRecord();
                System.out.println(" sai table data size holidys Afrer  add :" + holidays.size());

                //...   c.finish();
                break;
            case R.id.btn_no:
                // delete record
                //  holidays.remove(holiday);
                removeRecord();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    private void removeRecord() {
        for (Object obj : holidays) {
            if (obj instanceof Holidays) {
                //    Toast.makeText(view.getContext(), " holidays instance", Toast.LENGTH_SHORT).show();

                Holidays hol1 = (Holidays) obj;
                if (hol1.getName().equals("" + holiday.getName())) {
                    holidays.remove(hol1);
                    //Hint : How to refresh view ...
                    //notifyDataSetChanged();
                    break;
                }
            } else {
                //  Toast.makeText(view.getContext(), "Not holiday instance", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void updateRecord() {
        // int i = -1;
        // for (Object obj : holidays) {
        for (int i = 0; i < holidays.size(); i++) {
            Object obj = holidays.get(i);
            if (obj instanceof Holidays) {

                //    Toast.makeText(view.getContext(), " holidays instance", Toast.LENGTH_SHORT).show();

                Holidays hol1 = (Holidays) obj;
                if (hol1.getName().equals("" + holiday.getName())) {
                    holidays.remove(hol1);
                    holidays.add(i, holiday);
                    //holidays.add(holiday);
                    //Hint : How to refresh view ...
                    //notifyDataSetChanged();
                    break;
                }
            } else {
                //  Toast.makeText(view.getContext(), "Not holiday instance", Toast.LENGTH_SHORT).show();

            }
        }


    }
}
