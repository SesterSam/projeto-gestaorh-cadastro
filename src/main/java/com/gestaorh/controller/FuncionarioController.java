package com.gestaorh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestaorh.entity.Cargo;
import com.gestaorh.entity.Funcionario;
import com.gestaorh.enumeration.UF;
import com.gestaorh.service.CargoService;
import com.gestaorh.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@Autowired
	private CargoService cargoService;

	@GetMapping("/cadastrar") // /funcionarios/cadastrar
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro"; // funcionario é o diretorio e cadastro é o nome do arquivo, sem extensão
		// .html
	}

	@GetMapping("/listar") // /funcionarios/listar
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", service.findAll());
		return "/funcionario/lista"; // funcionario é o diretorio e lista é o nome do arquivo, sem extensão .html
	}

	@PostMapping("/salvar") // /funcionarios/salvar
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		String msg;
		if (funcionario.getId() == null) {
			msg = "Funcionário inserido com sucesso!!!";
		} else {
			msg = "Funcionário alterado com sucesso!!!";
		}

		service.saveOrUpdate(funcionario);
		attr.addFlashAttribute("success", msg);
		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("/editar/{id}") // /funcionarios/editar/7
	public String editar(@PathVariable("id") Funcionario funcionario, ModelMap model) {
		model.addAttribute("funcionario", funcionario);
		return "/funcionario/cadastro"; // funcionario é o diretorio e cadastro é o nome do arquivo, sem extensão
		// .html
	}

	@GetMapping("/excluir/{id}") // /funcionarios/excluir/7
	public String excluir(@PathVariable("id") Funcionario funcionario, ModelMap model) {
		service.delete(funcionario);
		model.addAttribute("success", "Funcionário excluído com sucesso!!!");
		return listar(model);
	}

	@ModelAttribute("cargos")
	public List<Cargo> listaCargo() {
		return cargoService.findAll();
	}

	@ModelAttribute("ufs")
	public UF[] listaEstados() {
		return UF.values();
	}

}
