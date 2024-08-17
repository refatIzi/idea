package com.example.idea.visualization;

import java.util.regex.Pattern;

public class TextColor {
    public final Pattern pattern;
    public final int color;
    public Pattern pattern(){
        return pattern;
    }
    public int getColor(){
        return color;
    }
    TextColor(Pattern pattern, int color) {
        this.pattern = pattern;
        this.color = color;
    }
}
