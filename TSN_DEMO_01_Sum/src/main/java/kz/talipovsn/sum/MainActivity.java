package kz.talipovsn.sum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText_a; // Переменная для доступа к компоненту со значением "a"
    EditText editText_b; // Переменная для доступа к компоненту со значением "b"
    EditText editText_x; // Переменная для доступа к компоненту со значением "b"
    TextView textView_sum; // Переменная для доступа к компоненту со значением результата
    Button buttonSum; // Переменная для доступа к компоненту кнопки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Доступ к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_x = findViewById(R.id.editText_x);
        textView_sum = findViewById(R.id.textView_sum);
        buttonSum = findViewById(R.id.buttonSum);

        //  Собственный обработчик событий для клавиатуры
        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (    editText_a.getText().toString().trim().equals("") ||
                        editText_b.getText().toString().trim().equals("") ||
                        editText_x.getText().toString().trim().equals("")) {

                    buttonSum.setEnabled(false);

                } else {
                    buttonSum.setEnabled(true);
                }
                return false;
            }
        };
        buttonSum.setEnabled(false); // Выключаем доступность нажатия у кнопки
        editText_a.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_b.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editText_x.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий

        // Проверка на переворот экрана и восстановление нужных значений в компонентах
        if (savedInstanceState != null) {
            editText_a.setText(savedInstanceState.getString("a"));
            editText_b.setText(savedInstanceState.getString("b"));
            editText_x.setText(savedInstanceState.getString("x"));
            textView_sum.setText(savedInstanceState.getString("y"));
            buttonSum.setEnabled(savedInstanceState.getBoolean("button"));
        }

    }

    // Сохранение нужных нам значений из компонент при перевороте экрана
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("a", editText_a.getText().toString());
        outState.putString("b", editText_b.getText().toString());
        outState.putString("x", editText_x.getText().toString());
        outState.putString("y", textView_sum.getText().toString());
        outState.putBoolean("button", buttonSum.isEnabled());
    }


    // МЕТОД КНОПКИ РАСЧЕТА
    public void onClick(View v) {
        // Объявление локальных переменных
        double a, b, x;
        double y = 0;

        try { // НАЧАЛО ЗАЩИЩЕННОГО БЛОКА

            // Чтение данных из компонент
            a = Double.parseDouble(editText_a.getText().toString().trim());
            b = Double.parseDouble(editText_b.getText().toString().trim());
            x = Double.parseDouble(editText_x.getText().toString().trim());


            // Основной алгоритм
            // Проверка условий
            if (x <= 4) {
                if (x == 0) {
                    textView_sum.setText("Ошибка! х = 0!");
                    return;
                }

                y = Math.pow(a, 2) / Math.pow(x, 2) + 6 * x;
            } else {
                y = Math.pow(b, 2) * Math.pow(4 + x, 2);
            }


            // Вывод полученного значения в компонент
            textView_sum.setText(String.valueOf(y));

        } catch (Exception e) { // ЧТО ДЕЛАТЬ ЕСЛИ ВОЗНИКНЕТ ОШИБКА В БЛОКЕ МЕЖДУ "TRY" И "CATCH":

            // Вывод сообщения об ошибке
            textView_sum.setText("Неверные входные данные для расчета!");

        }  // КОНЕЦ ЗАЩИЩЕННОГО БЛОКА

    }

}
