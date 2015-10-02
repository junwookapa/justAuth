package team.justtag.server.user.dao;

import java.util.List;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.user.model.User;

public interface UserDao {
	
	public DBStatus createUser(User user); // 유저 생성
	public DBStatus updateUser(String _id, User user); // 유저 변경
	public DBStatus deleteUser(String _id); // 유저 삭제

	
	public User getUserByObjectID(String _id); // 오브젝트 아이디에 의한 유저 객체 검색
	public User getUserByUserID(String user_id); // 유저 아이디에 의한 유저 객체 검색

	public String getAES_KEY(String _id);
	public String getObjIDByUserID(String user_id); // 유저 아이디에 의한 유저 아이디 검색
	public DBStatus isUserExist(String user_id);
	
	public List<User> getAllUsers(); // 모든 유저 찾기
	public List<User> getUsersByUserGroupID(String user_group_id); // 유저 그룹에 의한 모든 유저 찾기
		
}
