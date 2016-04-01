package edu.nju.git.type;

/**
 * Type of a user, including <b>USER</b> and <b>ORGANIZATION</b>
 * @author cuihao
 * @date 2016-03-01 23:06:14
 */
public enum OwnerType {
   USER,ORIGANIZATION;
	
   public static OwnerType getInstance(String string){
	   if(string.toUpperCase().equals("USER")){
		   return OwnerType.USER;
	   }else{
		   return OwnerType.ORIGANIZATION;
	   }
   }
}
