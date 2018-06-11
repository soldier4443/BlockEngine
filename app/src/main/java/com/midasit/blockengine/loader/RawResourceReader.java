package com.midasit.blockengine.loader;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tura on 2017-05-30.
 */
public class RawResourceReader {
    public static String readTextFileFromRawResources(final Context context, final int resourceId) {
        final InputStream inputStream = context.getResources().openRawResource(resourceId);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        
        String nextLine;
        final StringBuilder builder = new StringBuilder();
        
        try {
            while ((nextLine = bufferedReader.readLine()) != null)
            {
                builder.append(nextLine);
                builder.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        return builder.toString();
    }
}
