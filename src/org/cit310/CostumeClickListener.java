package org.cit310;

import android.view.View;

public class CostumeClickListener implements android.view.View.OnClickListener{
	BarneysCostumeBarn activity;
	Costume costume;
	
	CostumeClickListener(BarneysCostumeBarn a, Costume c){
		activity = a;
		costume = c;
	}
	
	@Override
	public void onClick(View v) {
		activity.switchToCostumeView(costume);	
	}

}
