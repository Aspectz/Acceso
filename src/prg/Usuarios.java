package prg;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class Usuarios {
	protected String dni;
	protected String usuario;
	protected String contrase�a;
	protected String nombreapellidos;
	protected Calendar fechanacimiento;
	
	
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombreapellidos() {
		return nombreapellidos;
	}
	public void setNombreapellidos(String nombreapellidos) {
		this.nombreapellidos = nombreapellidos;
	}
	public Calendar getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Calendar fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	//Methods
	public abstract void crearArchivo(String archivo);
	public void generarContrase�a() {
		
	}
	
}

