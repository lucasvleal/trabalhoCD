/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author lukin
 */
public interface Banco extends Remote {
    public void setValorAtual(float vAtual) throws RemoteException;
    public void setValorOp(float valorOp) throws RemoteException;
    public String getResponse(float valor, boolean flag, String op) throws RemoteException;
    
    public String extrato() throws RemoteException;
    public String sacar() throws RemoteException;
    public String depositar() throws RemoteException;
}
