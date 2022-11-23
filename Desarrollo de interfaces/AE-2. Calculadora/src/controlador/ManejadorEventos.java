package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vista.VentanaPrincipal;

public class ManejadorEventos implements ActionListener{

	
	VentanaPrincipal vp;
	
	public ManejadorEventos(VentanaPrincipal vp) {
		this.vp = vp;
	}
	
	public double suma(double num1, double num2) {
		return num1 + num2;
	}
	
	public double resta(double num1, double num2) {
		return num1 - num2;
	}
	
	public double multiplicacion(double num1, double num2) {
		return num1 * num2;
	}
	
	public double division(double num1, double num2) {
		return num1 / num2;
	}
	
	// FALTA ESTA FUNCION
	public String raiz3() {
		return "";
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			double num1 = Double.valueOf(vp.getNum1().getText());
			double num2 = Double.valueOf(vp.getNum2().getText());
			
			if(e.getSource() == vp.getSumar()) {
				// Realizamos la operaci�n.
				double resultado = suma(num1,num2);							
				// Si el resultado obtenido entre 1 da como resto 0, hacemos un cast e imprimimos el n�mero como un entero, si no, como decimal.
				if(resultado%1 == 0) {										
					int resultadoInt = (int) resultado;
					String resultadoFinal = String.valueOf(resultadoInt);
					vp.getResultado().setText(resultadoFinal);
				}else {
					String resultadoFinal = String.valueOf(suma(num1,num2));
					vp.getResultado().setText(resultadoFinal);
				}
			// 	Realizamos el mismo procedimiento para el resto de operaciones, con la salvedad de que en la resta y la divisi�n
			// 	redondeamos con dos decimales como m�ximo ya que, como te coment�, a veces el error tiene una imprecisi�n milim�trica y 
			//	esto es lo que nos recomendaste.
			}else if(e.getSource() == vp.getRestar()){
				double resultado = resta(num1,num2);
				if(resultado%1 == 0) {										
					int resultadoInt = (int) resultado;
					String resultadoFinal = String.valueOf(resultadoInt);
					vp.getResultado().setText(resultadoFinal);
				}else {
					String resultadoFinal = String.valueOf(Math.round(resta(num1,num2)*100d)/100d);	
					vp.getResultado().setText(resultadoFinal);
					
				}
				
			}else if(e.getSource() == vp.getMultiplicar()) {
				double resultado = multiplicacion(num1,num2);							

				if(resultado%1 == 0) {										
					int resultadoInt = (int) resultado;
					String resultadoFinal = String.valueOf(resultadoInt);
					vp.getResultado().setText(resultadoFinal);
				}else {
					String resultadoFinal = String.valueOf(multiplicacion(num1,num2));
					vp.getResultado().setText(resultadoFinal);
				}
				
			}else if(e.getSource() == vp.getDividir()) {
				double resultado = division(num1,num2);							
				
				if(num2 == 0) {
					vp.getResultado().setText("No se puede dividir\n entre 0");
				}else if(resultado%1 == 0) {										
					int resultadoInt = (int) resultado;
					String resultadoFinal = String.valueOf(resultadoInt);
					vp.getResultado().setText(resultadoFinal);
				}else {
					String resultadoFinal = String.valueOf(Math.round(division(num1,num2)*100d)/100d);	
					vp.getResultado().setText(resultadoFinal);
				}
			
			}else if(e.getSource() == vp.getRaiz3()) {
				// AQU� VENDR�A EL EVENTO DEL BOTON DE LA RAIZ CUBICA
				
			}else if(e.getSource() == vp.getRaiz2()) {
				JOptionPane.showMessageDialog(null,"Funcionalidad no disponible", "Error", JOptionPane.INFORMATION_MESSAGE);	
		}
		//Aqu� recogemos la excepci�n de si el usuario introduce caracteres  que no sean n�meros incluido campo vac�o.
		}catch(NumberFormatException e1){	
			JOptionPane.showMessageDialog(null,"                      Dato introducido no v�lido o el campo est� vac�o.\n\nPara introducir decimales, recuerda utilizar el punto (.) en lugar de la coma (,).", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
