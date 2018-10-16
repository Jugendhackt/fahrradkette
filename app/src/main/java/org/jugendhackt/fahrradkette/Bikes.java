package org.jugendhackt.fahrradkette;

import android.media.Image;

public class Bikes {
    public String name;
    public double lon;
    public double lat;
    public double money;
    public String specialities;
    public Image image;
    public String owner;
}

/* Put http senden

URL url = new URL("http://www.example.com/resource");
HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
httpCon.setDoOutput(true);
httpCon.setRequestMethod("PUT");
OutputStreamWriter out = new OutputStreamWriter(
    httpCon.getOutputStream());
out.write("Data you want to put");
out.close();

 */
