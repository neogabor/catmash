package com.latelier.servlet;

import com.latelier.database.Model;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

@WebServlet(name = "vote", urlPatterns = { "/index.html" })
public class VoteServlet extends HttpServlet{

    private static Map<String, String> imageMap = new HashMap<>();
    private volatile static Map<String, Integer> score = new HashMap<>();
    private final Model model = new Model();
    
    @Override
    public void init() throws ServletException {
        ServletContext sCtx = getServletConfig().getServletContext();
        try {
            URLConnection connection = new URL("https://latelier.co/data/cats.json").openConnection();
            JsonReader bf = Json.createReader(connection.getInputStream());
            JsonObject jo = bf.readObject();
            JsonArray images = jo.getJsonArray("images");
            Map<String,Integer> idScore =  model.getScoreFromDB();
            boolean needCheckForChanges = (idScore.isEmpty() || idScore.size()!=images.size());
            for (int i=0; i < images.size(); i++) {
                JsonObject object = images.getJsonObject(i);
                imageMap.put(object.getString("url"), object.getString("id"));
                if (needCheckForChanges) {
                    score.put(object.getString("url"), 
                            idScore.containsKey(object.getString("id")) ? idScore.get(object.getString("id")) : 0);
                }else {
                    score.put(object.getString("url"), idScore.get(object.getString("id")));
                }
            }
        } catch (MalformedURLException ex) {
            sCtx.log(ex.getMessage());
        } catch (IOException ex) {
            sCtx.log(ex.getMessage());
        } catch (SQLException | ClassNotFoundException ex) {
            sCtx.log(ex.getMessage(), ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> images = new ArrayList<>(imageMap.keySet());
        int[] randomNumbers = new Random().ints(0, imageMap.size() - 1).distinct().limit(2).toArray();
        String url1 = images.get(randomNumbers[0]);
        String url2 = images.get(randomNumbers[1]);

        request.setAttribute("kep1", url1);
        request.setAttribute("kep2", url2);
        request.getRequestDispatcher("/WEB-INF/views/vote.jsp").forward(
                        request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameterMap().keySet().iterator().next();
        synchronized (this) {
            int point = score.get(url) + 1;
            score.put(url, point);
            try {
                model.updateScore(imageMap.get(url), point);
            } catch (SQLException | ClassNotFoundException ex) {
                getServletConfig().getServletContext().log(ex.getMessage(), ex);
            }
        }
    }

    public static Map<String, Integer> getScore() {
        return score;
    }

    @Override
    public void destroy() {
        try {
            model.close();
        } catch (SQLException ex) {
            getServletConfig().getServletContext().log(ex.getMessage(), ex);
        }
    }
    
    
}
