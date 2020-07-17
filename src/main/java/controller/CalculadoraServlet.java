/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

@WebServlet(urlPatterns = { "/calc" })
public class CalculadoraServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 6979266527260277L;

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        Scanner in = new Scanner(new FileReader("src\\main\\html\\calculadora.html"));
        String loginUser = req.getParameter("nome");
        String senhaUser = req.getParameter("senha");
        try {
            if (ConexaoBd.autenticarUserBD(loginUser, senhaUser)) {
                ConexaoBd.salvarLoginNoBD(loginUser);
                while (in.hasNextLine()) {

                    String line = in.nextLine();
                    resp.setContentType("text/html");
                    resp.setCharacterEncoding("utf-8");
                    pw.write(line);
                }
            } else {
                pw.write("<h1>Usuario ou senha incorreto. Tente novamente.</h1>");
                pw.write("<input style='width:100;height:100' type='button' value='Voltar' onClick='history.go(-1)'>");
            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
