package application;

import java.util.List;

import dao.SellerDao;
import entities.BeanSeller;
import entities.Seller;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   BeanSeller dao = new BeanSeller();
	   
	     //dao.apagar(2);
	    
	    List<BeanSeller> lista = dao.listarTodos();
	    for(BeanSeller l:lista) {
	    	System.out.println(l.getName() + "  " +l.getEmail() + "  " +l.getSalario());
	    }
	    
	    
	   
		
			
	}

}
