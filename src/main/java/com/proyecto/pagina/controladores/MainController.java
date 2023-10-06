package com.proyecto.pagina.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.pagina.modelo.Modelo;
import com.proyecto.pagina.servicios.Servicio;

import jakarta.validation.Valid;


@Controller
public class MainController {
	@Autowired
	private Servicio servicio;
	
	@GetMapping({"/", "/modelo/index"})
	public String listado(Model model) {
		model.addAttribute("listaEmpleados", servicio.findAll());
		return "index";
	}
	@GetMapping("/modelo/new")
	public String nuevoEmpleadoForm(Model model) {
		model.addAttribute("empleadoForm", new Modelo());
		return "form";
		
	}
	@PostMapping("/modelo/new/submit")
	public String nuevoEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Modelo nuevoEmpleado,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form";
		}else {
			servicio.add(nuevoEmpleado);
			return "redirect:/modelo/index";
			
		}
		
	}
	
	/*estos son los mismos formularios pero para edicion*/
	@GetMapping("/modelo/edit/{id}")
	public String editarEmpleadoForm(@PathVariable long id, Model model) {
		
		Modelo empleado = servicio.findById(id);
		if(empleado != null){
			model.addAttribute("empleadoForm", empleado);
			return "form";
			
		}else {
			return "redirect:/modelo/new";
		}
		
		
		
	}
	@PostMapping("/modelo/edit/submit")
	public String editarEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Modelo empleado, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form";
		}else {
			servicio.edit(empleado);
			return "redirect:/modelo/index";
			
		}
		
	}
}
