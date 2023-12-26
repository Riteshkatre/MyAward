package com.example.myaward.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaward.Activity.TopScorerActivity;
import com.example.myaward.DataModel.CategoryDataModel;
import com.example.myaward.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryDataModel> categoryDataModelList, Searchlist;
    Context context;

    public CategoryAdapter(List<CategoryDataModel> categoryDataModelList, Context context) {
        this.categoryDataModelList = categoryDataModelList;
        this.Searchlist = categoryDataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_award_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryDataModel model = Searchlist.get(position);
        holder.categoryName.setText(model.getCategoryName());
        holder.imageArrow.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), TopScorerActivity.class);
            v.getContext().startActivity(i);
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false);
        CategoryScoreListAdapter replyCommentAdapter = new CategoryScoreListAdapter(model.getScoreDataModels(), context);
        holder.categoryRcv.setLayoutManager(layoutManager);
        holder.categoryRcv.setAdapter(replyCommentAdapter);

    }


    @Override
    public int getItemCount() {
        return Searchlist.size();
    }

    public void Search(CharSequence charSequence, RecyclerView rcv, TextView textView) {
        try {
            String charString = charSequence.toString().toLowerCase().trim();
            if (charString.isEmpty()) {
                Searchlist = categoryDataModelList;
                rcv.setVisibility(View.VISIBLE);
            } else {
                int flag = 0;
                List<CategoryDataModel> filterlist = new ArrayList<>();
                for (CategoryDataModel Row : categoryDataModelList) {
                    if (Row.getCategoryName().toLowerCase().contains(charString.toLowerCase())) {
                        filterlist.add(Row);
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    Searchlist = filterlist;
                    rcv.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                } else {
                    rcv.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return Searchlist.isEmpty();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView imageArrow;
        RecyclerView categoryRcv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            imageArrow = itemView.findViewById(R.id.imageArrow);
            categoryRcv = itemView.findViewById(R.id.categoryRcv);
        }
    }
}
