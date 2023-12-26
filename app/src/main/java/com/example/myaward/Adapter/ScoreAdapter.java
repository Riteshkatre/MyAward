package com.example.myaward.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myaward.DataModel.CategoryDataModel;
import com.example.myaward.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    List<CategoryDataModel.ScoreDataModel> scoreDataModelList, Searchlist;
    Context context;

    public ScoreAdapter(List<CategoryDataModel.ScoreDataModel> scoreDataModelList, Context context) {
        this.scoreDataModelList = scoreDataModelList;
        this.Searchlist = scoreDataModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_top_scorer, parent, false);
        return new ScoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryDataModel.ScoreDataModel model = Searchlist.get(position);
        holder.userName.setText(model.getName());
        holder.userEmail.setText(model.getEmail());
        holder.userAddress.setText(model.getAddress());

        String percentageText = model.getPercentage() + "%";
        holder.userPercentage.setText(percentageText);

        holder.mobileNumber.setText(model.getMobileno());
        holder.topperName.setText(model.getToppername());
        holder.relation.setText(model.getRelation());
        holder.rankno.setText(model.getRankno());

        holder.viewImage.setOnClickListener(v -> openFile(model.getCertificate()));
        holder.icon.setOnClickListener(v -> openFile(model.getCertificate()));

    }

    private void openFile(String fileUrl) {
        if (fileUrl.toLowerCase(Locale.US).endsWith(".pdf")) {
            viewPdf(fileUrl);
        } else if (fileUrl.toLowerCase(Locale.US).endsWith(".jpg") || fileUrl.toLowerCase(Locale.US).endsWith(".png")) {
            showImageInDialog(fileUrl);
        } else {
            Log.d("File Type", "Unsupported file type");
        }
    }

    private void showImageInDialog(String imageUrl) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);

        dialog.setContentView(R.layout.dialog_image_certificate);
        View rootLayout = dialog.findViewById(R.id.ll);
        if (rootLayout != null) {
            rootLayout.setBackgroundColor(Color.WHITE);
        }

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }

        ImageView imageView = dialog.findViewById(R.id.dialogImageView);
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void viewPdf(String value) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
        context.startActivity(intent);
    }


    public void Search(CharSequence charSequence, RecyclerView rcv, TextView textView) {
        try {
            String charString = charSequence.toString().toLowerCase().trim();
            if (charString.isEmpty()) {
                Searchlist = scoreDataModelList;
                rcv.setVisibility(View.VISIBLE);
            } else {
                int flag = 0;
                List<CategoryDataModel.ScoreDataModel> filterlist = new ArrayList<>();
                for (CategoryDataModel.ScoreDataModel Row : scoreDataModelList) {
                    if (Row.getToppername().toLowerCase().contains(charString.toLowerCase())
                            || Row.getPercentage().toLowerCase().contains(charString.toLowerCase())
                            || Row.getAddress().toLowerCase().contains(charString.toLowerCase())
                            || Row.getRankno().toLowerCase().contains(charString.toLowerCase())) {
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

    @Override
    public int getItemCount() {
        return Searchlist.size();
    }

    public boolean isEmpty() {
        return Searchlist.isEmpty();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName, userEmail, userPercentage, userAddress, topperName, mobileNumber, relation, rankno;
        ImageView userCertificate, icon;
        TextView viewImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            topperName = itemView.findViewById(R.id.topperName);
            mobileNumber = itemView.findViewById(R.id.mobileNumber);
            relation = itemView.findViewById(R.id.relation);
            userPercentage = itemView.findViewById(R.id.userPercentage);
            userAddress = itemView.findViewById(R.id.userAddress);
            viewImage = itemView.findViewById(R.id.viewImage);
            userCertificate = itemView.findViewById(R.id.userCertificate);
            icon = itemView.findViewById(R.id.icon);
            rankno = itemView.findViewById(R.id.rankno);
        }
    }
}
