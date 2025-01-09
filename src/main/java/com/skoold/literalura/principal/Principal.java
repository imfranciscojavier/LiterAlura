package com.skoold.literalura.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.skoold.literalura.servicio.GutendexServicio;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "com.skoold.literalura")
public class Principal implements CommandLineRunner {

    @Autowired
    private GutendexServicio gutendexServicio;

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar un libro");
            System.out.println("2. Consultar libros");
            System.out.println("3. Listar libros por idioma");
            System.out.println("4. Buscar libros por título");
            System.out.println("5. Listar autores registrados");
            System.out.println("6. Listar autores vivos en determinado tiempo");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarLibro(scanner);
                        break;
                    case 2:
                        consultarLibros();
                        break;
                    case 3:
                        listarLibrosPorIdioma(scanner);
                        break;
                    case 4:
                        buscarLibroPorTitulo(scanner);
                        break;
                    case 5:
                        listarAutores();
                        break;
                    case 6:
                        listarAutoresPorPeriodo(scanner);
                        break;
                    case 7:
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número válido.");
            }
        }
        scanner.close();
    }

    private void agregarLibro(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();

        System.out.print("Ingrese el idioma del libro: ");
        String idioma = scanner.nextLine();

        System.out.print("Ingrese el año de publicación: ");
        int anio = Integer.parseInt(scanner.nextLine());

        gutendexServicio.agregarLibro(titulo, autor, idioma, anio);
        System.out.println("Libro agregado con éxito.");
    }

    private void consultarLibros() {
        System.out.println("\nLista de libros:");
        gutendexServicio.obtenerLibros().forEach(System.out::println);
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro a buscar: ");
        String titulo = scanner.nextLine();
        gutendexServicio.buscarLibroPorTitulo(titulo).forEach(System.out::println);
    }

    private void listarAutores() {
        System.out.println("\nAutores registrados:");
        gutendexServicio.listarAutores().forEach(System.out::println);
    }

    private void listarAutoresPorPeriodo(Scanner scanner) {
        System.out.print("Ingrese el año de inicio: ");
        int inicio = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el año de fin: ");
        int fin = Integer.parseInt(scanner.nextLine());
        gutendexServicio.listarAutoresPorPeriodo(inicio, fin).forEach(System.out::println);
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma a filtrar (ej. 'en' para inglés, 'es' para español): ");
        String idioma = scanner.nextLine();
        gutendexServicio.listarLibrosPorIdioma(idioma).forEach(System.out::println);
    }
}
