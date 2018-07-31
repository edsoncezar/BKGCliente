package br.com.gc.utils;

import javax.swing.JOptionPane;

public class Validation{

	public boolean validateNulo(String validar, String campo){
	   boolean verdadeiro = true;
       if(validar == null || validar.equalsIgnoreCase("")){
    	   JOptionPane.showMessageDialog(null, "Favor preencher o campo : " +campo);
    	   verdadeiro = false;
       }
       return verdadeiro;
	}

	public boolean validateInt(String validar,String campo){

		boolean verdadeiro = true;
		if(validar == null || validar.equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Favor preencher o campo : " +campo);
			verdadeiro = false;
		}

		if((Integer) Integer.parseInt(validar) instanceof Integer ){
			JOptionPane.showMessageDialog(null, "O campo deve ser num�rico : " +campo);
			verdadeiro = false;
		}
		return verdadeiro;
	}

	public boolean validateLenght(String validar,String campo){

		boolean verdadeiro = true;

		if(validar.length() > 3){
			JOptionPane.showMessageDialog(null, "O campo deve ter no m�ximo 3 posi��es : " +campo);
			verdadeiro = false;
		}
		return verdadeiro;
	}
}
