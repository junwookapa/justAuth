package team.justtag.server.user.dao;

import java.util.List;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.user.model.UserGroup;

public interface UserGroupDao {
	public DBStatus createUserGroup(UserGroup userGroup); // 유저 그룹 생성
	public DBStatus updateUserGroup(UserGroup userGroup); // 유저 그룹 변경
	public DBStatus deleteUserGroup(String _id); // 유저 그룹 삭제
	public UserGroup findUserbyObjectID(String _id); // 오브젝트 아이디에 의한 유저 그룹 검색
	public UserGroup findUserbyUserGroupName(String user_name); // 유저 그룹 이름에 의한 유저 그룹 검색
	public List<UserGroup> findAllUserGroups(); // 모든 유저 찾기


}
