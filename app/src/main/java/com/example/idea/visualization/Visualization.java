package com.example.idea.visualization;


import android.content.res.XmlResourceParser;

import com.example.idea.MainActivity;
import com.example.idea.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * Клас `Visualization` відповідає за отримання кольорових шаблонів для підсвічування
 * синтаксису мов програмування. Ці шаблони зчитуються з XML-файлу ресурсів додатку.
 */
public class Visualization {

    /**
     * Метод `getColors` зчитує кольори та регулярні вирази з XML-файлу на основі вибраної мови програмування.
     *
     * @param mainActivity Контекст головної активності, що використовується для доступу до ресурсів.
     * @param languageName Назва мови програмування, для якої потрібно отримати кольорові шаблони.
     * @return Масив об'єктів `TextColor`, що містять регулярні вирази та відповідні кольори для підсвічування.
     */
    public static TextColor[] getColors(MainActivity mainActivity, String languageName) {
        // Список для зберігання шаблонів кольорів
        ArrayList<TextColor> colorsList = new ArrayList<>();

        try (XmlResourceParser parser = mainActivity.getResources().getXml(R.xml.languages)) {
            boolean isCorrectLanguage = false;

            // Проходить через всі елементи XML-файлу до кінця документа
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG) {
                    switch (parser.getName()) {
                        case "language":
                            // Перевіряє, чи відповідає мова програмування потрібній
                            String name = parser.getAttributeValue(null, "name");
                            isCorrectLanguage = name.equals(languageName);
                            break;

                        case "pattern":
                            // Якщо мова програмування співпадає, зчитує регулярний вираз і колір
                            if (isCorrectLanguage) {
                                int color = Integer.parseInt(parser.getAttributeValue(null, "color"));
                                Pattern pattern = Pattern.compile(parser.nextText());
                                colorsList.add(new TextColor(pattern, color));
                            }
                            break;
                    }
                }
                // Вихід з області мови програмування
                if (parser.getEventType() == XmlPullParser.END_TAG && "language".equals(parser.getName())) {
                    isCorrectLanguage = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Виводить помилку, якщо вона трапилась під час парсингу XML
        }

        // Перетворює список в масив та повертає його
        return colorsList.toArray(new TextColor[0]);
    }

    /* Треба зробити так щоб данні для відображення зберігалися у файлу або ще могло підтянутися від сервера до додатку */
}
