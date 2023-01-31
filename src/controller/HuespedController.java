package controller;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import model.Huesped;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class HuespedController {
    private HuespedDAO huespedDAO;
    public HuespedController(){
        Connection connection = new ConnectionFactory().recuperaConexion();
        this.huespedDAO= new HuespedDAO(connection);

    }
    public void guardar(Huesped huesped){
        this.huespedDAO.guardar(huesped);
    }
    public List<Huesped>listar(){return huespedDAO.listar();}

    public List<Huesped>listar(int valor){
        return huespedDAO.listar(valor);
    }

    public int eliminar(Integer id){return huespedDAO.eliminar(id);};

    public void actualizar(String nombre,String apellido,Date fechaN,String nacionalidad,String telefono,int idReserva,int id){
        huespedDAO.Actualizar(nombre,apellido,fechaN, nacionalidad, telefono, idReserva, id);
    };
}
