package com.example.parvatiarchana.myntra;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.parvatiarchana.R;
import com.example.parvatiarchana.carddata.CardItem;
import com.example.parvatiarchana.carddata.Carddata;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyntraButtomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyntraButtomFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
interface ButtonFragmentInterface{
    public void communicateButtom();
}
    public MyntraButtomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyntraButtomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyntraButtomFragment newInstance(String param1, String param2) {
        MyntraButtomFragment fragment = new MyntraButtomFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       this.view= inflater.inflate(R.layout.myntra_buttom_fragment, container, false);
       Button bt =view.findViewById(R.id.notif_count);
       bt.setOnClickListener( this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Object objB = view.findViewById(view.getId());
        if(objB instanceof Button){
            Button bt= (Button)objB;
            if(bt.getId()==R.id.notif_count){
                //Call chekout Activity and disaply Details
                List<CardItem> cardItems= Carddata.cardItems;
                String ItemDet="";
                for(CardItem it :cardItems ){
                    ItemDet+=""+it;
                }
                Toast.makeText(getContext(), "All Items :"+ItemDet, Toast.LENGTH_SHORT).show();
                //Call ProductSummayActivity
                Intent intent = new Intent(getActivity(), ProductSummayActivity.class);

                startActivity(intent);


            }
            }

    }
}