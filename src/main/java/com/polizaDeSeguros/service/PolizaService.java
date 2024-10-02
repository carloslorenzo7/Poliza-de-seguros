package com.polizaDeSeguros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.polizaDeSeguros.model.entity.Poliza;
import com.polizaDeSeguros.repository.PolizaRepository;




@Service
public class PolizaService {
	

	// final para que no se pueda reasignar a otro objeto luego de haberse inicializado en el constructor.
	 
	private final PolizaRepository polizaRepository;

	public PolizaService(PolizaRepository polizaRepository) {
		this.polizaRepository = polizaRepository;
	}

	// Metodo para crear una poliza
	public Poliza crearPoliza(Poliza poliza) {
		return polizaRepository.save(poliza);
	}

	// Metodo para obtener lista de poliza
	public List<Poliza> obetenerTodasLasPolizas() {
		return polizaRepository.findAll();
	}

	// metodo para buscar por id
	// Optional porque puede estar o no ese id solicitado, si esta lo devolvera,
	// sino devolvera vacio
	public Optional<Poliza> obtenerPolizaPorId(Long id) {
		return polizaRepository.findById(id);
	}

	// metodo para actualizar una poliza
	public Poliza actualizarPoliza(Long id, Poliza polizaActulizada) {

		// busco la poliza solicitada por id
		Optional<Poliza> optionalPoliza = polizaRepository.findById(id);

		// entra al if para ver si esa poliza esta presente o caso contrario que no este
		if (optionalPoliza.isPresent()) {
			// si esta la poliza la obetengo y actualizo datos
			Poliza polizaExistente = optionalPoliza.get();

			polizaExistente.setTipoDeSeguro(polizaActulizada.getTipoDeSeguro());
			polizaExistente.setFechaDeInicio(polizaActulizada.getFechaDeInicio());
			polizaExistente.setFechaDeVencimiento(polizaActulizada.getFechaDeVencimiento());
			polizaExistente.setMontoAsegurado(polizaActulizada.getMontoAsegurado());
			polizaExistente.setEstado(polizaActulizada.getEstado());

			return polizaRepository.save(polizaExistente);
		} else {
			throw new RuntimeException("Póliza no encontrada con ID: " + id);
		}

	}
	
	// metodo para eliminar poliza
	
	public void eliminarPoliza(Long id) {
		polizaRepository.deleteById(id);
	}

}
