package lv.venta.EATSystem.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lv.venta.EATSystem.models.MyUser;

public class MyUserDetails implements UserDetails{
	
	private final MyUser user;
	
	public MyUserDetails(MyUser user) {
			this.user = user;
		}

	public MyUser getMyUser() {
			return user;
		}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(user.getAuthority().getTitle().toString()));

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
