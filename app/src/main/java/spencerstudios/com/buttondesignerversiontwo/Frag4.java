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

import java.util.Locale;

public class Frag4 extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private View view;
    private LinearLayout radContainer, angelContainer;
    private RadioGroup radioGroupGradientType;
    private TextView angleTitle, tvAngelDegrees, tvCenXDeg, tvCenYDeg, tvRadDp;
    private Switch useGradient, threeColors;
    private RadioButton radLinear, radRadial;
    private AppCompatSeekBar seekAngle, seekCenX, seekCenY, seekRad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.frag_4, container, false);

            initiateViews();

            radRadial.setChecked(Utils.getBooleanPrefs(getActivity(), "use_radial"));

            radRadial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Utils.setBooleanPrefs(getActivity(), "use_radial", b);
                    if (b) {
                        angelContainer.setVisibility(View.GONE);
                        radContainer.setVisibility(View.VISIBLE);
                    } else {
                        angelContainer.setVisibility(View.VISIBLE);
                        radContainer.setVisibility(View.GONE);
                    }
                }
            });

            useGradient.setChecked(Utils.getBooleanPrefs(getActivity(), "use_gradient"));

            useGradient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Utils.setBooleanPrefs(getActivity(), "use_gradient", b);
                    configLayouts();
                }
            });

            configLayouts();
            setValues();
        }
        return view;
    }

    private void configLayouts() {
        if (Utils.getBooleanPrefs(getActivity(), "use_gradient")) {
            radioGroupGradientType.setVisibility(View.VISIBLE);
            if (Utils.getBooleanPrefs(getActivity(), "use_radial")) {
                radRadial.setChecked(true);
                radContainer.setVisibility(View.VISIBLE);
                angelContainer.setVisibility(View.GONE);
            } else {
                radLinear.setChecked(true);
                radContainer.setVisibility(View.GONE);
                angelContainer.setVisibility(View.VISIBLE);
            }
        } else {
            angelContainer.setVisibility(View.GONE);
            radContainer.setVisibility(View.GONE);
            radioGroupGradientType.setVisibility(View.GONE);
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {

            case R.id.seek_radial_center_x:
                tvCenXDeg.setText(String.format(Locale.getDefault(), "%d%%", i));
                Utils.setDimensionPrefs(getActivity(), "center_x", i);
                break;

            case R.id.seek_radial_center_y:
                tvCenYDeg.setText(String.format(Locale.getDefault(), "%d%%", i));
                Utils.setDimensionPrefs(getActivity(), "center_y", i);
                break;

            case R.id.seek_radial_radius:
                tvRadDp.setText(String.format(Locale.getDefault(), "%d DP", i+45));
                Utils.setDimensionPrefs(getActivity(), "radius", i);
                break;

            case R.id.seek_angle:
                tvAngelDegrees.setText(String.format(Locale.getDefault(), "%d degress", i+45));
                Utils.setDimensionPrefs(getActivity(), "angel", i + 45);


        }
    }

    private void setValues(){
        seekCenX.setProgress(Utils.getDimensionPrefs(getActivity(), "center_x"));
        tvCenXDeg.setText(String.format(Locale.getDefault(), "%d%%", Utils.getDimensionPrefs(getActivity(), "center_x")));

        seekCenY.setProgress(Utils.getDimensionPrefs(getActivity(), "center_y"));
        tvCenYDeg.setText(String.format(Locale.getDefault(), "%d%%", Utils.getDimensionPrefs(getActivity(), "center_y")));

        seekRad.setProgress(Utils.getDimensionPrefs(getActivity(), "radius"));
        tvRadDp.setText(String.format(Locale.getDefault(), "%d DP", Utils.getDimensionPrefs(getActivity(), "radius")));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    private void initiateViews() {
        radioGroupGradientType = view.findViewById(R.id.radio_gradient_type);
        radContainer = view.findViewById(R.id.radial_container);
        angelContainer = view.findViewById(R.id.ll_angle_container);

        radRadial = view.findViewById(R.id.radio_radial);
        radLinear = view.findViewById(R.id.radio_linear);

        angleTitle = view.findViewById(R.id.tv_angle_title);
        tvAngelDegrees = view.findViewById(R.id.tv_angle_degrees);
        tvCenXDeg = view.findViewById(R.id.tv_center_x_dp);
        tvCenYDeg = view.findViewById(R.id.tv_center_y_dp);
        tvRadDp = view.findViewById(R.id.tv_radius);

        seekAngle = view.findViewById(R.id.seek_angle);
        seekCenX = view.findViewById(R.id.seek_radial_center_x);
        seekCenY = view.findViewById(R.id.seek_radial_center_y);
        seekRad = view.findViewById(R.id.seek_radial_radius);

        useGradient = view.findViewById(R.id.switch_use_gradient);
        threeColors = view.findViewById(R.id.switch_use_3_colors);

        seekAngle.setOnSeekBarChangeListener(this);
        seekCenX.setOnSeekBarChangeListener(this);
        seekCenY.setOnSeekBarChangeListener(this);
        seekRad.setOnSeekBarChangeListener(this);
    }
}
