package net.fernandezgodinho.pedro.dovahzuldictionary;

import net.fernandezgodinho.pedro.helpers.ArrayHelpers;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Search extends Portrait {
	private String[] list1, list2, list3, list4;
	private ArrayHelpers ah = new ArrayHelpers();
	private ListView lvRoot;
	private boolean searchEnglish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		searchEnglish = getIntent().getBooleanExtra("SEARCH ENGLISH", true);
		lvRoot = (ListView) findViewById(R.id.searchList);

		if (searchEnglish) {
			list1 = ah.arrayFromArraySearchEnglish(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
			list2 = ah.arrayFromArraySearchClass(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
			list3 = ah.arrayFromArraySearchDovahzul(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
			list4 = ah.arrayFromArraySearchCanon(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
		} else {
			list1 = ah.arrayFromArraySearchDovahzul(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
			list2 = ah.arrayFromArraySearchClass(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
			list3 = ah.arrayFromArraySearchEnglish(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
			list4 = ah.arrayFromArraySearchCanon(MainMenu.getDictionary(),
					SearchMain.getSearchResultsIndex());
		}

		ListView view = (ListView) this.findViewById(R.id.searchList);

		view.setAdapter(new ListAdapter(this, list1, list2, list3, list4));

		lvRoot.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getApplicationContext(), Word.class);
				intent.putExtra("INDEX",
						SearchMain.getSearchResultsIndex()[position]);
				startActivity(intent);
			}

		});

	}

	private static class ListAdapter extends ArrayAdapter<String> {
		public ListAdapter(Search screen, String[] list1, String[] list2,
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

		private Search activity;
		private String[] list1, list2, list3, list4;
	}
}