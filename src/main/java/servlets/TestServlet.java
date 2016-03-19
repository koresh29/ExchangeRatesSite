package servlets;

import logic.HTMLparser;
import logic.NBU_course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Vlad on 16.03.2016.
 */
@WebServlet("/getusd")
public class TestServlet extends HttpServlet {

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

        //  String usd = HTMLparser.get_NBU_course_today().usd;


            PrintWriter out = resp.getWriter();
            out.print( "<p>"+HTMLparser.get_NBU_course_today().usd+"</p>");


        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            super.doPost(req,resp);
        }

    }


