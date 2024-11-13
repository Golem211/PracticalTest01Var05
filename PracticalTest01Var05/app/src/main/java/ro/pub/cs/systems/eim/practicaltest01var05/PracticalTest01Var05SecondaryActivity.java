package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    private TextView textMain;
    private Button verify;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        textMain = findViewById(R.id.textMain);
        cancel = findViewById(R.id.cancel);
        verify = findViewById(R.id.verify);
        System.out.println(getIntent().getStringExtra("template"));
        System.out.println(getIntent().getStringExtra("buttonPressCount"));
        textMain.setText(getIntent().getStringExtra("template"));
        textMain.setText(getIntent().getStringExtra("buttonPressCount"));

        verify.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        cancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}