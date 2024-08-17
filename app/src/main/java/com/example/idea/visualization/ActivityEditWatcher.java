package com.example.idea.visualization;

import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import com.example.idea.MainActivity;

import java.util.regex.Matcher;

/**
 * Клас `ActivityEditWatcher` реалізує інтерфейс `TextWatcher` та забезпечує
 * динамічне оновлення кольорів тексту на основі мови програмування.
 * Використовується в редакторі тексту для підсвічування синтаксису відповідно
 * до вибраної мови.
 */
public class ActivityEditWatcher implements TextWatcher {

    // Основний клас активності, який використовується для отримання контексту та ресурсів
    MainActivity mainActivity;
    // Назва мови програмування, яка використовується для підсвічування
    String languageName;

    /**
     * Конструктор `ActivityEditWatcher`.
     *
     * @param mainActivity Контекст головної активності, з якою пов'язаний цей спостерігач.
     * @param languageName Назва мови програмування, яка буде використовуватися для підсвічування.
     */
    public ActivityEditWatcher(MainActivity mainActivity, String languageName) {
        this.mainActivity = mainActivity;
        this.languageName = languageName;
    }

    /**
     * Викликається до того, як текст змінюється.
     *
     * @param s Текст перед зміною.
     * @param start Початкова позиція зміни.
     * @param count Кількість символів, що змінюються.
     * @param after Кількість символів, що будуть додані.
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Метод не реалізований, але може бути використаний для попередньої обробки тексту.
    }

    /**
     * Викликається під час зміни тексту.
     *
     * @param s Текст, що змінюється.
     * @param start Початкова позиція зміни.
     * @param before Кількість символів до зміни.
     * @param count Кількість доданих символів.
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Викликає метод numberOfConstruction в MainActivity, коли текст змінюється.
        mainActivity.numberOfConstruction(0);
    }

    /**
     * Викликається після зміни тексту.
     *
     * @param s Змінений текст.
     */
    @Override
    public void afterTextChanged(Editable s) {
        // Видаляє всі наявні підсвічування тексту
        removeSpans(s, ForegroundColorSpan.class);

        // Застосовує нові кольори на основі регулярних виразів для обраної мови програмування
        for (TextColor textColor : Visualization.getColors(mainActivity, languageName)) {
            for (Matcher m = textColor.pattern.matcher(s); m.find(); ) {
                s.setSpan(new ForegroundColorSpan(textColor.color),
                        m.start(),
                        m.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    /**
     * Видаляє всі спани (підсвічування) певного типу з тексту.
     *
     * @param e Текст, з якого потрібно видалити спани.
     * @param type Тип спанів, які потрібно видалити.
     */
    void removeSpans(Editable e, Class<? extends CharacterStyle> type) {
        // Отримує всі спани певного типу в тексті
        CharacterStyle[] spans = e.getSpans(0, e.length(), type);

        // Видаляє кожен знайдений спан
        for (CharacterStyle span : spans) {
            e.removeSpan(span);
        }
    }
}

