package android.example.triviaquiz;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
	
	static TextView counter;
	static int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_layout);
		counter = findViewById(R.id.counter);
		count = 0;
		counter.setText(count + "");
		
		ArrayList<Question> questions = MainActivity.questions;
		ListView listView = findViewById(R.id.questions);
		
		
		for (Question question : questions) {
			Log.v("question", question.toString());
		}
		
		QuestionAdapter adapter = new QuestionAdapter(this, 0, questions);
		listView.setAdapter(adapter);
	}
	
	static void incrementCounter() {
		count += 1;
		counter.setText("" + count);
	}
	
}