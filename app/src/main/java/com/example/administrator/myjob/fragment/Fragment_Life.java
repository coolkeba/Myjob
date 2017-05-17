package com.example.administrator.myjob.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myjob.ImageSlideshow.ImageSlideshow;
import com.example.administrator.myjob.R;
import com.example.administrator.myjob.adapter.RvAdapter_life;

/**
 * Created by lenovo on 2017/4/12.
 */

public class Fragment_Life extends Fragment {
    private ImageSlideshow imageSlideshow;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life, container, false);
        imageSlideshow = (ImageSlideshow) view.findViewById(R.id.is_gallery);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_life);
        recyclerView.setAdapter(new RvAdapter_life(getContext()));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        // 为ImageSlideshow设置数据
        imageSlideshow.setDotSpace(12);
        imageSlideshow.setDotSize(12);
        imageSlideshow.setDelay(3000);
//        imageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                switch (position) {
//                    case 0:
//                        startActivity(new Intent(getContext(), Activity_1.class));
//                        break;
//                    case 1:
//                        startActivity(new Intent(getContext(), Activity_2.class));
//                        break;
//                    case 2:
//                        startActivity(new Intent(getContext(), Activity_3.class));
//                        break;
//                }
//            }
//        });
        imageSlideshow.commit();
    }

    private void initData() {
        String[] imageUrls = {"http://pic3.zhimg.com/b5c5fc8e9141cb785ca3b0a1d037a9a2.jpg",
                "http://pic2.zhimg.com/551fac8833ec0f9e0a142aa2031b9b09.jpg",
                "http://pic2.zhimg.com/be6f444c9c8bc03baa8d79cecae40961.jpg"};
        String[] titles = {"读读日报 24 小时热门 TOP 5 · 余文乐和「香港贾玲」乌龙绯闻",
                "写给产品 / 市场 / 运营的数据抓取黑科技教程",
                "学做这些冰冰凉凉的下酒宵夜，简单又方便"};
        for (int i = 0; i < 3; i++) {
            imageSlideshow.addImageTitle(imageUrls[i], titles[i]);
        }
    }

    @Override
    public void onDestroy() {
        // 释放资源
        imageSlideshow.releaseResource();
        super.onDestroy();
    }
}
