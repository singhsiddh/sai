package com.example.parvatiarchana.myntra.mainfragment.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.parvatiarchana.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecycleViewMainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    private List<MyntraProducts> contacts;
    private ContactAdapter contactAdapter;
    private Random random;
private MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.muntra_recycle_view_main_activity);



        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        /*
        contacts = new ArrayList<MyntraProducts>();
        random = new Random();

        //set dummy data
        for (int i = 0; i < 10; i++) {
            MyntraProducts contact = new MyntraProducts();
            contact.setDescription("Descc: Test Decriptiom" + i);
            contact.setCode("Code:" + phoneNumberGenerating());
            contact.setPrice(100d);
            //contact.setPhone(phoneNumberGenerating());
            //contact.setEmail("DevExchanges" + i + "@gmail.com");
            contacts.add(contact);
        }
        System.out.println("1:sai  Contact sizes" + contacts.size());
        //find view by id and attaching adapter for the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        System.out.println("2:sai recyclerView..:" + recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(this,recyclerView, contacts, this);
        recyclerView.setAdapter(contactAdapter);

        //set load more listener for the RecyclerView adapter
        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListenerMyntra() {
            @Override
            public void onLoadMore() {
                System.out.println(" sai Inside onLoadMore..1:");
                if (contacts.size() <= 20) {
                    System.out.println("sai  Inside onLoadMore..2:");
                    //why adding null....
                    //..TODO    contacts.add(null);
                    contacts.add(new MyntraProducts());
                    System.out.println("sai onLoadMore  3: contacts.size()=" + contacts.size());
                    contactAdapter.notifyItemInserted(contacts.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("sai Inside onLoadMore..4:");
                            contacts.remove(contacts.size() - 1);
                            contactAdapter.notifyItemRemoved(contacts.size());
                            System.out.println(" sai Inside onLoadMore..5:");
                            //Generating more data
                            int index = contacts.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                MyntraProducts contact = new MyntraProducts();
                                contact.setDescription("Test Decriptiom" + i);
                                contact.setCode("" + phoneNumberGenerating());
                                contact.setPrice(100d);
                                contacts.add(contact);
                            }
                            System.out.println(" sai Inside onLoadMore..6 contas size :" + contacts.size());
                            contactAdapter.notifyDataSetChanged();
                            contactAdapter.setLoaded();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(RecycleViewMainActivity.this, "Loading data completed...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        */
    }

    private String phoneNumberGenerating() {
        int low = 100000000;
        int high = 999999999;
        int randomNumber = random.nextInt(high - low) + low;

        return "0" + randomNumber;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}