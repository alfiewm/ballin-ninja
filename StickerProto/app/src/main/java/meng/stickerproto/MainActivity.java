package meng.stickerproto;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity {

    LinearLayout stickerBoard;
    TextView editText;
    int[] stickers = {
            meng.stickerproto.R.raw.smile, R.raw.haha, R.raw.xixi
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (TextView) findViewById(R.id.editText);
        editText.setText(
                "[100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100][100]");
        stickerBoard = (LinearLayout) findViewById(R.id.sticker_board);
        renderStickers();
    }

    private void renderStickers() {
/*        int lineHeight = editText.getLineHeight();
        for (int i = 0; i < 60; ++i) {
            Bitmap bitmap = svg2Bitmap(R.raw.haha, lineHeight - 16, lineHeight - 16);
            EmojiSpan span = new EmojiSpan(this, bitmap, ImageSpan.ALIGN_BASELINE);
            SpannableStringBuilder ss = new SpannableStringBuilder(editText.getText());
            ss.setSpan(span, i * 5, i * 5 + 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            editText.setText(ss);
        }*/
        int lineHeight = editText.getLineHeight();
        for (int i = 0; i < 60; ++i) {
            Bitmap bitmap = svg2Bitmap(R.raw.haha, lineHeight, lineHeight);
            ImageSpan span = new ImageSpan(this, bitmap, ImageSpan.ALIGN_BASELINE);
            SpannableStringBuilder ss = new SpannableStringBuilder(editText.getText());
            ss.setSpan(span, i * 5, i * 5 + 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            editText.setText(ss);
        }
        // ImageView oldSVGLibImageView = (ImageView) findViewById(R.id.sticker_image);
        // oldSVGLibImageView.setImageBitmap(bp);
    }

    @DebugLog
    public Bitmap svg2Bitmap(int rawResource, float width, float height) {
        if (rawResource > 0) {
            try {
                com.caverock.androidsvg.SVG svg = com.caverock.androidsvg.SVG
                        .getFromResource(getResources(), rawResource);
                if (width == 0 || height == 0) {
                    float preWidth = svg.getDocumentWidth();
                    float preHeight = svg.getDocumentHeight();
                    if (width == 0 && height == 0) {
                        width = preWidth;
                        height = preHeight;
                    } else if (width == 0) {
                        width = height / preHeight * preWidth;
                    } else {
                        height = width / preWidth * preHeight;
                    }
                }
                svg.setDocumentWidth(width);
                svg.setDocumentHeight(height);
                Bitmap bitmap = Bitmap.createBitmap((int) width, (int) height,
                        Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawARGB(0, 255, 255, 255);
                svg.renderToCanvas(canvas);
                return bitmap;
            } catch (Exception e) {
                Log.e("svg", "svg2Bitmap failed", e);
            }
        }
        return null;
    }
}
