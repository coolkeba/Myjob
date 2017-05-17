package com.example.administrator.myjob.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myjob.R;
import com.example.administrator.myjob.adapter.MyDecoration;
import com.example.administrator.myjob.adapter.RvAdapter_personal;

/**
 * Created by lenovo on 2017/4/21.
 */

public class Fragment_Personal extends Fragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_personal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RvAdapter_personal(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), MyDecoration.VERTICAL_LIST));
        return view;
    }

}
