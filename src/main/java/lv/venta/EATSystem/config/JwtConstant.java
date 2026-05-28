package lv.venta.EATSystem.config;

public class JwtConstant {
	private static final String SECRET_KEY = "wpembytrwcvnryxksdbqwjebruyGHyudqgwveytrtrCSnwifoesarjbwe";
    private static final String JWT_HEADER = "Authorization";
    
    public static String returnKey() {
    	return SECRET_KEY;
    }
    
    public static String returnJWTheader() {
    	return JWT_HEADER;
    }

}
