package es.tew;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HolaMundo")

// Hola

public class HolaMundoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nombre = (String) req.getParameter("NombreUsuario");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Vector<String> listado = (Vector<String>) req.getSession().getAttribute("listado");
        if (listado == null) {
            listado = new Vector<String>();
        }

        if (nombre != null) {
            listado.addElement(nombre);
        }

        req.getSession().setAttribute("listado", listado);

       

        Integer contador = (Integer) getServletContext().getAttribute("contador");
        if (contador == null) {
            contador = 0;
        }
        // Establecemos el contador como atributo del context bajo el nombre
        // contador. En caso de que ya existiera, sobreescribiría la referencia
        // existente con la nueva.
        contador++;
        getServletContext().setAttribute("contador", contador);


        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("HolaMundoVista");
        dispatcher.forward(req, resp);

    }
}
