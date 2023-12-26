package com.example.myaward.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myaward.Activity.AddDetialsActivity;
import com.example.myaward.Adapter.ScoreAdapter;
import com.example.myaward.DataModel.CategoryDataModel;
import com.example.myaward.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class All_Top_Score_Fragment extends Fragment {
    private static final int VOICE_SEARCH_REQUEST_CODE = 200;
    RecyclerView scoreRcv;
    ScoreAdapter scoreAdapter;
    FloatingActionButton add;
    TextView tvNoData;
    EditText searchbar;
    ImageView mic, imgSearch;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all__top__score_, container, false);

        scoreRcv = view.findViewById(R.id.scoreRcv);
        add = view.findViewById(R.id.add);
        tvNoData = view.findViewById(R.id.tvNoData);
        searchbar = view.findViewById(R.id.searchbar);
        mic = view.findViewById(R.id.mic);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        imgSearch = view.findViewById(R.id.imgSearch);

        scoreRcv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));


        scoreAdapter = new ScoreAdapter(scoreDatamodel(), getContext());
        scoreRcv.setAdapter(scoreAdapter);

        add.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AddDetialsActivity.class);
            startActivity(i);
        });

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                scoreAdapter.Search(charSequence, scoreRcv, tvNoData);

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

        mic.setOnClickListener(v -> opneVoiceDialog());

        swipeRefresh.setOnRefreshListener(() -> scoreDatamodel());

        return view;
    }

    public List<CategoryDataModel.ScoreDataModel> scoreDatamodel() {
        swipeRefresh.setRefreshing(false);
        scoreRcv.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        List<CategoryDataModel.ScoreDataModel> models = new ArrayList<>();
        models.add(new CategoryDataModel.ScoreDataModel("Ritesh Katre", "katrelucky@gmail.com", "90", "Ahemdabad", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", "Satish", "1234567890", "Brother", "1", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Lucky Katre", "lucky60@gmail.com", "60", "Bhopal", "https://m.media-amazon.com/images/I/51c+NXm8zLL.jpg", "Simran", "1456788362", "Daughter", "2", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Paras", "paras30@gmail.com", "50", "Bopal", "https://cdn1.smartprix.com/rx-iZpeHskvL-w1200-h1200/ZpeHskvL.jpg", "Ram", "1678620836", "son", "3", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Rohit", "rohit10@gmail.com", "70", "Thaltez", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M.jpg", "kiran", "1293838678", "Sister", "4", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Aziz", "aziz777@gmail.com", "80", "Aanad", "https://www.africau.edu/images/default/sample.pdf", "Satish", "12345678", "Brother", "5", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Deepak", "deepk1234@gmail.com", "99", "Raipur", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "6", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Paras", "paras30@gmail.com", "50", "Bopal", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "7", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Rohit", "rohit10@gmail.com", "70", "Thaltez", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "8", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Aziz", "aziz777@gmail.com", "80", "Aanad", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "9", ""));
        models.add(new CategoryDataModel.ScoreDataModel("Deepak", "deepk1234@gmail.com", "99", "Raipur", "https://www.google.com/search?q=certificate+download&sca_esv=591655088&rlz=1C1RXQR_enIN1018IN1018&tbm=isch&sxsrf=AM9HkKkGFpdeuN3eXCCXqXzycHvPrRpwoQ:1702816244795&source=lnms&sa=X&ved=2ahUKEwik1o3rvJaDAxU3b2wGHXmQA0QQ_AUoAXoECAIQAw&biw=1280&bih=551&dpr=1.5#imgrc=Qd1cD3a6rIXG7M", "Satish", "12345678", "Brother", "10", ""));
        return models;
    }

    private void opneVoiceDialog() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(i, VOICE_SEARCH_REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_SEARCH_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (!arrayList.isEmpty()) {
                String voice = arrayList.get(0);
                searchCategory(voice);
            } else {
                Toast.makeText(getContext(), "No voice input detected", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Can't Complete The Action", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchCategory(String categoryName) {
        scoreAdapter.Search(categoryName, scoreRcv, tvNoData);
    }

    private void updateNoDataVisibility() {
        boolean isSearchResultsEmpty = scoreAdapter.isEmpty();
        if (isSearchResultsEmpty) {
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.GONE);
        }
    }
}