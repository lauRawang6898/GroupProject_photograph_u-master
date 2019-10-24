package com.photograph_u.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photograph_u.domain.MyResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    
    public static void sendResponse(HttpServletResponse response, MyResponse myResponse) throws IOException {
        response.getWriter().print(new Gson().toJson(myResponse));
    }

    public static void sendResponseWithDate(HttpServletResponse response, MyResponse myResponse) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        response.getWriter().print(gson.toJson(myResponse));
    }

    public static void sendResponseWithDateTime(HttpServletResponse response, MyResponse myResponse) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        response.getWriter().print(gson.toJson(myResponse));
    }
}
