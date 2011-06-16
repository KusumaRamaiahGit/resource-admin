
package utils;

import java.util.Iterator;
import java.util.List;

import model.Resource;
 

public class Main {
    public static void main(String[] args) {
    	List<Resource> res = ResourceDAO.getAllResources();
    	Iterator<Resource> it = res.iterator();
    	System.out.println(it.next().getClass().getName());
  //DatabaseUtil.FillDatabase();
    }

}
