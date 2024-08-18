package com.example.idea.help;

/**
 * Клас, що представляє приклад для конкретного ключового слова.
 */
public class Example {

    private String keyword;
    private String description;
    private String usage;

    /**
     * Конструктор для створення об'єкта Example.
     *
     * @param keyword Ключове слово.
     * @param description Опис ключового слова.
     * @param usage Використання ключового слова.
     */
    public Example(String keyword, String description, String usage) {
        this.keyword = keyword;
        this.description = description;
        this.usage = usage;
    }

    /**
     * Отримує ключове слово.
     *
     * @return Ключове слово.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Встановлює ключове слово.
     *
     * @param keyword Ключове слово.
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Отримує опис ключового слова.
     *
     * @return Опис ключового слова.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Встановлює опис ключового слова.
     *
     * @param description Опис ключового слова.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Отримує використання ключового слова.
     *
     * @return Використання ключового слова.
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Встановлює використання ключового слова.
     *
     * @param usage Використання ключового слова.
     */
    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "Example{" +
                "keyword='" + keyword + '\'' +
                ", description='" + description + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }
}
