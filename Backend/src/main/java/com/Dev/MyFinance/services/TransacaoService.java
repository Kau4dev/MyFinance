package com.Dev.MyFinance.services;


import com.Dev.MyFinance.models.Transacao;
import com.Dev.MyFinance.models.Usuario;
import com.Dev.MyFinance.repositories.TransacaoRepository;
import com.Dev.MyFinance.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Transacao criarTransacao (Transacao transacao){
        Usuario usuario = usuarioRepository.findById(transacao.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        transacao.setUsuario(usuario);
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> buscarTransacoesPorUsuario (Long UsuarioId) {
        return transacaoRepository.findByUsuarioId(UsuarioId);
    }

    public double calcularSaldo(Long usuarioId) {
        List<Transacao> transacoes = transacaoRepository.findByUsuarioId(usuarioId);

       double saldo = 0;

        for (Transacao transacao : transacoes) {
            switch (transacao.getTipo()) {
                case ENTRADA -> saldo += transacao.getValor();
                case SAIDA -> saldo -= transacao.getValor();
            }
        }
        return saldo;
    }

}
