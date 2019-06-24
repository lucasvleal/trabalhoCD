/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author lukin
 */
public interface Shopping extends Remote{
    public void setValorAtual(float vAtual) throws RemoteException;
    
    public void listarProdutos() throws RemoteException;
    public String comprar() throws RemoteException;
}