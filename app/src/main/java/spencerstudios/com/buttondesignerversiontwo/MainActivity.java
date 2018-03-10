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
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private Button button;
    private SharedPreferences prefs;
    private Context ctx = this;
    private LinearLayout buttonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonContainer = findViewById(R.id.ll_btn_container);

        buttonContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Utils.setDimensionPrefs(ctx, "parent_width", convertDpToPx(buttonContainer.getWidth()));
                Toast.makeText(ctx, ""+convertDpToPx(buttonContainer.getWidth()) + " dp", Toast.LENGTH_LONG).show();
                buttonContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        button = findViewById(R.id.button_preview);
        ViewPager viewPager = findViewById(R.id.container);
        TabLayout tabLayout = findViewById(R.id.tabs);

        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);

        applyButtonProperties();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        applyButtonProperties();
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
                case 3:
                    return new Frag4();
                case 4:
                    return new Frag5();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    private int convertDpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + .5);
    }

    public void onBackPressed() {
        prefs.unregisterOnSharedPreferenceChangeListener(this);
        finishAffinity();
    }

    private void applyButtonProperties(){

        GradientDrawable drawable = new GradientDrawable();

        //set button corner radii...
        if (Utils.getBooleanPrefs(ctx, "switch_corner")) {
            drawable.setCornerRadii(new float[]{
                    convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_tl")), convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_tl")), convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_tr")), convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_tr")), convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_br")), convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_br")), convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_bl")), convertDpToPx(Utils.getDimensionPrefs(ctx, "corner_bl"))
            });
        } else {
            int globalCornerRadius = Utils.getDimensionPrefs(ctx, "corner_all");
            drawable.setCornerRadii(new float[]{convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius), convertDpToPx(globalCornerRadius)});
        }

        //set button border width and color...
        int sw = Utils.getDimensionPrefs(ctx, "stroke_width");
        drawable.setStroke(convertDpToPx(sw), Color.parseColor(Utils.getColorPrefs(ctx, "stroke_color")));

        //set button color(s)...
        drawable.setColor(Color.parseColor("#90CAF9"));

        //set button width and height...
        int w = Utils.getDimensionPrefs(ctx, "size_width"), h = Utils.getDimensionPrefs(ctx, "size_height");
        button.setLayoutParams(new LinearLayout.LayoutParams(Utils.getBooleanPrefs(ctx, "switch_width") ? LinearLayout.LayoutParams.WRAP_CONTENT : convertDpToPx(w), Utils.getBooleanPrefs(ctx, "switch_height") ? LinearLayout.LayoutParams.WRAP_CONTENT : convertDpToPx(h)));

        //set button text...
        button.setAllCaps(Utils.getBooleanPrefs(ctx, "all_caps"));
        button.setText(Utils.getColorPrefs(ctx, "button_text"));

        //set button text size in sp...
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, Utils.getDimensionPrefs(ctx, "text_size"));

        //set text color..
        button.setTextColor(Color.parseColor(Utils.getColorPrefs(ctx, "text_color")));

        button.setBackground(drawable);

    }
}
