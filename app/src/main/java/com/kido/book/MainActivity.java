package com.kido.book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.Display;

import com.kido.book.curl.provider.TextPageProvider;
import com.kido.book.curl.view.CurlView;

public class MainActivity extends AppCompatActivity {
    private CurlView curlView;
    private TextPageProvider textPageProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        curlView = (CurlView) findViewById(R.id.curl_view);

        textPageProvider = new TextPageProvider();
        curlView.setPageProvider(textPageProvider);


        Display display = getWindowManager().getDefaultDisplay();
        Double w = display.getWidth() * 0.9;
        int screenWidth = w.intValue();
        w = display.getHeight() * 0.7;
        int screenHeight = w.intValue();

        PageSplitter pageSplitter = new PageSplitter(screenWidth, screenHeight, 1, 0);
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(28f);
        for (int i = 0; i < 1220; i++) {
            pageSplitter.append("Hello, ", textPaint);
            textPaint.setFakeBoldText(true);
            pageSplitter.append("world", textPaint);
            textPaint.setFakeBoldText(false);
            pageSplitter.append("! ", textPaint);
            if ((i + 1) % 100 == 0) {
                pageSplitter.append("\n", textPaint);
            }
        }
        textPageProvider.setStrings(pageSplitter.getPages());


    }
}
