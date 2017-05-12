package com.joke.v7demo.autocompletetextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.joke.v7demo.R;

import java.util.ArrayList;
import java.util.List;


public class AutoCompleteAdapter<T> extends BaseAdapter implements Filterable {
    private List<T> mOriginalValues;
    private List<T> mObjects;

    private Context mContext;
    private CustomFilter mFilter;
    private final Object mLock = new Object();

    public AutoCompleteAdapter(Context context) {
        mContext = context;
        mFilter = new CustomFilter(null);
    }

    @Override
    public int getCount() {
        return mOriginalValues == null ? 0 : mOriginalValues.size();
    }

    @Override
    public T getItem(int position) {
        return mOriginalValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_complete,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            T t = mOriginalValues.get(position);
            if (t instanceof  String)
            holder.text.setText((CharSequence) t);
        }
        return null;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public void setList(List<T> list) {
        mOriginalValues = list;
    }

    public void setPrefix(String prefix) {
        mFilter.setPrefix(prefix);
    }

    private static class ViewHolder {
        TextView text;

        private ViewHolder(View parent) {
            text = (TextView)parent.findViewById(R.id.auto_text);
        }
    }


    /**
     * 可以抽象出相应的相应的匹配规则，也就是Filter的过滤规则，
     * 我们可以自己指定相应的规则，来帮助我们完成相应的过滤条件
     * 通常情况下，我们使用AppCompatAutoCompleteTextView
     * 或者它的父类AutoCompleteTextView时，设置相应的Adapter
     * 必须实现为如下T extends ListAdapter & Filterable
     * 这是因为
     * 1、由于其内部使用的是ListPopupWindow，因此我们需要继承于ListAdapter
     * 2、由于需要过滤所以我们要保证它是可以执行过滤的
     */
    //Copy from ArrayAdapter.ArrayFilter
    private class CustomFilter extends Filter {

        String pattern;

        public CustomFilter(String pattern) {
            this.pattern = pattern;
        }

        private void setPrefix(String pattern) {
            this.pattern = pattern;
        }

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            final FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<>(mObjects);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                final ArrayList<T> list;
                synchronized (mLock) {
                    list = new ArrayList<>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();
            } else {
                final String prefixString = prefix.toString().toLowerCase();

                final ArrayList<T> values;
                synchronized (mLock) {
                    values = new ArrayList<>(mOriginalValues);
                }

                final int count = values.size();
                final ArrayList<T> newValues = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    final T value = values.get(i);
                    final String valueText = value.toString().toLowerCase();

                    // First match against the whole, non-splitted value
                    if (valueText.startsWith(prefix.toString().toLowerCase())) {
                        newValues.add(value);
                    } else {
                        final String[] words = valueText.split(" ");
                        for (String word : words) {
                            if (word.startsWith(prefixString)) {
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //noinspection unchecked
            mObjects = (List<T>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
