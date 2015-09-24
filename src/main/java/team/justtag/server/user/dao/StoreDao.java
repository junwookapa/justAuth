package team.justtag.server.user.dao;

import java.util.List;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.user.model.Store;

public interface StoreDao {
	public DBStatus createStore(Store store); // 스토어 생성
	public DBStatus updateStore(String _id, Store store); // 스토어 업데이트
	public DBStatus deleteUser(String _id); // 스토어 삭제
	
	public Store getStoreByObjectID(String _id); // 오브젝트 아이디에 의한 스토어 객체 검색	
	public String getObjIDBystoreName(String store_name); // 스토어 이름에 의한 스토어 검색
	
	public List<Store> getAllStores(); // 모든 유저 찾기		
}
