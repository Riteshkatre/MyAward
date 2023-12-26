package com.example.myaward.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myaward.Adapter.MyScoreAdapter;
import com.example.myaward.DataModel.CategoryDataModel;
import com.example.myaward.R;

import java.util.ArrayList;
import java.util.List;


public class My_Top_Score_Fragment extends Fragment {
    RecyclerView scoreRcv;
    MyScoreAdapter myScoreAdapter;
    TextView tvNoData;
    SwipeRefreshLayout swipeRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my__top__score, container, false);
        scoreRcv = view.findViewById(R.id.scoreRcv);
        tvNoData = view.findViewById(R.id.tvNoData);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(() -> scoreDatamodel());

        scoreRcv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        myScoreAdapter = new MyScoreAdapter(scoreDatamodel(), getContext());
        scoreRcv.setAdapter(myScoreAdapter);
        return view;
    }

    public List<CategoryDataModel.ScoreDataModel> scoreDatamodel() {
        swipeRefresh.setRefreshing(false);
        scoreRcv.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        List<CategoryDataModel.ScoreDataModel> models = new ArrayList<>();
        models.add(new CategoryDataModel.ScoreDataModel("Ritesh Katre", "katrelucky@gmail.com", "90", "Ahemdabad", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", "Satish", "1234567890", "Brother", "1", "Rejected"));
        models.add(new CategoryDataModel.ScoreDataModel("Ritesh Katre", "katrelucky@gmail.com", "60", "Ahemdabad", "https://cdn1.smartprix.com/rx-iZpeHskvL-w1200-h1200/ZpeHskvL.jpg", "Simran", "1456788362", "Daughter", "2", "Pending"));
        models.add(new CategoryDataModel.ScoreDataModel("Ritesh Katre", "katrelucky@gmail.com", "50", "Ahemdabad", "https://cdn1.smartprix.com/rx-iZpeHskvL-w1200-h1200/ZpeHskvL.jpg", "Ram", "1678620836", "son", "3", "Approved"));
        return models;
    }
}