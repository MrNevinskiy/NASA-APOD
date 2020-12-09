package com.hw.apod.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hw.apod.R;
import com.hw.apod.mvp.presenter.list.ISearchListPresenter;
import com.hw.apod.mvp.view.list.SearchItemView;

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.ViewHolder> {

    private ISearchListPresenter presenter;

    public SearchRVAdapter(ISearchListPresenter presenter){
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public SearchRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View searchView = inflater.inflate(R.layout.item_search, parent, false);

        SearchRVAdapter.ViewHolder viewHolder = new SearchRVAdapter.ViewHolder(searchView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRVAdapter.ViewHolder holder, int position) {
        holder.position = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onItemClick(holder);
            }
        });

        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements SearchItemView{
        TextView date;
        int position = -1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.rv_date);
        }

        @Override
        public void setDate(String text) {
            date.setText(text);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
