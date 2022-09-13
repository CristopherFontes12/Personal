package objetos;

public class Veterinario {

	private String codigo;
	private String especialidad;
	private String nombre;
	private String ci;

	public Veterinario(String codigo, String especialidad, String nombre, String ci) {
		this.codigo = codigo;
		this.especialidad = especialidad;
		this.nombre = nombre;
		this.ci = ci;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}
	
	@Override
	public String toString(){
		return this.codigo + "," + this.ci + "," + this.nombre + "," + this.especialidad;
	}

}
