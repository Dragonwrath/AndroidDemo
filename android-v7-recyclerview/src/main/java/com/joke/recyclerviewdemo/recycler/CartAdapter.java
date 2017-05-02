package com.joke.recyclerviewdemo.recycler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.TextView;

import com.joke.recyclerviewdemo.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private static final String TAG = "CartAdapter";

    private Context context;
    private List<CartBean> list;
    private boolean edit,selectAll;

    private RecyclerViewListener listener;

    public CartAdapter(Context context, List<CartBean> list) {
        this.context = context;
        this.list = list;
    }


    public void setEdited() {
        edit = !edit;
    }
    public void setSelectAll() {
        selectAll = !selectAll;
    }
    public void setOnListener(RecyclerViewListener listener) {
        this.listener = listener;
    }
    @Override
    public int getItemViewType(int position) {
        return edit ? 1 : 2;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);

        return new ViewHolder(viewType,view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int type = holder.getItemViewType();
        CartBean bean = list.get(position);

        switch (type) {
            case 1: {
                holder.mCheckBox.setChecked(selectAll);
                break;
            }
            case 2: {
                break;
            }
        }
        holder.mTitle.setText(bean.title);
        holder.mDescription.setText(bean.description);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox mCheckBox;
        private TextView mTitle;
        private TextView mDescription;
        public ViewHolder(int type ,View itemView) {
            super(itemView);
            if (edit) {//处于编辑模式才可以有相应的点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //传递相应的点击事件
                        listener.onItemClickListener(v, getAdapterPosition());
                    }
                });
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //传递相应的长按事件
                        listener.onItemLongClickListener(getAdapterPosition());
                        return true;
                    }
                });
            }
            switch (type) {
                case 1:
                    mCheckBox = (CheckBox)
                            //ViewStub填充之后，会回收相应的view资源，从而产生空指针，
                            // 因此需要使用填充之后的view去找到相应的控件
                            ((ViewStub)itemView.findViewById(R.id.select)).inflate()
                                    .findViewById(R.id.check);
                    break;
            }
            mTitle = (TextView)itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.description);
        }
    }

}
