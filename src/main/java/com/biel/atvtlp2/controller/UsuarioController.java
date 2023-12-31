package com.biel.atvtlp2.controller;


import java.util.ArrayList;
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

import com.biel.atvtlp2.modelo.Papel;
import com.biel.atvtlp2.repository.PapelRepository;
import com.biel.atvtlp2.modelo.Usuario;

import com.biel.atvtlp2.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	private PapelRepository papelRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@GetMapping("/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, 
				Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-usuario";
		}
		
		Usuario usr = usuarioRepository.findByLogin(usuario.getLogin());
		if (usr != null) {
			model.addAttribute("loginExiste", "Login já existe cadastrado");
			return "/publica-criar-usuario";
		}
		
		//Busca o papel básico de usuário
		Papel papel = papelRepository.findByPapel("USER");
		List<Papel> papeis = new ArrayList<Papel>();
		papeis.add(papel);				
		usuario.setPapeis(papeis); // associa o papel de USER ao usuário
		
		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/usuario/novo"; // fazer com roupa   
	}
	
	
	@RequestMapping("/admin/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());		
		return "/auth/admin/admin-listar-usuario";		
	}
	
	@GetMapping("/admin/apagar/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		usuarioRepository.delete(usuario);
	    return "redirect:/usuario/admin/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id, Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
            throw new IllegalArgumentException("Usuário inválido:" + id);
        } 
		Usuario usuario = usuarioVelho.get();
	    model.addAttribute("usuario", usuario);
	    return "/auth/user/user-alterar-usuario";
	}
	
	@PostMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id, 
			@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
	    	usuario.setId(id);
	        return "/auth/user/user-alterar-usuario";
	    }
	    usuarioRepository.save(usuario);
	    return "redirect:/usuario/admin/listar";
	}
		
}
