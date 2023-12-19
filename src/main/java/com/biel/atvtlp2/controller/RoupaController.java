package com.biel.atvtlp2.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biel.atvtlp2.modelo.Roupa;
import com.biel.atvtlp2.repository.RoupaRepository;

@Controller
public class RoupaController {
	
	@Autowired
	private RoupaRepository roupaRepository;
	
	
	@GetMapping("/novo")
	public String adicionarRoupa(Model model) {
		model.addAttribute("roupa", new Roupa());
		return "/publica-criar-roupa";
	}
	
	@PostMapping("/salvar")
	public String salvarRoupa(@Valid Roupa roupa, BindingResult result, 
				RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-roupa";
		}	
		roupaRepository.save(roupa);
		attributes.addFlashAttribute("mensagem", "Roupa salva com sucesso!");
		return "redirect:/";
	}
	
	@RequestMapping("/")
	public String listarRoupa(Model model) {
		List<Roupa> listaRoupa = roupaRepository.findAll();
		model.addAttribute("roupas", listaRoupa);
		return "/auth/admin/admin-listar-roupa";
	}
	
	@GetMapping("/editar/{id}")
	public String editaRoupa(@PathVariable("id") long id, Model model) {
		Optional<Roupa> roupaVelho = roupaRepository.findById(id);
				
		if(!roupaVelho.isPresent()) {
			throw new IllegalArgumentException("Roupa inválida: " + id);
		}
		Roupa roupa = roupaVelho.get();
		model.addAttribute("roupa", roupa);
	    return "/auth/user/user-alterar-roupa";
	}
	
	
	@PostMapping("/editar/{id}")
	public String editaRoupa(@PathVariable("id") long id, 
			@Valid Roupa roupa, BindingResult result) {
		if (result.hasErrors()) {
	    	roupa.setId(id);
	        return "/auth/user/user-alterar-roupa";
	    }
	    roupaRepository.save(roupa);
	    return "redirect:/";
	}
	
	@GetMapping("/admin/apagar/{id}")
	public String deleteRoupa(@PathVariable("id") long id, Model model) {
		Roupa roupa = roupaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		roupaRepository.delete (roupa);
	    return "redirect:/";
	}
	
	
}
