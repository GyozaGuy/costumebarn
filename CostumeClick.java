package db.db;

import android.view.View;

public class CostumeClick implements android.view.View.OnClickListener{
	MainMenuActivity activity;
	Costume costume;
	
	CostumeClick(MainMenuActivity a, Costume c){
		activity = a;
		costume = c;
	}
	
	@Override
	public void onClick(View v) {
		activity.switchToCostumeView(costume);	
	}

}
