package com.examle.image;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class LoadIm extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.im_act);
		new DownloadImageTask((ImageView) findViewById(R.id.im))
		.execute("https://www.google.co.in/logos/doodles/2014/kishore-kumars-85th-birthday-5765138946719744-hp.jpg");
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		      Log.i("debug","reached here");
		  }

		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.e("Error", e.getMessage());
		          e.printStackTrace();
		          runOnUiThread(new Runnable() {
		        	  public void run() {
		        		  Toast.makeText(LoadIm.this, R.string.toast, Toast.LENGTH_SHORT).show();
		        	      }
		        	  });
		          
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		      findViewById(R.id.progressBar1).setVisibility(View.GONE);
		  }
		}
}
