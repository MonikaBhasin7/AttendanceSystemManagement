package com.m.attendancesystemmanagement.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.attendancesystemmanagement.Model.Subject;
import com.m.attendancesystemmanagement.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    List<String> textlist=new ArrayList<String>();
    List<Integer> imagelist=new ArrayList<Integer>();

    public ListAdapter(List<String> textlist,List<Integer> imagelist) {
        this.textlist=textlist;
        this.imagelist=imagelist;

    }@NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,viewGroup,false);
        return new ListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        final String texty=textlist.get(i);
        final Integer imagey=imagelist.get(i);

        listViewHolder.text.setText(texty);
        listViewHolder.image.setImageResource(imagey);
    }

    @Override
    public int getItemCount() {
        return textlist.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            image=(ImageView)itemView.findViewById(R.id.image);
            text=(TextView)itemView.findViewById(R.id.text);
        }
    }
}
