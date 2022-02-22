package com.example.parvatiarchana.myntra;
//https://stackoverflow.com/questions/20656208/android-adding-view-dynamically-in-a-nested-layout
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.parvatiarchana.R;

import java.util.ArrayList;
import java.util.List;

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
public Activity parent=null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public interface InterFragCommuncation{
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
       parent= requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view= inflater.inflate(R.layout.myntra_header_fragment, container, false);
try {
    addCatInFragment(container, view);
}catch(Exception e){
    e.printStackTrace();
}
       return view;
    }
    private void addCatInFragment( ViewGroup container,View view){
        List<ProductCategorary> cats =getProdCat();
        Button bt = null;
        ViewGroup vg = view.findViewById(R.id.myntra_head_ll_sai);

        debug(" kali-sai-fragment...... layout instance ="+vg);
        LinearLayout ll=(LinearLayout) vg;
        for(ProductCategorary cat: cats){
            debug("sai button going to create ..1");
            bt = new Button(container.getContext()); //new Button(this)
            debug("sai button going to create ..2");
            bt.setText(cat.getDispplayName());
            debug("sai button going to create ..3");
            bt.setOnClickListener(this);
            ll.addView(bt);
            debug("sai button going to create ..4");

        }

    }

    private List<ProductCategorary> getProdCat(){

        List<ProductCategorary> prodCats= new ArrayList<>();
        ProductCategorary cat =null;
        List<ProductSubCat> subCats=null;
        List<ProdSuSubCat> subSubCats=null;
        ProdSuSubCat prodSuSubCat =null;
        ProductSubCat  subCat = null;
        cat = new ProductCategorary();
        cat.setDispplayName("New Launch");
        prodCats.add(cat);
/*
New Arrivals

Clothing
        TopWear
                Tshirts
                format-tshirts

        Bottom
                Jeans
                Causul Tousers
 */
//cat1
        cat = new ProductCategorary();
        cat.setDispplayName("Clothing");
        prodCats.add(cat);
        //sub cat-1 Topwear
        subCats = new ArrayList<>();
        cat.setSubCat(subCats);

        //subcat-1
        subCat = new ProductSubCat();
        subCat.setDispplayName("TopWear");
        subCats.add(subCat);

        subSubCats = new ArrayList<>();
        subCat.setSubSubCats(subSubCats);

        prodSuSubCat = new ProdSuSubCat();

        prodSuSubCat.setDispplayName("T-Shirt");

        subSubCats.add(prodSuSubCat);

        prodSuSubCat = new ProdSuSubCat();

        prodSuSubCat.setDispplayName("Formal Shirts");

        subSubCats.add(prodSuSubCat);

        prodSuSubCat = new ProdSuSubCat();

        prodSuSubCat.setDispplayName("Casual Shirts");

        subSubCats.add(prodSuSubCat);

//Sub cat 2


        // cat = new ProductCategorary();
        subCats = new ArrayList<>();


        cat.setSubCat(subCats);
        subCat = new ProductSubCat();
        subCat.setDispplayName("Bottom");

        subSubCats= new ArrayList<>();
        subCat.setSubSubCats(subSubCats);

        prodSuSubCat = new ProdSuSubCat();

        prodSuSubCat.setDispplayName("Jeans");


        subSubCats.add(prodSuSubCat);

        prodSuSubCat = new ProdSuSubCat();

        prodSuSubCat.setDispplayName("Casul Tousers");

        subSubCats.add(prodSuSubCat);

        prodSuSubCat = new ProdSuSubCat();

        prodSuSubCat.setDispplayName("Shorts");

        subSubCats.add(prodSuSubCat);



        debug("prodCats "+prodCats);

        return prodCats;
    }

    private String prodCatJson="" +
            "  [{ \"displayName\":\"Cloathing\",\"subCat\":[]}]  ";

    private  void  debug(String msg){
        System.out.println(""+msg);
    }

    @Override
    public void onClick(View view) {
        System.out.println(" Going to refresh Middle fragment & going to pass cat or sub cat name");
        //https://stackoverflow.com/questions/22386109/how-to-update-the-listview-of-a-fragment-from-another-fragment

        //FragmentManager fm = getSupportFragmentManager();
       // fm.beginTransaction().replace(R.id.frameBuy, YourFragment.newInstance(), "yourFragTag").commit();

        if( parent instanceof MyntraMainActivity){
            System.out.println("sai parent instanceof MyntraMainActivity");
            ((MyntraMainActivity) parent).communicateM("Categoray data");
        }else{
            System.out.println("sai parent NOT instanceof MyntraMainActivity");
        }
    }
}