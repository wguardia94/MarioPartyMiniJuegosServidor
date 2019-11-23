package comunicaciones;

public class DevolverDadosGen {
String nick;
int [] dados;
public String getNick() {
	return nick;
}
public void setNick(String nick) {
	this.nick = nick;
}
public int[] getDados() {
	return dados;
}
public void setDados(int[] dados) {
	this.dados = dados;
}
public DevolverDadosGen(String nick, int[] dados) {
	super();
	this.nick = nick;
	this.dados = dados;
}



}
