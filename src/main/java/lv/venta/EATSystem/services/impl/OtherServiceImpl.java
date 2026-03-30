package lv.venta.EATSystem.services.impl;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.services.IOtherService;
@Service
public class OtherServiceImpl implements IOtherService{

	@Override
	public Object[] selectAllSecurityRoles() {
		Object[] result = Arrays.stream(SecurityRole.values())
												.map(Enum::name)
												.collect(Collectors.toList()).toArray();
		return result;
	}

	@Override
	public Object[] selectAllGeneralStatuses() {
		Object[] result = Arrays.stream(GeneralStatus.values())
								.map(Enum::name)
								.collect(Collectors.toList()).toArray();
		return result;
	}

	@Override
	public Object[] selectAllOrderStatuses() {
		Object[] result = Arrays.stream(OrderStatus.values())
								.map(Enum::name)
								.collect(Collectors.toList()).toArray();
		return result;
	}

}
