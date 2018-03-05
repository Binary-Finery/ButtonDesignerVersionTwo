package spencerstudios.com.buttondesignerversiontwo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private DisplayMetrics displayMetrics;
    private GradientDrawable drawable;
    private Button btn;

    private SharedPreferences prefs;
    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;


    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        btn = findViewById(R.id.button_preview);
        ViewPager viewPager = findViewById(R.id.container);
        TabLayout tabLayout = findViewById(R.id.tabs);

        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        prefs.registerOnSharedPreferenceChangeListener(this);

        onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                drawable = new GradientDrawable();

                if (!Utils.getBooleanPrefs(context, "switch_corner")) {
                    drawable.setCornerRadii(new float[]{
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_tl")), //top left
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_tl")), //top left
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_tr")), //top right
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_tr")), //top right
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_br")), //bottom right
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_br")), //bottom right
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_bl")), //bottom left
                            convertDpToPx(Utils.getDimensionPrefs(context, "corner_bl"))   //bottom left
                    });
                } else {
                    int globalCornerRadius = Utils.getDimensionPrefs(context, "corner_all");
                    drawable.setCornerRadii(new float[]{
                            convertDpToPx(globalCornerRadius), //top left
                            convertDpToPx(globalCornerRadius), //top left
                            convertDpToPx(globalCornerRadius), //top right
                            convertDpToPx(globalCornerRadius), //top right
                            convertDpToPx(globalCornerRadius), //bottom right
                            convertDpToPx(globalCornerRadius), //bottom right
                            convertDpToPx(globalCornerRadius), //bottom left
                            convertDpToPx(globalCornerRadius)   //bottom left
                    });
                }
                Random r = new Random();
                btn.setText(""+r.nextInt(100));

                btn.setBackground(drawable);
            }
        };


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        drawable = new GradientDrawable();

        if (Utils.getBooleanPrefs(context, "switch_corner")) {
            drawable.setCornerRadii(new float[]{convertDpToPx(Utils.getDimensionPrefs(context, "corner_tl")), convertDpToPx(Utils.getDimensionPrefs(context, "corner_tl")), convertDpToPx(Utils.getDimensionPrefs(context, "corner_tr")), convertDpToPx(Utils.getDimensionPrefs(context, "corner_tr")), convertDpToPx(Utils.getDimensionPrefs(context, "corner_br")), convertDpToPx(Utils.getDimensionPrefs(context, "corner_br")), convertDpToPx(Utils.getDimensionPrefs(context, "corner_bl")), convertDpToPx(Utils.getDimensionPrefs(context, "corner_bl"))});
        } else {
            int globalCornerRadius = Utils.getDimensionPrefs(context, "corner_all");
            drawable.setCornerRadii(new float[]{convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius)});
        }

        int sw = Utils.getDimensionPrefs(context, "stroke_width");
        drawable.setStroke(convertDpToPx(sw), Color.RED);
        drawable.setColor(Color.parseColor("#90CAF9"));


        btn.setBackground(drawable);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Frag1();
                case 1:
                    return new Frag2();
                case 2:
                    return new Frag3();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private int convertDpToPx(int dp) {
        displayMetrics = getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + .5);
    }

    public void onBackPressed() {
        prefs.unregisterOnSharedPreferenceChangeListener(this);
        finishAffinity();
    }
}
