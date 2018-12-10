package com.latelier.servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "vote", urlPatterns = { "/vote" })
public class VoteServlet extends HttpServlet{

    private static Map<String, String> imageMap = new HashMap<>();
    
    @Override
    public void init() throws ServletException {
        try {
            URLConnection connection = new URL("https://latelier.co/data/cats.json").openConnection();
            JsonReader bf = Json.createReader(connection.getInputStream());
            JsonObject jo = bf.readObject();
            JsonArray images = jo.getJsonArray("images");
            for (int i=0; i < images.size(); i++) {
                    JsonObject object = images.getJsonObject(i);
                    imageMap.put(object.getString("url"), object.getString("id"));
            }
        } catch (MalformedURLException ex) {
            ServletContext sCtx = getServletConfig().getServletContext();
            sCtx.log(ex.getMessage());
        } catch (IOException ex) {
            ServletContext sCtx = getServletConfig().getServletContext();
            sCtx.log(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> images = new ArrayList<>(imageMap.keySet());
        String url1 = images.get(0);
        String url2 = images.get(1);

        request.setAttribute("kep1", url1);
        request.setAttribute("kep2", url2);
        request.getRequestDispatcher("/WEB-INF/views/vote.jsp").forward(
                        request, response);
    }
    
    

}
