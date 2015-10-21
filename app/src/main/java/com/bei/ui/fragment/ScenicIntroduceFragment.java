package com.bei.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bei.R;
import com.bei.bean.Scenic;


public class ScenicIntroduceFragment extends Fragment {

    private Scenic scenic;
    private TextView content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        scenic = (Scenic) bundle.getSerializable(Scenic.class.getSimpleName());
        return inflater.inflate(R.layout.f_scenic_introduce, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content = (TextView) view.findViewById(R.id.introduce_content);
        content.setText(scenic.getDes());
    }


}
