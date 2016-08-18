package com.jnidemo.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import com.demolib.JNICaller;
import com.jnidemo.R;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by olshanikov on 18.08.16.
 */
public class CallJniTask extends AsyncTask<Void, Void, String> {

    static {
        System.loadLibrary("library");
    }

    private final Context context;

    public CallJniTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
        byte[] data = getBytesPlanar(bitmap);
        byte[] result = new JNICaller().processImage(data);
        return Integer.toString(result.length);
    }

    @Override
    protected void onPostExecute(String v) {
        Toast.makeText(context, "Success " + v, Toast.LENGTH_SHORT).show();
    }

    private byte[] getBytesPlanar(Bitmap bitmap) {
        byte[] r = new byte[bitmap.getHeight() * bitmap.getWidth()];
        byte[] g = new byte[bitmap.getHeight() * bitmap.getWidth()];
        byte[] b = new byte[bitmap.getHeight() * bitmap.getWidth()];

        int[] pixels = new int[bitmap.getHeight() * bitmap.getWidth()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            r[i] = (byte) ((pixel >> 16) & 0xff);
            g[i] = (byte) ((pixel >> 8) & 0xff);
            b[i] = (byte) (pixel & 0xff);
        }

        return ArrayUtils.addAll(ArrayUtils.addAll(r, g), b);
    }
}
