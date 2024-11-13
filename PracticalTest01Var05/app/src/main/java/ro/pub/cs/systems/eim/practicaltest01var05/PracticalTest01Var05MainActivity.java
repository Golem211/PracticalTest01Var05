package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private static final String BUTTON_PRESS_COUNT_KEY = "buttonPressCount";
    private int buttonPressCount = 0;

    private static final int REQUEST_CODE = 1;
    private static final int TEMPLATE_THRESHOLD = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_var05_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        // Find the TextView
        TextView textView = findViewById(R.id.text);

        // Find the buttons
        Button buttonBottomLeft = findViewById(R.id.bottomLeft);
        Button buttonBottomRight = findViewById(R.id.bottomRight);
        Button buttonTopLeft = findViewById(R.id.topLeft);
        Button buttonTopRight = findViewById(R.id.topRight);
        Button center = findViewById(R.id.center);
        Button navigateToSecondaryActivity = findViewById(R.id.secondActivity);

        // Set OnClickListener for buttonBottomLeft
        buttonBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressCount++;
                textView.setText(textView.getText().toString() +", " + buttonBottomLeft.getText().toString());
            }
        });

        // Set OnClickListener for buttonBottomRight
        buttonBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressCount++;
                textView.setText(textView.getText().toString() +", " + buttonBottomRight.getText().toString());
            }
        });

        // Set OnClickListener for buttonTopLeft
        buttonTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressCount++;
                textView.setText(textView.getText().toString() +", " + buttonTopRight.getText().toString());
            }
        });
        buttonTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressCount++;
                textView.setText(textView.getText().toString() +", " + buttonTopLeft.getText().toString());
            }
        });
        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPressCount++;
                textView.setText(textView.getText().toString() +", " + center.getText().toString());
            }
        });
        navigateToSecondaryActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String template = textView.getText().toString();
                String[] templateElements = template.split(", ");
                if (templateElements.length > TEMPLATE_THRESHOLD) {
                    Intent serviceIntent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05Service.class);
                    serviceIntent.putExtra("templateElements", templateElements);
                    startService(serviceIntent);
                }

                Intent intent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05SecondaryActivity.class);
                intent.putExtra("template", textView.getText().toString());
                intent.putExtra("buttonPressCount", String.valueOf(buttonPressCount));
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        if (savedInstanceState != null) {
            buttonPressCount = savedInstanceState.getInt(BUTTON_PRESS_COUNT_KEY, 0);
            Toast.makeText(this, "Button press count: " + buttonPressCount, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUTTON_PRESS_COUNT_KEY, buttonPressCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        buttonPressCount = savedInstanceState.getInt(BUTTON_PRESS_COUNT_KEY, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Verify button clicked", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, PracticalTest01Var05Service.class));
    }
}