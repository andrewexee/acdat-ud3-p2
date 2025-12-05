package org.rafandco.dao;

import org.rafandco.db.SingletonConnection;
import org.rafandco.model.Tarea;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrés Iglesias Camacho
 * @date 28.11.2025
 */
public class TareaDAO {
    private static Connection connection;

    public TareaDAO() {
        this.connection = SingletonConnection.getConnection();
    }

    public static void insertar(Tarea tarea) {
        // INSERT INTO tareas (...)
        String sql = """
                    INSERT INTO tareas (titulo, descripcion, fechaCreacion)
                    VALUES (?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setDate(3, Date.valueOf(tarea.getFechaCreacion()));

            stmt.executeUpdate();
            System.out.println("Inserción exitosa");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void actualizar(Tarea tarea) {
        // UPDATE tareas SET ... WHERE id = ?
        String sql = """
                UPDATE tareas
                SET titulo = ?, descripcion = ?, fechaCreacion = ?, completada = ?
                WHERE id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setDate(3, Date.valueOf(tarea.getFechaCreacion()));
            stmt.setBoolean(4, tarea.isCompletada());
            stmt.setInt(5, tarea.getId());

            stmt.executeUpdate();
            System.out.println("Actualización exitosa");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void eliminar(int id) {
        // DELETE FROM tareas WHERE id = ?
        String sql = "DELETE FROM tareas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("Eliminación exitosa");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Tarea buscarPorId(int id) {
        // SELECT ... FROM tareas WHERE id = ?
        Tarea tareaRes =  null;

        String sql = "SELECT * FROM tareas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ident = (int) rs.getObject("id");
                String titulo = String.valueOf(rs.getObject("titulo"));
                String descripcion = String.valueOf(rs.getObject("descripcion"));
                boolean completada = (boolean) rs.getObject("completada");
                Date fechaCreacionAux = rs.getDate("fechaCreacion");
                LocalDate fechaCreacion = fechaCreacionAux.toLocalDate();

                tareaRes = new Tarea(ident, titulo, descripcion, completada, fechaCreacion);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return tareaRes;
    }

    public static List<Tarea> listarTodas() {
        // SELECT ... FROM tareas
        List<Tarea> tareas = new ArrayList<>();
        Tarea tareaRes = null;

        String sql = "SELECT * FROM tareas";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ident = (int) rs.getObject("id");
                String titulo = String.valueOf(rs.getObject("titulo"));
                String descripcion = String.valueOf(rs.getObject("descripcion"));
                boolean completada = (boolean) rs.getObject("completada");
                Date fechaCreacionAux = rs.getDate("fechaCreacion");
                LocalDate fechaCreacion = fechaCreacionAux.toLocalDate();
                tareaRes = new Tarea(ident, titulo, descripcion, completada, fechaCreacion);
                tareas.add(tareaRes);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return tareas;
    }
}
