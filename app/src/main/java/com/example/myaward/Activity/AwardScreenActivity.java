package com.example.myaward.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myaward.Adapter.CategoryAdapter;
import com.example.myaward.DataModel.CategoryDataModel;
import com.example.myaward.R;

import java.util.ArrayList;
import java.util.List;

public class AwardScreenActivity extends AppCompatActivity {
    private static final int VOICE_SEARCH_REQUEST_CODE = 200;
    RecyclerView awardRcv;
    CategoryAdapter categoryAdapter;
    TextView tvNoData;
    EditText searchbar;
    ImageView mic, imgSearch;
    CardView back;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_screen);

        awardRcv = findViewById(R.id.awardRcv);
        back = findViewById(R.id.back);
        tvNoData = findViewById(R.id.tvNoData);
        searchbar = findViewById(R.id.searchbar);
        imgSearch = findViewById(R.id.imgSearch);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        mic = findViewById(R.id.mic);
        awardRcv.setLayoutManager(new LinearLayoutManager(AwardScreenActivity.this, RecyclerView.VERTICAL, false));
        categoryAdapter = new CategoryAdapter(categoryDataModels(), this);
        awardRcv.setAdapter(categoryAdapter);

        back.setOnClickListener(v -> {
            finish();
        });

        mic.setOnClickListener(v -> opneVoiceDialog());

        swipeRefresh.setOnRefreshListener(() -> categoryDataModels());

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                categoryAdapter.Search(charSequence, awardRcv, tvNoData);

                if (charSequence.length() > 0) {
                    mic.setVisibility(View.GONE);
                } else {
                    mic.setVisibility(View.VISIBLE);
                }
                updateNoDataVisibility();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


    }

    public List<CategoryDataModel> categoryDataModels() {
        swipeRefresh.setRefreshing(false);
        awardRcv.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        List<CategoryDataModel> models = new ArrayList<>();
        models.add(new CategoryDataModel("Top Scorer of class 10th", scoreDatamodel()));
        models.add(new CategoryDataModel("Top Scorer of class 12th", scoreDatamodel()));
        models.add(new CategoryDataModel("Top Scorer of Diploma", scoreDatamodel()));
        models.add(new CategoryDataModel("Top Scorer of Degree", scoreDatamodel()));
        models.add(new CategoryDataModel("Top Scorer of Sports", scoreDatamodel()));
        models.add(new CategoryDataModel("Top Scorer of Degree", scoreDatamodel()));
        models.add(new CategoryDataModel("Top Scorer of Degree", scoreDatamodel()));
        models.add(new CategoryDataModel("Degree", scoreDatamodel()));
        models.add(new CategoryDataModel("Degree", scoreDatamodel()));
        scoreDatamodel();
        return models;
    }

    public List<CategoryDataModel.ScoreDataModel> scoreDatamodel() {


        List<CategoryDataModel.ScoreDataModel> models = new ArrayList<>();
        models.add(new CategoryDataModel.ScoreDataModel("Ritesh Katre", "katrelucky810@gmail.com", "90%", "Ahemdabad", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=kaqSpo1Gmz8UdM", "Satish sharma", "12345678", "Brother", "1", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Lucky Katre", "lucky60@gmail.com", "60%", "Bhopal", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=qOYV19Mqog3NFM", "Simran varma", "12345678", "Brother", "2", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Paras katre", "paras30@gmail.com", "50%", "Bopal", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Ram raj", "12345678", "Brother", "3", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Rohit katre", "rohit10@gmail.com", "70%", "Thaltez", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Rahul setupti", "12345678", "Brother", "4", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Aziz katre", "aziz777@gmail.com", "80%", "Aanad", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Vipul darbhanga", "12345678", "Brother", "5", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Deepak katre", "deepk1234@gmail.com", "99%", "Raipur", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "6", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Paras katre", "paras30@gmail.com", "50%", "Bopal", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "7", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Rohit katre", "rohit10@gmail.com", "70%", "Thaltez", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "8", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Aziz katre", "aziz777@gmail.com", "80%", "Aanad", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "9", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Deepak katre", "deepk1234@gmail.com", "99%", "Raipur", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "10", ""));
        return models;
    }

    private void opneVoiceDialog() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(i, VOICE_SEARCH_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_SEARCH_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!arrayList.isEmpty()) {
                String voice = arrayList.get(0);
                searchCategory(voice);
            } else {
                Toast.makeText(this, "No voice input detected", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Can't Complete The Action", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchCategory(String categoryName) {

        categoryAdapter.Search(categoryName, awardRcv, tvNoData);


    }

    private void updateNoDataVisibility() {
        boolean isSearchResultsEmpty = categoryAdapter.isEmpty();
        if (isSearchResultsEmpty) {
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.GONE);
        }
    }

}
