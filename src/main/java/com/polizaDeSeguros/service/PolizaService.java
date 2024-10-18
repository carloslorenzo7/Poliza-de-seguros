package com.polizaDeSeguros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.polizaDeSeguros.model.dto.PolizaRequest;
import com.polizaDeSeguros.model.entity.Poliza;
import com.polizaDeSeguros.model.entity.Seguro;
import com.polizaDeSeguros.model.entity.SeguroAuto;
import com.polizaDeSeguros.model.entity.SeguroCelular;
import com.polizaDeSeguros.model.entity.SeguroInmueble;
import com.polizaDeSeguros.model.entity.Usuario;
import com.polizaDeSeguros.repository.PolizaRepository;
import com.polizaDeSeguros.repository.SeguroAutoRepository;
import com.polizaDeSeguros.repository.SeguroCelularRepository;
import com.polizaDeSeguros.repository.SeguroInmuebleRepository;
import com.polizaDeSeguros.repository.UsuarioRepository;

@Service
public class PolizaService {

	private UsuarioRepository usuarioRepository;

	private PolizaRepository polizaRepository;

	private SeguroAutoRepository seguroAutoRepository;

	private SeguroCelularRepository seguroCelularRepository;

	private SeguroInmuebleRepository seguroInmuebleRepository;

	public PolizaService(UsuarioRepository usuarioRepository, PolizaRepository polizaRepository,
			SeguroAutoRepository seguroAutoRepository, SeguroCelularRepository seguroCelularRepository,
			SeguroInmuebleRepository seguroInmuebleRepository) {
		this.usuarioRepository = usuarioRepository;
		this.polizaRepository = polizaRepository;
		this.seguroAutoRepository = seguroAutoRepository;
		this.seguroCelularRepository = seguroCelularRepository;
		this.seguroInmuebleRepository = seguroInmuebleRepository;
	}

	public Poliza crearPoliza(PolizaRequest polizaRequest) {
		System.out.println(polizaRequest.toString()+ "Hola soy To.string");
		Poliza poliza = new Poliza();

		// Asigno el usuario a la póliza
		Usuario usuario = usuarioRepository.findById(polizaRequest.getUsuarioId()).orElseThrow(
				() -> new IllegalArgumentException("Usuario no encontrado con id: " + polizaRequest.getUsuarioId()));
		poliza.setUsuario(usuario);
		poliza.setNumeroDePoliza(polizaRequest.getNumeroDePoliza());
		poliza.setFechaDeInicio(polizaRequest.getFechaDeInicio());
		poliza.setFechaDeVencimiento(polizaRequest.getFechaDeVencimiento());
		poliza.setMontoAsegurado(polizaRequest.getMontoAsegurado());
		poliza.setEstado(polizaRequest.getEstado());

		// Creo el seguro según el tipo de seguro
		Seguro seguro;

		// Obtén el tipo de seguro como String
		String tipoDeSeguro = polizaRequest.getTipoDeSeguro();

		switch (tipoDeSeguro) {
		case "SEGURO_AUTO":
			SeguroAuto seguroAuto = new SeguroAuto();
			seguroAuto.setDescripcion(polizaRequest.getDescripcionAuto());
			seguroAuto.setMarca(polizaRequest.getMarcaAuto());
			seguroAuto.setModelo(polizaRequest.getModeloAuto());
			seguroAuto.setPatente(polizaRequest.getPatenteAuto());
			seguroAutoRepository.save(seguroAuto);
			seguro = seguroAuto;
			break;

		case "SEGURO_CELULAR":
			System.out.println("Entre aca");
			SeguroCelular seguroCelular = new SeguroCelular();
			seguroCelular.setDescripcion(polizaRequest.getDescripcionCelular());
			seguroCelular.setMarca(polizaRequest.getMarcaCelular());
			seguroCelular.setModelo(polizaRequest.getModeloCelular());
			seguroCelular.setNumeroDeSerie(polizaRequest.getNumeroDeSerieCelular());
			seguroCelularRepository.save(seguroCelular);
			seguro = seguroCelular;
			break;

		case "SEGURO_INMUEBLE":
			SeguroInmueble seguroInmueble = new SeguroInmueble();
			seguroInmueble.setDescripcion(polizaRequest.getDescripcionInmueble());
			seguroInmueble.setDireccion(polizaRequest.getDireccionInmueble());
			seguroInmueble.setTipoDeConstruccion(polizaRequest.getTipoDeConstruccionInmueble());
			seguroInmuebleRepository.save(seguroInmueble);
			seguro = seguroInmueble;
			break;

		default:
			throw new IllegalArgumentException("Tipo de seguro no válido: " + tipoDeSeguro);
		}

		poliza.setTipoDeSeguro(seguro);

		return polizaRepository.save(poliza);
	}

	// Metodo para obtener lista de poliza
	public List<Poliza> obtenerTodasLasPolizas() {
		return polizaRepository.findAll();
	}

	// metodo para buscar por id

	public Optional<Poliza> obtenerPolizaPorId(Long id) {
		return polizaRepository.findById(id);
	}

	// método para actualizar una póliza
	public Poliza actualizarPoliza(Long id, PolizaRequest polizaRequest) {
	    // Buscar la póliza existente por ID
	    Optional<Poliza> optionalPoliza = polizaRepository.findById(id);

	    if (optionalPoliza.isPresent()) {
	        Poliza polizaExistente = optionalPoliza.get();

	        // Actualizar los atributos de la póliza
	        polizaExistente.setNumeroDePoliza(polizaRequest.getNumeroDePoliza());
	        polizaExistente.setFechaDeInicio(polizaRequest.getFechaDeInicio());
	        polizaExistente.setFechaDeVencimiento(polizaRequest.getFechaDeVencimiento());
	        polizaExistente.setMontoAsegurado(polizaRequest.getMontoAsegurado());
	        polizaExistente.setEstado(polizaRequest.getEstado());

	        // Actualizar el tipo de seguro
	        Seguro seguro = polizaExistente.getTipoDeSeguro();
	        String tipoDeSeguro = polizaRequest.getTipoDeSeguro();

	        switch (tipoDeSeguro) {
	            case "SEGURO_AUTO":
	                if (seguro instanceof SeguroAuto) {
	                    SeguroAuto seguroAuto = (SeguroAuto) seguro;
	                    seguroAuto.setDescripcion(polizaRequest.getDescripcionAuto());
	                    seguroAuto.setMarca(polizaRequest.getMarcaAuto());
	                    seguroAuto.setModelo(polizaRequest.getModeloAuto());
	                    seguroAuto.setPatente(polizaRequest.getPatenteAuto());
	                    seguroAutoRepository.save(seguroAuto);
	                } else {
	                    throw new IllegalArgumentException("El tipo de seguro no coincide con la póliza existente.");
	                }
	                break;

	            case "SEGURO_CELULAR":
	                if (seguro instanceof SeguroCelular) {
	                    SeguroCelular seguroCelular = (SeguroCelular) seguro;
	                    seguroCelular.setDescripcion(polizaRequest.getDescripcionCelular());
	                    seguroCelular.setMarca(polizaRequest.getMarcaCelular());
	                    seguroCelular.setModelo(polizaRequest.getModeloCelular());
	                    seguroCelular.setNumeroDeSerie(polizaRequest.getNumeroDeSerieCelular());
	                    seguroCelularRepository.save(seguroCelular);
	                } else {
	                    throw new IllegalArgumentException("El tipo de seguro no coincide con la póliza existente.");
	                }
	                break;

	            case "SEGURO_INMUEBLE":
	                if (seguro instanceof SeguroInmueble) {
	                    SeguroInmueble seguroInmueble = (SeguroInmueble) seguro;
	                    seguroInmueble.setDescripcion(polizaRequest.getDescripcionInmueble());
	                    seguroInmueble.setDireccion(polizaRequest.getDireccionInmueble());
	                    seguroInmueble.setTipoDeConstruccion(polizaRequest.getTipoDeConstruccionInmueble());
	                    seguroInmuebleRepository.save(seguroInmueble);
	                } else {
	                    throw new IllegalArgumentException("El tipo de seguro no coincide con la póliza existente.");
	                }
	                break;

	            default:
	                throw new IllegalArgumentException("Tipo de seguro no válido: " + tipoDeSeguro);
	        }

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
