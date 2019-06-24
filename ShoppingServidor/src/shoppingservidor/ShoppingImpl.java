/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingservidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

/**
 *
 * @author lukin
 */
public class ShoppingImpl extends UnicastRemoteObject implements  Shopping {
    float valorAtual;
    String produtos = "";  
    
    public ShoppingImpl () throws RemoteException {
        super();
    }
    
    @Override
    public void setValorAtual(float vAtual) throws RemoteException {
        this.valorAtual = vAtual;
    }

    @Override
    public void listarProdutos() throws RemoteException {
        produtos += "----PRODUTOS----\n";
        produtos += "Cód. \t Nome \t Preço \n";
        produtos += "001 \t MacBook \t R$9.550 \n";
        produtos += "002 \t SmarTV 42pol. \t R$2.200 \n";
        produtos += "003 \t Kindle \t R$437,50 \n";
        produtos += "004 \t Galaxy S9 \t R$3.599,90 \n";
        produtos += "005 \t 3 Camisetas \t R$99,90 \n";
        
        JOptionPane.showMessageDialog(null,produtos);
    }

    @Override
    public String comprar() throws RemoteException {
        String cod = JOptionPane.showInputDialog("Insira o código do produto que deseja comprar: ");
        String qntd = JOptionPane.showInputDialog("Quantas unidades? ");
        String codRast = "0";
        float result = 0;
        
        switch(cod) {
            case "001":
                result = valorAtual - (9550 * Integer.parseInt(qntd));
                codRast = "AX0103";
                break;
            case "002":
                result = valorAtual - (2200 * Integer.parseInt(qntd));
                codRast = "AX0203";
                break;
            case "003":
                result = (float) (valorAtual - (437.50 * Integer.parseInt(qntd)));
                codRast = "AX0303";
                break;
            case "004":
                result = (float) (valorAtual - (3599.90 * Integer.parseInt(qntd)));
                codRast = "AX0404";
                break;
            case "005":
                result = (float) (valorAtual - (99.90 * Integer.parseInt(qntd)));
                codRast = "AX0504";                
                break;
            default:
                result = -1;
                break;
        }
        
        String response = result + "-" + codRast;
        return response; //retorna uma string c/ o saldo anterior menos o preço da compra e o codigo de rastreio
    }
    
}
