package comunicaciones;

import java.io.Serializable;

public class AbrirVentana implements Serializable{

	public AbrirVentana(String tipoVentana) {
		super();
		this.tipoVentana = tipoVentana;
	}

	public String getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(String tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	String tipoVentana;
	
}
