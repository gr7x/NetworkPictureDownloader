package com.example.rgavi.networkpicturedownloader;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ProgressBarFileDownloadingActivity extends AppCompatActivity implements OnClickListener {
    final static String DYERS_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/dyers.jpg";
    final static String MOON_PINE_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/moonpine.jpg";
    final static String PLUM_ESTATE_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/plum.jpg";
    final static String USHIMACHI_URL = "http://www.ibiblio.org/wm/paint/auth/hiroshige/takanawa.jpg";
    static final String FILE_PATH = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";

    Button mBtnStartProgress;
    ImageView imageView;
    ProgressDialog progressbar;
    int progressBarStatus = 0;
    Handler progressBarHandler = new Handler();
    long fileSize = 0;
    static final String PROGRESS_MESSAGE = "File is Downloading ...";
    static final String COMPLETION_MESSAGE = "Download in Progress ...";

    @Override // - this is working
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnStartProgress = (Button) findViewById(R.id.button3);
        imageView = (ImageView) findViewById(R.id.imageView00);
        mBtnStartProgress.setOnClickListener(this);
    }


    public void downloads() throws IOException {


        while (progressBarStatus != 100) {
            int count;
            URL url = new URL(DYERS_URL);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();
            // input stream to read file -with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            // Output stream to write file
            OutputStream output = new FileOutputStream(FILE_PATH);
            byte data[] = new byte[1024];
            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // writing data to file
                output.write(data, 0, count); // publishing the progress....
                // After this onProgressUpdate will be called m
                progressBarStatus = (int) ((total * 100) / lenghtOfFile);
                // update the progress bar
                progressBarHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressbar.setProgress(progressBarStatus);
                        if (progressBarStatus == 100) {
                            progressbar.setMessage(COMPLETION_MESSAGE);
                            imageView.setImageDrawable(Drawable.createFromPath(FILE_PATH))
                            ;
                        }
                    }
                });
            }
            output.flush();

            output.close();

            input.close();

        }
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.progress_bar_demo, menu);
        return true;
    }


    @Override
    public void onClick(View view) { // this appears okay
        progressbar = new ProgressDialog(view.getContext());

        progressbar.setCancelable(true);

        progressbar.setMessage(PROGRESS_MESSAGE);

        progressbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressbar.setProgress(0);

        progressbar.setMax(100);

        progressbar.show();

        progressBarStatus = 0;

        fileSize = 0;

        Thread thread = new Thread() {
            public void run() {
                try {
                    downloads();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }





    private void simulateFileDownload() {
        while (progressBarStatus < 100) {

            //progressBarStatus = downloadFile(); --- fix this to call downloader
            // two download files sections of code check this function
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            progressBarHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressbar.setProgress(progressBarStatus);
                    if (progressBarStatus >= 100)
                        progressbar.setMessage(COMPLETION_MESSAGE);
                }
            });
        }


        if(progressBarStatus >= 100){
            try{Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
            progressbar.dismiss();
        }



    }


    private void downloadFile() throws IOException {

        while (progressBarStatus != 100) {
            int count;
            URL url = new URL(DYERS_URL);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();
            // input stream to read file -with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            // Output stream to write file
            OutputStream output = new FileOutputStream(FILE_PATH);
            byte data[] = new byte[1024];
            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // writing data to file
                output.write(data, 0, count); // publishing the progress....
                // After this onProgressUpdate will be called m
                progressBarStatus = (int) ((total * 100) / lenghtOfFile);
                // update the progress bar
                progressBarHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressbar.setProgress(progressBarStatus);
                        if (progressBarStatus == 100) {
                            progressbar.setMessage(COMPLETION_MESSAGE);
                            imageView.setImageDrawable(Drawable.createFromPath(FILE_PATH))
                            ;
                        }
                    }
                });
            }
            output.flush();

            output.close();

            input.close();

        }
    }
}




// public void onClick(View view) {






