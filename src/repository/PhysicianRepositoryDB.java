package repository;

import model.Physician;

// Importamos las herramientas de JDBC que Java necesita para hablar con la base de datos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhysicianRepositoryDB implements PhysicianRepository {

    // Variable para guardar la conexión a la base de datos
    private DBConnection dbConnection;

    // CONSTRUCTOR: Inyección de Dependencias
    // Aquí le decimos: "No crees tu propia conexión, alguien más te la pasará lista para usar".
    // Esto hace que tu código sea flexible y fácil de cambiar en el futuro.
    public PhysicianRepositoryDB(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // ------------------------------------------------------------------------
    // MÉTODOS DE ESCRITURA (Guardar, Actualizar, Borrar) - Usan executeUpdate()
    // ------------------------------------------------------------------------

    @Override
    public void save(Physician physician) {
        // 1. EL SQL: Los signos '?' son "espacios en blanco" de seguridad. 
        // Evitan que hackers inyecten código malicioso (SQL Injection).
        String sql = "INSERT INTO Physician (physicianId, firstName, lastName, specialty, phone, email) VALUES (?, ?, ?, ?, ?, ?)";

        // 2. ABRIR CONEXIÓN
        Connection conn = dbConnection.connect();

        // Si la conexión falla, avisamos y detenemos el proceso (return)
        if (conn == null) {
            System.out.println("Database connection failed. Physician was not saved.");
            return;
        }

        // 3. PREPARAR Y EJECUTAR: try-with-resources
        // Esto crea el 'Statement' y asegura que se cierre automáticamente de la memoria al terminar
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Llenamos los '?' del SQL con los datos del objeto que nos pasaron, de izquierda a derecha.
            stmt.setInt(1, physician.getPhysicianId());
            stmt.setString(2, physician.getFirstName());
            stmt.setString(3, physician.getLastName());
            stmt.setString(4, physician.getSpecialty());
            stmt.setString(5, physician.getPhone());
            stmt.setString(6, physician.getEmail());

            // Ejecutamos la orden de inserción. (executeUpdate se usa cuando modificamos datos)
            stmt.executeUpdate();
            System.out.println("Physician saved successfully.");

        } catch (SQLException e) { // Si algo sale mal en la base de datos, lo atrapamos aquí
            System.out.println("Error saving physician.");
            e.printStackTrace();
        } finally {
            // 4. CERRAR CONEXIÓN: El 'finally' garantiza que, haya error o no, nos desconectamos.
            dbConnection.disconnect();
        }
    }

    @Override
    public void update(Physician physician) {
        // El UPDATE modifica a un médico específico usando el WHERE
        String sql = "UPDATE Physician SET firstName = ?, lastName = ?, specialty = ?, phone = ?, email = ? WHERE physicianId = ?";
        Connection conn = dbConnection.connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Llenamos los datos a actualizar
            stmt.setString(1, physician.getFirstName());
            stmt.setString(2, physician.getLastName());
            stmt.setString(3, physician.getSpecialty());
            stmt.setString(4, physician.getPhone());
            stmt.setString(5, physician.getEmail());
            // ¡OJO! El ID es el parámetro número 6 porque corresponde al '?' del WHERE al final del SQL
            stmt.setInt(6, physician.getPhysicianId());

            stmt.executeUpdate(); // Ejecutamos el cambio
            System.out.println("Physician updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating physician.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }
    }

    @Override
    public void delete(int physicianId) {
        // Borramos al médico que coincida con el ID
        String sql = "DELETE FROM Physician WHERE physicianId = ?";
        Connection conn = dbConnection.connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, physicianId); // Solo hay un '?' que llenar
            stmt.executeUpdate(); // Ejecutamos el borrado
            System.out.println("Physician deleted successfully.");

        } catch (SQLException e) {
            System.out.println("Error deleting physician.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }
    }

    // ------------------------------------------------------------------------
    // MÉTODOS DE LECTURA (Buscar) - Usan executeQuery() y devuelven un ResultSet
    // ------------------------------------------------------------------------

    @Override
    public Physician findById(int physicianId) {
        // SELECT pide datos, no los modifica
        String sql = "SELECT * FROM Physician WHERE physicianId = ?";
        Connection conn = dbConnection.connect();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, physicianId);
            
            // Como pedimos datos, usamos executeQuery(). Nos devuelve un ResultSet (una tabla virtual)
            ResultSet rs = stmt.executeQuery();

            // Si rs.next() es true, significa que encontró la fila con ese médico
            if (rs.next()) {
                // Tomamos los datos de esa fila (rs) y creamos un Objeto Java (Physician) nuevo
                return new Physician(
                    rs.getInt("physicianId"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("specialty"),
                    rs.getString("phone"),
                    rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error finding physician by ID.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }

        return null; // Si no encontró a nadie, devuelve nulo
    }

    @Override
    public List<Physician> findAll() {
        // No hay WHERE, queremos a todos los médicos
        String sql = "SELECT * FROM Physician";
        
        // Creamos una lista vacía donde iremos guardando los médicos que encontremos
        List<Physician> physicians = new ArrayList<>();
        Connection conn = dbConnection.connect();

        // Preparamos la orden y al mismo tiempo creamos el ResultSet
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Usamos un 'while' porque puede haber muchas filas. 
            // Mientras haya un "siguiente" (next), repetimos el ciclo.
            while (rs.next()) {
                // Convertimos cada fila de la base de datos en un Objeto Physician
                Physician physician = new Physician(
                    rs.getInt("physicianId"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("specialty"),
                    rs.getString("phone"),
                    rs.getString("email")
                );

                // Añadimos el médico a nuestra lista
                physicians.add(physician);
            }

        } catch (SQLException e) {
            System.out.println("Error finding all physicians.");
            e.printStackTrace();
        } finally {
            dbConnection.disconnect();
        }

        // Devolvemos la lista llena de médicos a quien haya llamado este método
        return physicians;
    }
}