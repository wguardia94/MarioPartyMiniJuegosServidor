package comunicaciones;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DarRespuestaMinJuegoDondeEstaBola implements Serializable{
private int respuesta;
private String nick;
public int getRespuesta() {
	return respuesta;
}
public void setRespuesta(char respuesta) {
	this.respuesta = respuesta;
}
public String getNick() {
	return nick;
}
public void setNick(String nick) {
	this.nick = nick;
}
public DarRespuestaMinJuegoDondeEstaBola(int eleccion, String nick) {
	super();
	this.respuesta = eleccion;
	this.nick = nick;
}

	



}
