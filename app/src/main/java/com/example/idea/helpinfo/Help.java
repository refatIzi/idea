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

    private static final String[] FUNCTIONS1 = {
            "self", "def", "as", "assert", "break", "continue", "del", "elif", "else",
            "except", "finally", "for", "from", "global", "if", "import", "in", "pass",
            "raise", "return", "try", "while", "with", "yield"
    };

    private static final String[] FUNCTIONS2 = {
            "min()", "setattr()", "abs()", "all()", "dir()", "hex()", "next()", "any()",
            "divmod()", "id()", "sorted()", "ascii()", "enumerate()", "input()", "oct()",
            "max()", "round()", "bin()", "eval()", "exec()", "isinstance()", "ord()", "sum()",
            "filter()", "issubclass()", "pow()", "iter()", "print()", "callable()", "format()",
            "delattr()", "len()", "chr()", "range()", "vars()", "getattr()", "locals()", "repr()",
            "zip()", "compile()", "globals()", "map()", "reversed()", "__import__()", "hasattr()",
            "hash()", "memoryview()"
    };

    private ListView listView;
    private List<HelpInfo> listWords;
    private List<HelpInfo> helper = new ArrayList<>();
    private HelpAdapter adapter;
    private MainInterface mainInterface;
    private Context context;
    private String help;
    private EditText configuration;

    /**
     * Конструктор фрагмента Help.
     *
     * @param context Контекст головної активності.
     */
    @SuppressLint("ValidFragment")
    public Help(Context context) {
        this.mainInterface = (MainInterface) context;
        this.context = context;
        this.listWords = parseInfoByLanguage(context, "BASH");
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
                helper.add(new HelpInfo(info.getHelp(), info.getType(), info.getInformation()));
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * Очищає поточний список інформації про допомогу.
     */
    public void clear() {
        helper.clear();
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
            mainInterface.Information(adapter.getItem(position).getHelp());
            return true;
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedHelp = adapter.getItem(position).getHelp();
        mainInterface.setEdit(selectedHelp.replace(help, ""));

    }


    public List<HelpInfo> parseInfoByLanguage(Context context, String languageName) {

        List<HelpInfo> listWords = new ArrayList<>();
        try {
            XmlResourceParser parser = context.getResources().getXml(R.xml.languages);
            int eventType = parser.getEventType();
            boolean isCorrectLanguage = false;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("language")) {
                            String name = parser.getAttributeValue(null, "name");
                            isCorrectLanguage = name.equals(languageName);
                        } else if (parser.getName().equals("info") && isCorrectLanguage) {
                            String keyword = parser.getAttributeValue(null, "keyword");
                            String type = parser.getAttributeValue(null, "type");
                            String description = parser.getAttributeValue(null, "description");
                            listWords.add(new HelpInfo(keyword, type, description));
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("language")) {
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
