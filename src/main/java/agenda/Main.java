/*
Cada registro de la agenda deberá contener, los siguientes datos: Nombre, Apellidos, Nº de Teléfono, E-mail. 

El programa deberá permitir las siguientes operaciones, mediante un menú.
 
a) Leer los datos de un nuevo registro (dar de alta), almacenándolo.)

b) Buscar una persona de la agenda leyendo de teclado su nombre y apellidos. Se 
visualizará el resto de los datos. 

c) Modificar el teléfono o e_mail, de una persona que se pedirá por teclado.  

d) Eliminar una persona de la agenda telefónica dando su nombre y apellidos.

e ) Deberá existir un contador de personas añadidas, consultadas, modificadas y borradas. 
Finalmente, cuando se salga del menú, se escribirá el número de contactos añadidas, el número      de contactos consultadas, el número de contactos modificados y el número de contactos  eliminados.
*/
package agenda;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Main {

	public static void main(String[] args) {
		
		int contadorAlta = 0;
		int contadorBuscar = 0;
		int contadorModificar = 0;
		int contadorBorrar = 0;
		
		Scanner sc = new Scanner(System.in);
		int opcion;
		
		do {
			System.out.println("1.ALTA");
			System.out.println("2.BUSCAR");
			System.out.println("3.MODIFICAR");
			System.out.println("4.ELIMINAR");
			System.out.println("5.SALIR");
			opcion = sc.nextInt();
			
			if (opcion == 1) {
				
				
				String nombre;
				String apellidos;
				int tlf;
				String email;
				
				System.out.println("ALTA");
				
				System.out.println("Escriba nombre");
				nombre = sc.next();
				System.out.println("escriba apellidos");
				apellidos = sc.next();
				System.out.println("escriba telefono");
				tlf = sc.nextInt();
				System.out.println("escriba email");
				email = sc.next();
				
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
				EntityManager entityManager = factory.createEntityManager();
				entityManager.getTransaction().begin();

				User usuario = new User();
				usuario.setNombre(nombre);
				usuario.setApellidos(apellidos);
				usuario.setTelefono(tlf);
				usuario.setEmail(email);
				entityManager.persist(usuario);
				
				contadorAlta ++;
				System.out.println(usuario.toString());
				
				entityManager.getTransaction().commit();
				entityManager.close();
				factory.close();
				
				
			}
			else if (opcion == 2) {
				
				String nombre;
				String apellidos;
				
				System.out.println("BUSCAR");
				System.out.println("Escriba nombre a buscar");
				nombre = sc.next();
				System.out.println("escriba apellidos a buscar");
				apellidos = sc.next();
				
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
				EntityManager entityManager = factory.createEntityManager();
				entityManager.getTransaction().begin();
			
				
			String sql = "FROM User u WHERE u.nombre = '"+ nombre +"' AND u.apellidos = '"+apellidos+"'";
			Query query = entityManager.createQuery(sql);
		
			 User usuario = (User) query.getSingleResult();
			 System.out.println(usuario.toString());
			 
				contadorBuscar++;
				
			}
			else if (opcion == 3) {
				
				System.out.println("MODIFICAR");
				String nombre;
				String apellidos;
				String email;
				String respuesta;
				int telefono;
				System.out.println("Escriba el nombre de la persona que quiera modificar");
				nombre = sc.next();
				System.out.println("Escriba los apellidos de la persona que quiera modificar");
				apellidos = sc.next();
				System.out.println("¿Que desea modificar el email(E) o el telefono(T)?");
				respuesta = sc.next();
				
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
				EntityManager entityManager = factory.createEntityManager();
				entityManager.getTransaction().begin();
				
				if (respuesta.equalsIgnoreCase("E")) {
					System.out.println("Introduzca nuevo email");
					email = sc.next();
					
					
					String sql = "UPDATE User set email= '"+ email +"' where nombre= '"+nombre+"' AND apellidos ='"+apellidos+"'";
					Query query = entityManager.createQuery(sql);
					query.executeUpdate();
					
					entityManager.getTransaction().commit();
					entityManager.close();
					factory.close();
					System.out.println("se ha modificado el EMAIL de: "+ nombre +" "+apellidos);
					contadorModificar++;
				}
				
				else if (respuesta.equalsIgnoreCase("T")) {
					System.out.println("Introduzca nuevo telefono");
					telefono = sc.nextInt();
					
					
					String sql = "UPDATE User set telefono= '"+ telefono +"' where nombre= '"+nombre+"' AND apellidos ='"+apellidos+"'";
					Query query = entityManager.createQuery(sql);
					query.executeUpdate();
					
					entityManager.getTransaction().commit();
					entityManager.close();
					factory.close();
					System.out.println("se ha modificado el TELEFONO de: "+ nombre +" "+apellidos);
					contadorModificar++;
				}
				
				else {
					System.out.println("Introduzca un valor válido");
				}
				

			}
			else if (opcion == 4) {
				System.out.println("ELIMINAR");
				String nombre;
				String apellidos;
				System.out.println("Escriba el nombre de la persona que quiera eliminar");
				nombre = sc.next();
				System.out.println("Escriba los apellidos de la persona que quiera eliminar");
				apellidos = sc.next();
				
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("agenda");
				EntityManager entityManager = factory.createEntityManager();
				entityManager.getTransaction().begin();
				
				String sql = "DELETE FROM User WHERE nombre = '"+ nombre +"' AND apellidos = '"+apellidos+"'";
				Query query = entityManager.createQuery(sql);
				query.executeUpdate();
				contadorBorrar++;
				
				entityManager.getTransaction().commit();
				entityManager.close();
				factory.close();
			}
			
			
			else if (opcion == 5) {
				
				
				System.out.println("numero de usuarios dados de alta: "+contadorAlta);
				System.out.println("numero de usuarios buscados: "+contadorBuscar);
				System.out.println("numero de usuarios modificados: "+contadorModificar);
				System.out.println("numero de usuarios borrados: "+contadorBorrar);
				
				
				
				
			}
		}
		
		while(opcion!=5);
		
		
	}

}