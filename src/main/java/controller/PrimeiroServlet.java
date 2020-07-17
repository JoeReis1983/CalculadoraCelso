/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/teste" })
public class PrimeiroServlet extends HttpServlet {

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        try {
            PrintWriter pw = resp.getWriter();
            Scanner in = new Scanner(new FileReader("src\\main\\html\\login.html"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                resp.setContentType("text/html");
                pw.write(line);
            }
        } catch (IOException ioe) {
            System.out.println("Impossível escrever na resposta!");
        }
    }

}
