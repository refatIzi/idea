package com.example.idea;

import android.graphics.Color;
import android.os.Bundle;

import com.example.idea.visualization.ActivityEditWatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainInterface {

    EditText editText;
    TextView numberCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numberCode = findViewById(R.id.numberCode);
        editText = findViewById(R.id.txtCode);
        editText.addTextChangedListener(new ActivityEditWatcher(this,"BASH"));
        String text = "#!/bin/bash\n" +
                "# while-menu: a menu driven system information program\n" +
                "DELAY=1 # Number of seconds to display results\n" +
                "while true; do\n" +
                "    clear\n" +
                "\tcat << EOF\n" +
                "        Please Select:\n" +
                "        1. Display System Information\n" +
                "        2. Display Disk Space\n" +
                "        3. Display Home Space Utilization\n" +
                "        0. Quit\n" +
                "EOF\n" +
                "    read -p \"Enter selection [0-3] > \"\n" +
                "    case \"$REPLY\" in\n" +
                "        0)\n" +
                "            break\n" +
                "            ;;\n" +
                "        1)\n" +
                "            echo \"Hostname: $HOSTNAME\"\n" +
                "            uptime\n" +
                "            ;;\n" +
                "        2)\n" +
                "            df -h\n" +
                "            ;;\n" +
                "        3)\n" +
                "            if [[ $(id -u) -eq 0 ]]; then\n" +
                "                echo \"Home Space Utilization (All Users)\"\n" +
                "                du -sh /home/*\n" +
                "            else\n" +
                "                echo \"Home Space Utilization ($USER)\"\n" +
                "                du -sh $HOME\n" +
                "            fi\n" +
                "            ;;\n" +
                "        *)\n" +
                "            echo \"Invalid entry.\"\n" +
                "            ;;\n" +
                "    esac\n" +
                "    sleep \"$DELAY\"\n" +
                "done\n" +
                "echo \"Program terminated.\"";
        editText.setText(text);

      //  writeData();
    }


    public static final String FEB_ONION_DIR = Environment.getExternalStorageDirectory().toString() + "/Documents/";
/*
    public static void writeData() {
        try {
            File filename = new File(FEB_ONION_DIR, "bash.json");
            JSONObject object = new JSONObject();
            JSONObject bash = new JSONObject();
            JSONObject shell = new JSONObject();
            JSONObject python = new JSONObject();
            for (TextColor textColor : Bash.colors()) {
                bash.put(textColor.pattern.toString(), textColor.color);
            }
            object.put("BASH", bash);

            for (TextColor textColor : Shell.colors()) {
                shell.put(textColor.pattern.toString(), textColor.color);
            }
            object.put("SHELL", shell);

            for (TextColor textColor : Python.colors()) {
                python.put(textColor.pattern.toString(), textColor.color);
            }
            object.put("Python", python);

            new FileOutputStream(filename).write(object.toString().getBytes());
        } catch (IOException ex) {
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


 */

    /**
     * Метод котрый выводит номурецию строки
     */

    public void numberOfConstruction(int errLine) {
        int lineCount = editText.getLineCount();
        StringBuilder numberBuilder = new StringBuilder();

        try {
            for (int i = 1; i <= lineCount; i++) {
                numberBuilder.append(i).append("\n");
            }

            numberCode.clearFocus();
            int errorPosition;

            if (errLine == 0) {
                numberCode.setText(numberBuilder.toString());
            } else {
                SpannableStringBuilder text = new SpannableStringBuilder(numberBuilder);
                ForegroundColorSpan errorSpan = new ForegroundColorSpan(Color.rgb(255, 0, 0));

                if (errLine < 10) {
                    errorPosition = 2 * (errLine - 1);
                    text.setSpan(errorSpan, errorPosition, errorPosition + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                } else {
                    errorPosition = 3 * (errLine - 9) + 15;
                    text.setSpan(errorSpan, errorPosition, errorPosition + 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                }

                numberCode.setText(text);
            }
        } catch (Exception e) {
            // Логування або обробка винятків може бути додана тут
            e.printStackTrace();
        }
    }
}