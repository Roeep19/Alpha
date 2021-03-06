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

        TextView age= listViewItem.findViewById(R.id.age);
        TextView city = listViewItem.findViewById(R.id.city);
        TextView email = listViewItem.findViewById(R.id.email);
        TextView  description= listViewItem.findViewById(R.id.description);
        TextView conditions= listViewItem.findViewById(R.id.conditions);



        Item item= itemList.get(position);
        city.setText(item.getCity());
        age.setText(""+item.getAge());
        email.setText(item.getEmail());
        description.setText(item.getDescription());
        conditions.setText(item.getConditions());

        return listViewItem;
    }
}
