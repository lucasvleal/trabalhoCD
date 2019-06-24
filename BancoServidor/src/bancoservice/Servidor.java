/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoservice;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author lukin
 */
public class Servidor {
    public Servidor() {
		try {
			Banco ms = new BancoImpl();
                        Registry reg = LocateRegistry.getRegistry("localhost",1099);
			reg.bind("BancoService", ms);                       
		}
		catch( Exception e ) {
			System.out.println( "Trouble: " + e );
		}
	}

	public static void main(String[] args) {
		new Servidor();
	}
}
