package Test;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.j12lib.service.VoideoService;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		VoideoService voideoService= (VoideoService) ctx.getBean("VoideoService"); 
		 List<Map<String, Object>> list=voideoService.queryGenres(0, "AAAA");
		if(list.size()>0){
			 Map<String, Object> map=list.get(0);
			 System.out.println(map.get("id"));
		}else{
			System.out.println("没有查到值");
		}
	}

}
