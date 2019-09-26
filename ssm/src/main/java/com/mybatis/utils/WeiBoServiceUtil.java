package com.mybatis.utils;

import java.util.List;

import weibo4j.Friendships;
import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserCounts;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class WeiBoServiceUtil {
	
	/**
	* 
	* @Description: 授权获取相关信息
	* @param:code
	* @throws WeiboException 
	* @return：AccessToken
	*/
	public static AccessToken getAccessTokenByCode(String code) throws WeiboException {
			if(checkStrIsNull(code)) return null;
			AccessToken token = new Oauth().getAccessTokenByCode(code);
			if(token == null) return null;
			return token;
	}
	
	/**
	 * 
	* @Description: 获取用户的基本信息
	*
	* @param:token
	* @return：User
	* @throws：WeiboException
	 */
	public static User getUsersByAccessToken(AccessToken token) throws WeiboException {
		if(token == null) return null;
		String accessToken = token.getAccessToken();
		if(checkStrIsNull(accessToken)) return null;
		String uid = token.getUid();
		if(checkStrIsNull(uid)) return null;
		User u = new Users(accessToken).showUserById(uid);
		return u;
	}
	
	/**
	 * 
	* @Description: 获取关注者的用户数据
	*
	* @param:access_token  uid
	* @return：UserWapper
	* @throws：WeiboException
	 */
	public static UserWapper getFollowersListByUid(String access_token ,String uid) throws WeiboException {
		if(checkStrIsNull(access_token) || checkStrIsNull(uid)) return null;
		UserWapper userWapper = new Friendships(access_token).getFollowersById(uid);
		if(userWapper == null) return null;
		return userWapper;
	}
	
	/**
	 * 
	* @Description: 获取关注者的uid列表
	*
	* @param:access_token  uid
	* @return：String[]
	* @throws：WeiboException
	*
	 */
	public static String[] getFollowersIdsListByUid(String access_token ,String uid) throws WeiboException {
		if(checkStrIsNull(access_token) || checkStrIsNull(uid)) return null;
		String[] friendsIdsList = new Friendships(access_token).getFriendsIdsByUid(uid);
		if(friendsIdsList == null) return null;
		return friendsIdsList;
	}
	
	/**
	 * 
	* @Description: 批量获取用户的粉丝数、关注数、微博数
	*
	* @param:access_token  uids(用户uid可以多个，其中用逗号隔开)
	* @return：List<UserCounts>
	* @throws：WeiboException
	 */
	public static List<UserCounts> getUsersCount(String access_token,String uids) throws WeiboException{
		if(checkStrIsNull(access_token) || checkStrIsNull(uids)) return null;
		List<UserCounts> userCount = new Users(access_token).getUserCount(uids);
		if(userCount == null) return null;
		return userCount;
	}
	
	/**
	 * 
	* @Description: 获取某个用户最新发表的微博列表
	*
	* @param:access_token
	* @return：StatusWapper
	* @throws：WeiboException
	*
	 */
	public static StatusWapper getUserTimeline(String access_token) throws WeiboException {
		if(checkStrIsNull(access_token)) return null;
		StatusWapper statusWapper = new Timeline(access_token).getUserTimeline();
		if(statusWapper == null) return null;
		return statusWapper;
	}
	
	/**
	 * 
	* @Description: 根据微博ID获取单条微博内容
	*
	* @param: access_token stausId
	* @return：Status
	* @throws：WeiboException
	 */
	public static Status showStatusById(String access_token,String stausId) throws WeiboException {
		if(checkStrIsNull(access_token)|| checkStrIsNull(stausId)) return null;
		Status showStatus = new Timeline(access_token).showStatus(stausId);
		if(showStatus == null) return null;
		return showStatus;
	}
	
	
	private static boolean checkStrIsNull(String str) {
		if(str == null || str.trim().length() == 0) 
			return true;
		else
			return false;
	}
}
