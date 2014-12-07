package com.bugchain.android.development.googlestaticmap;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pnikosis.materialishprogress.ProgressWheel;


public class Main extends ActionBarActivity {

    Bitmap image1,image2;
    ImageView img1,img2;
    GoogleStaticMaps googleStaticMaps;

    ProgressWheel progressWheel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        img1 = (ImageView)findViewById(R.id.imageView1);
        img2 = (ImageView)findViewById(R.id.imageView2);

        progressWheel = (ProgressWheel)findViewById(R.id.progress_wheel);

        progressWheel.setProgress(1.0f);

        new LoadImage().execute();

    }

    class LoadImage extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressWheel.spin();
        }

        @Override
        protected Void doInBackground(Void... params) {

            googleStaticMaps = new GoogleStaticMaps();
            googleStaticMaps.setSize(800, 800);
            googleStaticMaps.addMarker(13.729526, 102.580533);
            googleStaticMaps.addMarker(23.749536, 104.590543);
            googleStaticMaps.addMarker(10.829546, 106.600553);
            image1 = googleStaticMaps.getMapByMarker();

            googleStaticMaps.clearMarker();

            googleStaticMaps.addMarker(13.729626, 100.580443);
            googleStaticMaps.setLanguage("th");
            googleStaticMaps.setMapType(GoogleStaticMaps.TYPE_HYBRID);
            googleStaticMaps.setImageFormat(GoogleStaticMaps.FORMAT_JPEG);

            image2 = googleStaticMaps.getMapByCenter(13.73031, 100.58183, 15);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressWheel.stopSpinning();
            img1.setImageBitmap(image1);
            img2.setImageBitmap(image2);
        }
    }

}
