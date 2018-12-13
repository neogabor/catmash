package com.latelier.servlet;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "rankings", urlPatterns = { "/rankings" })
public class RankingsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Integer> score = VoteServlet.getScore();
        Map<String, Integer> sortedScore = score.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        request.getSession().setAttribute("catsScore", sortedScore.values());
        request.getSession().setAttribute("catsURL", sortedScore.keySet().toArray());
        request.getRequestDispatcher("/WEB-INF/views/rankings.jsp").forward(
                request, response);
    }

}
