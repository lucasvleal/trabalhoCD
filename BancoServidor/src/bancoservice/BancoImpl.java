/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 *
 * @author lukin
 */
public class BancoImpl extends UnicastRemoteObject implements Banco {
    float valorAtual, valorOp;

    public BancoImpl () throws RemoteException {
        super();
    }
    
    @Override
    public void setValorAtual(float vAtual) throws RemoteException {
        this.valorAtual = vAtual;
    }
    
    @Override
    public void setValorOp(float valorOp) throws RemoteException {
        this.valorOp = valorOp;
    }

    @Override
    public String getResponse(float valor, boolean flag, String op) throws RemoteException {
        String valorStr = Float.toString(valor);
        String status;
        String[] response = new String[2];
        
        if(flag) status = "A operação de " + op.toUpperCase() + " ocorreu com sucesso! :>";
        else status = "Ocorreu um erro com a operação de " + op.toUpperCase() + "! :<";
        
        switch(op) {
            case "extrato" :
                String ext = "----EXTRATO DO CLIENTE----\n";
                ext += "Valor: " + valor;
                ext += "\nData: " + new Date();
                
                response[0] = ext;
                response[1] = status;
                break;
            
            case "sacar" :
                if(!flag) status += "\n O valor não pode ser sacado pois a conta zeraria.";
                response[0] = valorStr;
                response[1] = status;
                break;
            
            case "depositar" :
                if(!flag) status += "\n O valor é alto demais para ser depositado. Fale com seu gerente.";
                response[0] = valorStr;
                response[1] = status;
                break;
        }
        
        String retorno = response[0] + "-" + response[1];
        return retorno;
    }

    @Override
    public String extrato() throws RemoteException {
        String response = getResponse(valorAtual, true, "extrato");        
        return response;
    }

    @Override
    public String sacar() throws RemoteException {
        String response;
        float newValor = valorAtual - valorOp;
        
        if(newValor > 0) response = getResponse(newValor, true, "sacar");
        else response = getResponse(valorAtual, false, "sacar");
        
        return response;
    }

    @Override
    public String depositar() throws RemoteException {
        String response;
        float newValor = valorAtual + valorOp;
        
        if(valorOp < 100000) response = getResponse(newValor, true, "sacar");
        else response = getResponse(valorAtual, false, "sacar");
        
        return response;}
}
