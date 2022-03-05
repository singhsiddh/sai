package com.example.parvatiarchana.myntra.mainfragment.recycleview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parvatiarchana.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListenerMyntra onLoadMoreListener;
    public void setOnLoadMoreListener(OnLoadMoreListenerMyntra mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }private boolean isLoading;
    private Activity activity;
   // private List<Contact> contacts;
    private List<MyntraProducts> contacts;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public ContactAdapter(RecyclerView recyclerView, List<MyntraProducts> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                System.out.println("sai dx ="+dx +" dy "+dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                System.out.println("sai totalItemCount ="+totalItemCount +" lastVisibleItem "+lastVisibleItem);
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }


        });
    }




    @Override
    public int getItemViewType(int position) {
        return contacts.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder =null;
        System.out.println(" sai babab 5 march Contact Adapater View Type "+viewType);
        if (viewType == VIEW_TYPE_ITEM) {
            //View view = LayoutInflater.from(activity).inflate(R.layout.item_recycler_view_row, parent, false);
            View view = LayoutInflater.from(activity).inflate(R.layout.myntra_recyview_items_row_layout, parent, false);
System.out.println(" sai view Holder at userviewHolder"+view);
            holder= new UserViewHolder(view);
        } else if(viewType == VIEW_TYPE_LOADING)
    {
           // View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            View view = LayoutInflater.from(activity).inflate(R.layout.myntra_recyview_items_progessbar_layout, parent, false);

            holder= new LoadingViewHolder(view);
        }else{
            System.out.println(" Sai Error no view type found");
        }
        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        System.out.println("sai view holder type ..."+holder.getClass().getName());
        if (holder instanceof UserViewHolder) {
            MyntraProducts contact = contacts.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
           //...TODO userViewHolder.item_img.setImageURI(contact.getDisplayImageURL());

           // userViewHolder.
            userViewHolder.item_desc.setText(""+contact.getShortDesc());
            
            userViewHolder.item_price.setText(""+contact.getPrice());
            System.out.println(" sai View Holder... "+userViewHolder);



            //userViewHolder..setText(""+contact.getDescription());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }else{
            System.out.println(" sai ELSE  View Holder...EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE ");
        }
    }

    @Override
    public int getItemCount() {
        return contacts == null ? 0 : contacts.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    // "Loading item" ViewHolder
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    // "Normal item" ViewHolder
    protected class UserViewHolder extends RecyclerView.ViewHolder {
      //  public TextView phone;
       // public TextView email;
        public ImageView item_img;
        public TextView wrap_content;
        public TextView item_desc;
        public TextView item_price;
        public UserViewHolder(View view) {
            super(view);
            item_img = (ImageView) view.findViewById(R.id.item_img);
            wrap_content = (TextView) view.findViewById(R.id.wrap_content);
            item_desc = (TextView) view.findViewById(R.id.item_desc);
            item_price = (TextView) view.findViewById(R.id.item_price);


        }


    }
}

