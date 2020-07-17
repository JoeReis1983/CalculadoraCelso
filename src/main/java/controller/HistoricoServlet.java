/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/historico" })
public class HistoricoServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {

        try {
            ArrayList<String> historico = ConexaoBd.consultarHistoricoBD();
            int i = 0;
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            PrintWriter pw = resp.getWriter();

            while (i < historico.size()) {
                pw.write("<h3>" + historico.get(i) + "</h3>");

                i = i + 1;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    };

}
