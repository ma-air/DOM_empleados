package practica_5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Lista {

	File fichero;
	ArrayList<Empleado> lista_Empleados;

	// CONSTRUCTORES

	Lista() throws IOException {
		this.fichero = new File("src/", "Empleados.xml");
		this.lista_Empleados = new ArrayList<Empleado>();
	}

	// METODOS GET/SET
	public ArrayList<Empleado> getLista_Empleados() {
		return lista_Empleados;
	}

	public void setLista_Empleados(ArrayList<Empleado> lista_Empleados) {
		this.lista_Empleados = lista_Empleados;
	}

	// METODOS IMPLEMENTADOS
	// ESCRIBIR EN FICHERO LA LISTA (IMPRIMIR)
	public void escribirEmpleado() throws IOException {
		// CREAMOS UN DOCUMENT BUILDER HACIENDO USO DE LA FACTORIA DOCUMENT
		// BUILDER FACTORY
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// CREAMOS UN DOCUMENTO VACIO CON NOMBRE REGISTRO_EMPLEADOS Y NODO RAIZ
		// RRHH
		DOMImplementation implementacion = builder.getDOMImplementation();
		Document registroEmpleados = implementacion.createDocument(null, "rrhh",
				null);
		// SE CREA NULO PARA QUE NO DE PROBLEMAS ENTRO DEL WHILE
		Element nodoEmpleado = null, nodoDatos = null;
		Text texto = null;
		// VAMOS EMPLEADO POR EMPLEADO
		for (Empleado empleado : lista_Empleados) {
			// CREAMOS EL NODO EMPLEADO
			nodoEmpleado = registroEmpleados.createElement("empleado");
			// LE AÑADIMOS COMO HIJO DE EMPLEADOS
			registroEmpleados.getDocumentElement().appendChild(nodoEmpleado);
			// CREAMOS EL NODO NIF
			nodoDatos = registroEmpleados.createElement("nif");
			// SE AÑADE COMO HIJO DE EMPLEADO
			nodoEmpleado.appendChild(nodoDatos);
			texto = registroEmpleados.createTextNode(empleado.getNif());
			nodoDatos.appendChild(texto);
			nodoDatos = registroEmpleados.createElement("nombre");
			nodoEmpleado.appendChild(nodoDatos);
			texto = registroEmpleados.createTextNode(empleado.getNombre());
			nodoDatos.appendChild(texto);
			nodoDatos = registroEmpleados.createElement("apellidos");
			nodoEmpleado.appendChild(nodoDatos);
			texto = registroEmpleados.createTextNode(empleado.getApellidos());
			nodoDatos.appendChild(texto);
			nodoDatos = registroEmpleados.createElement("salario");
			nodoEmpleado.appendChild(nodoDatos);
			texto = (Text) registroEmpleados
					.createTextNode(Double.toString(empleado.getSalario()));
			nodoDatos.appendChild(texto);
		}

		// HAY QUE GUARDAR EL DOCUMENTO
		// CREAR EL ORIGEN DE DATOS
		Source origen = new DOMSource(registroEmpleados);
		// CREAR EL RESULTADO (EL FICHERO DE DESTINO)
		Result resultado = new StreamResult(new File("src/", "Empleados.xml"));
		// CREAR UN TRANSFORME FACTORY
		Transformer transformador = null;
		try {
			transformador = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// REALIZAR LA TRANSFORMACION
		try {
			transformador.transform(origen, resultado);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// LEER ARCHIVO
	public void consultarEnLista(String nif) {
		Empleado aux = new Empleado();
		// CREAMOS EL DOCUMENT BUILDER PARA PODER OBTENER EL DOCUEMNTO
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// LEER EL DOCUMETNO DESDE EL FICHERO
		Document registroEmpleados = null;
		try {
			registroEmpleados = builder
					.parse(new File("src/", "Empleados.xml"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// NORMALIZAMOS EL DOCUMENTO PARA EVITAR ERRORES DE LECTURA
		registroEmpleados.getDocumentElement().normalize();

		/*
		 * // MOSTRAMOS EL NOMBRE DEL ELEMENTO RAIZ
		 * System.out.println("El elemento raiz es: " +
		 * registroEmpleados.getDocumentElement().getNodeName());
		 */
		// CREAMOS UNA LISTA DE TODOS LOS NODODS EMPLEADO
		NodeList empleados = registroEmpleados.getElementsByTagName("empleado");
		/*
		 * // MOSTRAMOS EL NUMERO DE ELEMENTOS EMPLEADO QUE HEMOS ENCONTRADO
		 * System.out.println( "Se han econtrado " + empleados.getLength() +
		 * "empleados");
		 */

		// RECORREMOS LA LISTA
		for (int i = 0; i < lista_Empleados.size(); i++) {
			// OBTENEMOS EL PRIMER NODO DE LA LISTA
			Node emple = empleados.item(i);
			// EN CASO DE QUE ESE NODO SEA UN ELEMENTO
			if (emple.getNodeType() == Node.ELEMENT_NODE) {
				// CREAMOS EL ELEMENTO EMPLEADO Y LEERMOS SU INFORMACION
				Element empleado = (Element) emple;
				String dni = empleado.getElementsByTagName("nif").item(0)
						.getTextContent();
				aux.setNif(dni);
				String name = empleado.getElementsByTagName("nombre").item(0)
						.getTextContent();
				aux.setNombre(name);
				String surname = empleado.getElementsByTagName("apellidos")
						.item(0).getTextContent();
				aux.setApellidos(surname);
				String wage = empleado.getElementsByTagName("salario").item(0)
						.getTextContent();
				aux.setSalario(Double.parseDouble(wage));
				if (dni.equals(nif)) {
					System.out.println(aux.toString());
				}
			}
		}
	}

	public void conultar() {
		Scanner entrada = new Scanner(System.in);
		System.out.print(
				"Has seleccionado CONSULTAR, introduce el NIF del mepleado que desee consultar: ");
		String nif = entrada.next();

		try {
			if (validarNif(nif) == true && existeEmpleado(nif) == true) {

				System.out.println("Se esta buscando al empleado... \n");
				consultarEnLista(nif);
			} else if (validarNif(nif) == false) {
				System.err.println(
						"Por favor, introduce un NIF con 8 dígitos y una letra");
			} else if (existeEmpleado(nif) == false) {
				System.err.println("No existe el empleado con NIF: " + nif);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// LISTAR LOS EMPLEADOS
	public void listar() throws IOException {
		String todo = "";
		for(int i =0; i<lista_Empleados.size(); i++) {
			todo = todo +lista_Empleados.get(i).toString();
		}
		System.out.println(todo);

	}

	// VALIDAR EL NIF QUE TE DA EL CLIENTE
	public boolean validarNif(String nif) throws Exception {

		boolean aux = false;
		try {
			Pattern pat = Pattern.compile("[0-9]{8,9}[A-Z a-z]");
			Matcher mat = pat.matcher(nif);
			if (mat.matches() == true) {
				aux = true;
			}
		} catch (Exception e) {
			System.err.println("Introduce 8 dígitos y una letra.");
		}
		return aux;

	}

	// VALIDAR EL NOMBRE Y APELLIDOS QUE TE DA EL CLIENTE
	public boolean validarNombre(String nombre) throws Exception {

		boolean aux = false;
		try {
			Pattern pat = Pattern.compile("[A-Z a-z]{0,10}");
			Matcher mat = pat.matcher(nombre);
			if (mat.matches() == true) {
				aux = true;
			}
		} catch (Exception e) {
			System.err.println("Introduce 10 cáracteres como máx.");
		}
		return aux;

	}

	// BUSCAR EMPLEADO EN LA LISTA
	public String buscarEmpleado(String nif) {
		Empleado aux = new Empleado();
		for (int i = 0; i < this.lista_Empleados.size(); i++) {
			if (nif.equals(this.lista_Empleados.get(i).nif)) {
				aux = this.lista_Empleados.get(i);
			}
		}
		return aux.toString();
	}

	// EXISTE EMPLEADO EN LA LISTA
	public boolean existeEmpleado(String nif) {
		boolean aux = false;
		for (int i = 0; i < this.lista_Empleados.size(); i++) {
			if (nif.equals(this.lista_Empleados.get(i).nif)) {
				aux = true;
			}
		}
		return aux;
	}

	// ANADIR A LA LISTA DE EMPLEADOS
	public void anadirEmpleado(Empleado worker) {
		lista_Empleados.add(worker);
		try {
			escribirEmpleado();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// HACER LA INSERCCION DEL NUEVO EMPLEADO
	public void inserccion() {
		Scanner entrada = new Scanner(System.in);
		System.out.print(
				"Has seleccionado INSERCCIÓN, introduce el NIF del empleado que desee insertar: ");
		String nif = entrada.next();

		try {
			if (validarNif(nif) == true && existeEmpleado(nif) == true) {
				System.out.println("Ya existe el empleado con NIF: " + nif);

			} else if (existeEmpleado(nif) == false) {
				System.out.println("Introduce el nombre: ");
				String nombre = entrada.next();
				if (validarNombre(nombre) == true) {
					System.out.println("Introduce los apellidos: ");
					String apellidos = entrada.next();
					if (validarNombre(apellidos) == true) {
						System.out.println("Introduce el salario: ");
						double salario = entrada.nextDouble();
						Empleado aux = new Empleado(nif, nombre, apellidos,
								salario);
						anadirEmpleado(aux);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ELIMINAR UN EMPLEADO
	public void eliminacion() {
		Scanner entrada = new Scanner(System.in);
		System.out.print(
				"Has seleccionado BORRADO, introduce el NIF del mepleado que desee borrar: ");
		String nif = entrada.next();

		try {
			if (validarNif(nif) == true && existeEmpleado(nif) == true) {
				borrar(nif);
			} else if (validarNif(nif) == false) {
				System.err.println(
						"Por favor, introduce un NIF con 8 dígitos y una letra");
			} else if (existeEmpleado(nif) == false) {
				System.err.println("No existe el empleado con NIF: " + nif);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// BORRAR EMPLEADO
	public void borrar(String nif) throws IOException {
		Empleado aux = new Empleado();
		// CREAMOS EL DOCUMENT BUILDER PARA PODER OBTENER EL DOCUEMNTO
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// LEER EL DOCUMETNO DESDE EL FICHERO
		Document registroEmpleados = null;
		try {
			registroEmpleados = builder
					.parse(new File("src/", "Empleados.xml"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// NORMALIZAMOS EL DOCUMENTO PARA EVITAR ERRORES DE LECTURA
		registroEmpleados.getDocumentElement().normalize();

		/*
		 * // MOSTRAMOS EL NOMBRE DEL ELEMENTO RAIZ
		 * System.out.println("El elemento raiz es: " +
		 * registroEmpleados.getDocumentElement().getNodeName());
		 */
		// CREAMOS UNA LISTA DE TODOS LOS NODODS EMPLEADO
		NodeList empleados = registroEmpleados.getElementsByTagName("empleado");
		/*
		 * // MOSTRAMOS EL NUMERO DE ELEMENTOS EMPLEADO QUE HEMOS ENCONTRADO
		 * System.out.println( "Se han econtrado " + empleados.getLength() +
		 * "empleados");
		 */

		// RECORREMOS LA LISTA
		for (int i = 0; i < lista_Empleados.size(); i++) {
			// OBTENEMOS EL PRIMER NODO DE LA LISTA
			Node emple = empleados.item(i);
			// EN CASO DE QUE ESE NODO SEA UN ELEMENTO
			if (emple.getNodeType() == Node.ELEMENT_NODE) {
				// CREAMOS EL ELEMENTO EMPLEADO Y LEERMOS SU INFORMACION
				Element empleado = (Element) emple;
				String dni = empleado.getElementsByTagName("nif").item(0)
						.getTextContent();
				aux.setNif(dni);
				String name = empleado.getElementsByTagName("nombre").item(0)
						.getTextContent();
				aux.setNombre(name);
				String surname = empleado.getElementsByTagName("apellidos")
						.item(0).getTextContent();
				aux.setApellidos(surname);
				String wage = empleado.getElementsByTagName("salario").item(0)
						.getTextContent();
				aux.setSalario(Double.parseDouble(wage));
				if (dni.equals(nif)) {
					System.out.println(
							"empleado a eliminar: \n" + aux.toString());
					lista_Empleados.remove(i);
					escribirEmpleado();
				}
			}
		}

	}

	// MODIFICACION UN EMPLEADO
	public void modificacion() {
		Scanner entrada = new Scanner(System.in);
		System.out.print(
				"Has seleccionado MODIFICAR, introduce el NIF del mepleado que desee modificar: ");
		String nif = entrada.next();

		try {
			if (validarNif(nif) == true && existeEmpleado(nif) == true) {
				modificar(nif);
			} else if (validarNif(nif) == false) {
				System.err.println(
						"Por favor, introduce un NIF con 8 dígitos y una letra");
			} else if (existeEmpleado(nif) == false) {
				System.err.println("No existe el empleado con NIF: " + nif);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// MODIFICAR EMPLEADO
	public void modificar(String nif) throws IOException {
		Scanner entrada = new Scanner(System.in);
		Empleado aux = new Empleado();
		// CREAMOS EL DOCUMENT BUILDER PARA PODER OBTENER EL DOCUEMNTO
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// LEER EL DOCUMETNO DESDE EL FICHERO
		Document registroEmpleados = null;
		try {
			registroEmpleados = builder
					.parse(new File("src/", "Empleados.xml"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// NORMALIZAMOS EL DOCUMENTO PARA EVITAR ERRORES DE LECTURA
		registroEmpleados.getDocumentElement().normalize();

		/*
		 * // MOSTRAMOS EL NOMBRE DEL ELEMENTO RAIZ
		 * System.out.println("El elemento raiz es: " +
		 * registroEmpleados.getDocumentElement().getNodeName());
		 */
		// CREAMOS UNA LISTA DE TODOS LOS NODODS EMPLEADO
		NodeList empleados = registroEmpleados.getElementsByTagName("empleado");
		/*
		 * // MOSTRAMOS EL NUMERO DE ELEMENTOS EMPLEADO QUE HEMOS ENCONTRADO
		 * System.out.println( "Se han econtrado " + empleados.getLength() +
		 * "empleados");
		 */

		// RECORREMOS LA LISTA
		for (int i = 0; i < lista_Empleados.size(); i++) {
			// OBTENEMOS EL PRIMER NODO DE LA LISTA
			Node emple = empleados.item(i);
			// EN CASO DE QUE ESE NODO SEA UN ELEMENTO
			if (emple.getNodeType() == Node.ELEMENT_NODE) {
				// CREAMOS EL ELEMENTO EMPLEADO Y LEERMOS SU INFORMACION
				Element empleado = (Element) emple;
				String dni = empleado.getElementsByTagName("nif").item(0)
						.getTextContent();
				aux.setNif(dni);
				String name = empleado.getElementsByTagName("nombre").item(0)
						.getTextContent();
				aux.setNombre(name);
				String surname = empleado.getElementsByTagName("apellidos")
						.item(0).getTextContent();
				aux.setApellidos(surname);
				String wage = empleado.getElementsByTagName("salario").item(0)
						.getTextContent();
				aux.setSalario(Double.parseDouble(wage));
				if (dni.equals(nif)) {
					System.out.println(
							"empleado a modificar: \n" + aux.toString());
					System.out.print("A cuanto aumenta su salario: ");
					double wages = entrada.nextDouble();
					aux.setSalario(wages);
					lista_Empleados.get(i).setSalario(wages);
					escribirEmpleado();
				}
			}
		}
	}
}
