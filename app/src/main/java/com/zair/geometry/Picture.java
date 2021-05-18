package com.zair.geometry;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class Picture extends AppCompatActivity {

    TextView given;
    TextView found;
    TextView given_;
    TextView found_;
    TextView solution;
    TextView long_dash;
    TextView solve;

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getIntent().getExtras();
        HashMap<String, Float> dataGiven = (HashMap<String, Float>) arguments.getSerializable("dataGiven");
        HashMap<String, Float> dataFound = (HashMap<String, Float>) arguments.getSerializable("dataFound");
        DrawView.setData(dataGiven);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        float a_given = 0;
        float b_given = 0;
        float c_given = 0;
        float alpha_given = 0;
        float betta_given = 0;
        float gamma_given = 0;

        solve = findViewById(R.id.solve);
        solve.setTextColor(R.color.lightGray);
        String solveStr = "";

        given = findViewById(R.id.given);
        given.setTextColor(R.color.lightGray);
        String givenStr = "";
        if (dataGiven.containsKey("a")) {
            givenStr += "a = " + dataGiven.get("a") + "\n";
            a_given = dataGiven.get("a");
        }
        if (dataGiven.containsKey("b")) {
            givenStr += "b = " + dataGiven.get("b") + "\n";
            b_given = dataGiven.get("b");
        }
        if (dataGiven.containsKey("c")) {
            givenStr += "c = " + dataGiven.get("c") + "\n";
            c_given = dataGiven.get("c");
        }
        if (dataGiven.containsKey("alpha")) {
            givenStr += "alpha = " + dataGiven.get("alpha") + "\n";
            alpha_given = dataGiven.get("alpha");
        }
        if (dataGiven.containsKey("betta")) {
            givenStr += "betta = " + dataGiven.get("betta") + "\n";
            betta_given = dataGiven.get("betta");
        }
        if (dataGiven.containsKey("gamma")) {
            givenStr += "gamma = " + dataGiven.get("gamma") + "\n";
            gamma_given = dataGiven.get("gamma");
        }
        given.setText(givenStr);

        int str = 1;

        found = findViewById(R.id.found);
        found.setTextColor(R.color.lightGray);
        String foundStr = "";
        if (dataFound.containsKey("a")) {
            foundStr += "a - ?" + "\n";
            if (b_given != 0 && c_given != 0 && alpha_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "a = " + Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                str++;
            } else if (b_given != 0 && alpha_given != 0 && gamma_given != 0) {
                betta_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(betta) = sin(180 - (alpha + gamma)) = sin(alpha + gamma) = " +
                        "= sin(alpha)cos(gamma) + cos(alpha)sin(gamma) = " + Math.sin(betta_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + " => " + "a = sin(alpha)b/sin(betta) = " + Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given);
                str++;
            } else if (c_given != 0 && betta_given != 0 && alpha_given != 0) {
                gamma_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(gamma) = sin(180 - (alpha + betta)) = sin(alpha + betta) = " +
                        "= sin(alpha)cos(betta) + cos(alpha)sin(betta) = " + Math.sin(gamma_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                        "a / sin(alpha) = c / sin(gamma)" + " => " + "a = sin(alpha)c/sin(gamma) = " + Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given);
                str++;
            }
        }
        if (dataFound.containsKey("b")) {
            foundStr += "b - ?" + "\n";
            if (a_given != 0 && c_given != 0 && betta_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "b = " + Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));
                str++;
            } else if (a_given != 0 && betta_given != 0 && gamma_given != 0) {
                alpha_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180)
                        + Math.sin(betta_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(alpha) = sin(180 - (betta + gamma)) = sin(betta + gamma) = " +
                        "= sin(betta)cos(gamma) + cos(betta)sin(gamma) = " + Math.sin(alpha_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + " => " + "b = sin(betta)a/sin(alpha) = " + Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given);
                str++;
            } else if (c_given != 0 && betta_given != 0 && alpha_given != 0) {
                gamma_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(gamma) = sin(180 - (alpha + betta)) = sin(alpha + betta) = " +
                        "= sin(alpha)cos(betta) + cos(alpha)sin(betta) = " + Math.sin(gamma_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                        "b / sin(betta) = c / sin(gamma)" + " => " + "b = sin(betta)c/sin(gamma) = " + Math.sin(betta_given * Math.PI / 180) * c_given / Math.sin(gamma_given);
                str++;
            }
        }
        double c = Math.sqrt(b_given * b_given + a_given * a_given - 2 * b_given * a_given * Math.cos(gamma_given * Math.PI / 180));
        if (dataFound.containsKey("c")) {
            foundStr += "c - ?" + "\n";
            if (b_given != 0 && a_given != 0 && gamma_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "c = " + c;
                str++;
            } else if (b_given != 0 && alpha_given != 0 && gamma_given != 0) {
                betta_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(betta) = sin(180 - (alpha + gamma)) = sin(alpha + gamma) = " +
                        "= sin(alpha)cos(gamma) + cos(alpha)sin(gamma) = " + Math.sin(betta_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону c по теореме синусов: " +
                        "c / sin(gamma) = b / sin(betta)" + " => " + "c = sin(gamma)b/sin(betta) = " + Math.sin(gamma_given * Math.PI / 180) * b_given / Math.sin(betta_given);
                str++;
            } else if (a_given != 0 && betta_given != 0 && gamma_given != 0) {
                alpha_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180)
                        + Math.sin(gamma_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(alpha) = sin(180 - (gamma + betta)) = sin(gamma + betta) = " +
                        "= sin(gamma)cos(betta) + cos(gamma)sin(betta) = " + Math.sin(alpha_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону c по теореме синусов: " +
                        "a / sin(alpha) = c / sin(gamma)" + " => " + "c = sin(gamma)a/sin(alpha) = " + Math.sin(gamma_given * Math.PI / 180) * a_given / Math.sin(alpha_given);
                str++;
            }
        }
        if (dataFound.containsKey("alpha")) {
            foundStr += "alpha - ?" + "\n";
            if (a_given != 0 && b_given != 0 && c_given != 0) {
                float cosAlpha = (a_given * a_given - b_given * b_given - c_given * c_given) / (-2 * c_given * b_given);
                solveStr += str + ")" + "Найдем косинус alpha по теореме косинусов: " +
                        "cos(alpha) = " + cosAlpha;
                solveStr += "=>" + "alpha = " + Math.acos(cosAlpha) * 180 / Math.PI + "\n";
                str++;
            } else if (gamma_given != 0 && betta_given != 0 && a_given != 0) {
                alpha_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180)
                        + Math.sin(gamma_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(alpha) = sin(180 - (gamma + betta)) = sin(gamma + betta) = " +
                        "= sin(gamma)cos(betta) + cos(gamma)sin(betta) = " + Math.sin(alpha_given);
                solveStr += "=>" + "alpha = " + alpha_given * 180 / Math.PI + "\n";
                str++;
            } else if (gamma_given != 0 && a_given != 0 && b_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "c = " + c;
                str++;
                solveStr += str + ")" + "Найдем синус alpha по теореме синусов: " +
                        "a / sin(alpha) = c / sin(gamma)" + " => " + "sin(alpha) = sin(gamma)a/c" + " => " + "alpha = " + Math.asin(Math.sin(gamma_given * 180 / Math.PI) * a_given / c) + "\n";
                str++;
            } else if (betta_given != 0 && c_given != 0 && a_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "b = " + Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));
                str++;
                solveStr += str + ")" + "Найдем синус alpha по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + "=>" + "sin(alpha) = sin(betta)a/b" + " => " + "alpha = " + Math.asin(Math.sin(betta_given * 180 / Math.PI) * a_given / c_given) + "\n";
                str++;
            }
        }
        if (dataFound.containsKey("betta")) {
            foundStr += "betta - ?" + "\n";
            if (a_given != 0 && b_given != 0 && c_given != 0) {
                float cosBetta = (b_given * b_given - a_given * a_given - c_given * c_given) / (-2 * a_given * c_given);
                solveStr += str + ")" + "Найдем косинус betta по теореме косинусов: " +
                        "cos(betta) = " + cosBetta;
                solveStr += "=>" + "betta = " + Math.acos(cosBetta) * 180 / Math.PI + "\n";
                str++;
            } else if (gamma_given != 0 && alpha_given != 0 && b_given != 0) {
                betta_given = (float) Math.asin(Math.sin(alpha_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180)
                        + Math.sin(gamma_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(betta) = sin(180 - (gamma + alpha)) = sin(gamma + alpha) = " +
                        "= sin(gamma)cos(alpha) + cos(gamma)sin(alpha) = " + Math.sin(betta_given);
                solveStr += "=>" + "betta = " + betta_given * 180 / Math.PI + "\n";
                str++;
            } else if (gamma_given != 0 && a_given != 0 && b_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "c = " + c;
                str++;
                solveStr += str + ")" + "Найдем синус betta по теореме синусов: " +
                        "b / sin(betta) = c / sin(gamma)" + " => " + "sin(betta) = sin(gamma)b/c" + " => " + "betta = " + Math.asin(Math.sin(gamma_given * 180 / Math.PI) * b_given / c) + "\n";
                str++;
            } else if (alpha_given != 0 && c_given != 0 && b_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "a = " + Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                str++;
                solveStr += str + ")" + "Найдем синус betta по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + "=>" + "sin(betta) = sin(alpha)b/a" + " => " + "betta = " + Math.asin(Math.sin(alpha_given * 180 / Math.PI) * b_given / a_given) + "\n";
                str++;
            }
        }
        if (dataFound.containsKey("gamma")) {
            foundStr += "gamma - ?" + "\n";
            if (a_given != 0 && b_given != 0 && c_given != 0) {
                float cosGamma = (c_given * c_given - b_given * b_given - a_given * a_given) / (-2 * a_given * b_given);
                solveStr += str + ")" + "Найдем косинус gamma по теореме косинусов: " +
                        "cos(gamma) = " + cosGamma;
                solveStr += "=>" + "gamma = " + Math.acos(cosGamma) * 180 / Math.PI + "\n";
                str++;
            } else if (alpha_given != 0 && betta_given != 0 && c_given != 0) {
                gamma_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(gamma) = sin(180 - (alpha + betta)) = sin(alpha + betta) = " +
                        "= sin(alpha)cos(betta) + cos(alpha)sin(betta) = " + Math.sin(gamma_given);
                solveStr += "=>" + "gamma = " + gamma_given * 180 / Math.PI + "\n";
                str++;
            } else if (alpha_given != 0 && c_given != 0 && b_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "a = " + Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                str++;
                solveStr += str + ")" + "Найдем синус gamma по теореме синусов: " +
                        "a / sin(alpha) = c / sin(gamma)" + " => " + "sin(gamma) = sin(alpha)c/a" + " => " + "alpha = " + Math.asin(Math.sin(alpha_given * 180 / Math.PI) * c_given / a_given) + "\n";
                str++;
            } else if (betta_given != 0 && c_given != 0 && a_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "b = " + Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));
                str++;
                solveStr += str + ")" + "Найдем синус gamma по теореме синусов: " +
                        "c / sin(gamma) = b / sin(betta)" + "=>" + "sin(gamma) = sin(betta)c/b" + " => " + "gamma = " + Math.asin(Math.sin(betta_given * 180 / Math.PI) * c_given / b_given) + "\n";
                str++;
            }
        }
        if (dataFound.containsKey("radiusI")) {
            foundStr += "r - ?" + "\n";
            float p = 0;
            float S = 0;
            if (a_given != 0 && b_given != 0 && gamma_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "c = " + c;
                p = a_given + b_given + c_given;
                solveStr += " => " + "P = " + p + "\n";
                str++;
            } else if (b_given != 0 && c_given != 0 && alpha_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "a = " + Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                a_given = (float) Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                p = a_given + b_given + c_given;
                solveStr += " => " + "P = " + p + "\n";
                str++;
            } else if (a_given != 0 && c_given != 0 && betta_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "b = " + Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));
                b_given = (float) Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));

                p = a_given + b_given + c_given;
                solveStr += " => " + "P = " + p + "\n";
                str++;
            } else if (a_given != 0 && b_given != 0 && c_given != 0) {

                p = a_given + b_given + c_given;
                solveStr += str + ")" + "P = " + p + "\n";
                str++;
            } else if (a_given != 0 && gamma_given != 0 && betta_given != 0) {
                alpha_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180)
                        + Math.sin(betta_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(alpha) = sin(180 - (betta + gamma)) = sin(betta + gamma) = " +
                        "= sin(betta)cos(gamma) + cos(betta)sin(gamma) = " + Math.sin(alpha_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + " => " + "b = sin(betta)a/sin(alpha) = " + Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given) + "\n";
                b_given = (float) (Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given));
                str++;
                solveStr += str + ")" + "Найдем сторону c по теореме синусов: " +
                        "a / sin(alpha) = c / sin(gamma)" + " => " + "c = sin(gamma)a/sin(alpha) = " + Math.sin(gamma_given * Math.PI / 180) * a_given / Math.sin(alpha_given) + "\n";
                c_given = (float) (Math.sin(gamma_given * Math.PI / 180) * a_given / Math.sin(alpha_given));
                str++;

                p = a_given + b_given + c_given;
                solveStr += str + ")" + "P = " + p + "\n";
                str++;
            } else if (b_given != 0 && gamma_given != 0 && alpha_given != 0) {
                betta_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(betta) = sin(180 - (alpha + gamma)) = sin(alpha + gamma) = " +
                        "= sin(alpha)cos(gamma) + cos(alpha)sin(gamma) = " + Math.sin(betta_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + " => " + "a = sin(alpha)b/sin(betta) = " + Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given) + "\n";
                a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given));
                str++;
                solveStr += str + ")" + "Найдем сторону c по теореме синусов: " +
                        "c / sin(gamma) = b / sin(betta)" + " => " + "c = sin(gamma)b/sin(betta) = " + Math.sin(gamma_given * Math.PI / 180) * b_given / Math.sin(betta_given) + "\n";
                c_given = (float) (Math.sin(gamma_given * Math.PI / 180) * b_given / Math.sin(betta_given));
                str++;

                p = a_given + b_given + c_given;
                solveStr += str + ")" + "P = " + p + "\n";
                str++;
            } else if (c_given != 0 && alpha_given != 0 && betta_given != 0) {
                gamma_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(gamma) = sin(180 - (alpha + betta)) = sin(alpha + betta) = " +
                        "= sin(alpha)cos(betta) + cos(alpha)sin(betta) = " + Math.sin(gamma_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                        "a / sin(alpha) = c / sin(gamma)" + " => " + "a = sin(alpha)c/sin(gamma) = " + Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given) + "\n";
                a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given));
                str++;
                solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                        "b / sin(betta) = c / sin(gamma)" + " => " + "b = sin(betta)c/sin(gamma) = " + Math.sin(betta_given * Math.PI / 180) * c_given / Math.sin(gamma_given) + "\n";
                b_given = (float) (Math.sin(betta_given * Math.PI / 180) * c_given / Math.sin(gamma_given));
                str++;
                p = a_given + b_given + c_given;
                solveStr += str + ")" + "P = " + p + "\n";
                str++;
            }
            if (a_given != 0 && b_given != 0 && gamma_given != 0) {
                solveStr += str + ")" + "Найдем площадь по формуле: " +
                        "S = 0.5sin(alpha)ba = " + Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given + "\n";
                S = (float) (Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given);
                str++;
            } else if (b_given != 0 && c_given != 0 && alpha_given != 0) {
                solveStr += str + ")" + "Найдем площадь по формуле: " +
                        "S = 0.5sin(alpha)bc = " + Math.sin(alpha_given * Math.PI / 180) * 0.5 * c_given * b_given + "\n";
                S = (float) (Math.sin(alpha_given * Math.PI / 180) * 0.5 * c_given * b_given);
                str++;
            } else if (a_given != 0 && c_given != 0 && betta_given != 0) {
                solveStr += str + ")" + "Найдем площадь по формуле: " +
                        "S = 0.5sin(betta)ac = " + Math.sin(betta_given * Math.PI / 180) * 0.5 * c_given * a_given + "\n";
                S = (float) (Math.sin(betta_given * Math.PI / 180) * 0.5 * c_given * a_given);
                str++;
            } else if (a_given != 0 && b_given != 0 && c_given != 0) {
                float P = (a_given + b_given + c_given) / 2;
                S = (float) Math.sqrt(P * (P - a_given) * (P - b_given) * (P - c_given));
                solveStr += str + ")" + "Найдем площадь по формуле Герона: " +
                        "S = " + S + "\n";

                str++;
            } else if (a_given != 0 && gamma_given != 0 && betta_given != 0) {
                alpha_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180)
                        + Math.sin(betta_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(alpha) = sin(180 - (betta + gamma)) = sin(betta + gamma) = " +
                        "= sin(betta)cos(gamma) + cos(betta)sin(gamma) = " + Math.sin(alpha_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + " => " + "b = sin(betta)a/sin(alpha) = " + Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given) + "\n";
                b_given = (float) (Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given));
                str++;
                solveStr += str + ")" + "Найдем площадь по формуле: " +
                        "S = 0.5sin(gamma)ab = " + Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given + "\n";
                S = (float) (Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given);
                str++;
            } else if (b_given != 0 && gamma_given != 0 && alpha_given != 0) {
                betta_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(betta) = sin(180 - (alpha + gamma)) = sin(alpha + gamma) = " +
                        "= sin(alpha)cos(gamma) + cos(alpha)sin(gamma) = " + Math.sin(betta_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                        "a / sin(alpha) = b / sin(betta)" + " => " + "a = sin(alpha)b/sin(betta) = " + Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given) + "\n";
                a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given));
                str++;
                solveStr += str + ")" + "Найдем площадь по формуле: " +
                        "S = 0.5sin(gamma)ab = " + Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given + "\n";
                S = (float) (Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given);
                str++;
            } else if (c_given != 0 && alpha_given != 0 && betta_given != 0) {
                gamma_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                        + Math.sin(alpha_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                        "sin(gamma) = sin(180 - (alpha + betta)) = sin(alpha + betta) = " +
                        "= sin(alpha)cos(betta) + cos(alpha)sin(betta) = " + Math.sin(gamma_given) + "\n";
                str++;
                solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                        "a / sin(alpha) = c / sin(gamma)" + " => " + "a = sin(alpha)c/sin(gamma) = " + Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given) + "\n";
                a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given));
                str++;
                solveStr += str + ")" + "Найдем площадь по формуле: " +
                        "S = 0.5sin(betta)ac = " + Math.sin(betta_given * Math.PI / 180) * 0.5 * c_given * a_given + "\n";
                S = (float) (Math.sin(betta_given * Math.PI / 180) * 0.5 * c_given * a_given);
                str++;
            }
            solveStr += str + ")" + "По формуле находим радиус вписанной окружности : " +
                    "S = 0.5Pr => r = 2S/P = " + 2 * S / p;
            str++;

        }
        if (dataFound.containsKey("radiusO")) {
            foundStr += "R - ?" + "\n";
            if (a_given != 0 && b_given != 0 && gamma_given != 0) {
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "c = " + c + "\n";
                str++;
                solveStr += str + ")" + "Найдем радиус описанной окружности по теореме синусов: " +
                        "R = c/2sin(gamma) = " +  c / (2*Math.sin(gamma_given * Math.PI / 180)) + "\n";
                str++;
            } else if (b_given != 0 && c_given != 0 && alpha_given != 0) {
                a_given = (float) Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "a = " + a_given + "\n";
                str++;
                solveStr += str + ")" + "Найдем радиус описанной окружности по теореме синусов: " +
                        "R = a/2sin(alpha) = " + a_given / (2*Math.sin(alpha_given * Math.PI / 180)) + "\n";
                str++;
            } else if (a_given != 0 && c_given != 0 && betta_given != 0) {
                b_given = (float) Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));
                solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                        "b = " + b_given + "\n";
                str++;
                solveStr += str + ")" + "Найдем радиус описанной окружности по теореме синусов: " +
                        "R = b/2sin(betta) = " + b_given /(2*Math.sin(betta_given * Math.PI / 180)) + "\n";
                str++;
            } else if (a_given != 0 && b_given != 0 && c_given != 0) {
                float p = (a_given + b_given + c_given) / 2;
                double S = Math.sqrt(p * (p - a_given) * (p - b_given) * (p - c_given));
                solveStr += str + ")" + "Найдем площадь по формуле Герона: " +
                        "S = " + S + "\n";
                str++;
                solveStr += str + ")" + "Найдем радиус описанной окружности по формуле для нахождения S: " +
                        "S = abc/4R => R = abc/4S " + a_given * b_given * c_given / (4 * S) + "\n";
                str++;
            } else if (a_given != 0 && gamma_given != 0 && betta_given != 0) {
                alpha_given = 180 - gamma_given - betta_given;
                solveStr += str + ")" + "Найдем третий угол: " +
                        "alpha = 180 - gamma - betta = " + alpha_given + "\n";
                solveStr += str + ")" + "Найдем радиус описанной окружности по теореме синусов: " +
                        "R = a/2sin(alpha) = " +  a_given /(2*Math.sin(alpha_given * Math.PI / 180)) + "\n";
                str++;
            } else if (b_given != 0 && gamma_given != 0 && alpha_given != 0) {
                betta_given = 180 - gamma_given - alpha_given;
                solveStr += str + ")" + "Найдем третий угол: " +
                        "betta = 180 - gamma - alpha = " + betta_given + "\n";
                solveStr += str + ")" + "Найдем радиус описанной окружности по теореме синусов: " +
                        "R = b/2sin(betta) = " + b_given /(2*Math.sin(betta_given * Math.PI / 180)) + "\n";
                str++;
            } else if (c_given != 0 && alpha_given != 0 && betta_given != 0) {
                gamma_given = 180 - alpha_given - betta_given;
                solveStr += str + ")" + "Найдем третий угол: " +
                        "gamma = 180 - alpha - betta = " + gamma_given + "\n";
                solveStr += str + ")" + "Найдем радиус описанной окружности по теореме синусов: " +
                        "R = a/2sin(alpha) = " + c_given /(2*Math.sin(gamma_given * Math.PI / 180)) + "\n";
                str++;
            }
        }
            if (dataFound.containsKey("area")) {
                foundStr += "S - ?" + "\n";
                if (a_given != 0 && b_given != 0 && gamma_given != 0) {
                    solveStr += str + ")" + "Найдем площадь по формуле: " +
                            "S = 0.5sin(alpha)ba = " + Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given + "\n";
                    str++;
                } else if (b_given != 0 && c_given != 0 && alpha_given != 0) {
                    solveStr += str + ")" + "Найдем площадь по формуле: " +
                            "S = 0.5sin(alpha)bc = " + Math.sin(alpha_given * Math.PI / 180) * 0.5 * c_given * b_given + "\n";
                    str++;
                } else if (a_given != 0 && c_given != 0 && betta_given != 0) {
                    solveStr += str + ")" + "Найдем площадь по формуле: " +
                            "S = 0.5sin(betta)ac = " + Math.sin(betta_given * Math.PI / 180) * 0.5 * c_given * a_given + "\n";
                    str++;
                } else if (a_given != 0 && b_given != 0 && c_given != 0) {
                    float p = (a_given + b_given + c_given) / 2;
                    solveStr += str + ")" + "Найдем площадь по формуле Герона: " +
                            "S = " + Math.sqrt(p * (p - a_given) * (p - b_given) * (p - c_given)) + "\n";
                    str++;
                } else if (a_given != 0 && gamma_given != 0 && betta_given != 0) {
                    alpha_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180)
                            + Math.sin(betta_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                    solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                            "sin(alpha) = sin(180 - (betta + gamma)) = sin(betta + gamma) = " +
                            "= sin(betta)cos(gamma) + cos(betta)sin(gamma) = " + Math.sin(alpha_given) + "\n";
                    str++;
                    solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                            "a / sin(alpha) = b / sin(betta)" + " => " + "b = sin(betta)a/sin(alpha) = " + Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given) + "\n";
                    b_given = (float) (Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given));
                    str++;
                    solveStr += str + ")" + "Найдем площадь по формуле: " +
                            "S = 0.5sin(gamma)ab = " + Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given + "\n";
                    str++;
                } else if (b_given != 0 && gamma_given != 0 && alpha_given != 0) {
                    betta_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                            + Math.sin(alpha_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                    solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                            "sin(betta) = sin(180 - (alpha + gamma)) = sin(alpha + gamma) = " +
                            "= sin(alpha)cos(gamma) + cos(alpha)sin(gamma) = " + Math.sin(betta_given) + "\n";
                    str++;
                    solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                            "a / sin(alpha) = b / sin(betta)" + " => " + "a = sin(alpha)b/sin(betta) = " + Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given) + "\n";
                    a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given));
                    str++;
                    solveStr += str + ")" + "Найдем площадь по формуле: " +
                            "S = 0.5sin(gamma)ab = " + Math.sin(gamma_given * Math.PI / 180) * 0.5 * a_given * b_given + "\n";
                    str++;
                } else if (c_given != 0 && alpha_given != 0 && betta_given != 0) {
                    gamma_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                            + Math.sin(alpha_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                    solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                            "sin(gamma) = sin(180 - (alpha + betta)) = sin(alpha + betta) = " +
                            "= sin(alpha)cos(betta) + cos(alpha)sin(betta) = " + Math.sin(gamma_given) + "\n";
                    str++;
                    solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                            "a / sin(alpha) = c / sin(gamma)" + " => " + "a = sin(alpha)c/sin(gamma) = " + Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given) + "\n";
                    a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given));
                    str++;
                    solveStr += str + ")" + "Найдем площадь по формуле: " +
                            "S = 0.5sin(betta)ac = " + Math.sin(betta_given * Math.PI / 180) * 0.5 * c_given * a_given + "\n";
                    str++;
                }
            }
            if (dataFound.containsKey("perimeter")) {
                foundStr += "P - ?" + "\n";
                if (a_given != 0 && b_given != 0 && gamma_given != 0) {
                    solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                            "c = " + c;
                    float p = a_given + b_given + c_given;
                    solveStr += " => " + "P = " + p + "\n";
                    str++;
                } else if (b_given != 0 && c_given != 0 && alpha_given != 0) {
                    solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                            "a = " + Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                    a_given = (float) Math.sqrt(b_given * b_given + c_given * c_given - 2 * b_given * c_given * Math.cos(alpha_given * Math.PI / 180));
                    float p = a_given + b_given + c_given;
                    solveStr += " => " + "P = " + p + "\n";
                    str++;
                } else if (a_given != 0 && c_given != 0 && betta_given != 0) {
                    solveStr += str + ")" + "Найдем 3-ю сторону по теореме косинусов: " +
                            "b = " + Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));
                    b_given = (float) Math.sqrt(a_given * a_given + c_given * c_given - 2 * a_given * c_given * Math.cos(betta_given * Math.PI / 180));

                    float p = a_given + b_given + c_given;
                    solveStr += " => " + "P = " + p + "\n";
                    str++;
                } else if (a_given != 0 && b_given != 0 && c_given != 0) {

                    float p = a_given + b_given + c_given;
                    solveStr += str + ")" + "P = " + p + "\n";
                    str++;
                } else if (a_given != 0 && gamma_given != 0 && betta_given != 0) {
                    alpha_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180)
                            + Math.sin(betta_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                    solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                            "sin(alpha) = sin(180 - (betta + gamma)) = sin(betta + gamma) = " +
                            "= sin(betta)cos(gamma) + cos(betta)sin(gamma) = " + Math.sin(alpha_given) + "\n";
                    str++;
                    solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                            "a / sin(alpha) = b / sin(betta)" + " => " + "b = sin(betta)a/sin(alpha) = " + Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given) + "\n";
                    b_given = (float) (Math.sin(betta_given * Math.PI / 180) * a_given / Math.sin(alpha_given));
                    str++;
                    solveStr += str + ")" + "Найдем сторону c по теореме синусов: " +
                            "a / sin(alpha) = c / sin(gamma)" + " => " + "c = sin(gamma)a/sin(alpha) = " + Math.sin(gamma_given * Math.PI / 180) * a_given / Math.sin(alpha_given) + "\n";
                    c_given = (float) (Math.sin(gamma_given * Math.PI / 180) * a_given / Math.sin(alpha_given));
                    str++;

                    float p = a_given + b_given + c_given;
                    solveStr += str + ")" + "P = " + p + "\n";
                    str++;
                } else if (b_given != 0 && gamma_given != 0 && alpha_given != 0) {
                    betta_given = (float) Math.asin(Math.sin(gamma_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                            + Math.sin(alpha_given * Math.PI / 180) * Math.cos(gamma_given * Math.PI / 180));
                    solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                            "sin(betta) = sin(180 - (alpha + gamma)) = sin(alpha + gamma) = " +
                            "= sin(alpha)cos(gamma) + cos(alpha)sin(gamma) = " + Math.sin(betta_given) + "\n";
                    str++;
                    solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                            "a / sin(alpha) = b / sin(betta)" + " => " + "a = sin(alpha)b/sin(betta) = " + Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given) + "\n";
                    a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * b_given / Math.sin(betta_given));
                    str++;
                    solveStr += str + ")" + "Найдем сторону c по теореме синусов: " +
                            "c / sin(gamma) = b / sin(betta)" + " => " + "c = sin(gamma)b/sin(betta) = " + Math.sin(gamma_given * Math.PI / 180) * b_given / Math.sin(betta_given) + "\n";
                    c_given = (float) (Math.sin(gamma_given * Math.PI / 180) * b_given / Math.sin(betta_given));
                    str++;

                    float p = a_given + b_given + c_given;
                    solveStr += str + ")" + "P = " + p + "\n";
                    str++;
                } else if (c_given != 0 && alpha_given != 0 && betta_given != 0) {
                    gamma_given = (float) Math.asin(Math.sin(betta_given * Math.PI / 180) * Math.cos(alpha_given * Math.PI / 180)
                            + Math.sin(alpha_given * Math.PI / 180) * Math.cos(betta_given * Math.PI / 180));
                    solveStr += str + ")" + "Найдем синус третьего угла по формуле синуса суммы:" +
                            "sin(gamma) = sin(180 - (alpha + betta)) = sin(alpha + betta) = " +
                            "= sin(alpha)cos(betta) + cos(alpha)sin(betta) = " + Math.sin(gamma_given) + "\n";
                    str++;
                    solveStr += str + ")" + "Найдем сторону а по теореме синусов: " +
                            "a / sin(alpha) = c / sin(gamma)" + " => " + "a = sin(alpha)c/sin(gamma) = " + Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given) + "\n";
                    a_given = (float) (Math.sin(alpha_given * Math.PI / 180) * c_given / Math.sin(gamma_given));
                    str++;
                    solveStr += str + ")" + "Найдем сторону b по теореме синусов: " +
                            "b / sin(betta) = c / sin(gamma)" + " => " + "b = sin(betta)c/sin(gamma) = " + Math.sin(betta_given * Math.PI / 180) * c_given / Math.sin(gamma_given) + "\n";
                    b_given = (float) (Math.sin(betta_given * Math.PI / 180) * c_given / Math.sin(gamma_given));
                    str++;
                    float p = a_given + b_given + c_given;
                    solveStr += str + ")" + "P = " + p + "\n";
                    str++;
                }
            }
            found.setText(foundStr);
            solve.setText(solveStr);

            given_ = findViewById(R.id.dano);
            given_.setTextColor(R.color.lightGray);

            found_ = findViewById(R.id.found_);
            found_.setTextColor(R.color.lightGray);

            solution = findViewById(R.id.solution);
            solution.setTextColor(R.color.lightGray);

            long_dash = findViewById(R.id.long_dash);
            long_dash.setTextColor(R.color.lightGray);

        }
}
