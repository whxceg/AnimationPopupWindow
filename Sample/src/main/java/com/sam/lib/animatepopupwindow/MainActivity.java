package com.sam.lib.animatepopupwindow;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sam.lib.animation.widget.AnimationPopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AnimationPopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        normalView(v);
//        listView(v);
        recyclerView(v);
    }

    private void normalView(View v) {
        if (popupWindow == null) {
            View view = LayoutInflater.from(v.getContext()).inflate(R.layout.popup_main, null, false);
            popupWindow = new AnimationPopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

//            popupWindow.setClippingEnabled(false);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setFocusable(true);
            popupWindow.addAnimation(R.anim.show_top_to_bottom_sam_anim, R.anim.dismiss_bottom_to_top_sam_anim);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(v, 0, 0);
        }
    }

    private void listView(View v) {
        if (popupWindow == null) {
            View view = LayoutInflater.from(v.getContext()).inflate(R.layout.popup_list, null, false);
            popupWindow = new AnimationPopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setClippingEnabled(false);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setFocusable(true);
            popupWindow.addAnimation(R.anim.show_top_to_bottom_sam_anim, R.anim.dismiss_bottom_to_top_sam_anim);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ListView listView = view.findViewById(R.id.lv);
            listView.setAdapter(new ListViewAdapter());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    popupWindow.dismiss();
                }
            });
        }

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(v, 0, 0);
        }
    }

    private void recyclerView(View v) {
        if (popupWindow == null) {
            View view = LayoutInflater.from(v.getContext()).inflate(R.layout.popup_recyclerview, null, false);
            popupWindow = new AnimationPopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//            popupWindow.setClippingEnabled(false);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setFocusable(true);
            popupWindow.addAnimation(R.anim.show_top_to_bottom_sam_anim, R.anim.dismiss_bottom_to_top_sam_anim);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            RecyclerView recyclerView = view.findViewById(R.id.rv);
            recyclerView.setAdapter(new RecyclerViewAdapter());
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    Log.e("sam", "onDismiss: ");
                }
            });
        }

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(v, 0, 0);
//            popupWindow.showAtLocation(v, Gravity.TOP, 0, 0);
        }


    }


    private static class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_item, parent, false);
            }
            return convertView;
        }
    }

    private static class RecyclerViewAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popup_item, parent, false)) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
