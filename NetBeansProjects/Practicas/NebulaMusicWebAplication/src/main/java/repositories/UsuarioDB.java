/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.util.HashMap;
import java.util.Map;
import models.Usuario;

/**
 *
 * @author sebas
 */
public class UsuarioDB {
    public static Map<String, Usuario> usuarios = new HashMap<>();
    
    public static void registrar(Usuario usuario){
        usuarios.put(usuario.getCorreo(),usuario);
    }
    
    public static boolean autenticar(String correo, String contrasenia){
        if(!usuarios.containsKey(correo)){
            return false;
        }
        return usuarios.get(correo).getContrasenia().equals(contrasenia);
    }
    
    public static boolean existeCorreo(String correo){
        return usuarios.containsKey(correo);
    }
    
    
}
