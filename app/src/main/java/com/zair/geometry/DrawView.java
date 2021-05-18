package com.zair.geometry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import java.util.HashMap;


//Рисование треугольника
public class DrawView extends View {

    private final Paint paint = new Paint();
    private final Path path = new Path();

    static float a_data = 0;
    static float b_data = 0;
    static float c_data = 0;
    static float alpha_data = 0;
    static float betta_data = 0;
    static float gamma_data = 0;
    static float radiusI_data = 0;
    static float radiusO_data = 0;
    static float area_data = 0;
    static float perimeter_data = 0;

    //Получение данных с первой активности
    public static void setData(HashMap<String, Float> dataGiven){
        if(dataGiven.containsKey("a")){
            a_data = dataGiven.get("a");
        }
        if(dataGiven.containsKey("b")){
            b_data = dataGiven.get("b");
        }
        if(dataGiven.containsKey("c")){
            c_data = dataGiven.get("c");
        }
        if(dataGiven.containsKey("alpha")){
            alpha_data = dataGiven.get("alpha");
        }
        if(dataGiven.containsKey("betta")){
            betta_data = dataGiven.get("betta");
        }
        if(dataGiven.containsKey("gamma")){
            gamma_data = dataGiven.get("gamma");
        }
        if(dataGiven.containsKey("radiusI")){
            radiusI_data = dataGiven.get("radiusI");
        }
        if(dataGiven.containsKey("radiusO")){
            radiusO_data = dataGiven.get("radiusO");
        }
        if(dataGiven.containsKey("area")){
            area_data = dataGiven.get("area");
        }
        if(dataGiven.containsKey("perimeter")){
            perimeter_data = dataGiven.get("perimeter");
        }
    }

    //Данные для рисования
    float a = 0;
    float b = 0;
    float c = 0;
    float alpha = 0;
    float betta = 0;
    float gamma = 0;
    float radiusI = 0;
    float area = 0;

    float x0 = 340; //начальные координаты
    float y0 = 700; //начальные координаты

    float xCG = 0; //координаты центра тяжести
    float yCG = 0; //координаты центра тяжести

    float xI = 0; //координаты центра вписанной окружности
    float yI = 0; //координаты центра вписанной окружности

    //во сколько раз уменьшили стороны, чтобы треугольник не вышел за пределы экрана
    int scale = 1;

    //масштабировать стороны только 1 раз
    int n = 0;

    //для существования треугольника
    static boolean exist = true;


    //для перетаскивания
    boolean drag = false;



    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        //Настраиваем paint
        paint.setAntiAlias(true);
        paint.setColor(R.color.lightGray);
        paint.setTextSize(60.0f);
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Paint.Style.FILL);

        path.reset();

        canvas.drawText("Рисунок:", 500, 150, paint);

        if (n < 1) {
            if (a_data != 0 && b_data != 0 && gamma_data != 0) {
                while ((a_data * 160 + 340 - b_data * 160 * Math.cos(gamma_data * Math.PI / 180)) > 1080 ||
                        (a_data * 160 + 340 - b_data * 160 * Math.cos(gamma_data * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 *  Math.sin(gamma_data * Math.PI / 180)) < 0 ||
                        (1000 -  b_data * 160 *  Math.sin(gamma_data * Math.PI / 180)) > 2000 ||
                        ( a_data * 160 + 340) > 1080 || ( a_data * 160 + 340) < 0 || (700 - b_data * 160 * Math.sin(gamma_data * Math.PI / 180)) < 0) {
                    a_data /= 2;
                    b_data /= 2;
                    scale *= 2;
                }
                a = a_data;
                b = b_data;
                gamma = gamma_data;
            } else if (b_data != 0 && c_data != 0 && alpha_data != 0) {
                while (( b_data * 160 + 340 -  c_data * 160 *  Math.cos(alpha_data * Math.PI / 180)) > 1080 ||
                        ( b_data * 160 + 340 -  c_data * 160 *  Math.cos(alpha_data * Math.PI / 180)) < 0 ||
                        (1000 -  c_data * 160 *  Math.sin(alpha_data * Math.PI / 180)) < 0 ||
                        (1000 -  c_data * 160 *  Math.sin(alpha_data * Math.PI / 180)) > 2000 ||
                        ( b_data * 160 + 340) > 1080 || ( b_data * 160 + 340) < 0 || (700 - b_data * 160 * Math.sin(gamma_data * Math.PI / 180)) < 0) {
                    b_data /= 2;
                    c_data /= 2;
                    scale *= 2;
                }
                a = b_data;
                b = c_data;
                gamma = alpha_data;
            } else if (a_data != 0 && c_data != 0 && betta_data != 0) {
                while (( a_data * 160 + 340 - c_data * 160 *  Math.cos(betta_data * Math.PI / 180)) > 1080 ||
                        ( a_data * 160 + 340 -  c_data * 160 *  Math.cos(betta_data * Math.PI / 180)) < 0 ||
                        (1000 -  c_data * 160 *  Math.sin(betta_data * Math.PI / 180)) < 0 ||
                        (1000 -  c_data * 160 *  Math.sin(betta_data * Math.PI / 180)) > 2000 ||
                        ( a_data * 160 + 340) > 1080 || ( a_data * 160 + 340) < 0 || (700 - b_data * 160 * Math.sin(gamma_data * Math.PI / 180)) < 0) {
                    a_data /= 2;
                    c_data /= 2;
                    scale *= 2;
                }
                a = a_data;
                b = c_data;
                gamma = betta_data;
            } else if (a_data != 0 && b_data != 0 && c_data != 0) {
                gamma = (float) (180 * Math.acos((a_data * a_data + b_data * b_data - c_data * c_data) / (2 * a_data * b_data)) / Math.PI);
                while (a_data * 160 + 340 - (b_data * 160 * Math.cos(gamma * Math.PI / 180)) > 1080 ||
                        (a_data * 160 + 340 - b_data * 160 * Math.cos(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) > 2000 ||
                        (a_data * 160 + 340) > 1080 || (a_data * 160 + 340) < 0 || (700 - b_data * 160 * Math.sin(gamma_data * Math.PI / 180)) < 0) {
                    a_data /= 2;
                    b_data /= 2;
                    scale *= 2;
                }
                a = a_data;
                b = b_data;
            }else if (a_data != 0 && gamma_data != 0 && betta_data !=0){
                gamma = gamma_data;
                alpha = (float)Math.asin(Math.sin(gamma_data * Math.PI / 180) * Math.cos(betta_data * Math.PI / 180)
                              + Math.sin(betta_data * Math.PI / 180) * Math.cos(gamma_data * Math.PI / 180));
                b_data = (float)(Math.sin(betta_data * Math.PI / 180)  * a_data / Math.sin(alpha));
                while (a_data * 160 + 340 - (b_data * 160 * Math.cos(gamma * Math.PI / 180)) > 1080 ||
                        (a_data * 160 + 340 - b_data * 160 * Math.cos(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) > 2000 ||
                        (a_data * 160 + 340) > 1080 || (a_data * 160 + 340) < 0 || (700 - b_data * 160 * Math.sin(gamma_data * Math.PI / 180)) < 0) {
                    a_data /= 2;
                    b_data /= 2;
                    scale *= 2;
                }
                a = a_data;
                b = b_data;
            }else if (b_data != 0 && gamma_data != 0 && alpha_data !=0){
                gamma = gamma_data;
                betta = (float)Math.asin(Math.sin(gamma_data * Math.PI / 180) * Math.cos(alpha_data * Math.PI / 180)
                        + Math.sin(alpha_data * Math.PI / 180) * Math.cos(gamma_data * Math.PI / 180));
                a_data = (float)(Math.sin(alpha_data * Math.PI / 180)  * b_data / Math.sin(betta));
                while (a_data * 160 + 340 - (b_data * 160 * Math.cos(gamma * Math.PI / 180)) > 1080 ||
                        (a_data * 160 + 340 - b_data * 160 * Math.cos(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) > 2000 ||
                        (a_data * 160 + 340) > 1080 || (a_data * 160 + 340) < 0 || (700 - b_data * 160 * Math.sin(gamma_data * Math.PI / 180)) < 0) {
                    a_data /= 2;
                    b_data /= 2;
                    scale *= 2;
                }
                a = a_data;
                b = b_data;
            }else if (c_data != 0 && alpha_data != 0 && betta_data !=0){
                gamma = (float)Math.asin(Math.sin(alpha_data * Math.PI / 180) * Math.cos(betta_data * Math.PI / 180)
                        + Math.sin(betta_data * Math.PI / 180) * Math.cos(alpha_data * Math.PI / 180));
                b_data = (float)(Math.sin(betta_data * Math.PI / 180)  * c_data / Math.sin(gamma));
                a_data = (float)(Math.sin(alpha_data * Math.PI / 180)  * c_data / Math.sin(gamma));
                while (a_data * 160 + 340 - (b_data * 160 * Math.cos(gamma * Math.PI / 180)) > 1080 ||
                        (a_data * 160 + 340 - b_data * 160 * Math.cos(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) < 0 ||
                        (1000 - b_data * 160 * Math.sin(gamma * Math.PI / 180)) > 2000 ||
                        (a_data * 160 + 340) > 1080 || (a_data * 160 + 340) < 0 || (700 - b_data * 160 * Math.sin(gamma_data * Math.PI / 180)) < 0) {
                    a_data /= 2;
                    b_data /= 2;
                    scale *= 2;
                }
                a = a_data;
                b = b_data;
                gamma *= 180 / Math.PI;
            }
                //Находим третью сторону по теореме косинусов
                c = (float) Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(gamma * Math.PI / 180));

                //Проверяем существует ли такой треугольник
                if (((a + b) < c) || ((a + c) < b) || ((c + b) < a)) {
                    exist = false;
                }
            n++;
        }

        if (exist) {
            if (scale != 1) {
                paint.setColor(Color.RED);
                paint.setTextSize(30.0f);
                paint.setStrokeWidth(2.0f);
                paint.setStyle(Paint.Style.FILL);
                if(scale%10!=2&&scale%10!=3&&scale%10!=4) {
                    canvas.drawText("Внимание! Каждая сторона треугольника была уменьшена в " +
                            scale + " раз", 20, 40, paint);
                }else{
                    canvas.drawText("Внимание! Каждая сторона треугольника была уменьшена в " +
                            scale + " раза", 20, 40, paint);
                }
            }

            //центр вписанной окружности
            xI = ((c * ( a * 160 + x0) + b * x0 + a * ( a * 160 + x0 -  b * 160 * (float) Math.cos(gamma * Math.PI / 180))) / (a + b + c));
            yI = (c * y0 + b * y0 + a * (y0 -  b * 160 * (float) Math.sin(gamma * Math.PI / 180))) / (a + b + c);

            //площадь треугольника
            area = (float) (a * b * Math.sin(gamma * Math.PI / 180) * 0.5);

            //Радиус вписанной окружности
            radiusI = 2 * area / (a + b + c);

            if (xCG != 0 && yCG != 0) {
                x0 = (3 * xCG - a * 320 +  b * 160 * (float) Math.cos(gamma * Math.PI / 180)) / 3;
                y0 = (3 * yCG +  b * 160 * (float) Math.sin(gamma * Math.PI / 180)) / 3;
            }

            //Рисуем треугольник с двумя заданными двумя сторонами и углом между ними
           path.moveTo(x0, y0);
           path.lineTo( a * 160 + x0, y0);
           path.lineTo( a * 160 + x0 - b * 160 * (float) Math.cos(gamma * Math.PI / 180),
                  y0 - b * 160 * (float) Math.sin(gamma * Math.PI / 180));
           path.close();

            //Рисуем вписанную окружность
            path.addCircle( xI, yI, radiusI * 160, Path.Direction.CW);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawPath(path, paint);

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Координаты touch события
        float evX = event.getX();
        float evY = event.getY();

        switch(event.getAction()){
            //Касание началось
            case MotionEvent.ACTION_DOWN:
                //включаем режим перетаскивания
                drag = true;
                break;
            //тащим
            case MotionEvent.ACTION_MOVE:
                //если режим перетаскивания включен
                if(drag){
                    //определяем новые координаты для рисования
                    xCG = evX;
                    yCG = evY;
                    //перерисовываем экран
                    invalidate();
                }
                break;
            //касание заваршено
            case MotionEvent.ACTION_UP:
                //выключаем  режим перетаскивания
                drag = false;
                break;

        }
        return true;

     }

}