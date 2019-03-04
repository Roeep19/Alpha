package com.example.roee_p.alpha;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NameList extends ArrayAdapter<Item> {
    public Activity context;
    public List<Item> itemList;

    public NameList(Activity context, List<Item> itemList)
    {
        super(context, R.layout.list_layout, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.name_list, null, true);
        TextView description;
        description = listViewItem.findViewById(R.id.description);


        Item item = itemList.get(position);
        description.setText(item.getDescription());


        return listViewItem;
    }
}
