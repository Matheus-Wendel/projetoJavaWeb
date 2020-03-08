package com.fatec.javaweb.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fatec.javaweb.model.Usuario;
import com.fatec.javaweb.repository.UsuarioRepository;
@Service
@Transactional
public class ServiceDetalhesUsuario implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioDAO;

    
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        

        
    	Usuario usuario = usuarioDAO.login(userName)
       .orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
        
    	//CRIA VARIAVEL DE DETALHE DE USUARIO QUE POSSUI O NOME(LOGIN/EMAIL) A SENHA  E A LISTA DE PERMISSOES DESSE USUARIO
        UserDetails userDetails = (UserDetails) new User(usuario.getEmail(), usuario.getSenha(),
              getAuthorities(usuario));
        
        return userDetails;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {

        //CRIA UMA LISTA DE PERMISSOES
        //A QUAL SERA A ATRIBUIDA A ELA AS PERMICOES QUE OS NOMES ESTAO SALVOS NO PROPRIO USUARIO( usuario.getPermissao() )
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(usuario.getPermissao().toString()));
        
        return authorities;
    }
}