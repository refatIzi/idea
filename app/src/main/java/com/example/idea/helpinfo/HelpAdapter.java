package com.example.idea.helpinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.idea.R;

import java.util.List;

/**
 * Адаптер для відображення інформації про допомогу у ListView.
 */
public class HelpAdapter extends ArrayAdapter<HelpInfo> {
    private final List<HelpInfo> objects;
    private final Context context;
    private final int resource;

    /**
     * Конструктор адаптера HelpAdapter.
     *
     * @param context Контекст використання адаптера.
     * @param resource Ресурс розмітки для кожного елемента.
     * @param objects Список об'єктів HelpInfo для відображення.
     */
    public HelpAdapter(@NonNull Context context, int resource, List<HelpInfo> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.helpTextView = convertView.findViewById(R.id.help);
            viewHolder.typeTextView = convertView.findViewById(R.id.type);
            viewHolder.infoTextView = convertView.findViewById(R.id.info);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HelpInfo helpInfo = objects.get(position);
        viewHolder.helpTextView.setText(helpInfo.getHelp());
        viewHolder.typeTextView.setText(helpInfo.getType());
        viewHolder.infoTextView.setText(helpInfo.getInformation());

        return convertView;
    }

    /**
     * ViewHolder для зберігання посилань на види.
     */
    private static class ViewHolder {
        TextView helpTextView;
        TextView typeTextView;
        TextView infoTextView;
    }
}
