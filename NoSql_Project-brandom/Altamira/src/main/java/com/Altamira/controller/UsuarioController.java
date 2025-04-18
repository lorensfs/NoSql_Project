package com.Altamira.controller;

import com.Altamira.domain.Usuario;
import com.Altamira.service.UsuarioService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;



    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

    @GetMapping("/registroUsuario")
    public String registroUsuario() {
        return "/usuarios/registroUsuario";
    }
    @PostMapping("/registro")
    public String usuarioGuardar(Usuario usuario) {

        usuarioService.save(usuario,true);
        return "redirect:/usuarios/login";
    }


    @GetMapping("/login")
    public String Login(Model model, Usuario usuario) {
        model.addAttribute("usuario", usuario);

        return "/usuarios/login";
    }

    @PostMapping("/login")
    public String confirmarLogin(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpSession session) {
        boolean existeUsuario = usuarioService.existeUsuarioPorUsernameOCorreo(username, username);
        if (existeUsuario) {
            boolean existeContraseña= usuarioService.existsByPassword(password);
            if (existeContraseña){
                Usuario usuario=usuarioService.getUsuarioPorUsernameYPassword(username, password);
                session.setAttribute("id_usuario", usuario.getId());
                session.setAttribute("userName", usuario.getUsername());
                session.setAttribute("email", usuario.getEmail());
                session.setAttribute("usuarioRol", usuario.getRol());

                return "redirect:/usuarios/usuario";
            }else  {
                return "redirect:/usuarios/registroUsuario";
            }
        }else {
            return "redirect:/usuarios/registroUsuario";
        }

    }
    @GetMapping("/usuario")
    public String consultaUsuario() {


        return "/usuarios/usuario";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/usuarios/login";
    }




}
