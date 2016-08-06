package com.hzuapp.team.sports.fragment.mainFrgment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzuapp.team.sports.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataGraphFragment extends Fragment {


    @Bind(R.id.img_head)
    ImageView imgHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.step_count)
    ColumnChartView stepCount;
    @Bind(R.id.time)
    PieChartView time;


    public DataGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_graph, container, false);
        ButterKnife.bind(this,view);
        generateReputationData();
        generateAgeData();
        return view;
    }


    private void generateReputationData() {
        final List<Integer> creditData = new ArrayList<>();
        creditData.add(120);
        creditData.add(220);
        creditData.add(320);
        creditData.add(420);
        creditData.add(520);

        int numColumns = creditData.size();
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<>();
            SubcolumnValue value = new SubcolumnValue((float) creditData.get(i), ChartUtils.pickColor());
            value.setLabel(creditData.get(i).toString());
            values.add(value);

            Column column = new Column(values);
            column.setHasLabels(true);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }

        ColumnChartData data = new ColumnChartData(columns);

        Axis axisY = new Axis().setHasLines(true);
        data.setAxisYLeft(axisY);

        stepCount.setColumnChartData(data);
        stepCount.setValueSelectionEnabled(true);
        stepCount.setZoomEnabled(false);
        stepCount.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {

            }

            @Override
            public void onValueDeselected() {
            }
        });

    }

    private void generateAgeData() {
        int numValues = 4;
        int step[]=new int[]{120,220,320,420,520};
        int ages[] = new int[5];
        for (int i=0;i<step.length;i++){
            ages[i]=step[i];
        }


        String[] labes = {"06~09", "09~12", "12~15", "15~18","18~21"};

        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < numValues; ++i) {
            float a = ages[i];
            float b = step.length;
            SliceValue sliceValue = new SliceValue((a / b) * 100, ChartUtils.pickColor());
            sliceValue.setLabel(labes[i] + " " + String.format("%.2f", (a / b) * 100) + "%");
            values.add(sliceValue);
        }

        PieChartData data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(true);
        data.setHasCenterCircle(false);
        time.setPieChartData(data);
        time.setCircleFillRatio(0.7f);
        time.setChartRotationEnabled(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
