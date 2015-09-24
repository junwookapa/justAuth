package team.justtag.server.user.dao;

import java.util.List;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.user.model.User;

public interface StoreDao {
	public DBStatus createStore(User user); // 유저 생성
	public DBStatus updateStore(String _id, User user); // 유저 변경
	public DBStatus deleteUser(String _id); // 유저 삭제
	
	public User getUserByObjectID(String _id); // 오브젝트 아이디에 의한 유저 객체 검색
	public User getUserByUserID(String user_id); // 유저 아이디에 의한 유저 객체 검색
	
	public String getObjIDByUserID(String user_id); // 유저 아이디에 의한 유저 아이디 검색
	
	public List<User> getAllUsers(); // 모든 유저 찾기
	public List<User> getUsersByUserGroupID(String group_id); // 유저 그룹에 의한 모든 유저 찾기
		
}