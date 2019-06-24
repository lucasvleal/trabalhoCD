/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
/**
 *
 * @author lukin
 */
public class Cliente {
    static String menu = "";
//    static String nome, codRastreio = "";
//    static float valorInicial, saldo;
       
    public static void main( String args[] ) throws IOException {
        Socket con = new Socket(InetAddress.getLocalHost(),10000);
        
        while(true){            
        
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            DataInputStream in = new DataInputStream(con.getInputStream());

            menu += "---BEM VINDO(A) A CENTRAL DE COMPRAS LUVANNI ONLINE---\n";
            menu += "| Escolha o que deseja fazer:                        \t|\n";
            menu += "| 1. Criar Conta                                     \t|\n";
            menu += "| 2. Acessar Banco                                   \t|\n";
            menu += "| 3. Acessar Shopping                                \t|\n";
            menu += "| 4. Acessar Transportadora                          \t|\n";
            menu += " ---------------------------------------------------- ";

            String op = JOptionPane.showInputDialog(menu);
            out.writeUTF(op);
            String ok = in.readUTF();
            menu = "";
        }
        
    }

}
