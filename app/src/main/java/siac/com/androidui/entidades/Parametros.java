package siac.com.androidui.entidades;


import br.com.httpagent.soapaction.JSoapClass;
import br.com.httpagent.soapaction.JSoapReqField;

@JSoapClass(namespace = "http://tempuri.org/")
public class Parametros {

    @JSoapReqField(order = 0, fieldName = "DataHora")
    private String DataHora;

    @JSoapReqField(order = 1, fieldName = "VendedorId")
    private String VendedorId;

    public Parametros(String DataHora) {
        this.DataHora = DataHora;
    }

    public Parametros(String dataHora, String vendedorId) {
        DataHora = dataHora;
        VendedorId = vendedorId;
    }
}
