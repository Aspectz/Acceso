package prg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Administradores extends Usuarios {
	private String titulacion;
	public Administradores(String dni,String usuario,String nombreapellidos,Calendar d,String titulacion) {
		this.dni=dni;
		this.usuario=usuario;
		this.nombreapellidos=nombreapellidos;
		this.fechanacimiento=d;
		this.titulacion=titulacion;
		
	}
	public String getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	@Override
	public void generarContraseña() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechanac=getFechanacimiento().getTime();
		String fecha=sdf.format(fechanac);
		File admin=new File("USUARIOS\\ALUMNOS\\"+this.getDni()+".txt");
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(admin));
			pw.print(this.getDni()+";"+this.getUsuario()+";"+1234+";"+this.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+this.getTitulacion()+";");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		
	}
	
	@Override
	public void crearArchivo(String archivo) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechanac=getFechanacimiento().getTime();
		String fecha=sdf.format(fechanac);
		
		File admin=new File("USUARIOS\\ADMIN\\"+archivo);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(admin));
			pw.print(dni+";"+this.getUsuario()+";"+this.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+this.getTitulacion()+";");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechanacimiento=getFechanacimiento().getTime();
		String fecha=sdf.format(fechanacimiento);
		return "DNI:"+this.getDni()+"\nUsuario:"+this.getUsuario()+"\nNombres y Apellidos: "+this.getNombreapellidos()+""
				+ "\nTitulacion: "+this.getTitulacion()+"\nFecha Nacimiento: "+fecha;
	}
	
	
	
}
