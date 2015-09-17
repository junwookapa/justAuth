package team.justtag.auth.user.service;

import java.util.List;

import team.justtag.auth.user.vo.User;
import team.justtag.server.main.Status.UserStatus;

public interface UserService {
	public UserStatus createUser(String body); // 유저 생성
	public User findbyUserID(String userID); // 유저 아이디로 유저 정보 찾기
	public List<User> findAll(); // 유저 검색
	public UserStatus deleteUser(String body); // 유저 삭제
	public UserStatus login(String body); // 로그인 정보
}
