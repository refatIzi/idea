package com.example.idea.helpinfo;

/**
 * Клас HelpInfo представляє інформацію для підказок, що включає саму підказку та додаткову інформацію.
 */
public class HelpInfo {
    private String help;
    private String type;

    private String information;

    /**
     * Конструктор класу HelpInfo.
     *
     * @param help        Підказка, яка буде зберігатися.
     * @param type
     * @param information Додаткова інформація, пов'язана з підказкою.
     */
    public HelpInfo(String help, String type, String information) {
        this.help = help;
        this.type = type;
        this.information = information;
    }

    /**
     * Повертає підказку.
     *
     * @return Підказка.
     */
    public String getHelp() {
        return help;
    }

    /**
     * Встановлює підказку.
     *
     * @param help Підказка для встановлення.
     */
    public void setHelp(String help) {
        this.help = help;
    }

    /**
     * Повертає тип інформації, наприклад, Keyword або Command...
     *
     * @return тип інформації.
     */
    public String getType() {
        return type;
    }

    /**
     * Встановлює тип інформації, наприклад, Keyword або Command..
     *
     * @param type тип інформації для встановлення.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Повертає додаткову інформацію.
     *
     * @return Додаткова інформація.
     */
    public String getInformation() {
        return information;
    }

    /**
     * Встановлює додаткову інформацію.
     *
     * @param information Додаткова інформація для встановлення.
     */
    public void setInformation(String information) {
        this.information = information;
    }
}
