package com.nalazoocare.chatex;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-06-16
 */
public class CustomAdapter extends BaseAdapter {

    public class ListContents {
        private String msg;
        private int type;

        ListContents(String msg, int type) {
            this.msg = msg;
            this.type = type;
        }
    }


    private ArrayList<ListContents> mList;
    private Button btn;
    private EditText editText;

    public CustomAdapter() {
        this.mList = new ArrayList();
    }

    public void add(String msg, int type) {
        mList.add(new ListContents(msg, type));
    }

    public void remove(int position) {
        mList.remove(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        TextView text = null;
        CustomHolder holder = null;
        LinearLayout layout = null;
        View viewRight = null;
        View viewLeft = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_chatitem, parent, false);
            layout = convertView.findViewById(R.id.layout);
            text = convertView.findViewById(R.id.text);
            viewRight = convertView.findViewById(R.id.imageViewRight);
            viewLeft = convertView.findViewById(R.id.imageViewLeft);


            holder = new CustomHolder();
            holder.mTextView = text;
            holder.layout = layout;
            holder.viewR = viewRight;
            holder.viewL = viewLeft;
            convertView.setTag(holder);

        } else {
            holder = (CustomHolder) convertView.getTag();
            text = holder.mTextView;
            layout = holder.layout;
            viewRight = holder.viewR;
            viewLeft = holder.viewL;
        }


        text.setText(mList.get(position).msg);

        if (mList.get(position).type == 0) {
            layout.setGravity(Gravity.LEFT);
            viewRight.setVisibility(View.GONE);
            viewLeft.setVisibility(View.GONE);
        } else if (mList.get(position).type == 1) {
            layout.setGravity(Gravity.RIGHT);
            viewRight.setVisibility(View.GONE);
            viewLeft.setVisibility(View.GONE);
        } else if (mList.get(position).type == 2) {
            layout.setGravity(Gravity.CENTER);
            viewRight.setVisibility(View.VISIBLE);
            viewLeft.setVisibility(View.VISIBLE);
        }


        // 리스트 아이템을 터치 했을 때 이벤트 발생
//        convertView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // 터치 시 해당 아이템 이름 출력
//                Toast.makeText(context, "리스트 클릭 : "+mList.get(pos), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//        // 리스트 아이템을 길게 터치 했을때 이벤트 발생
//        convertView.setOnLongClickListener(new View.OnLongClickListener() {
//
//            @Override
//            public boolean onLongClick(View v) {
//                // 터치 시 해당 아이템 이름 출력
//                Toast.makeText(context, "리스트 롱 클릭 : "+mList.get(pos), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });

        return convertView;
    }

    private class CustomHolder {
        TextView mTextView;
        LinearLayout layout;
        View viewR;
        View viewL;
    }
}
