/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportadoraservidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author lukin
 */
public interface Transportadora extends Remote {
    public void setValorAtual(float vAtual) throws RemoteException;
    public void setCod(String cod) throws RemoteException;
    
    public String fretar() throws RemoteException;
    public String localizar() throws RemoteException;
}
