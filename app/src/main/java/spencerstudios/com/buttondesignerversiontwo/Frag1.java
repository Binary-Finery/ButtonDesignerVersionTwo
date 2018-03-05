package spencerstudios.com.buttondesignerversiontwo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class Frag1 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_1, container, false);

        AppCompatSeekBar strokeWidth = view.findViewById(R.id.stroke_width);
        final TextView tvStrokeWidth = view.findViewById(R.id.tv_stroke_width);
        Button strokeColor = view.findViewById(R.id.stroke_color);

        strokeWidth.setMax(25);
        strokeWidth.setProgress(Utils.getDimensionPrefs(getContext(), "stroke_width"));
        strokeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo select stroke color
            }
        });

        strokeWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvStrokeWidth.setText(String.format(Locale.getDefault(), "%d dp", i));
                Utils.setDimensionPrefs(getContext(), "stroke_width", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }
}
