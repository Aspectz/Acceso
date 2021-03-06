package prg;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class App {
	public static Scanner entrada=new Scanner(System.in);
	public static ArrayList<Usuarios> usuarios=new ArrayList<Usuarios>();
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub

		int opc=0;
		System.out.println("Quieres cargar el arraylist: ");
		String answ=entrada.nextLine();
		if(answ.equalsIgnoreCase("si")) {
			cargarArrayList();
		}
		do {
			
			
			verMenu();
			System.out.println("Introduce una opcion: ");
			opc=Integer.parseInt(entrada.nextLine()); 
			switch(opc) {
				case 2:
					altaUsuarios();
					break;
				case 3:
					if(!crearArchivo())
						System.out.println("No existe ese usuario");
					break;
				case 4:
					
					System.out.println("Indica el dni del usuario para modificarlo: ");
					String dni=entrada.next();
					entrada.nextLine();

					if(buscarArchivo(dni)==null) {
						System.out.println("--------------------");
						System.out.println("El usuario no existe");
						System.out.println("--------------------");
					}else {
						System.out.println("Indica el nuevo dni: ");
						String newDNI=entrada.next();
						entrada.nextLine();
						File archivoBorrar=buscarArchivo(dni);
						String ruta=archivoBorrar.toString().replace("\\", "\\\\");
						if(modificarDNI(archivoBorrar,newDNI)) {
							borrarArchivo(ruta);
						}else {
							System.out.println("No se ha borrado");
						}
					}
					break;
				case 5:
					crearContraseña();
					break;
				case 6:
					System.out.println("Indica el dni del usuario para borrar: ");
					dni=entrada.next();
					entrada.nextLine();
					if(buscarArchivo(dni)==null) {
						System.out.println("--------------------");
						System.out.println("El usuario no existe");
						System.out.println("--------------------");
					}else {
						File archivoBorrar=buscarArchivo(dni);
						String ruta=archivoBorrar.toString().replace("\\", "\\\\");
						File f=new File(ruta);
						moverPapelera(f);
						borrarArchivo(ruta);
					}
					 
					break;
				case 7:
					visualizarUsuarios();
					break;
				case 8:
					copiaArrayList();
					break;
				case 9:
					copiarCarpetas();
					break;	
			}
			
		}while(opc!=0);
		entrada.close();
	}
	
	public static void verMenu() {
		System.out.println("2-Alta de usuarios");
		System.out.println("3-Crear archivo dni");
		System.out.println("4-Modificar el dni del usuario.");
		System.out.println("5-Generar contraseña");
		System.out.println("6-Borrar usuario");
		System.out.println("7-Visualizar usuarios del sistema");
		System.out.println("8-Copia de seguridad del ArrayList");
		System.out.println("9-Copia de seguridad de las carpetas");
		System.out.println("0-Salir.");
	}
	
	public static boolean crearArchivo() {
		System.out.println("Que usuario quieres crear el archivo: ");
		String dni=entrada.next();
		entrada.nextLine();
		String archivo;
		for(Usuarios u : usuarios) {
			if(u instanceof Administradores) {
				if(u.getDni().compareTo(dni)==0) {
					archivo=u.getDni()+".txt";
					u.crearArchivo(archivo);
					return true;
				}
				
			}
			if(u instanceof Profesores) {
				if(u.getDni().compareTo(dni)==0) {
					archivo=u.getDni()+".txt";
					u.crearArchivo(archivo);
					return true;
				}
			}
			if(u instanceof Alumnos) {
				if(u.getDni().compareTo(dni)==0) {
					archivo=u.getDni()+".txt";
					u.crearArchivo(archivo);
					return true;
				}
			}
		}
		return false;
	}
	
	public static void crearContraseña() {
		System.out.println("A que usuario quieres generar la contraseña(dni): ");
		String dni=entrada.next();
		entrada.nextLine();
		String archivo;
		for(Usuarios u : usuarios) {
			if(u instanceof Administradores) {
				if(u.getDni().compareTo(dni)==0) {
					u.generarContraseña();
				}
			}
			if(u instanceof Alumnos) {
				if(u.getDni().compareTo(dni)==0) {
					u.generarContraseña();
				}
			}
			if(u instanceof Profesores) {
				if(u.getDni().compareTo(dni)==0) {
					u.generarContraseña();
				}
			}
		}
	}
	
	public static void altaUsuarios() throws ParseException {
		int opc1=0;
		System.out.println("Introduce el dni del usuario: ");
		String dni=entrada.next();
		String archivo=dni+".txt";
		System.out.println("Introduce su usuario: ");
		String usuario=entrada.next();
		entrada.nextLine();
		System.out.println("Introduce sus nombres y apellidos: ");
		String nombreyAp=entrada.nextLine();
		System.out.println("Introduce dia nacimiento: ");
		int dia=Integer.parseInt(entrada.nextLine());
		System.out.println("Introduce mes nacimiento: ");
		int mes=Integer.parseInt(entrada.nextLine());
		mes=-1;
		System.out.println("Introduce año nacimiento: ");
		int ano=Integer.parseInt(entrada.nextLine());
		
		
		Calendar cal=Calendar.getInstance();
		cal.set(ano, mes, dia);
		Date fecha=cal.getTime();
		do {
			
			System.out.println("Admin=0;Profe=1;Alumno=2");
			opc1=Integer.parseInt(entrada.nextLine());
			switch(opc1) {
				case 0:
					System.out.println("Introduce tu titulacion: ");
					String titulacion=entrada.nextLine();
					Administradores admin=new Administradores(dni,usuario,nombreyAp,cal,titulacion);
					usuarios.add(admin);
					
					break;
				case 1:
					System.out.println("Introduce tu departamento: ");
					String departamento=entrada.nextLine();
					Profesores prof=new Profesores(dni,usuario,nombreyAp,cal,departamento);
					usuarios.add(prof);
					break;
				case 2:
					System.out.println("Introduce tu nivel de estudios: ");
					String nivel=entrada.nextLine();
					Alumnos alum=new Alumnos(dni,usuario,nombreyAp,cal,nivel);
					usuarios.add(alum);
					break;
			}
		}while(opc1>=3);
	}
	public static File buscarArchivo(String dni) {
		File admin=new File("USUARIOS/");
		File [] files=admin.listFiles();
		for(File f : files) {
			for(File b : f.listFiles()) {
				String nombreArchivo=b.getName();
				nombreArchivo=nombreArchivo.substring(0,nombreArchivo.indexOf("."));
				if(nombreArchivo.equalsIgnoreCase(dni)) {
					File fileBorrar=new File(b.toString());
					return fileBorrar;
				}
			}
		}
		return null;
	}
	public static boolean modificarDNI(File archivoBorrar,String newDNI) {
		Scanner leer=null;		
		try {
			leer=new Scanner(archivoBorrar);
			String linea=leer.next();
			linea=linea.substring(linea.indexOf(";")+1,linea.length());
			String nuevoSitio=archivoBorrar.toString();
			nuevoSitio=nuevoSitio.substring(0,nuevoSitio.indexOf(archivoBorrar.getName()));
			PrintWriter pw=new PrintWriter(nuevoSitio+newDNI+".txt");
			pw.print(newDNI+";"+linea);
			pw.close();
			leer.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leer.close();
		return false;
	}
	public static void borrarArchivo(String ruta) {
		File borrar=new File(ruta);
		borrar.delete();
	}
	public static void visualizarUsuarios() {
		Iterator it = usuarios.iterator();
		while(it.hasNext()) {
			System.out.println();
			System.out.println(it.next().toString());
			System.out.println();
		}
	}
	public static void moverPapelera(File f) {
		Scanner leer=null;
		
		try {
			leer = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(leer.hasNext()) {
			try {
				PrintWriter pw=new PrintWriter(new FileWriter("PAPELERA/"+f.getName()));
				pw.print(leer.nextLine());
				pw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		leer.close();
	}
	public static void copiaArrayList() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");  
		
		
		File copia=new File("COPIAS\\alumnosArrayList.bac");
		try {
			String linea=null;
			PrintWriter pw=new PrintWriter(new FileWriter(copia));
			for(Usuarios u : usuarios) {
				if(u instanceof Administradores) {
					Date fechanac=u.getFechanacimiento().getTime();
					String fecha=sdf.format(fechanac);
					pw.println(u.getDni()+";"+u.getUsuario()+";"+u.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+((Administradores) u).getTitulacion()+";");
					
				}
				if(u instanceof Profesores) {
					Date fechanac=u.getFechanacimiento().getTime();
					String fecha=sdf.format(fechanac);
					pw.println(u.getDni()+";"+u.getUsuario()+";"+u.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+((Profesores) u).getDepartamento()+";");
					
				}
				if(u instanceof Alumnos) {
					Date fechanac=u.getFechanacimiento().getTime();
					String fecha=sdf.format(fechanac);
					pw.println(u.getDni()+";"+u.getUsuario()+";"+u.getNombreapellidos().replaceAll("\\s+","")+";"+fecha+";"+((Alumnos) u).getLvlestudios()+";");
					
				}
				//pw.println(u);
				
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void copiarCarpetas() throws IOException {
		File guardar=new File("COPIAS\\alumnosFile.bac");
		File ficheros=new File("USUARIOS\\");
		PrintWriter pw=new PrintWriter(new FileWriter(guardar));
		
		pw.println(ficheros.getName()+":");
		for(File f : ficheros.listFiles()) {
			pw.println("\t"+f.getName()+":");
			for(File f1 : f.listFiles()) {
				File usuarios=new File(f1.getPath());
				Scanner leer=new Scanner(usuarios);
				pw.println("\t\t\t"+leer.next());
				leer.close();
			}
		}
		pw.close();
	}
	public static void cargarArrayList() throws FileNotFoundException, ParseException{
		File ficheros=new File("USUARIOS\\");
		Scanner leer;
		for(File f : ficheros.listFiles()) {
			for(File x : f.listFiles()) {
				File us=new File(x.getPath());
				leer=new Scanner(us);
				String[] d=leer.next().split(";");
				leer.close();
				if(f.toString().contains("ADMIN")) {
					
					if(d.length==5) {
						Administradores admin;
						String dni=d[0];
						String usuario=d[1];
						String nombreapellidos=d[2];
						Calendar fecha=Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date date=sdf.parse(d[3]);
						fecha.setTime(date);
						String titulacion=d[4];
						admin=new Administradores(dni,usuario,nombreapellidos,fecha,titulacion);
						usuarios.add(admin);
					}if(d.length==6) {
						Administradores admin;
						String dni=d[0];
						String usuario=d[1];
						String nombreapellidos=d[2];
						Calendar fecha=Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date date=sdf.parse(d[4]);
						fecha.setTime(date);
						String titulacion=d[4];
						admin=new Administradores(dni,usuario,nombreapellidos,fecha,titulacion);
						usuarios.add(admin);
					}
					}
				if(f.toString().contains("ALUMNOS")) {
					if(d.length==5) {
						Alumnos alumnos;
						String dni=d[0];
						String usuario=d[1];
						String nombreapellidos=d[2];
						Calendar fecha=Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date date=sdf.parse(d[3]);
						fecha.setTime(date);
						String estudios=d[4];
						alumnos=new Alumnos(dni,usuario,nombreapellidos,fecha,estudios);
						usuarios.add(alumnos);
					}if(d.length==6) {
						Alumnos alumnos;
						String dni=d[0];
						String usuario=d[1];
						String nombreapellidos=d[3];
						Calendar fecha=Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date date=sdf.parse(d[4]);
						fecha.setTime(date);
						String estudios=d[5];
						alumnos=new Alumnos(dni,usuario,nombreapellidos,fecha,estudios);
						usuarios.add(alumnos);
					}
					
				}if(f.toString().contains("PROFESORES")) {
					if(d.length==5) {
						Profesores prof;
						String dni=d[0];
						String usuario=d[1];
						String nombreapellidos=d[2];
						Calendar fecha=Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date date=sdf.parse(d[3]);
						fecha.setTime(date);
						String departamento=d[4];
						prof=new Profesores(dni,usuario,nombreapellidos,fecha,departamento);
						usuarios.add(prof);
					}if(d.length==6) {
						Profesores prof;
						String dni=d[0];
						String usuario=d[1];
						String nombreapellidos=d[3];
						Calendar fecha=Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date date=sdf.parse(d[4]);
						fecha.setTime(date);
						String departamento=d[5];
						prof=new Profesores(dni,usuario,nombreapellidos,fecha,departamento);
						usuarios.add(prof);
					}
					
				}
				}
				}
			}
}
