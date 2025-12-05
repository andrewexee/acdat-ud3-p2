package org.rafandco;

import org.rafandco.dao.TareaDAO;
import org.rafandco.db.Definition;
import org.rafandco.db.SingletonConnection;
import org.rafandco.model.Tarea;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Definition d = new Definition(SingletonConnection.getConnection());

        int opcion = 0;
        do {
            System.out.println("""
                    \n----------------------------
                    Menú Opciones
                    ----------------------------
                    1. Crear Nueva Tarea
                    2. Listar Tareas
                    3. Buscar Por ID
                    4. Marcar Como Completada
                    5. Eliminar Tarea
                    6. Salir
                    ----------------------------\n""");

            switch (opcion) {
                case 1:
                    System.out.println("""
                            \n----------------------------
                            1. Creación de nueva Tarea
                            ----------------------------\n""");
                    crearNuevaTarea();
                    break;
                case 2:
                    System.out.println("""
                            \n----------------------------
                            2. Listado de Tareas
                            ----------------------------\n""");
                    for (Tarea tarea : TareaDAO.listarTodas()) {
                        System.out.println(tarea);
                    }
                    break;
                case 3:
                    System.out.println("""
                            \n----------------------------
                            1. Buscar Tarea (por ID)
                            ----------------------------\n""");
                    System.out.println(buscarPorId());
                    break;
                case 4:
                    System.out.println("""
                            \n----------------------------
                            4. Marcar como Completada
                            ----------------------------\n""");
                    marcarComoCompletada();
                    break;
                case 5:
                    System.out.println("""
                            \n----------------------------
                            5. Eliminar Tarea
                            ----------------------------\n""");
                    eliminar();
                    break;
                case 6:
                    System.out.println("""
                            \n----------------------------
                            
                            Saliendo de la app (Hasta luego bro)
                            
                            ----------------------------\n""");
                    break;
                default:
                    System.out.println("Número no válido, solo del 1 al 6");
            }
        } while (opcion != 6);


        // Cerrar la conexión
        SingletonConnection.closeConnection();
    }

    private static void crearNuevaTarea() {
        System.out.print("Socio dime el titulo de la tarea: ");
        String titulo =  sc.nextLine();

        System.out.println("Y ahora dime la descripción: ");
        String descripcion = sc.nextLine();

        LocalDate fecha = LocalDate.now();

        TareaDAO.insertar(new Tarea(titulo, descripcion, fecha));
    }

    private static Tarea buscarPorId() {
        System.out.println("Socio dime el ID de la tarea que estás buscando: ");
        Tarea tarea = TareaDAO.buscarPorId(sc.nextInt());
        sc.nextLine();
        if (tarea != null) {
            return tarea;
        } else {
            System.out.println("Hermano, vaya mierdon de tarea que has buscado (ni existe)");
            return null;
        }
    }

    private static void marcarComoCompletada() {
        Tarea tarea = buscarPorId();
        if (tarea != null) {
            tarea.setCompletada(true);
            TareaDAO.actualizar(tarea);
            System.out.println("Tarea actualizada");
        } else {
            System.out.println("Hermano, vaya mierdon de tarea que has buscado (ni existe)");
        }

    }

    private static void eliminar() {
        System.out.println("Socio dime el ID de la tarea que estás buscando: ");
        TareaDAO.eliminar(sc.nextInt());
    }
}
