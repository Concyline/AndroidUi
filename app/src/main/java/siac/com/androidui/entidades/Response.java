package siac.com.androidui.entidades;


import br.com.httpagent.soapaction.JSoapClass;
import br.com.httpagent.soapaction.JSoapResField;

@JSoapClass(namespace = "http://tempuri.org/")
public class Response {

    @JSoapResField(name = "GetCidadesResult")
    public Cidade[] result;

}
