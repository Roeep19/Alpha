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

public class ItemList extends ArrayAdapter<Item> {
    private Activity context;
    private List<Item> itemList;
    public ItemList(Activity context, List<Item> itemList){
        super(context, R.layout.list_layout, itemList);
        this.context = context;
        this.itemList = itemList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView name = listViewItem.findViewById(R.id.name);
        TextView value = listViewItem.findViewById(R.id.value);

        Item item= itemList.get(position);
        name.setText(item.getName());
        value.setText(""+item.getValue());

        return listViewItem;
    }
}
