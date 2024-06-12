package datlowashere.project.joincharacter;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText edInput;
    private Button btnSubmit;
    private TextView tvResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edInput = findViewById(R.id.edInput);
        btnSubmit = findViewById(R.id.btnComplete);
        tvResult = findViewById(R.id.tvResult);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = edInput.getText().toString().trim();
                String target = "CodeComplete";

                String result = rearrangeToTarget(input, target);
                if (result == null) {
                    tvResult.setText("Không tìm thấy kết quả.");
                } else {
                    tvResult.setText(result);
                }
            }
        });
    }

    private String rearrangeToTarget(String input, String target) {
        Map<Character, Integer> inputCharCount = new HashMap<>();
        for (char c : input.toCharArray()) {
            inputCharCount.put(c, inputCharCount.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> targetCharCount = new HashMap<>();
        for (char c : target.toCharArray()) {
            targetCharCount.put(c, targetCharCount.getOrDefault(c, 0) + 1);
        }

        for (char c : targetCharCount.keySet()) {
            if (!inputCharCount.containsKey(c) || inputCharCount.get(c) < targetCharCount.get(c)) {
                return null;
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : target.toCharArray()) {
            result.append(c);
            inputCharCount.put(c, inputCharCount.get(c) - 1);
        }

        return result.toString();
    }
}