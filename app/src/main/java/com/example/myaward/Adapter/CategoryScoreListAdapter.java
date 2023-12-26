package com.example.myaward.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaward.DataModel.CategoryDataModel;
import com.example.myaward.R;

import java.util.List;

public class CategoryScoreListAdapter extends RecyclerView.Adapter<CategoryScoreListAdapter.ViewHolder> {

    List<CategoryDataModel.ScoreDataModel> scoreDataModelList;
    Context context;

    public CategoryScoreListAdapter(List<CategoryDataModel.ScoreDataModel> scoreDataModelList, Context context) {
        this.scoreDataModelList = scoreDataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_category_score_list, parent, false);
        return new CategoryScoreListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryDataModel.ScoreDataModel model = scoreDataModelList.get(position);
        holder.userName.setText(model.getToppername());
        holder.rankno.setText(model.getRankno());

        holder.ll.setOnClickListener(v -> {
            showDetailsDialog(model.getName(), model.getEmail(), model.getToppername(), model.getMobileno(), model.getRelation(), model.getPercentage()
                    , model.getAddress(), model.getRankno());
        });

    }

    private void showDetailsDialog(String userName, String email, String topperName, String mono, String relation,
                                   String percentage, String location, String rank) {
        Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.dialog_show_details);
        View rootLayout = dialog.findViewById(R.id.ll);
        if (rootLayout != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }


        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }

        TextView userNameTextView = dialog.findViewById(R.id.userName);
        TextView emailTextView = dialog.findViewById(R.id.userEmail);
        TextView topperNameTextView = dialog.findViewById(R.id.topperName);
        TextView monoTextView = dialog.findViewById(R.id.mobileNumber);
        TextView relationTextView = dialog.findViewById(R.id.relation);
        TextView percentageTextView = dialog.findViewById(R.id.userPercentage);
        TextView locationTextView = dialog.findViewById(R.id.userAddress);
        TextView rankTextView = dialog.findViewById(R.id.rankno);

        userNameTextView.setText(userName);
        emailTextView.setText(email);
        topperNameTextView.setText(topperName);
        monoTextView.setText(mono);
        relationTextView.setText(relation);
        percentageTextView.setText(percentage);
        locationTextView.setText(location);
        rankTextView.setText(rank);
        CardView cancel = dialog.findViewById(R.id.back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }

    @Override
    public int getItemCount() {
        return scoreDataModelList != null ? scoreDataModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, rankno;
        LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            rankno = itemView.findViewById(R.id.rankno);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
