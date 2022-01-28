 package com.example.parvatiarchana;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

 /**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParvatiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParvatiFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view ;

    public ParvatiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParvatiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParvatiFragment newInstance(String param1, String param2) {
        ParvatiFragment fragment = new ParvatiFragment();
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
       view=inflater.inflate(R.layout.fragment_parvati, container, false);
Button bt = (Button) view.findViewById(R.id.button2par);
    bt.setOnClickListener(this::onClick);
        Button bt1 = (Button) view.findViewById(R.id.button2par1);
        bt1.setOnClickListener(this::onClick);
       return view;
    }

    @Override
    public void onClick(View view) {
System.out.println(" Click inside Parvati Listener called :::::::::::::::::::::::::::::::::................");

        Toast.makeText(view.getContext(),"Click in Parvati frgmanet id ="+view.getId(),Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.button2par:
               // c *= fComm.fragmentContactActivity(2);
                Toast.makeText(view.getContext(),"first button="+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2par1:
                Toast.makeText(view.getContext(),"2nd  button="+view.getId(),Toast.LENGTH_SHORT).show();
               // c *= fComm.fragmentContactActivity(4);
                break;
            default:
               // c *= fComm.fragmentContactActivity(100);
                break;
        }
    }
}