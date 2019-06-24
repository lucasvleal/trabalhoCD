/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportadoraservidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author lukin
 */
public class TransportadoraImpl extends UnicastRemoteObject implements Transportadora {
    float valorAtual, result;
    String cod, preco;
    
    public TransportadoraImpl () throws RemoteException {
        super();
    }
    
    @Override
    public void setValorAtual(float vAtual) throws RemoteException {
        this.valorAtual = vAtual;
    }

    @Override
    public void setCod(String cod) throws RemoteException {
        this.cod = cod;
    }

    @Override
    public String fretar() throws RemoteException {
        switch(cod){
            case "AX0103":
                result = valorAtual - 150f;
                preco = "150";
                break;
            case "AX0203":
                result = valorAtual - 300f;
                preco = "300";
                break;
            case "AX0303":
                result = valorAtual - 36;
                preco = "36";
                break;
            case "AX0404":
                result = valorAtual - 29.90f;
                preco = "29.90";
                break;
            case "AX0504":
                result = valorAtual - 15.55f;
                preco = "15.55";
                break;             
        }
        
        String response = Float.toString(result) + "-" + preco;
        return response;
    }

    @Override
    public String localizar() throws RemoteException {
        float random = (float) Math.random();
        
        if(random < 0.3f) return "Em transferência para a unidade da cidade...";
        else if(random < 0.6f) return "Preso em Curitiba...";
        else if(random < 0.9f) return "Saiu para entrega!";
        else if(random > 0.9f) return "Objeto entregue, verifique sua caixa de correio!";
        
        return "Erro";
    }
    
}
