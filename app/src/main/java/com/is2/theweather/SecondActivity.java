package com.is2.theweather;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textToShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textToShare = findViewById(R.id.textToShare);
        Intent intent = getIntent();
        String text = intent.getStringExtra("toShare");
        TextView textToShare = findViewById(R.id.textToShare);
        textToShare.setText(text);

    }

    public void shareText(View view) {

        String txt = textToShare.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }

    public void oky() {

    }

}
