package spencerstudios.com.buttondesignerversiontwo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class Frag4 extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private View view;
    private LinearLayout radContainer, angelContainer;
    private RadioGroup radioGroupGradientType;
    private TextView angleTitle, tvAngelDegrees, tvCenXDeg, tvCenYDeg, tvRadDp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.frag_4, container, false);

            radioGroupGradientType = view.findViewById(R.id.radio_gradient_type);

            radContainer = view.findViewById(R.id.radial_container);
            angelContainer = view.findViewById(R.id.ll_angle_container);

            final RadioButton radRadial = view.findViewById(R.id.radio_radial);
            RadioButton radLinear = view.findViewById(R.id.radio_linear);

            angleTitle = view.findViewById(R.id.tv_angle_title);
            tvAngelDegrees = view.findViewById(R.id.tv_angle_degrees);
            tvCenXDeg = view.findViewById(R.id.tv_center_x_dp);
            tvCenYDeg = view.findViewById(R.id.tv_center_y_dp);
            tvRadDp = view.findViewById(R.id.tv_radius);

            AppCompatSeekBar seekAngle = view.findViewById(R.id.seek_angle);
            AppCompatSeekBar seekCenX = view.findViewById(R.id.seek_radial_center_x);
            AppCompatSeekBar seekCenY = view.findViewById(R.id.seek_radial_center_y);
            AppCompatSeekBar seekRad = view.findViewById(R.id.seek_radial_radius);

            final Switch useGradient = view.findViewById(R.id.switch_use_gradient);
            Switch threeColors = view.findViewById(R.id.switch_use_3_colors);

            seekAngle.setOnSeekBarChangeListener(this);
            seekCenX.setOnSeekBarChangeListener(this);
            seekCenY.setOnSeekBarChangeListener(this);
            seekRad.setOnSeekBarChangeListener(this);

            radRadial.setChecked(Utils.getBooleanPrefs(getActivity(), "use_radial"));

            radRadial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Utils.setBooleanPrefs(getActivity(), "use_radial", b);
                }
            });

            useGradient.setChecked(Utils.getBooleanPrefs(getActivity(), "use_gradient"));

            useGradient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Utils.setBooleanPrefs(getActivity(), "use_gradient", b);
                    if (b) {
                        radioGroupGradientType.setVisibility(View.VISIBLE);
                        if (Utils.getBooleanPrefs(getActivity(), "use_radial")) {
                            angelContainer.setVisibility(View.GONE);
                            radContainer.setVisibility(View.VISIBLE);
                        } else {
                            angelContainer.setVisibility(View.VISIBLE);
                            radContainer.setVisibility(View.GONE);
                        }
                    } else {
                        radioGroupGradientType.setVisibility(View.GONE);
                    }
                }
            });

        }
        return view;
    }

    private void setLayouts() {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {


        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
