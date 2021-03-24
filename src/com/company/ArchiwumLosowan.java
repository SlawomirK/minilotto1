package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ArchiwumLosowan {

    private URL url = null;
    private URLConnection connection = null;
    private InputStreamReader inputStreamReader = null;

    public ArchiwumLosowan() {
        url = getUrl();
        connection = getUrlConnection(url, connection);
        inputStreamReader = getInputStreamReader(connection, inputStreamReader);
        BufferedReader data = new BufferedReader(inputStreamReader);
        List<String> listOfDraws = getStrings(data);
        Statystyka przetworzDane = new Statystyka(listOfDraws);

    }

    private List<String> getStrings(BufferedReader data) {
        String oneDraw = null;
        List<String> listOfDraws = new ArrayList<>();
        while (true) {
            try {
                if (!((oneDraw = data.readLine()) != null)) break;

            } catch (IOException e) {
                e.printStackTrace();
            }
            listOfDraws.add(oneDraw.split("\\d\\s")[1]);
        }

        return listOfDraws;
    }

    private InputStreamReader getInputStreamReader(URLConnection connection, InputStreamReader inputStreamReader) {
        try {
            inputStreamReader = new InputStreamReader(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStreamReader;
    }

    private URLConnection getUrlConnection(URL url, URLConnection connection) {
        try {
            connection = url.openConnection();
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private URL getUrl() {
        try {
            url = new URL("http://www.mbnet.com.pl/el.txt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
