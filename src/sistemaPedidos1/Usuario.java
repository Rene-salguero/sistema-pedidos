package sistemaPedidos1;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre;
    private String userName;
    private String password;
    private String email;
    private static Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    public Usuario(String nombre, String userName, String password, String email) {
        this.nombre = nombre;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public static boolean registrar(String nombre, String userName, String password, String email) {
        if (usuariosRegistrados.containsKey(userName)) {
            return false;
        }
        Usuario nuevoUsuario = new Usuario(nombre, userName, password, email);
        usuariosRegistrados.put(userName, nuevoUsuario);
        return true;
    }

    public static Usuario iniciarSesion(String userName, String password) {
        Usuario usuario = usuariosRegistrados.get(userName);
        if (usuario != null && usuario.password.equals(password)) {
            return usuario;
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }
}
