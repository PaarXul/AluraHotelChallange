package dao;

import model.Huesped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {
    private Connection connection;

    public HuespedDAO(Connection connection){
        this.connection=connection;
    }
    public void guardar(Huesped huesped){
        String sql = "INSERT INTO huespedes (nombre,apellido,FechaNacimiento, nacionalidad, telefono, IdReserva)"+
                "VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1,huesped.getNombreH());
            pstm.setString(2,huesped.getApellidoH());
            pstm.setDate(3, (Date) huesped.getFechaNacimientoH());
            pstm.setString(4,huesped.getNacionalidadH());
            pstm.setString(5,huesped.getTelefonoH());
            pstm.setInt(6,huesped.getIdReserva());
            pstm.execute();

            try (ResultSet rst = pstm.getGeneratedKeys()){
                while (rst.next()){
                    huesped.setId(rst.getInt(1));
                }
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT id,nombre, apellido, FechaNacimiento, nacionalidad, telefono, IdReserva FROM Huespedes");

            try (statement){
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet) {
                    while (resultSet.next()){
                        resultado.add(new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("FechaNacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("IdReserva")
                        ));
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public List<Huesped> listar(int valor) {
        List<Huesped> resultado = new ArrayList<>();

        try {
            String sql = "SELECT id,nombre, apellido, FechaNacimiento, nacionalidad, telefono, IdReserva FROM Huespedes WHERE id = ?";
            System.out.println(sql);

            final PreparedStatement statement = connection.prepareStatement(
                    sql);

            try (statement) {
                statement.setInt(1, valor);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("FechaNacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("IdReserva")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    public int eliminar(Integer id){
        try {
            final PreparedStatement statement =connection.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
            try(statement) {
                statement.setInt(1,id);
                statement.execute();
                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void Actualizar(String nombre,String apellido,Date fechaN,String nacionalidad,String telefono,int idReserva,int id) {
        try (PreparedStatement statement = connection
                .prepareStatement("UPDATE HUESPEDES SET nombre=?, apellido=?, FechaNacimiento=?, nacionalidad=?, telefono=?, IdReserva=? WHERE ID = ?")) {
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setDate(3, fechaN);
            statement.setString(4, nacionalidad);
            statement.setString(5, telefono);
            statement.setInt(6, idReserva);
            statement.setInt(7, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
