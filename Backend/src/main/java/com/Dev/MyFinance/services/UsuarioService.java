package com.Dev.MyFinance.services;

import com.Dev.MyFinance.models.Usuario;
import com.Dev.MyFinance.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder senhaEncoder = new BCryptPasswordEncoder();

    public Usuario cadastrarUsuario (Usuario usuario) {
        try {
            usuario.setSenha(senhaEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException("O email já está cadastrado!");
        }
    }

   public Optional<Usuario> buscarUsuarioPorEmail (String email){
        return usuarioRepository.findByEmail(email);
    }

   public Optional<Usuario> autenticarUsuario (String email , String senha) {
        Usuario usuario = buscarUsuarioPorEmail(email)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

      if(!senhaEncoder.matches(senha , usuario.getSenha())){
          throw new RuntimeException("Senha incorreta!");
       }
       return Optional.of(usuario);
   }
}
