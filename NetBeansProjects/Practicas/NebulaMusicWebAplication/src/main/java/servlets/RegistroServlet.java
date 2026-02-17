/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import models.Usuario;
import repositories.UsuarioDB;

/**
 *
 * @author sebas
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String nombre = request.getParameter("txt_name");
        String correo = request.getParameter("txt_correo");
        String contrasenia = request.getParameter("txt_contrasenia");
        String pseudonimo = request.getParameter("txt_pseudonimo");
        String estado = request.getParameter("txt_estado");
        String tipoCuenta = request.getParameter("txt_tipoCuenta");
        String fecha_nacimiento_str = request.getParameter("txt_fecha_nacimiento");
        String terminos = request.getParameter("txt_terminos");
        
        if(nombre == null || correo == null || contrasenia == null || pseudonimo == null || estado == null || tipoCuenta == null || fecha_nacimiento_str == null || terminos == null){
            response.sendRedirect("error.html");
            return;
        }
         
        if(UsuarioDB.existeCorreo(correo)){
            response.sendRedirect("error.html");
            return;
        }
        
        LocalDate fechaNacimiento = LocalDate.parse(fecha_nacimiento_str);
        boolean aceptoTerminos = terminos.equals("aceptado");
        
        Usuario usuario = new Usuario(nombre,correo,contrasenia, pseudonimo, estado, tipoCuenta, fechaNacimiento, aceptoTerminos);
        
        UsuarioDB.registrar(usuario);
        
        response.sendRedirect("iniciar-sesion.html");
    }

    



}
