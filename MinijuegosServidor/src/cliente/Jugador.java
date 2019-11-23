package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Jugador {

	
	ObjectInputStream in;
	ObjectOutputStream out;
	public Jugador(ObjectInputStream in, ObjectOutputStream out) {
		super();
		this.in = in;
		this.out = out;
	}
	public ObjectInputStream getIn() {
		return in;
	}
	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
	public ObjectOutputStream getOut() {
		return out;
	}
	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}
	
}
