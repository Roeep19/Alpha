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

public class JobList extends ArrayAdapter<Item> {
    public Activity context;
    public List<Item> itemList;

    public JobList(Activity context, List<Item> itemList)
    {
        super(context, R.layout.list_layout, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView description, city, age, email, conditions;
        description = listViewItem.findViewById(R.id.description);
        city = listViewItem.findViewById(R.id.city);
        age = listViewItem.findViewById(R.id.age);
        email = listViewItem.findViewById(R.id.email);
        conditions = listViewItem.findViewById(R.id.conditions);

        Item item = itemList.get(position);
        description.setText(item.getDescription());
        city.setText(item.getCity());
        age.setText(item.getAge());
        email.setText(item.getEmail());
        conditions.setText(item.getConditions());

        return listViewItem;
    }
}
