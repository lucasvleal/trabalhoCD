/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//import static communication.Cliente.menu;
import javax.swing.JOptionPane;

/**
 *
 * @author lukin
 */
public class ServidorDist {
    String menu = "";
    
    public static void main (String args[]) throws IOException {
        ServerSocket c = new ServerSocket(10000);        
        Socket con = c.accept();
        

        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        DataInputStream in = new DataInputStream(con.getInputStream());
        
        while(true){
            String opc = in.readUTF();

            System.out.println("Leu");

            menu(opc, out);        

            System.out.println("Abriu menu");
        }
    }
    
    public static void menu(String op, DataOutputStream out) throws IOException {
        String nome = "", response = "", codRastreio = "";
        float valorInicial = 0f, saldo = 0f;       
        
        System.out.println("Escolha a opcao");
        switch(op) {
            case "1" :
                nome = JOptionPane.showInputDialog("Insira seu nome: ");
                valorInicial = Float.parseFloat(JOptionPane.showInputDialog("Entre com o valor inicial na sua conta: "));
                saldo = valorInicial;
                JOptionPane.showMessageDialog(null,"Conta criada!");
                out.writeUTF("ok");
                break;
            
            case "2" :  
                String menuBanco = "";                    
                menuBanco += "----------------BANCO LUVANNI ONLINE------------------\n";
                menuBanco += "| Escolha o que deseja fazer:                        |\n";
                menuBanco += "| 1. Verificar Extrato                               |\n";
                menuBanco += "| 2. Sacar Valor                                     |\n";
                menuBanco += "| 3. Depositar Valor                                 |\n";
                menuBanco += " ---------------------------------------------------- ";
                    
                String opc = JOptionPane.showInputDialog(menuBanco);
                    
                    try {
                        Banco mref = (Banco) Naming.lookup( "rmi://localhost:1099/BancoService" );
                        mref.setValorAtual(saldo);
                        
                        switch(opc) {
                            case "1" :
                                response = mref.extrato();
                                break;      

                            case "2" :
                                response = mref.sacar();
                                break;

                            case "3" :
                                response = mref.depositar();
                                break;
                        }
                        
                        String infos[] = response.split("-");
                        
                        if(valorInicial != Float.parseFloat(infos[0])){
                            saldo = Float.parseFloat(infos[0]);                            
                        } else saldo = valorInicial;
                        
                        JOptionPane.showMessageDialog(null,infos[1]);
                        out.writeUTF("ok");
                    }
                    
                    //////////----CATCH
                    catch( MalformedURLException e ) {
                            System.out.println();
                            System.out.println( "MalformedURLException: " + e.toString() );
                    }
                    catch( RemoteException e ) {
                            System.out.println();
                            System.out.println( "RemoteException: " + e.toString() );
                    }
                    catch( NotBoundException e ) {
                            System.out.println();
                            System.out.println( "NotBoundException: " + e.toString() );
                    }
                    catch( Exception e ) {
                            System.out.println();
                            System.out.println( "Exception: " + e.toString() );
                    }
                    break;
            
            case "3" :
                String menuShop = "";                    
                menuShop += "--------------SHOPPING LUVANNI ONLINE----------------\n";
                menuShop += "| Escolha o que deseja fazer:                        |\n";
                menuShop += "| 1. Listar Pordutos                                 |\n";
                menuShop += "| 2. Comprar                                         |\n";
                menuShop += " ---------------------------------------------------- ";
                    
                String opcShop = JOptionPane.showInputDialog(menuShop);
                    
                    try {
                        Shopping mref = (Shopping) Naming.lookup( "rmi://localhost:1099/ShoppingService" );
                        mref.setValorAtual(saldo);
                        
                        switch(opcShop) {
                            case "1" :
                                mref.listarProdutos();
                                break;      

                            case "2" :
                                String resp = mref.comprar();
                                String infos[] = resp.split("-");
                                
                                if(Float.parseFloat(infos[0]) > 0){
                                    saldo = Float.parseFloat(infos[0]);
                                    codRastreio = infos[1];
                                    JOptionPane.showMessageDialog(null, "Compra realizada com sucesso! \n Código de Rastreamento: " + infos[1]);
                                } else JOptionPane.showMessageDialog(null, "Ocorreu um erro com a compra! :<");                                
                                break;                            
                        }
                       out.writeUTF("ok");

                    }
                    
                    //////////----CATCH
                    catch( MalformedURLException e ) {
                            System.out.println();
                            System.out.println( "MalformedURLException: " + e.toString() );
                    }
                    catch( RemoteException e ) {
                            System.out.println();
                            System.out.println( "RemoteException: " + e.toString() );
                    }
                    catch( NotBoundException e ) {
                            System.out.println();
                            System.out.println( "NotBoundException: " + e.toString() );
                    }
                    catch( Exception e ) {
                            System.out.println();
                            System.out.println( "Exception: " + e.toString() );
                    }
                    break;
            
           case "4" :
                String menuTransp = "";                    
                menuTransp += "------------TRASNPORTADORA LUVANNI ONLINE------------\n";
                menuTransp += "| Escolha o que deseja fazer:                        |\n";
                menuTransp += "| 1. Verificar Frete                                 |\n";
                menuTransp += "| 2. Verificar Localização                           |\n";
                menuTransp += " ---------------------------------------------------- ";
                    
                String opcTransp = JOptionPane.showInputDialog(menuTransp);
                    
                    try {
                        Transportadora mref = (Transportadora) Naming.lookup( "rmi://localhost:1099/TransportadoraService" );
                        mref.setValorAtual(saldo);
                        
                        switch(opcTransp) {
                            case "1" :
                                String cod = "";
                                if(codRastreio != "") {
                                    cod = JOptionPane.showInputDialog("Entre com o código de rastreamento: \n Cod. da última compra: " + codRastreio);
                                } else {
                                    cod = JOptionPane.showInputDialog("Entre com o código de rastreamento: ");
                                }
                                
                                mref.setCod(cod);
                                String resp = mref.fretar();
                                String infos[] = resp.split("-");
                                
                                if(Float.parseFloat(infos[0]) > 0){
                                    saldo = Float.parseFloat(infos[0]);
                                    JOptionPane.showMessageDialog(null,"O frete para esse produto será de R$" + infos[1]);
                                } else {
                                    JOptionPane.showMessageDialog(null,"Ocorreu um erro com a operação :<");
                                }                                
                                break;      

                            case "2" :
                                String codTransp = JOptionPane.showInputDialog("Entre com o código de rastreamento: ");
                                mref.setCod(codTransp);
                                
                                String respTransp = mref.localizar();
                                JOptionPane.showMessageDialog(null,"Status do transporte: " + respTransp);                                
                                break;                            
                        }  
                       out.writeUTF("ok");

                    }
                    
                    //////////----CATCH
                    catch( MalformedURLException e ) {
                            System.out.println();
                            System.out.println( "MalformedURLException: " + e.toString() );
                    }
                    catch( RemoteException e ) {
                            System.out.println();
                            System.out.println( "RemoteException: " + e.toString() );
                    }
                    catch( NotBoundException e ) {
                            System.out.println();
                            System.out.println( "NotBoundException: " + e.toString() );
                    }
                    catch( Exception e ) {
                            System.out.println();
                            System.out.println( "Exception: " + e.toString() );
                    }
                    break;               
        }
        
//        menu();
    }
}
