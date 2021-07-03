package com.gestaorh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestaorh.entity.Departamento;
import com.gestaorh.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar") // /departamentos/cadastrar
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro"; // departamento é o diretorio e cadastro é o nome do arquivo, sem extensão
											// .html
	}

	@GetMapping("/listar") // /departamentos/listar
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.findAll());
		return "/departamento/lista"; // departamento é o diretorio e lista é o nome do arquivo, sem extensão .html
	}

	@PostMapping("/salvar") // /departamentos/salvar
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		String msg;
		if (departamento.getId() == null) {
			msg = "Departamento inserido com sucesso!!!";
		} else {
			msg = "Departamento alterado com sucesso!!!";
		}

		service.saveOrUpdate(departamento);
		attr.addFlashAttribute("success", msg);
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}") // /departamentos/editar/7
	public String editar(@PathVariable("id") Departamento departamento, ModelMap model) {
		model.addAttribute("departamento", departamento);
		return "/departamento/cadastro"; // departamento é o diretorio e cadastro é o nome do arquivo, sem extensão
											// .html
	}

	@GetMapping("/excluir/{id}") // /departamentos/excluir/7
	public String excluir(@PathVariable("id") Departamento departamento, ModelMap model) {
		if (departamento.getCargos().isEmpty()) {
			service.delete(departamento);
			model.addAttribute("success", "Departamento excluído com sucesso!!!");
		} else {
			model.addAttribute("fail", "Departamento não pode ser excluído. Possui cargo(s) vinculado(s)");
		}

		return listar(model);
	}

}
