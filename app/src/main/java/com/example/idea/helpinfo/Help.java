package com.example.idea.helpinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.idea.MainInterface;
import com.example.idea.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Фрагмент для надання інформації допомоги на основі введеного тексту.
 */
@SuppressLint("ValidFragment")
public class Help extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<HelpInfo> listWords;
    private final List<HelpInfo> helper = new ArrayList<>();
    private HelpAdapter adapter;
    private final MainInterface mainInterface;
    private final Context context;
    private String help;
    private EditText configuration;

    /**
     * Конструктор фрагмента Help.
     *
     * @param context Контекст головної активності.
     */
    public Help(Context context, String languageName) {
        this.mainInterface = (MainInterface) context;
        this.context = context;
        this.listWords = parseInfoByLanguage(context, languageName);
    }

    /**
     * Додає інформацію про допомогу на основі введеного тексту.
     *
     * @param help Префікс для порівняння допоміжних функцій.
     */
    public void helpAdd(String help) {
        this.help = help;
        helper.clear();
        for (HelpInfo info : listWords) {
            if (info.getHelp().startsWith(help)) {
                helper.add(info);
            }
        }
        updateAdapter();
    }

    /**
     * Очищає поточний список інформації про допомогу.
     */
    public void clear() {
        helper.clear();
        updateAdapter();
    }

    /**
     * Оновлює адаптер списку.
     */
    private void updateAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        initializeViews(view);
        setupListView();
        return view;
    }

    /**
     * Ініціалізує види та налаштовує адаптер.
     *
     * @param view Кореневий вигляд фрагмента.
     */
    private void initializeViews(View view) {
        listView = view.findViewById(R.id.search);
        configuration = view.findViewById(R.id.configuration);
        adapter = new HelpAdapter(context, R.layout.iteam_help, helper);
        listView.setAdapter(adapter);
    }

    /**
     * Налаштовує ListView та обробники кліків на елементи.
     */
    private void setupListView() {
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
           // mainInterface.Information(adapter.getItem(position).getHelp());
            Toast.makeText(context,""+adapter.getItem(position).getInformation(),Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedHelp = adapter.getItem(position).getHelp();
        mainInterface.setEdit(selectedHelp.replace(help, ""));
    }

    /**
     * Парсить інформацію про ключові слова та їх типи для вказаної мови.
     *
     * @param context Контекст для отримання ресурсів.
     * @param languageName Назва мови, для якої потрібно парсити дані.
     * @return Список об'єктів HelpInfo з інформацією про ключові слова.
     */
    private List<HelpInfo> parseInfoByLanguage(Context context, String languageName) {
        List<HelpInfo> listWords = new ArrayList<>();
        try {
            XmlResourceParser parser = context.getResources().getXml(R.xml.languages);
            int eventType = parser.getEventType();
            boolean isCorrectLanguage = false;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("language".equals(parser.getName())) {
                            String name = parser.getAttributeValue(null, "name");
                            isCorrectLanguage = name.equals(languageName);
                        } else if ("info".equals(parser.getName()) && isCorrectLanguage) {
                            String keyword = parser.getAttributeValue(null, "keyword");
                            String type = parser.getAttributeValue(null, "type");
                            String description = parser.getAttributeValue(null, "description");
                            listWords.add(new HelpInfo(keyword, type, description));
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("language".equals(parser.getName())) {
                            isCorrectLanguage = false;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listWords;
    }
}
