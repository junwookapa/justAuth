package team.justtag.server.user.service;

import java.util.List;

import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.user.model.User;

public interface UserService {
	// user method
	public UserStatus createUser(String body); // 유저 생성
	public UserStatus updateUser(String body); // 유저 변경
	public UserStatus deleteUser(String body); // 유저 삭제
	public User findUserbyUserID(String userID); // 유저 아이디로 유저 정보 찾기
	public List<User> findAllUsers(); // 모든 유저 찾기
	public List<User> findAllUsersByUserGroup(String userGroupID); // 유저 그룹에 의한 모든 유저 찾기
	
	// login method
	public UserStatus login(String body); // 로그인 정보
}
