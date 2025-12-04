package org.rafandco;

import org.rafandco.dao.TareaDAO;
import org.rafandco.db.Definition;
import org.rafandco.db.SingletonConnection;
import org.rafandco.model.Tarea;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Definition d = new Definition(SingletonConnection.getConnection());

        int opcion = 0;
        do {
            switch (opcion) {
                case 1:
                    crearNuevaTarea();
                    break;
                case 2:
                    for (Tarea tarea : TareaDAO.listarTodas()) {
                        System.out.println(tarea);
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("""
                            ----------------------------
                            
                            Saliendo de la app (Hasta luego bro)
                            
                            ----------------------------
                            """);
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
}
