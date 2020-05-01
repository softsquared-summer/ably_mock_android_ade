package com.jinwoo.ably.src.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.jinwoo.ably.R;
import com.jinwoo.ably.src.main.data.Category;

import java.util.ArrayList;

public class DrawerListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int childLayout = 0;
    private ArrayList<Category> categoryList;
    private LayoutInflater inflater;

    public DrawerListAdapter(Context context, int groupLayout, int childLayout, ArrayList<Category> cateogryList) {
        this.context = context;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
        this.categoryList = cateogryList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryList.get(groupPosition).child.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoryList.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(this.groupLayout, parent, false);
        }

        final TextView category = (TextView) convertView.findViewById(R.id.drawer_tv_category);
        category.setText(categoryList.get(groupPosition).groupName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(this.childLayout, parent, false);
        }

        TextView subCategory = (TextView) convertView.findViewById(R.id.drawer_tv_sub_categroy);
        subCategory.setText(categoryList.get(groupPosition).child.get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
