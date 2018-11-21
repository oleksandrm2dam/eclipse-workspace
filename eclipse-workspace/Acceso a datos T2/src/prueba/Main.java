package prueba;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {

	final static String DBPath = "C:\\Users\\madrid\\Documents\\GitHub\\eclipse-workspace\\eclipse-workspace\\Acceso a datos T2\\databases\\db4o\\EMPLEDEP.YAP";
	
	public static void main(String[] args) {
		
		leer();
		
	}
	
	public static void leer() {
		ObjectContainer db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), 
				DBPath
				);
		
		Departamento dProg = new Departamento(50, null, null);
		
		Empleado empleadoEj = new Empleado(0, null, null, 0, null, 0, 0, dProg.getDept_no());
		
		ObjectSet<Empleado> empleados = db.queryByExample(empleadoEj);
		
		if(empleados.size() == 0) {
			System.out.println("No se han encontrado empleados");
		} else {
			for(Empleado empl: empleados) {
				System.out.println(empl.toString());
			}
		}
		
		
		db.close();
	}
	
	public static void escribir() {
		ObjectContainer db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), 
				DBPath
				);
		
		Departamento d1 = new Departamento(10, "CONTABILIDAD", "SEVILLA");
		Departamento d2 = new Departamento(20, "INVESTIGACIÓN", "MADRID");
		Departamento d3 = new Departamento(30, "VENTAS", "BARCELONA");
		Departamento d4 = new Departamento(40, "PRODUCCIÓN", "BILBAO");
		Departamento d5 = new Departamento(50, "RRHH", "CEUTA");
		
		Empleado e1 = new Empleado(7369, "SANCHEZ", "EMPLEADO", 7902, "1990-12-17", 1040, 0, 20);
		Empleado e2 = new Empleado(1234, "MALYGA", "PROGR", 1555, "1998-11-25", 1640, 0, 40);
		Empleado e3 = new Empleado(4566, "GONZÁLEZ", "PROGR", 1333, "1992-09-11", 1640, 0, 40);
		Empleado e4 = new Empleado(1000, "GÓMEZ", "VENDEDOR", 1444, "1997-11-12", 2000, 14, 30);
		Empleado e5 = new Empleado(2000, "PÉREZ", "PROGR", 1555, "1990-03-01", 1500, 0, 40);
		
		db.store(d1);
		db.store(d2);
		db.store(d3);
		db.store(d4);
		db.store(d5);
		
		db.store(e1);
		db.store(e2);
		db.store(e3);
		db.store(e4);
		db.store(e5);
		
		db.close();
	}
	
	public static class Departamento {
		private int dept_no;
		private String dnombre;
		private String loc;
		public Departamento(int dept_no, String dnombre, String loc) {
			super();
			this.dept_no = dept_no;
			this.dnombre = dnombre;
			this.loc = loc;
		}
		public int getDept_no() {
			return dept_no;
		}
		public void setDept_no(int dept_no) {
			this.dept_no = dept_no;
		}
		public String getDnombre() {
			return dnombre;
		}
		public void setDnombre(String dnombre) {
			this.dnombre = dnombre;
		}
		public String getLoc() {
			return loc;
		}
		public void setLoc(String loc) {
			this.loc = loc;
		}
		@Override
		public String toString() {
			return "Departamento [dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + "]";
		}
		
	}
	
	public static class Empleado {
		
		private int emp_no;
		private String apellido;
		private String oficio;
		private int dir;
		private String fecha_alt;
		private float salario;
		private float comision;
		private int dept_no;
		
		public Empleado(int emp_no, String apellido, String oficio, int dir, String fecha_alt, float salario,
				float comision, int dept_no) {
			super();
			this.emp_no = emp_no;
			this.apellido = apellido;
			this.oficio = oficio;
			this.dir = dir;
			this.fecha_alt = fecha_alt;
			this.salario = salario;
			this.comision = comision;
			this.dept_no = dept_no;
		}

		public int getEmp_no() {
			return emp_no;
		}

		public void setEmp_no(int emp_no) {
			this.emp_no = emp_no;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getOficio() {
			return oficio;
		}

		public void setOficio(String oficio) {
			this.oficio = oficio;
		}

		public int getDir() {
			return dir;
		}

		public void setDir(int dir) {
			this.dir = dir;
		}

		public String getFecha_alt() {
			return fecha_alt;
		}

		public void setFecha_alt(String fecha_alt) {
			this.fecha_alt = fecha_alt;
		}

		public float getSalario() {
			return salario;
		}

		public void setSalario(float salario) {
			this.salario = salario;
		}

		public float getComision() {
			return comision;
		}

		public void setComision(float comision) {
			this.comision = comision;
		}

		public int getDept_no() {
			return dept_no;
		}

		public void setDept_no(int dept_no) {
			this.dept_no = dept_no;
		}

		@Override
		public String toString() {
			return "Empleado [emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir
					+ ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept_no="
					+ dept_no + "]";
		}
		
	}

}
