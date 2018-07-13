package com.alphabeticalscrollbardemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alphabeticalscrollbardemo.R;
import com.alphabeticalscrollbardemo.activities.RecyclerViewFastScroller;
import com.alphabeticalscrollbardemo.model.ListModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder>  implements RecyclerViewFastScroller.BubbleTextGetter {
    public ArrayList<ListModel> objList = null;

    private Context context = null;

    public ListAdapter(Context context) {
        this.context = context;
        objList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_list, viewGroup, false);
        Holder viewHolder = new Holder(v);
        return viewHolder;
    }

    public void addData(ArrayList<ListModel> mobjList) {
        objList = new ArrayList<>();
        if (mobjList != null && mobjList.size() > 0)
            objList.addAll(mobjList);

        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.txtTitle.setText(objList.get(position).name);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objList != null ? objList.size() : 0;
    }

    @Override
    public String getTextToShowInBubble(int pos) {
        if (pos < 0 || pos >= objList.size())
            return null;

        String name = objList.get(pos).name;
        name = name.contains(" ")? name.split(" ")[1] : name;;
        if (name == null || name.length() < 1)
            return null;

        return name.substring(0, 1);
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtDescription)
        TextView txtDescription;
        @BindView(R.id.row)
        RelativeLayout row;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


   /* @Override
    public String getTextToShowInBubble(int pos) {
        if (pos < 0 || pos >= objList.size())
            return null;

        String name = objList.get(pos).title;
        name = name.contains(" ")? name.split(" ")[1] : name;;
        if (name == null || name.length() < 1)
            return null;

        return name.substring(0, 1);
    }


*/

}
