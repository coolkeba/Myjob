package com.example.administrator.myjob.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myjob.DataHttp.DataUpdata;
import com.example.administrator.myjob.R;
import com.example.administrator.myjob.adapter.Express_Adapter;
import com.example.administrator.myjob.massage_class.Person;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/3/31.
 */

public class Fragment_Take extends Fragment implements Express_Adapter.OnItemClickLitener,SwipeRefreshLayout.OnRefreshListener {

    RecyclerView re;
    SwipeRefreshLayout swipefy;
    public Express_Adapter myAdapter;
    DataUpdata dataUpdata = new DataUpdata(getActivity(),
            "http://s14i594712.iask.in:40293/Action/Action_PostAction_getAll_Phone");

    List<Person> list;
    boolean isCreate = false;
//    DataUpdata dataUpdata = new DataUpdata(getActivity(),
//            "http://s14i594712.iask.in:40293/Action/Action_PostAction_getAll_Phone");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qujian, container, false);
        swipefy = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fy);
        swipefy.setOnRefreshListener(this);
        init();
//        list = new ArrayList<Person>();

        re = (RecyclerView) view.findViewById(R.id.re);
//        GridLayoutManager layout = new GridLayoutManager(getActivity(), 12);
//        layout.setOrientation(LinearLayoutManager.VERTICAL);
//        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//
//            @Override
//            public int getSpanSize(int position) {
//
//                return liveAdapter.getSpanSize(position);
//            }
//        });
        //re.setLayoutManager(layout);
        //re.setAdapter(liveAdapter);
        re.setLayoutManager(new LinearLayoutManager(getActivity()));
        re.setItemAnimator(new DefaultItemAnimator());
        myAdapter = new Express_Adapter(getActivity(), list);
        re.setAdapter(myAdapter);
//        timer.schedule(task, 0, 1000);
        myAdapter.setOnItemClickLitener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

        private void init() {
        list = new ArrayList<Person>();

        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("MM dd, yyyy");
        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);

        for (int i = 0; i < 1; i++) {
            list.add(new Person(bitmap,"罗马仕充电宝20000mA","货架号：2-33",str,"72:00"));
            list.add(new Person(bitmap,"香蕉牛奶6盒装","货架号：12-2",str,"21:69"));
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    int t = 0;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (t >= 2) {
                    timer.cancel();
                    swipefy.setRefreshing(false);
                    is = false;

                } else {
                    is = true;
                    swipefy.setRefreshing(true);
                    t++;
                    Insert();

                }
            }
            super.handleMessage(msg);
        }
    };
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    boolean is = false;
    String ti = null;
    String ce = null;

    public void Insert() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM dd, yyyy");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        dataUpdata.onCreate();
//        Toast.makeText(getActivity(), dataUpdata.resp, Toast.LENGTH_SHORT).show();

        try {
            JSONArray jArray = new JSONArray(dataUpdata.resp);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                String kd_id = json_data.getString("postName");
                String kd_name = json_data.getString("huojiahao");
                list.add(new Person(bitmap,kd_id,"货架号："+kd_name,str,"1"));
                myAdapter.notifyItemInserted(myAdapter.getItemCount() - 1);
                myAdapter.notifyItemRangeChanged(myAdapter.getItemCount() - 1, list.size() - myAdapter.getItemCount() + 1);
                //Toast.makeText(context,kd_id+kd_name,Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (ti != null && ce != null) {
//                list.add(new Person(bitmap, String.valueOf(ti), ce, str, R.drawable.add));
//                myAdapter.notifyItemInserted(myAdapter.getItemCount() - 1);
//                myAdapter.notifyItemRangeChanged(myAdapter.getItemCount() - 1, list.size() - myAdapter.getItemCount() + 1);
        }
        if (is == false) {
            swipefy.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        Insert();
    }



    }



