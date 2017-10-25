package com.benqzl.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm{

	/* (非 Javadoc) 
	* <p>Title: doGetAuthorizationInfo</p> 
	* <p>Description: 授权操作</p> 
	* @param principals
	* @return 
	* @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection) 
	*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc) 
	* <p>Title: doGetAuthenticationInfo</p> 
	* <p>Description: 身份验证操作</p> 
	* @param token
	* @return
	* @throws AuthenticationException 
	* @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken) 
	*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
