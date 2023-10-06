package com.proyecto.pagina.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.pagina.modelo.Modelo;

import jakarta.annotation.PostConstruct;

@Service
public class Servicio {
	public List<Modelo> repositorio = new ArrayList<>();
	
	public Modelo add(Modelo e) {
		repositorio.add(e);
		return e;
		
	}
	public List<Modelo> findAll(){
		return repositorio;
		
	}
	
	public Modelo findById(long id) {
		Modelo result = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < repositorio.size()) {
			if (repositorio.get(i).getId() == id) {
				encontrado = true;
				result = repositorio.get(i);
			} else {
				i++;
			}
		}
		
		return result;
	}
	
	public Modelo edit(Modelo e) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < repositorio.size()) {
			if (repositorio.get(i).getId() == e.getId()) {
				encontrado = true;
				repositorio.remove(i);
				repositorio.add(i, e);
			} else {
				i++;
			}
		}
		
		if (!encontrado)
			repositorio.add(e);
		
		return e;
	}
	
	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Modelo(1, "juan carlos", "cosa@gmail.com", "234234234"),
						new Modelo(2, "paco gimenez", "nombre@gmail.com", "456456456"),
						new Modelo(3, "gonzalo garcia", "otro@gmail.com", "345098234")
						)
				);
	}
}
