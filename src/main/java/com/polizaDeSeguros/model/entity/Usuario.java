package com.polizaDeSeguros.model.entity;

import java.util.List;

import com.polizaDeSeguros.enums.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity 
@Table(name = "usuario")
public class Usuario {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	@Column(unique = true)
	private String dni;

	@NotEmpty
	private String telefono;

	@NotEmpty
	private String direccion;

	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotEmpty
	@Enumerated(EnumType.STRING)
	private Roles rol;

	@NotEmpty
	@Size(min = 8,message = "La contraseña debe tener al menos 8 caracteres")
	private String password;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // todas las operaciones en usuario tambien repercuten en poliza 
	private List<Poliza> polizas; // Un usuario puede tener varias pólizas ya que cada numero de poliza corresponderia a un seguro

	public Usuario() {
	}

	public Usuario(String nombre, String apellido, String dni,
			String telefono, String direccion,String email, Roles rol,
			String password, List<Poliza> polizas) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		this.rol = rol;
		this.password = password;
		this.polizas = polizas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Poliza> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}

	
}