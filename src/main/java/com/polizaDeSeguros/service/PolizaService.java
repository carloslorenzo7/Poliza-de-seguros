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

import exceptions.exception.PolizaException;
import exceptions.exception.SeguroException;

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

	
	// metodo para crear poliza
	
	public Poliza crearPoliza(PolizaRequest polizaRequest) throws PolizaException, SeguroException {
		System.out.println(polizaRequest.toString()+ "Hola soy To.string");

		if(polizaRequest.getUsuarioId()== null) {
			throw new PolizaException(" ID de usuario faltante para la creacion de una poliza");
		}
		
		if(polizaRequest.getNumeroDePoliza()== null) {
			throw new PolizaException("Numero de poliza faltante para la creacion de poliza");
		}
		if(polizaRequest.getFechaDeInicio()== null) {
			throw new PolizaException("Numero de inicio faltante para la creacion de poliza");
		}
		if(polizaRequest.getFechaDeVencimiento()== null) {
			throw new PolizaException("Numero de vencimiento faltante para la creacion de poliza");
		}
		
		if(polizaRequest.getMontoAsegurado()<= 0) {
			throw new PolizaException("Monto asegurado faltante o invalido para la creacion de poliza");
		}
		
		
		// Asigno el usuario a la póliza
		Usuario usuario = usuarioRepository.findById(polizaRequest.getUsuarioId()).orElseThrow(
				() -> new PolizaException("Usuario no encontrado con id: " + polizaRequest.getUsuarioId()));
		
		Poliza poliza = new Poliza();
		
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
			
			if(polizaRequest.getDescripcionAuto() == null || polizaRequest.getMarcaAuto()== null || polizaRequest.getModeloAuto()== null|| polizaRequest.getPatenteAuto()== null) {
				throw new SeguroException("Informacion de auto faltatante para la creacion del seguro auto ");
			}

			SeguroAuto seguroAuto = new SeguroAuto();
			seguroAuto.setDescripcion(polizaRequest.getDescripcionAuto());
			seguroAuto.setMarca(polizaRequest.getMarcaAuto());
			seguroAuto.setModelo(polizaRequest.getModeloAuto());
			seguroAuto.setPatente(polizaRequest.getPatenteAuto());
			seguroAutoRepository.save(seguroAuto);
			seguro = seguroAuto;
			break;

		case "SEGURO_CELULAR":
			
			if (polizaRequest.getDescripcionCelular() == null || polizaRequest.getMarcaCelular() == null || 
            polizaRequest.getModeloCelular() == null || polizaRequest.getNumeroDeSerieCelular() == null) {
            throw new SeguroException("Información del celular faltante para la creación del seguro de celular");
        }
			
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
			
			 if (polizaRequest.getDescripcionInmueble() == null || polizaRequest.getDireccionInmueble() == null || 
	            polizaRequest.getTipoDeConstruccionInmueble() == null) {
	            throw new SeguroException("Información del inmueble faltante para la creación del seguro de inmueble");
	        }
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

	public Poliza obtenerPolizaPorId(Long id) throws PolizaException {
	    return polizaRepository.findById(id)
	            .orElseThrow(() -> new PolizaException("Póliza no encontrada con ID: " + id));
	}

	// método para actualizar una póliza
	public Poliza actualizarPoliza(Long id, PolizaRequest polizaRequest) throws PolizaException ,SeguroException{
		
		
		 System.out.println("Buscando póliza con ID: " + id);
		    System.out.println(polizaRequest.toString() + " - Datos de la solicitud");
		    
		    
	    Optional<Poliza> optionalPoliza = polizaRepository.findById(id);
	    
	    if (optionalPoliza.isEmpty()) {
            throw new PolizaException("Póliza no encontrada con ID: " + id);
        }

	    
	        Poliza polizaExistente = optionalPoliza.get();
	        System.out.println("Póliza encontrada: " + polizaExistente.toString());

	        
	        polizaExistente.setNumeroDePoliza(polizaRequest.getNumeroDePoliza());
	        polizaExistente.setFechaDeInicio(polizaRequest.getFechaDeInicio());
	        polizaExistente.setFechaDeVencimiento(polizaRequest.getFechaDeVencimiento());
	        polizaExistente.setMontoAsegurado(polizaRequest.getMontoAsegurado());
	        polizaExistente.setEstado(polizaRequest.getEstado());

	        
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
	                    throw new SeguroException("El tipo de seguro no coincide con la póliza existente.");
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
	                    throw new SeguroException("El tipo de seguro no coincide con la póliza existente.");
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
	                    throw new SeguroException("El tipo de seguro no coincide con la póliza existente.");
	                }
	                break;

	            default:
	                throw new IllegalArgumentException("Tipo de seguro no válido: " + tipoDeSeguro);
	        }
	        System.out.println("Póliza actualizada correctamente");
	        return polizaRepository.save(polizaExistente);
	    } 
	


	// metodo para eliminar poliza

	 public void eliminarPoliza(Long id) throws PolizaException {
	        if (!polizaRepository.existsById(id)) {
	            throw new PolizaException("Póliza no encontrada con ID: " + id);
	        }
	        polizaRepository.deleteById(id);
	    }

}
