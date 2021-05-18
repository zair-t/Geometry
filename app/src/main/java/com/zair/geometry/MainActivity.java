package com.zair.geometry;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void solvingTriangle(View view) {
        float a = 0;
        float b = 0;
        float c = 0;
        float alpha = 0;
        float betta = 0;
        float gamma = 0;
        float radiusI = 0;
        float radiusO = 0;
        float area = 0;
        float perimeter = 0;

        HashMap <String, Float> dataGiven = new HashMap<String, Float>();
        HashMap <String, Float> dataFound = new HashMap <String, Float>();

        if(((EditText) findViewById(R.id.a)).getText().toString().equals("?")){
            dataFound.put("a", a);
        }else if(!(((EditText) findViewById(R.id.a)).getText().toString().equals(""))){
            a = Float.parseFloat( ((EditText) findViewById(R.id.a)).getText().toString());
            dataGiven.put("a", a);
        }

        if(((EditText) findViewById(R.id.b)).getText().toString().equals("?")){
            dataFound.put("b", b);
        }else if(!(((EditText) findViewById(R.id.b)).getText().toString().equals(""))){
            b = Float.parseFloat( ((EditText) findViewById(R.id.b)).getText().toString());
            dataGiven.put("b", b);
        }

        if(((EditText) findViewById(R.id.c)).getText().toString().equals("?")){
            dataFound.put("c", c);
        }else if(!(((EditText) findViewById(R.id.c)).getText().toString().equals(""))){
            c = Float.parseFloat( ((EditText) findViewById(R.id.c)).getText().toString());
            dataGiven.put("c", c);
        }

        if(((EditText) findViewById(R.id.alpha)).getText().toString().equals("?")){
            dataFound.put("alpha", alpha);
        }else if(!(((EditText) findViewById(R.id.alpha)).getText().toString().equals(""))){
            alpha = Float.parseFloat( ((EditText) findViewById(R.id.alpha)).getText().toString());
            dataGiven.put("alpha", alpha);
        }

        if(((EditText) findViewById(R.id.betta)).getText().toString().equals("?")){
            dataFound.put("betta", betta);
        }else if(!(((EditText) findViewById(R.id.betta)).getText().toString().equals(""))){
            betta = Float.parseFloat( ((EditText) findViewById(R.id.betta)).getText().toString());
            dataGiven.put("betta", betta);
        }

        if(((EditText) findViewById(R.id.gamma)).getText().toString().equals("?")){
            dataFound.put("gamma", gamma);
        }else if(!(((EditText) findViewById(R.id.gamma)).getText().toString().equals(""))){
            gamma = Float.parseFloat( ((EditText) findViewById(R.id.gamma)).getText().toString());
            dataGiven.put("gamma", gamma);
        }

        if(((EditText) findViewById(R.id.radiusI)).getText().toString().equals("?")){
            dataFound.put("radiusI", radiusI);
        }else if(!(((EditText) findViewById(R.id.radiusI)).getText().toString().equals(""))){
            radiusI = Float.parseFloat( ((EditText) findViewById(R.id.radiusI)).getText().toString());
            dataGiven.put("radiusI", radiusI);
        }

        if(((EditText) findViewById(R.id.radiusO)).getText().toString().equals("?")){
            dataFound.put("radiusO", radiusO);
        }else if(!(((EditText) findViewById(R.id.radiusO)).getText().toString().equals(""))){
            radiusO = Float.parseFloat( ((EditText) findViewById(R.id.radiusO)).getText().toString());
            dataGiven.put("radiusO", radiusO);
        }

        if(((EditText) findViewById(R.id.area)).getText().toString().equals("?")){
            dataFound.put("area", area);
        }else if(!(((EditText) findViewById(R.id.area)).getText().toString().equals(""))){
            area = Float.parseFloat( ((EditText) findViewById(R.id.area)).getText().toString());
            dataGiven.put("area", area);
        }

        if(((EditText) findViewById(R.id.perimeter)).getText().toString().equals("?")){
            dataFound.put("perimeter", perimeter);
        }else if(!(((EditText) findViewById(R.id.perimeter)).getText().toString().equals(""))){
            perimeter = Float.parseFloat( ((EditText) findViewById(R.id.perimeter)).getText().toString());
            dataGiven.put("perimeter", perimeter);
        }

        Intent intent = new Intent(this, Picture.class);
        intent.putExtra("dataFound", dataFound);
        intent.putExtra("dataGiven", dataGiven);
        startActivity(intent);
    }
}
