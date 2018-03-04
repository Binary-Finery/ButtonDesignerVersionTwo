package spencerstudios.com.buttondesignerversiontwo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class Frag2 extends Fragment implements SeekBar.OnSeekBarChangeListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.frag_2, container, false);

        AppCompatSeekBar cornerAll = view.findViewById(R.id.corners_all);
        AppCompatSeekBar cornerTopLeft = view.findViewById(R.id.corners_top_left);
        AppCompatSeekBar cornerTopRight = view.findViewById(R.id.corners_top_right);
        AppCompatSeekBar cornerBottomLeft = view.findViewById(R.id.corners_bottom_left);
        AppCompatSeekBar cornerBottomRight = view.findViewById(R.id.corners_bottom_right) ;

        cornerAll.setOnSeekBarChangeListener(this);
        cornerTopLeft.setOnSeekBarChangeListener(this);
        cornerTopRight.setOnSeekBarChangeListener(this);
        cornerBottomLeft.setOnSeekBarChangeListener(this);
        cornerBottomRight.setOnSeekBarChangeListener(this);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        switch (seekBar.getId()){

            case R.id.corners_all:

                break;
            case R.id.corners_top_left:

                break;

            case R.id.corners_top_right:

                break;

            case R.id.corners_bottom_left:

                break;

            case R.id.corners_bottom_right:

                break;
       }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
