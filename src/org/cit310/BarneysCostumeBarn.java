package org.cit310;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.json.JSONException;
import org.quickconnect.json.JSONInputStream;
import org.quickconnect.json.JSONOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class BarneysCostumeBarn extends Activity {
	private String address = "10.18.151.112";
	private int port = 7777;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switchToMainView();
	}
    
    private void switchToMainView(){
    	setContentView(R.layout.main);
    	
    	Button insertButton = (Button) findViewById(R.id.main_addCostumeButton);
    	insertButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				BarneysCostumeBarn.this.switchToInsertView();
			}    		
    	});
    	
    	ImageView western = (ImageView) findViewById(R.id.main_imageViewWestern);
    	western.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				switchToGalleryView("western");
			}});
    	
    	ImageView medieval = (ImageView) findViewById(R.id.main_imageViewMedieval);
    	medieval.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				switchToGalleryView("medieval");
			}});
    	
    	ImageView freaky = (ImageView) findViewById(R.id.main_imageViewFreaky);
    	freaky.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				switchToGalleryView("freaky");
			}});
    }
    
    private void switchToGalleryView(String type){
    	try {
			Socket toServer = new Socket(address,port);
			JSONOutputStream jsonOut = new JSONOutputStream(toServer.getOutputStream());
			JSONInputStream jsonIn = new JSONInputStream(toServer.getInputStream());
			jsonOut.writeObject(type);
			ArrayList<HashMap<String,String>> jsonCostumes = (ArrayList<HashMap<String, String>>) jsonIn.readObject();
			Costume[] costumes = new Costume[jsonCostumes.size()];
			for(int i = 0; i < jsonCostumes.size(); i++){
				costumes[i] = new Costume(jsonCostumes.get(i));
			}
			

    		setContentView(R.layout.categoriesview);
			
    		Button backButton = (Button) findViewById(R.id.gallery_backButton);
    		backButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					BarneysCostumeBarn.this.switchToMainView();					
				}});
    		
			// Get horizontalLayout
			LinearLayout horizontalLayout = (LinearLayout) findViewById(R.id.horizontalLayout);
			for (int i = 0; i < costumes.length; i++) {
				// Create images
				ImageView myImage = new ImageView(this);
				String mDrawableName = costumes[i].getImage();
				int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
				myImage.setBackgroundResource(resID);

				// Create buttons
				TextView myTextView = new TextView(this);
				myTextView.setText(costumes[i].getName());
				// Create new linear layouts
				LinearLayout verticalLayout = new LinearLayout(this);
				verticalLayout.setClickable(true);
				verticalLayout.setOnClickListener(new CostumeClickListener(this,costumes[i]));
				verticalLayout.setPadding(20, 20, 20, 20);
				verticalLayout.setOrientation(LinearLayout.VERTICAL);
				verticalLayout.addView(myImage);
				verticalLayout.addView(myTextView);
				horizontalLayout.addView(verticalLayout);
			}

			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	
    }
    
    private void switchToInsertView(){
    	setContentView(R.layout.addview);
    	Button backButton = (Button) findViewById(R.id.buttonBack);
    	backButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				BarneysCostumeBarn.this.switchToMainView();
			}});
    }
    
    public void switchToCostumeView(final Costume aCostume){
    	setContentView(R.layout.descriptionview);
    	TextView name = (TextView) findViewById(R.id.nameFromDb);
    	TextView type = (TextView) findViewById(R.id.typeFromDb);
    	TextView size = (TextView) findViewById(R.id.sizeFromDb);
    	RatingBar rating = (RatingBar) findViewById(R.id.ratingBar1);
    	TextView description = (TextView) findViewById(R.id.descriptionFromDb);
    	TextView storageUnit = (TextView) findViewById(R.id.UnitFromDb);
    	name.setText(aCostume.getName());
    	type.setText(aCostume.getType());
    	size.setText(aCostume.getSize());
    	rating.setNumStars(Integer.parseInt(aCostume.getRating()));
    	description.setText(aCostume.getDescription());
    	storageUnit.setText(aCostume.getStorageUnit()+"");
    	
    	Button back = (Button) findViewById(R.id.description_buttonBack);
    	back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				BarneysCostumeBarn.this.switchToGalleryView(aCostume.getType());
				
			}});
    }
    
}
