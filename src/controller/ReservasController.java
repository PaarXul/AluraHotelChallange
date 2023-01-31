package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import model.Reserva;

public class ReservasController {
private ReservaDAO reservaDAO;

public ReservasController() {
	Connection connection = new ConnectionFactory().recuperaConexion();
	
	this.reservaDAO = new ReservaDAO(connection);
	
}
	public int guardar(Reserva reserva) {
		return this.reservaDAO.guardar(reserva);

	}
	public List<Reserva> listar(){
		return reservaDAO.listar();
	}
	public List<Reserva> listar(int valor){
		return reservaDAO.listar(valor);
	}
	public int eliminar(Integer id){return reservaDAO.eliminar(id);};

	public void actualizar(Date fechaE, Date fechaS, String Valor, String FormaPago,int ID){
		reservaDAO.Actualizar(fechaE,fechaS,Valor,FormaPago,ID);
	};

}
