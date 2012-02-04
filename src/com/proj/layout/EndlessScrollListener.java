package com.proj.layout;

import java.util.ArrayList;

import com.proj.food.Food;
import com.proj.food.FoodService;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.AbsListView.OnScrollListener;

public class EndlessScrollListener implements OnScrollListener {
	 
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private String KeyWord = null;
    private int initPosition = 0;
    private ArrayAdapter<Food> adapter = null;
    
    public EndlessScrollListener() {
    }
    public EndlessScrollListener(int visibleThreshold, String keyword, int position, ArrayAdapter<Food> _adapter) {
        this.visibleThreshold = visibleThreshold;
        KeyWord = keyword;
        initPosition = position;
        adapter = _adapter;
    }
 
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // I load the next page of gigs using a background task,
            // but you can call any function here.
        	FoodService service = new FoodService();
            ArrayList<Food> newItems = service.getSearchList(KeyWord, initPosition+10);
            if(newItems != null && newItems.size() > 0)
            {
            	for(Food item : newItems)
            	{
            		adapter.add(item);
            	}
            
            }
            loading = true;
        }
    }
 
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

}