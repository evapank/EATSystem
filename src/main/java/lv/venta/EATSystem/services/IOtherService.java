package lv.venta.EATSystem.services;

import java.util.ArrayList;

public interface IOtherService {
	
	public abstract Object[] selectAllSecurityRoles();
	
	public abstract Object[] selectAllGeneralStatuses();
	
	public abstract Object[] selectAllOrderStatuses();
	
	public abstract ArrayList<Object> selectInPersonAndOnlineStatuses();

}
