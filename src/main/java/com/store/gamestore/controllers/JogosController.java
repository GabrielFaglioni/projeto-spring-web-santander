package com.store.gamestore.controllers;

import com.store.gamestore.exceptions.JogoNotFoundException;
import com.store.gamestore.model.Jogos;
import com.store.gamestore.repositories.JogosRepository;
import com.store.gamestore.services.JogoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class JogosController {
    @Autowired private JogoServices services;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/jogos")
    public String showJogosList(Model model) {
        List<Jogos> jogos = services.listAll();
        model.addAttribute("listJogos", jogos);
        return "jogos";
    }

    @GetMapping("/jogos/novo")
    public String showNewForm(Model model) {
        model.addAttribute("jogos", new Jogos());
        model.addAttribute("pageTitle", "Adicionar novo jogo");
        return "jogos_form";
    }

    @PostMapping("jogos/salvar")
    public String saveJogo(Jogos jogo, RedirectAttributes ra) {
        services.save(jogo);
        ra.addFlashAttribute("message", "O jogo foi adicionado com sucesso!");
        return "redirect:/jogos";
    }

    @GetMapping("jogos/deletar/{id_jogo}")
    public String deleteJogo(@PathVariable("id_jogo") Long id_jogo, RedirectAttributes ra){
        try {
            services.delete(id_jogo);
            ra.addFlashAttribute("message", "O jogo foi deletado com sucesso!");
        } catch (JogoNotFoundException e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/jogos";
    }

    @GetMapping("/jogos/editar/{id_jogo}")
    public String showEditForm(@PathVariable("id_jogo") Long id_jogo, Model model, RedirectAttributes ra) {
        try {
            Jogos jogo = services.get(id_jogo);
            model.addAttribute("jogos", jogo);
            model.addAttribute("pageTitle", "Editar jogo - id_jogo: " + id_jogo);
            return "jogos_form";
        } catch (JogoNotFoundException e) {
            ra.addFlashAttribute("error", "O jogo não foi encontrado na base de dados");
            return "redirect:/jogos";
        }
    }


}
