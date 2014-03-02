package net.fernandezgodinho.pedro.dovahzuldictionary;

import java.util.ArrayList;

import net.fernandezgodinho.pedro.helpers.ArrayHelpers;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Toast;

public class Translator extends Portrait {
	DictionaryObject[] englishDictionaryObject;
	RadioButton english, dovahzul;
	Button translate;
	FlowLayout fl;
	ScrollView sv;
	EditText et;
	ArrayHelpers ah;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_translator);

		ah = new ArrayHelpers();

		fl = new FlowLayout(getApplicationContext());
		translate = (Button) findViewById(R.id.translate);
		sv = (ScrollView) findViewById(R.id.svt);
		et = (EditText) findViewById(R.id.ett);
		english = (RadioButton) findViewById(R.id.rtEnglish);
		dovahzul = (RadioButton) findViewById(R.id.rtDovahzul);
		sv.addView(fl);

		et.setTypeface(MainMenu.palatinoTf);
		english.setTypeface(MainMenu.palatinoTf);
		dovahzul.setTypeface(MainMenu.palatinoTf);

		addListeners();
	}

	public void addListeners() {
		translate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				updateTextView();
			}
		});
	}

	public void updateTextView() {
		fl.removeAllViews();

		String s = et.getText().toString();

		String[] words = s.split("\\ ");

		for (int i = 0; i < words.length; i++) {
			TranslatorWord wordView = new TranslatorWord(
					getApplicationContext());
			if (english.isChecked()) {
				wordView.setWord(words[i], true);
			} else {
				wordView.setWord(words[i], false);
			}
			fl.addView(wordView);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
