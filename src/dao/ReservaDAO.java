package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;

public class ReservaDAO {

  private Connection connection;

  public ReservaDAO(Connection connection) {
    this.connection = connection;

  }

  public int guardar(Reserva reserva) {
    String sql = "INSERT INTO Reservas (FechaEntrada,FechaSalida,Valor, FormaPago)" +
        "VALUES (?,?,?,?)";
    int nReservaH = 0;
    try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstm.setDate(1, reserva.getFechaE());
      pstm.setDate(2, reserva.getFechaS());
      pstm.setString(3, reserva.getValor());
      pstm.setString(4, reserva.getFormaPago());

      pstm.executeUpdate();

      try (ResultSet rst = pstm.getGeneratedKeys()) {
        while (rst.next()) {
          nReservaH = rst.getInt(1);
          reserva.setId(rst.getInt(1));
        }
        System.out.println("se ve 1");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return nReservaH;
  }
  public List<Reserva> listar() {
    List<Reserva> resultado = new ArrayList<>();

    try {
      final PreparedStatement statement = connection.prepareStatement("SELECT ID,FechaEntrada, FechaSalida, Valor, FormaPago FROM Reservas");

      try (statement){
        statement.execute();
        final ResultSet resultSet = statement.getResultSet();
        try(resultSet) {
          while (resultSet.next()){
            resultado.add(new Reserva(
                    resultSet.getInt("ID"),
                    resultSet.getDate("FechaEntrada"),
                    resultSet.getDate("FechaSalida"),
                    resultSet.getString("Valor"),
                    resultSet.getString("FormaPago")
            ));
          }
        }
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }

    return resultado;
  }
  public List<Reserva> listar(int valor) {
    List<Reserva> resultado = new ArrayList<>();

    try {
      final PreparedStatement statement = connection.prepareStatement("SELECT ID,FechaEntrada, FechaSalida, Valor, FormaPago FROM Reservas WHERE ID = ?");

      try (statement){
        statement.setInt(1, valor);
        statement.execute();
        final ResultSet resultSet = statement.getResultSet();
        try(resultSet) {
          while (resultSet.next()){
            resultado.add(new Reserva(
                    resultSet.getInt("ID"),
                    resultSet.getDate("FechaEntrada"),
                    resultSet.getDate("FechaSalida"),
                    resultSet.getString("Valor"),
                    resultSet.getString("FormaPago")
            ));
          }
        }
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }

    return resultado;
  }

  public int eliminar(Integer id){
    try {
      final PreparedStatement statement =connection.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
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
  public void Actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
    try (PreparedStatement statement = connection
            .prepareStatement("UPDATE RESERVAS SET FechaEntrada = ?, FechaSalida = ?, Valor = ?, FormaPago = ? WHERE ID = ?")) {
      statement.setDate(1, fechaE);
      statement.setDate(2, fechaS);
      statement.setString(3, valor);
      statement.setString(4, formaPago);
      statement.setInt(5, id);
      statement.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


}
