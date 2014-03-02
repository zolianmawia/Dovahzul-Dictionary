package net.fernandezgodinho.pedro.dovahzuldictionary;

import java.util.Arrays;

import net.fernandezgodinho.pedro.helpers.ArrayHelpers;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Dictionary extends Portrait {
	String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z" };
	private String[] list1, list2, list3, list4;
	private ArrayHelpers ah = new ArrayHelpers();
	private Spinner letter;
	private RadioButton english, dovahzul;
	private DictionaryObject[] dictionaryMenu;
	private ListView dictionaryRoot;
	private Dictionary thisVar = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary);

		dictionaryRoot = (ListView) findViewById(R.id.dictionaryList);
		letter = (Spinner) findViewById(R.id.letter);
		english = (RadioButton) findViewById(R.id.dictionaruEnglish);
		dovahzul = (RadioButton) findViewById(R.id.dictionaryDovahzul);

		english.setTypeface(MainMenu.palatinoTf);
		dovahzul.setTypeface(MainMenu.palatinoTf);

		addListeners();
		updateDictionary();
	}

	private void addListeners() {
		english.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				updateDictionary();
			}
		});

		dovahzul.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateDictionary();
			}
		});

		letter.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				dovahzul.setClickable(true);

				if (alphabet[letter.getSelectedItemPosition()].equals("c")) {
					dovahzul.setClickable(false);
					dovahzul.setChecked(false);
					english.setChecked(true);
				}

				updateDictionary();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		dictionaryRoot.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getApplicationContext(), Word.class);
				intent.putExtra("INDEX", ah.searchForOneEnglish(
						MainMenu.getDictionary(), dictionaryMenu, position));
				startActivity(intent);
			}
		});
	}

	private void updateDictionary() {
		DictionaryObject[] dictionary = MainMenu.getDictionary();
		if (dovahzul.isChecked()) {
			DictionaryObject.setCompareEnglish(false);
			Arrays.sort(dictionary);
			dictionaryMenu = ah.allStartedByDovahzul(dictionary,
					alphabet[letter.getSelectedItemPosition()]);
			list1 = ah.stringArrayFromGetDovahzul(dictionaryMenu);
			list2 = ah.stringArrayFromGetClass(dictionaryMenu);
			list3 = ah.stringArrayFromGetEnglish(dictionaryMenu);
			list4 = ah.stringArrayFromGetCanon(dictionaryMenu);
		} else {
			DictionaryObject.setCompareEnglish(true);
			Arrays.sort(dictionary);
			dictionaryMenu = ah.allStartedByEnglish(dictionary,
					alphabet[letter.getSelectedItemPosition()]);
			list1 = ah.stringArrayFromGetEnglish(dictionaryMenu);
			list2 = ah.stringArrayFromGetClass(dictionaryMenu);
			list3 = ah.stringArrayFromGetDovahzul(dictionaryMenu);
			list4 = ah.stringArrayFromGetCanon(dictionaryMenu);
		}

		dictionaryRoot.setAdapter(new ListAdapter(thisVar, list1, list2, list3,
				list4));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	private static class ListAdapter extends ArrayAdapter<String> {
		public ListAdapter(Dictionary screen, String[] list1, String[] list2,
				String[] list3, String[] list4) {
			super(screen, R.layout.row, list1);
			this.list1 = list1;
			this.list2 = list2;
			this.list3 = list3;
			this.list4 = list4;
			this.activity = screen;
		}

		public View getView(int position, View row, ViewGroup parent) {
			row = activity.getLayoutInflater().inflate(R.layout.row, parent,
					false);

			TextView view1 = (TextView) row.findViewById(R.id.item1);
			view1.setText(list1[position] + "");
			TextView view2 = (TextView) row.findViewById(R.id.item2);
			view2.setText("\t(" + list2[position] + ") ");
			TextView view3 = (TextView) row.findViewById(R.id.item3);
			view3.setText(list3[position] + "");
			TextView view4 = (TextView) row.findViewById(R.id.item4);
			view4.setText("\t" + list4[position] + "");
			
			view1.setTypeface(MainMenu.palatinoTf);
			view2.setTypeface(MainMenu.palatinoTf);
			view3.setTypeface(MainMenu.palatinoTf);
			view4.setTypeface(MainMenu.palatinoTf);

			return row;
		}

		private Dictionary activity;
		private String[] list1, list2, list3, list4;
	}

}
