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
import com.gestaorh.entity.Departamento;
import com.gestaorh.service.CargoService;
import com.gestaorh.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService service;

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar") // /cargos/cadastrar
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro"; // cargo é o diretorio e cadastro é o nome do arquivo, sem extensão
									// .html
	}

	@GetMapping("/listar") // /cargos/listar
	public String listar(ModelMap model) {
		model.addAttribute("cargos", service.findAll());
		return "/cargo/lista"; // cargo é o diretorio e lista é o nome do arquivo, sem extensão .html
	}

	@PostMapping("/salvar") // /cargos/salvar
	public String salvar(Cargo cargo, RedirectAttributes attr) {
		String msg;
		if (cargo.getId() == null) {
			msg = "Cargo inserido com sucesso!!!";
		} else {
			msg = "Cargo alterado com sucesso!!!";
		}

		service.saveOrUpdate(cargo);
		attr.addFlashAttribute("success", msg);
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/editar/{id}") // /cargos/editar/7
	public String editar(@PathVariable("id") Cargo cargo, ModelMap model) {
		model.addAttribute("cargo", cargo);
		return "/cargo/cadastro"; // cargo é o diretorio e cadastro é o nome do arquivo, sem extensão
									// .html
	}

	@GetMapping("/excluir/{id}") // /cargos/excluir/7
	public String excluir(@PathVariable("id") Cargo cargo, ModelMap model) {
		if (cargo.getFuncionarios().isEmpty()) {
			service.delete(cargo);
			model.addAttribute("success", "Cargo excluído com sucesso!!!");
		} else {
			model.addAttribute("fail", "Cargo não pode ser excluído. Possui funcionário(s) vinculado(s)");
		}

		return listar(model);
	}

	@ModelAttribute("departamentos")
	public List<Departamento> listaDepartamento() {
		return departamentoService.findAll();
	}

}
