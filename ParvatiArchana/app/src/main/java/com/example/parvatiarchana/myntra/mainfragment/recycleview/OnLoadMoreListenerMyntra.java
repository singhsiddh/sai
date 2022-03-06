package com.example.parvatiarchana.myntra.mainfragment.recycleview;

public interface OnLoadMoreListenerMyntra {
//http://www.devexchanges.info/2017/02/android-recyclerview-dynamically-load.html

    void onLoadMore();
}
/**
 * Adding listner
 * //position to be clicked
 * final int pos = 0;
 * new Handler().postDelayed(new Runnable() {
 *
 * @Override public void run() {
 * recyclerView.findViewHolderForAdapterPosition(pos).itemView.performClick();
 * }
 * },1);
 */