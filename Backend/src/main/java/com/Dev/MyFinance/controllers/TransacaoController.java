package com.Dev.MyFinance.controllers;

import com.Dev.MyFinance.models.Transacao;
import com.Dev.MyFinance.models.Usuario;
import com.Dev.MyFinance.services.TransacaoService;
import com.Dev.MyFinance.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Transacao> adicionarTransacao(@RequestBody Transacao transacao) {
        Transacao novaTransacao = transacaoService.criarTransacao(transacao);
        return ResponseEntity.ok(novaTransacao);
    }
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Transacao>> listarTransacoes(@PathVariable Long usuarioId) {
        List<Transacao> transacoes = transacaoService.buscarTransacoesPorUsuario(usuarioId);
        return ResponseEntity.ok(transacoes);
    }
    @GetMapping("/saldo/{usuarioId}")
    public ResponseEntity<Double> saldoDoUsuario(@PathVariable Long usuarioId) {
        double saldo = transacaoService.calcularSaldo(usuarioId);
        return ResponseEntity.ok(saldo);

    }

}
