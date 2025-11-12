package br.com.biblioteca.apibibli;

// Classe desabilitada para login custom sem Spring Security
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
public class WebSecurityConfigDisabled {

    // private final UsuarioRepository usuarioRepository;

    // public WebSecurityConfig(UsuarioRepository usuarioRepository) {
    //     this.usuarioRepository = usuarioRepository;
    // }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests((requests) -> requests
    //             .requestMatchers("/login", "/css/**", "/js/**").permitAll()
    //             .anyRequest().authenticated()
    //         )
    //     .formLogin((form) -> form
    //         .loginPage("/login")
    //         .defaultSuccessUrl("/listar-livros", true)
    //         .failureUrl("/login?error=true")
    //         .permitAll()
    //     )
    //     .logout((logout) -> logout
    //         .logoutSuccessUrl("/login")
    //         .permitAll()
    //     );

    //     return http.build();
    // }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return username -> {
    //         Usuario usuario = usuarioRepository.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    //         return org.springframework.security.core.userdetails.User
    //             .withUsername(usuario.getUsername())
    //             .password(usuario.getPassword())
    //             .roles(usuario.getRole())
    //             .build();
    //     };
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
}
