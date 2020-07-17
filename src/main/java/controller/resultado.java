/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/resultado" })
public class resultado extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        try {

            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            PrintWriter pw = resp.getWriter();
            String calculo = req.getParameter("result");

            if (calculo != null) {

                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("JavaScript");
                Object obj = null;
                if (calculo.contains("^")) {
                    int pos = calculo.indexOf("^");
                    obj = Math.pow(Double.parseDouble(calculo.substring(0, pos)),
                            Double.parseDouble(calculo.substring(pos + 1, calculo.length())));
                } else if (calculo.contains("V")) {
                    obj = Math.sqrt(Double.parseDouble(calculo.replaceAll("V", "")));
                } else {
                    try {
                        obj = engine.eval(calculo);
                    } catch (ScriptException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                ConexaoBd.salvarCalcNoBD(calculo + " = " + obj);
                pw.write("<h1>" + calculo + " = " + obj + "</h1>");
            }

        } catch (Exception err) {

        }

    }

}
