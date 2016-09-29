package com.curso.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.curso.modelo.GestorBBDD;

@Controller
public class ControladorGeneradorBBDD {
	
	@Autowired
	private GestorBBDD gestorBBDD;
	
	@RequestMapping(value="mostrarPagina")
	public ModelAndView mostrarPagina(){
		return new ModelAndView("pagina");
	}
	
	@RequestMapping(value="generarEsquema", method=RequestMethod.POST)
	public ModelAndView generarEsquema(){
		gestorBBDD.crearEsquema();		
		return new ModelAndView("pagina", "mensaje", "Esquema creado");
	}
	
	
}
