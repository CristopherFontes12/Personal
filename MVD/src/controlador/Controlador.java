package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import vista.Vista;

public class Controlador implements ActionListener {

	private Vista view;
	private Modelo model;
	
	
	public Controlador(Vista view, Modelo model){
		
		this.view = view;
		this.model = model;
		this.view.btnMultiplicar.addActionListener(this);
		
	}
	
	
	public void iniciar () {
		
		view.setTitle("MVC Multiplicar");
		view.setLocationRelativeTo(null);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		model.setNumeroUno(Integer.parseInt(view.txtNumeroUno.getText()));
		model.setNumeroDos(Integer.parseInt(view.txtNumeroDos.getText()));
		model.multiplicar();
		view.txtResultado.setText(String.valueOf(model.getResultado()));
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

}
