package com.example.idea.help;

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
public class HelpAdapter extends ArrayAdapter<Help> {
    private final List<Help> objects;
    private final Context context;
    private final int resource;

    /**
     * Конструктор адаптера HelpAdapter.
     *
     * @param context Контекст використання адаптера.
     * @param resource Ресурс розмітки для кожного елемента.
     * @param objects Список об'єктів HelpInfo для відображення.
     */
    public HelpAdapter(@NonNull Context context, int resource, List<Help> objects) {
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

        Help help = objects.get(position);
        viewHolder.helpTextView.setText(help.getHelp());
        viewHolder.typeTextView.setText(help.getType());
        viewHolder.infoTextView.setText(help.getInformation());

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
