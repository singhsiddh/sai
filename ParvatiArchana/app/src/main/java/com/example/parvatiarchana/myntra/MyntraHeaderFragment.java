package com.example.parvatiarchana.myntra;
//https://stackoverflow.com/questions/20656208/android-adding-view-dynamically-in-a-nested-layout

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parvatiarchana.R;
import com.example.parvatiarchana.myntra.bo.Cat;
import com.example.parvatiarchana.myntra.bo.GetProduct;
import com.example.parvatiarchana.myntra.bo.SubCat;
import com.example.parvatiarchana.myntra.bo.SubSubCat;
import com.example.parvatiarchana.myntra.so.MyntraSO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyntraHeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyntraHeaderFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Cat> catsGlobal = null;
    private Context context;
    private String catCurrent = "";
    private String subcatCurrent = "";
    private String subsubcatCurrent = "";
    public Activity parent = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public interface InterFragCommuncation {
        public void communicateH(String input);
    }

    public MyntraHeaderFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyntraHeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyntraHeaderFragment newInstance(String param1, String param2) {
        MyntraHeaderFragment fragment = new MyntraHeaderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        parent = requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.context = container.getContext();
        View view = inflater.inflate(R.layout.myntra_header_fragment, container, false);
        try {
            addCatInFragment(container, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private void addCatInFragment(ViewGroup container, View view) {
      //  List<ProductCategorary> cats = getProdCat();
        Button bt = null;
        ViewGroup vg = view.findViewById(R.id.myntra_head_ll_sai);

        debug(" kali-sai-fragment...... layout instance =" + vg);
        LinearLayout ll = (LinearLayout) vg;
        int i = 100;
        List<Cat> cats1 = GetProduct.getProduct(context);
        catsGlobal = cats1;
        // for(ProductCategorary cat: cats){
        for (Cat cat : cats1) {
            i++;
            debug("sai button going to create ..1");
            bt = new Button(container.getContext()); //new Button(this)
            debug("sai button going to create ..2");
            bt.setId(i);
            bt.setText(cat.getName());
            //  bt.setText(cat.getDispplayName());
            debug("sai button going to create ..3");
            bt.setOnClickListener(this);
            ll.addView(bt);
            debug("sai button going to create ..4");

        }
        TextView tv = new TextView(container.getContext());
        // int id =2307;
        tv.setId(R.id.my_edit_text_1);
        tv.setText("dummy text ..");
        ll.addView(tv);

    }

    /**
     * catDepthLevel ={subcat,subsubcat}
     * getnextdepth Cat if null then on clickdisplayitem in main fragment  else fetch & make popup menu sub item
     */
    //https://learningprogramming.net/mobile/android/create-dynamically-popup-menu-in-android/
    private void popupMenuDisplay(List<SubCat> subcats, Button buttonPopupMenu) {
        PopupMenu popupMenu = new PopupMenu(this.context, buttonPopupMenu);
        int MENU = 0;
        int MENU_ITEM = Menu.FIRST;
        int MENU_ITEM1 = Menu.FIRST;
        int MENU_ITEM2 = Menu.FIRST;
        int SUB_MENU = 0;
        int i = -1;
        int j = -1;
        String[] inputD = new String[3];
        for (SubCat subcat : subcats) {
            MENU++;
            i++;

            if (subcat.getSubsubcat() == null || subcat.getSubsubcat().size() == 0) {
                // Bind listner with data
                Menu m = popupMenu.getMenu();
                MenuItem mi = m.add(MENU, MENU_ITEM, i, subcat.getName());
                mi.setOnMenuItemClickListener((mitem) -> {
                    subcatCurrent = subcat.getName();
                    subsubcatCurrent = "";
                    if (parent instanceof MyntraMainActivity) {
                        System.out.println("sai111 parent instanceof MyntraMainActivity");
                        inputD[0] = catCurrent;
                        inputD[1] = subcatCurrent;
                        inputD[2] = subsubcatCurrent;
                        ((MyntraMainActivity) parent).communicateM(inputD);// catCurrent + ":" + subcatCurrent + ":" + subsubcatCurrent);
                    } else {
                        System.out.println("sai111 parent NOT instanceof MyntraMainActivity");
                    }
                    return false;
                });
            } else {
                //Cretae submenu
                MENU_ITEM1 = Menu.FIRST;
                MENU_ITEM2 = Menu.FIRST;
                List<SubSubCat> subsubcats = subcat.getSubsubcat();

                SubMenu subMenu = popupMenu.getMenu().addSubMenu(MENU, MENU_ITEM, i, subcat.getName());
                subMenu.getItem().setOnMenuItemClickListener((sItem) -> {
                    subcatCurrent = subcat.getName();
                    return false;
                });


                for (SubSubCat sscat : subsubcats) {
                    j++;
                    MenuItem smItem = subMenu.add(MENU_ITEM1, MENU_ITEM2, j, sscat.getName());
                    smItem.setOnMenuItemClickListener((mitem) -> {

                        subsubcatCurrent = sscat.getName();
                        inputD[0] = catCurrent;
                        inputD[1] = subcatCurrent;
                        inputD[2] = subsubcatCurrent;
                        if (parent instanceof MyntraMainActivity) {
                            System.out.println("sai111 parent instanceof MyntraMainActivity");
                            ((MyntraMainActivity) parent).communicateM(inputD);
                        } else {
                            System.out.println("sai111 parent NOT instanceof MyntraMainActivity");
                        }
                        return false;
                    });
                    //Bind Listener with data
                    MENU_ITEM2++;
                }
            }
            MENU_ITEM++;
        }
/*
        popupMenu.getMenu().add(MENU1, MENU_1_ITEM, 0, getText(R.string.menu1));
        popupMenu.getMenu().add(MENU2, MENU_2_ITEM, 1, getText(R.string.menu2));
        SubMenu menu3 = popupMenu.getMenu().addSubMenu(MENU3, MENU_3_ITEM, 2, getText(R.string.menu3));
        menu3.add(MENU3, MENU_3_1_ITEM, 0, getText(R.string.menu3_1));
        menu3.add(MENU3, MENU_3_2_ITEM, 1, getText(R.string.menu3_2));
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

 */
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getSubMenu() != null) {
                    Toast.makeText(context, "1:" + catCurrent + "2:>" + subcatCurrent + "3:>" + subsubcatCurrent + ">" + " Sub Menu FOiund " + item.getTitle(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "1:" + catCurrent + "2:>" + subcatCurrent + "3:>" + subsubcatCurrent + ">" + " No Sub Menu" + item.getTitle(), Toast.LENGTH_SHORT).show();
                  /*  if (parent instanceof MyntraMainActivity) {
                        System.out.println("sai111 parent instanceof MyntraMainActivity");
                        ((MyntraMainActivity) parent).communicateM("Categoray data");
                    } else {
                        System.out.println("sai111 parent NOT instanceof MyntraMainActivity");
                    }
*/
                }

                //invoke data based on Item text
                return false;
            }
        });
        popupMenu.show();

    }


    private String prodCatJson = "" +
            "  [{ \"displayName\":\"Cloathing\",\"subCat\":[]}]  ";

    private void debug(String msg) {
        System.out.println("" + msg);
    }

    @Override
    public void onClick(View view) {
        System.out.println(" Going to refresh Middle fragment & going to pass cat or sub cat name");
        //https://stackoverflow.com/questions/22386109/how-to-update-the-listview-of-a-fragment-from-another-fragment

        //FragmentManager fm = getSupportFragmentManager();
        // fm.beginTransaction().replace(R.id.frameBuy, YourFragment.newInstance(), "yourFragTag").commit();
        try {
            MyntraSO.loadGenPruducts(context);//TODO testing purpose only
        } catch (IOException e) {
            e.printStackTrace();
        }
        catCurrent = "";
        subcatCurrent = "";
        subsubcatCurrent = "";
        Object objB = view.findViewById(view.getId());
        int buttonId = view.getId();
        if (objB instanceof Button) {
            Button bt = (Button) objB;
            catCurrent = "" + bt.getText();
            bt.getId();
            System.out.println("sai 10 march  button text" + bt.getText() + " id " + bt.getId());
            //
            Map<String, List<SubCat>> map = GetProduct.getAllCatMap(catsGlobal);
            if (map.get(bt.getText()) != null) {
                //https://learningprogramming.net/mobile/android/create-dynamically-popup-menu-in-android/
                // SHOW popup menu
                System.out.println("12march SaiPopmenu category=" + bt.getText());
                List<SubCat> subact = map.get(bt.getText());
                System.out.println("12march SaiPopmenu Sub Categorery=" + subact);
                if (subact != null && subact.size() > 0) {

                    popupMenuDisplay(subact, bt);

                    //  return;
                }
            } else {
                //Populate Itenms as no sub categoray
                if (parent instanceof MyntraMainActivity) {
                    System.out.println("sai111 parent instanceof MyntraMainActivity");
                    String inputD[] = new String[3];
                    inputD[0] = catCurrent;
                    inputD[1] = "";
                    inputD[2] = "";//""
                    ((MyntraMainActivity) parent).communicateM(inputD);
                } else {
                    System.out.println("sai111 parent NOT instanceof MyntraMainActivity");
                }

            }
            System.out.println("sai 10 march not button ");
        }
//TODO : This code will move at last menu Item call where no parent
/*
        if( parent instanceof MyntraMainActivity){
            System.out.println("sai111 parent instanceof MyntraMainActivity");
            ((MyntraMainActivity) parent).communicateM("Categoray data");
        }else{
            System.out.println("sai111 parent NOT instanceof MyntraMainActivity");
        }

 */
    }
}