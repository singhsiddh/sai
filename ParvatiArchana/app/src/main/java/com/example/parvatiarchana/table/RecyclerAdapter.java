package com.example.parvatiarchana.table;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parvatiarchana.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public RecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView date;
        private Button btn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
            btn =(Button) itemView.findViewById(R.id.tab_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "name"+name.getText()+"dt : "+date.getText(), Toast.LENGTH_SHORT).show();
                   Holidays hol = new Holidays(""+name.getText(),""+date.getText());
                    // open popup and display  item and update list
                  for ( Object obj : listRecyclerItem)  {
                      if (obj instanceof Holidays) {
                          Toast.makeText(view.getContext(), " holidays instance", Toast.LENGTH_SHORT).show();

                          Holidays hol1 = (Holidays)obj;
                          if(hol1.getName().equals(""+name.getText())){
                              listRecyclerItem.remove(hol1);
                              //Hint : How to refresh view ...
                              notifyDataSetChanged();
                              break;
                          }
                      }else{
                          Toast.makeText(view.getContext(), "Not holiday instance", Toast.LENGTH_SHORT).show();

                      }
                  }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_item, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Holidays holidays = (Holidays) listRecyclerItem.get(i);

                itemViewHolder.name.setText(holidays.getName());
                itemViewHolder.date.setText(holidays.getDate());
        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}