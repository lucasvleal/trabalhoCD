/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportadoraservidor;

import java.rmi.Naming;

/**
 *
 * @author lukin
 */
public class Servidor {
    public Servidor() {
		try {
			Transportadora ms = new TransportadoraImpl();
			Naming.rebind("rmi://localhost:1099/TransportadoraService", ms);
		}
		catch( Exception e ) {
			System.out.println( "Trouble: " + e );
		}
    }

    public static void main(String[] args) {
	new Servidor();
    }
}
