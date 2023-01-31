package view;

import controller.HuespedController;
import controller.ReservasController;
import model.Huesped;
import model.Reserva;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController;
	private HuespedController huespedController;
	String reserva;
	String huespedes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservasController = new ReservasController();
		this.huespedController = new HuespedController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setText("Ingrese un ID");
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.setForeground(Color.gray);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 380, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), new JScrollPane(tbReservas), null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("N° Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		tbReservas.getTableHeader().setBackground(new Color(12, 138, 199));
		tbReservas.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 18));
		tbReservas.getTableHeader().setForeground(Color.white);

		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		LlenarTablaReserva();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));

		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), new JScrollPane(tbHuespedes), null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();

		tbHuespedes.setBackground(Color.white);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		//panel.add(ScrollPane,BorderLayout.CENTER);
		modeloH.addColumn("N° Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("F.Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("N° Reserva");
		tbHuespedes.getTableHeader().setBackground(new Color(12, 138, 199));
		tbHuespedes.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 18));
		tbHuespedes.getTableHeader().setForeground(Color.white);
		tbHuespedes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		LlenarTablaHuesped();

		JTableHeader theader = tbHuespedes.getTableHeader();
		theader.setVisible(true);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				////
				limpiarTabla();
				int valor = Integer.parseInt(txtBuscar.getText());
				if(txtBuscar.getText().equals("")){
					LlenarTablaHuesped();
					LlenarTablaReserva();
				}else {
					LlenarTablaHuespedId(valor);
					LlenarTablaReservaId(valor);
				}
			}
		});

		txtBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtBuscar.getText().equals("Ingrese un ID")) {
					txtBuscar.setText("");
					txtBuscar.setForeground(Color.black);
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);

		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				////
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuesped = tbHuespedes.getSelectedRow();

				eliminar(filaReservas,filaHuesped);
				limpiarTabla();
				LlenarTablaHuesped();
				LlenarTablaReserva();
			}
		});

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event){
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();

				if (filaReservas >= 0) {
					ActualizarReservas();
					limpiarTabla();
					LlenarTablaReserva();
					LlenarTablaHuesped();
				}
				else if (filaHuespedes >= 0) {
					ActualizarHuesped();
					limpiarTabla();
					LlenarTablaReserva();
					LlenarTablaHuesped();
				}
			}
		});
	}
	private boolean tieneFilaElegidaHuespedes(){
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount()==0;
	}
	private boolean tieneFilaElegidaReservas(){
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount()==0;
	}
	private List<Reserva> encontrarReservas(){
		return this.reservasController.listar();
	}
	private List<Reserva> encontrarReservas(int valor){
		return this.reservasController.listar(valor);
	}
	private List<Huesped> encontrarHuespedes(){
		return this.huespedController.listar();
	}
	private List<Huesped> encontrarHuespedesId(int valor){
		return this.huespedController.listar(valor);
	}

	private void limpiarTabla(){
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}


	private void LlenarTablaReserva(){
		List<Reserva> reservas = encontrarReservas();
		try {
			for (Reserva reserva : reservas){
				modelo.addRow(new Object[]{reserva.getId(),reserva.getFechaE(),reserva.getFechaS(),reserva.getValor(),reserva.getFormaPago()});
			}
		}catch (Exception e){
			throw e;
		}
	}
	private void LlenarTablaReservaId(int valor){
		List<Reserva> reservas = encontrarReservas(valor);
		try {
			for (Reserva reserva : reservas){
				modelo.addRow(new Object[]{reserva.getId(),reserva.getFechaE(),reserva.getFechaS(),reserva.getValor(),reserva.getFormaPago()});
			}
		}catch (Exception e){
			throw e;
		}
	}
	private void LlenarTablaHuesped(){
		List<Huesped> huesped = encontrarHuespedes();
		try {
			for (Huesped huespedes : huesped){
				modeloH.addRow(new Object[]{huespedes.getId(),huespedes.getNombreH(),huespedes.getApellidoH(),huespedes.getFechaNacimientoH(),huespedes.getNacionalidadH(),huespedes.getTelefonoH(),huespedes.getIdReserva()});
			}
		}catch (Exception e){
			throw e;
		}
	}
	private void LlenarTablaHuespedId(int valor){
		List<Huesped>huesped = encontrarHuespedesId(valor);
		try {
			for (Huesped huespedes:huesped){
				modeloH.addRow(new Object[]{huespedes.getId(),huespedes.getNombreH(),huespedes.getApellidoH(),huespedes.getFechaNacimientoH(),huespedes.getNacionalidadH(),huespedes.getTelefonoH(),huespedes.getIdReserva()});

			}
		}catch (Exception e){
			throw e;
		}
	}
	private void eliminar(int filareserva, int filaHuesped) {
		//if (tieneFilaElegidaHuespedes() || tieneFilaElegidaReservas()) {
		//	JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para eliminar");
		//}
		if (filareserva >= 0) {
			reserva = tbReservas.getValueAt(filareserva, 0).toString();
			int confirmar = JOptionPane.showConfirmDialog(null, "¿Eliminar este dato?");
			if (confirmar == JOptionPane.YES_OPTION) {
				String valor = tbReservas.getValueAt(filareserva, 0).toString();
				int NumeroEliminado = reservasController.eliminar(Integer.valueOf(valor));
				JOptionPane.showMessageDialog(contentPane, "Registro N°"+NumeroEliminado+" Eliminado");

			}
		} else if (filaHuesped >= 0) {
			huespedes = tbHuespedes.getValueAt(filaHuesped, 0).toString();
			int confirmarh = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar los datos?");

			if (confirmarh == JOptionPane.YES_OPTION) {

				String valor = tbHuespedes.getValueAt(filaHuesped, 0).toString();
				int NumeroEliminado = huespedController.eliminar(Integer.valueOf(valor));
				JOptionPane.showMessageDialog(contentPane, "Registro N°"+NumeroEliminado+" Eliminado");
			}

		}else {
			JOptionPane.showMessageDialog(null, "Error fila no seleccionada, por favor realice una busqueda y seleccione una fila para eliminar");
		}
	}

	private void ActualizarReservas() {
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {

					Date fechaE = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
					Date fechaS = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
					String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
					String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
					Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					this.reservasController.actualizar(fechaE,fechaS, valor, formaPago, id);
					JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));

	}

	private void ActualizarHuesped() {
		Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(filaHuesped -> {

					String nombre = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1);
					String apellido = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2);
					Date fechaN = Date.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
					String nacionalidad = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4);
					String telefono = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5);
					int idReserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
					int id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					this.huespedController.actualizar(nombre,apellido,fechaN, nacionalidad, telefono, idReserva, id);
					JOptionPane.showMessageDialog(this, String.format("Registro modificado con éxito"));
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un registro"));

	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
