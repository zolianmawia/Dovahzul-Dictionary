package net.fernandezgodinho.pedro.dovahzuldictionary;

import net.fernandezgodinho.pedro.helpers.ArrayHelpers;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SearchMain extends Portrait {

	private CheckBox exactMatch;

	private static DictionaryObject[] dictionary;

	private static int[] searchResultsIndex;

	private static boolean searchInDovahzul, searchInEnglish;

	private static EditText search;

	private Button bSearch;

	private RadioButton rSearchEnglish, rSearchDovahzul;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		dictionary = MainMenu.getDictionary();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_main);

		rSearchEnglish = (RadioButton) findViewById(R.id.rEnglish);
		rSearchDovahzul = (RadioButton) findViewById(R.id.rDovahzul);
		bSearch = (Button) findViewById(R.id.search);

		search = (EditText) findViewById(R.id.tvSearch);

		exactMatch = (CheckBox) findViewById(R.id.exactMatch);

		search.setTextColor(Color.WHITE);

		search.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);

		rSearchEnglish.setTypeface(MainMenu.palatinoTf);
		rSearchDovahzul.setTypeface(MainMenu.palatinoTf);
		search.setTypeface(MainMenu.palatinoTf);
		exactMatch.setTypeface(MainMenu.palatinoTf);
		bSearch.setTypeface(MainMenu.palatinoTf);

		// Add Listeners
		addListeners();
	}

	// Adds the listeners
	public void addListeners() {
		// Radio Buttons Listeners
		rSearchEnglish.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				searchInEnglish = true;
				searchInDovahzul = false;
				rSearchDovahzul.setChecked(false);
			}
		});

		rSearchDovahzul.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				searchInEnglish = false;
				searchInDovahzul = true;
				rSearchEnglish.setChecked(false);
			}
		});

		bSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				search();
			}
		});

		// On enter clicked in the search box
		search.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				search();
				search.setText(null);
				return false;
			}
		});
	}

	public void search() {
		if (rSearchDovahzul.isChecked()) {
			if (exactMatch.isChecked()) {
				ArrayHelpers ah = new ArrayHelpers();
				searchResultsIndex = ah.searchWithExactMatchDovahzul(
						dictionary, search.getText().toString().trim());
				if (searchResultsIndex.length == 0) {
					Toast.makeText(getApplicationContext(),
							"No search results", Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(this, Search.class);
					intent.putExtra("SEARCH ENGLISH", false);
					search.setText(null);
					startActivity(intent);
				}
			} else {
				ArrayHelpers ah = new ArrayHelpers();
				searchResultsIndex = ah.searchWithoutExactMatchDovahzul(
						dictionary, search.getText().toString().trim());
				if (searchResultsIndex.length == 0) {
					Toast.makeText(getApplicationContext(),
							"No search results", Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(this, Search.class);
					intent.putExtra("SEARCH ENGLISH", false);
					search.setText(null);
					startActivity(intent);
				}
			}
		} else {
			if (exactMatch.isChecked()) {
				ArrayHelpers ah = new ArrayHelpers();
				searchResultsIndex = ah.searchWithExactMatchEnglish(dictionary,
						search.getText().toString().trim());
				if (searchResultsIndex.length == 0) {
					Toast.makeText(getApplicationContext(),
							"No search results", Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(this, Search.class);
					intent.putExtra("SEARCH ENGLISH", true);
					search.setText(null);
					startActivity(intent);
				}
			} else {
				ArrayHelpers ah = new ArrayHelpers();
				searchResultsIndex = ah.searchWithoutExactMatchEnglish(
						dictionary, search.getText().toString().trim());
				if (searchResultsIndex.length == 0) {
					Toast.makeText(getApplicationContext(),
							"No search results", Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(this, Search.class);
					intent.putExtra("SEARCH ENGLISH", true);
					search.setText(null);
					startActivity(intent);
				}
			}
		}
	}

	// when the user clicks the menu button
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public static boolean isSearchInDovahzul() {
		return searchInDovahzul;
	}

	public static boolean isSearchInEnglish() {
		return searchInEnglish;
	}

	public static int[] getSearchResultsIndex() {
		return searchResultsIndex;
	}
}
