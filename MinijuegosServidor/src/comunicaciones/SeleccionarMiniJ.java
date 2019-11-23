package comunicaciones;

import java.io.Serializable;

public class SeleccionarMiniJ implements Serializable {

	int opcion;

	public SeleccionarMiniJ(int opcion) {
		super();
		this.opcion = opcion;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}
	
	
	
}
