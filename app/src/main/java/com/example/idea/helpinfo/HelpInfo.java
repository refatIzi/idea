package com.example.idea.helpinfo;

/**
 * Клас HelpInfo представляє інформацію для підказок, що включає саму підказку та додаткову інформацію.
 */
public class HelpInfo {
    private String help;
    private String information;

    /**
     * Конструктор класу HelpInfo.
     *
     * @param help Підказка, яка буде зберігатися.
     * @param information Додаткова інформація, пов'язана з підказкою.
     */
    public HelpInfo(String help, String information) {
        this.help = help;
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
