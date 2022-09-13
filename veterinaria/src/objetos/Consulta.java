package objetos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta {
	
	private Mascota mascota;
	private Veterinario veterinario;
	private Date fecha;
	
	public Consulta(Mascota mascota, Veterinario veterinario, Date fecha) {
		this.mascota = mascota;
		this.veterinario = veterinario;
		this.fecha = fecha;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		
		return sdf.format(this.fecha) + "," + this.getMascota().getNroPatente() + "," + this.getMascota().getNombre() + ","  + this.getVeterinario().getNombre();
	}
	

}
