package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.DadosLogin;
import br.com.jota.finance.DTOs.UserRegistrationData;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServices implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public String login(@Valid DadosLogin dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var user = (UserDetails) authentication.getPrincipal();

        var tokenAccess = tokenService.gerarToken(user.getUsername());

        return tokenAccess;
    }

    @Transactional
    public User signup(UserRegistrationData data) {
        var encodePassword = passwordEncoder.encode(data.password());

        User user = new User(data.name(), data.email(), encodePassword, true, data.role(), LocalDateTime.now());

        return userRepository.save(user);
    }
}
