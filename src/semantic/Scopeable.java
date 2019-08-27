package semantic;
/*Interfaccia utilizzata per effettuare un attach allo scope*/
public interface Scopeable {
  void attachScope(Scope sc);
  Scope getAttachScope();
}
