package team.justauth.server.user.service;

import java.util.List;

import team.justauth.server.main.Status.UserStatus;
import team.justauth.server.user.model.User;
import team.justauth.server.user.model.UserInfo;

public interface UserService {
	// user method
	public UserStatus createUser(String body); // 회원가입
	public UserStatus updateUser(String body, String userID); // 유저 변경
	public UserStatus deleteUser(String userID); // 유저 삭제
	public UserInfo getUserInfoByUserID(String userID); // 유저 아이디로 유저 정보 찾기
	public List<UserInfo> findAllUsers(String token, String userID); // 모든 유저 찾기
	
	// login method
	public UserStatus login(String body); // 로그인 정보
	public UserStatus logout(String body); // 로그아웃
}