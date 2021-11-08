package practica_5;

public class Empleado {
	String nif, nombre, apellidos;
	double salario;

	Empleado() {

	}

	Empleado(String nif, String nombre, String apellidos, double salario) {
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.salario = salario;
	
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	
	public String toString() {
		return "nif: " + nif + "\n Nombre: " + nombre + "\n Apellidos: " + apellidos + "\n Salario: " + salario + "\n";
	}
	

}
