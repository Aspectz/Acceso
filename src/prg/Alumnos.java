package prg;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Alumnos extends Usuarios {
	private String lvlestudios;

	public Alumnos(String dni,String usuario,String nombreapellidos,Calendar d,String lvlestudios) {
		this.dni=dni;
		this.usuario=usuario;
		this.nombreapellidos=nombreapellidos;
		this.fechanacimiento=d;
		this.lvlestudios=lvlestudios;
	}
	
	public String getLvlestudios() {
		return lvlestudios;
	}

	public void setLvlestudios(String lvlestudios) {
		this.lvlestudios = lvlestudios;
	}
	@Override
	public void generarContraseña() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechanac=getFechanacimiento().getTime();
		String fecha=sdf.format(fechanac);
		String dni=this.getDni();
		String archivo=dni+".txt";
		String año=String.valueOf(this.getFechanacimiento().get(Calendar.YEAR));
		dni=dni.substring(0, 3);
		String contraseña=dni+año;
		File admin=new File("USUARIOS\\ALUMNOS\\"+archivo);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(admin));
			pw.print(this.getDni()+";"+this.getUsuario()+";"+contraseña+";"+this.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+this.getLvlestudios()+";");
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
		
		File admin=new File("USUARIOS\\ALUMNOS\\"+archivo);
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(admin));
			pw.print(this.getDni()+";"+this.getUsuario()+";"+this.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+this.getLvlestudios()+";");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechanacimiento=getFechanacimiento().getTime();
		String fecha=sdf.format(fechanacimiento);
		
		return "DNI:"+this.getDni()+"\nUsuario:"+this.getUsuario()+"\nNombres y Apellidos: "+this.getNombreapellidos()+""
		+ "\nTitulacion: "+this.getLvlestudios()+"\nFecha Nacimiento: "+fecha;
	}
	
}
