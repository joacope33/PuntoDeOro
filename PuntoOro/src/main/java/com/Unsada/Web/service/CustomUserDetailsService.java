package com.Unsada.Web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
    /* 
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private AdministradorRepository administradorRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Determina si es administrador aquí y pasa los roles correspondientes
        boolean isAdmin = administradorRepository.existsByUsuario(usuario);

        // Retorna el objeto Usuario con roles
        return usuario;
    }
    */
    

}
