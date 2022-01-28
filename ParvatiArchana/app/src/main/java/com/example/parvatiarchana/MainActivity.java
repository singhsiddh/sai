package com.example.parvatiarchana;
//https://www.javatpoint.com/android-option-menu-example
//https://medium.com/analytics-vidhya/creating-a-barcode-scanner-using-android-studio-71cff11800a2

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.parvatiarchana.imagegrid.ImgGridActivity;
import com.example.parvatiarchana.scanner.BarCodeActivity;
import com.example.parvatiarchana.sqlite.AddCountryActivity;
//import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        ///.
System.out.println(" GOinmg to add menu dynamic in toolbar");
        //toolbar.getMenu().add(Menu.NONE,Menu.FIRST,Menu.NONE,"Om01"+R.string.placeholder1);
        //toolbar.getMenu().add(Menu.NONE,Menu.FIRST+1,Menu.NONE,R.string.placeholder2);


        //..
        toolbar.showOverflowMenu();
        System.out.println(" Inside onCreate ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);//added siddh
        System.out.println(" Inside onCreateOptionsMenu ");
        getMenuInflater().inflate(R.menu.sai_menu, menu);

        MenuItem item = menu.add(1, 100, 300, "Settings");
        item.setIcon(R.drawable.ic_baseline_5g_24);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
// hint:: dynamically addinging
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        //setSupportActionBar(toolbar);

        ///.
        System.out.println(" GOinmg to add menu dynamic in toolbar");
        toolbar.getMenu().add(Menu.NONE,Menu.FIRST,Menu.NONE,"Om1.."+R.string.placeholder1);
        toolbar.getMenu().add(Menu.NONE,Menu.FIRST+1,Menu.NONE,""+"Om2.."+R.string.placeholder2);
        //..
        SubMenu topChannelMenu = toolbar.getMenu().addSubMenu("Top Channels");
        topChannelMenu.add("Foo");
        topChannelMenu.add("Bar");
        topChannelMenu.add("Baz");
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println(" Inside onOptionsItemSelected ");
        int id = item.getItemId();
        switch (id){
            case R.id.item11:
                Toast.makeText(getApplicationContext(),"IMG Grid ..",Toast.LENGTH_LONG).show();
                System.out.println("  before ImgGrifactivity1 ");
                Intent intent = new Intent(MainActivity.this, ImgGridActivity.class);
                System.out.println("  before ImgGrifactivity2 ");
                startActivity(intent);
                System.out.println(" After starting ImgGrifactivity");
                return true;
            case R.id.item22:
                Toast.makeText(getApplicationContext(),"Parvati frgment",Toast.LENGTH_LONG).show();
                FragmentManager manager120= getSupportFragmentManager();


                if(manager120 !=null&& manager120.findFragmentById(R.id.fragmetsaibabaContainer) == null) {

                    //If a fragment is not already loaded into your container, then add one...

                    ParvatiFragment    fragment = new ParvatiFragment();
                    manager120.beginTransaction().add(R.id.fragmetsaibabaContainer,fragment).commit();

                }else {
                    System.out.println(" Fragmenat manager is null");

                }
                    return true;
            case R.id.item33:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
              FragmentManager manager12= getSupportFragmentManager();


                  if(manager12 !=null&& manager12.findFragmentById(R.id.fragmetsaibabaContainer) == null) {

                      //If a fragment is not already loaded into your container, then add one...

                      SaibabakFragment    fragment = new SaibabakFragment();
                     manager12.beginTransaction().add(R.id.fragmetsaibabaContainer,fragment).commit();

              }else{
                  System.out.println(" Fragmenat manager is null");
              }
                return true;
            case R.id.item221:
                Toast.makeText(getApplicationContext(),"Bar Code ",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(MainActivity.this, BarCodeActivity.class);
                System.out.println("  before ImgGrifactivity2 ");
                startActivity(intent1);
                return true;
            case R.id.item222:
                Toast.makeText(getApplicationContext(),"SQLite",Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(MainActivity.this, AddCountryActivity.class);
                System.out.println("  before ImgGrifactivity2 ");
                startActivity(intent2);

                return true;
            case R.id.item2200:
                Toast.makeText(getApplicationContext(),"Nested layout",Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(MainActivity.this, LinerNestedActivity.class);
                System.out.println("  before nested layout ");
                startActivity(intent4);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}