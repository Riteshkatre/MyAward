package com.example.myaward.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myaward.Fragment.All_Top_Score_Fragment;
import com.example.myaward.Fragment.My_Top_Score_Fragment;
import com.example.myaward.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TopScorerActivity extends AppCompatActivity {
    TabLayout tablay;
    ViewPager2 viewPage2;
    Fragment AllScoreFragment, MyScoreFragment;
    CardView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scorer);
        tablay = findViewById(R.id.tablay);
        viewPage2 = findViewById(R.id.viewpage2);
        back = findViewById(R.id.back);

        AllScoreFragment = new All_Top_Score_Fragment();
        MyScoreFragment = new My_Top_Score_Fragment();

        viewPage2.setAdapter(new ViewPagerAdapter(TopScorerActivity.this));
        new TabLayoutMediator(tablay, viewPage2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0)
                    tab.setText("All Scorer");
                else
                    tab.setText("My Score");
            }
        }).attach();

        back.setOnClickListener(v -> finish());

    }

    public class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return AllScoreFragment;
            } else {
                return MyScoreFragment;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}