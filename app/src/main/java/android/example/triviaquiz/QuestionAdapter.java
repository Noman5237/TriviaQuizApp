package android.example.triviaquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import io.github.kexanie.library.MathView;

public class QuestionAdapter extends ArrayAdapter<Question> {
	
	public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> questions) {
		super(context, 0, questions);
	}
	
	
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		LinearLayout toBeRendered = (LinearLayout) convertView;
		final Question question = getItem(position);
		
		if (toBeRendered == null) {
			if (question.isLatex()) {
				toBeRendered = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.question_item_latex_layout, parent, false);
			} else {
				toBeRendered = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.question_item_layout, parent, false);
			}
		}
		
		
		final RadioButton[] radioButtons = new RadioButton[4];
		
		radioButtons[0] = toBeRendered.findViewById(R.id.op1);
		radioButtons[1] = toBeRendered.findViewById(R.id.op2);
		radioButtons[2] = toBeRendered.findViewById(R.id.op3);
		radioButtons[3] = toBeRendered.findViewById(R.id.op4);
		
		if (question.isLatex()) {
			MathView questionText = toBeRendered.findViewById(R.id.question);
			questionText.setText(question.getQuestion());
			setLatexLayoutTexts(question, toBeRendered);
		} else {
			TextView questionText = toBeRendered.findViewById(R.id.question);
			questionText.setText(question.getQuestion());
			setRadioButtonTexts(question, radioButtons);
		}
		
		clearChecked(radioButtons);
		for (int i = 0; i < 4; i++) {
			final RadioButton radioButton = radioButtons[i];
			final int finalI = i;
			radioButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					clearChecked(radioButtons);
					question.setChecked(finalI);
					radioButton.setChecked(true);
				}
			});
		}
		
		if (question.checkedIndex() != -1)
			radioButtons[question.checkedIndex()].setChecked(true);
		
		return toBeRendered;
	}
	
	void setRadioButtonTexts(Question question, RadioButton... radioButtons) {
		radioButtons[0].setText(question.getCorrectOption());
		radioButtons[1].setText(question.getIncorrectOptions()[0]);
		radioButtons[2].setText(question.getIncorrectOptions()[1]);
		radioButtons[3].setText(question.getIncorrectOptions()[2]);
		
	}
	
	void setLatexLayoutTexts(Question question, LinearLayout toBeRendered) {
		
		((MathView) toBeRendered.findViewById(R.id.op1m)).setText(question.getCorrectOption());
		((MathView) toBeRendered.findViewById(R.id.op2m)).setText(question.getIncorrectOptions()[0]);
		((MathView) toBeRendered.findViewById(R.id.op3m)).setText(question.getIncorrectOptions()[1]);
		((MathView) toBeRendered.findViewById(R.id.op4m)).setText(question.getIncorrectOptions()[2]);
	}
	
	
	public void clearChecked(RadioButton... radioButtons) {
		for (RadioButton radioButton : radioButtons) {
			radioButton.setChecked(false);
		}
	}
}
