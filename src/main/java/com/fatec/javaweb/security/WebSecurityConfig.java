package com.fatec.javaweb.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fatec.javaweb.util.Util;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

    //@Autowired
    //UserDetailsServiceImpl userDetailsService;
	@Autowired
    ServiceDetalhesUsuario userDetailsService;
	
	
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
     
     
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
        .and()
        .inMemoryAuthentication()
        	.withUser("javaweb")
        	.password("$2a$10$iiBnmmpWsX3szghaCSmMZuTYYB7nPOu3yTG98d9G8rDveN2jOcBoi")
        	.authorities("ADMINISTRADOR");     


 
        
    }
 

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//        		//PERMITE LIVRE AOS RECURSO "/INICIAL"
//        		// "/layout/**" E "/componentes/** SAO AS PASTAS COM A NAVBAR  O JS E CSS , SE NÃO FOREM COLOCADOS AQUI ELES NAO GARREGAM
//        		//TODO Alterar as permissoes
//                .authorizeRequests().antMatchers(Util.RECURSOS_LIVRE_ACESSO).permitAll()
//                // TODOS OS OUTROS RECURSOS E ARQUIVOS EM PASTAS ESTAO BLOQUEADOS POR LOGIN
//                .anyRequest().authenticated()
//                //E
//                .and()
//                // QUANDO ALGUM RECURSO BLOQUEADO FOR SOLICITADO O USUARIO EH DIRECIONADO PARA "/inicial/login" , A PAGINA DE LOGIN 
//                // DEPOIS DE LOGADO O USUARIO E DIRECIONADO PARA A PAGINA BLOQUEADA SOLICITADA ANERIORMENTE
//                .formLogin().loginPage(Util.PAGINA_LOGIN).usernameParameter("username").passwordParameter("password").permitAll()
//                //CASO O USUARIO TENTE LOGAR SEM REQUISITAR UM RECURCO BLOQUEADO, ELE EH DIRECIONADO PARA ESSA PAGINA
//                .defaultSuccessUrl(Util.PAGINA_SUCESSO_LOGIN)
//                .and()
//                //PAGINA DE LOGOUT 
//                .logout().logoutSuccessUrl(Util.PAGINA_SUCESSO_LOGOUT).permitAll();
				
        //PERMITE LIVRE AOS RECURSO "/INICIAL"
        // "/layout/**" E "/componentes/** SAO AS PASTAS COM A NAVBAR  O JS E CSS , SE NÃO FOREM COLOCADOS AQUI ELES NAO GARREGAM
        //TODO Alterar as permissoes
        .authorizeRequests().antMatchers("/administrativo").hasAuthority("ADMINISTRADOR")       
        .and()
        .formLogin()
        .defaultSuccessUrl(Util.PAGINA_SUCESSO_LOGIN)
        .and()
        .logout().logoutSuccessUrl(Util.PAGINA_SUCESSO_LOGOUT).permitAll()
        .and()
        .exceptionHandling().accessDeniedPage(Util.PAGINA_SUCESSO_LOGIN);
        
        
        		//???
				httpSecurity.csrf().disable();
				httpSecurity.headers().frameOptions().disable();
    }
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
    
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("{noop}admin").roles("ADMIN")
//                .and().withUser("user").password("{noop}user").roles("USER");
//    }
}
