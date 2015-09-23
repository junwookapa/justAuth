package team.justtag.server.user.dao;

import java.util.List;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.user.model.UserGroup;

public interface UserGroupDao {
	public DBStatus createUserGroup(UserGroup user); // 유저 그룹 생성
	public DBStatus updateUserGroup(String _id, UserGroup user); // 유저 그룹 변경
	public DBStatus deleteUserGroup(String _id); // 유저 그룹 삭제
	
	public UserGroup getUserGroupByObjectID(String _id); // 오브젝트 아이디에 의한 유저 그룹 객체 검색
	public UserGroup getUserGroupByUserGroupName(String group_name); // 유저 그룹 이름에 의한 유저 그룹 객체 검색
	
	public String getObjIDByUserGroupName(String group_name); // 유저 그룹 이름에 의한 유저그룹 객체 아이디 검색
	
	public List<UserGroup> getAllUserGroups(); // 모든 유저 그룹 검색
	
	public DBStatus addUser(String group_name, String userObjid);
	public DBStatus deleteUser(String group_name, String userObjid);

}
