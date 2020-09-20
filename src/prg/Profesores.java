package prg;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Profesores extends Usuarios {
	private String departamento;
	
	public Profesores(String dni,String usuario,String nombreapellidos,Calendar d,String departamento) {
		this.dni=dni;
		this.usuario=usuario;
		this.nombreapellidos=nombreapellidos;
		this.fechanacimiento=d;
		this.departamento=departamento;
	}
	
	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	@Override
	public void generarContraseña() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechanac=getFechanacimiento().getTime();
		String fecha=sdf.format(fechanac);
		
		String nombre=this.getNombreapellidos();
		String archivo=this.getDni()+".txt";
		String diames=String.valueOf(this.getFechanacimiento().get(Calendar.DAY_OF_MONTH));
		nombre=nombre.substring(0, 3);
		String contraseña=nombre+diames;
		File admin=new File("USUARIOS\\ALUMNOS\\"+archivo);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(admin));
			pw.print(this.getDni()+";"+this.getUsuario()+";"+contraseña+";"+this.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+this.getDepartamento()+";");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void crearArchivo(String archivo) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechanac=getFechanacimiento().getTime();
		String fecha=sdf.format(fechanac);
		
		File admin=new File("USUARIOS\\PROFESORES\\"+archivo);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(admin));
			pw.print(this.getDni()+";"+this.getUsuario()+";"+this.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+this.getDepartamento()+";");
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
		
		return "DNI:"+this.getDni()+"\nUsuario"+this.getUsuario()+"\nNombres y Apellidos: "+this.getNombreapellidos()+""
		+ "\nTitulacion: "+this.getDepartamento()+"\nFecha Nacimiento: "+fecha;
	}
}
