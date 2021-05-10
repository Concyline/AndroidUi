package siac.com.androidui.entidades;


import br.com.httpagent.soapaction.JSoapClass;
import br.com.httpagent.soapaction.JSoapResField;

@JSoapClass(namespace = "http://tempuri.org/")
public class Cidade {

    @JSoapResField(name = "CidadeId")
    public int CidadeId;

    @JSoapResField(name = "EstadoId")
    public String EstadoId;

    @JSoapResField(name = "Nome")
    public String Nome;

    public Cidade() {
    }

    public int getCidadeId() {
        return CidadeId;
    }

    public void setCidadeId(int cidadeId) {
        CidadeId = cidadeId;
    }

    public String getEstadoId() {
        return EstadoId;
    }

    public void setEstadoId(String estadoId) {
        EstadoId = estadoId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return getCidadeId() +" - "+getNome() +" - "+getEstadoId();
    }
}
