package team.justtag.server.item.service;

import java.util.List;

import team.justtag.server.item.model.ItemMeta;
import team.justtag.server.main.Status.UserStatus;

public interface ItemMetaService {
	//아이템 그룹
	public UserStatus createItemMeta(String body); // 아이템 메타 생성
	public UserStatus updateItemMeta(String body); // 아이템 메타 업데이트
	public UserStatus deleteItemMeta(String body); // 아이템 메타 삭제
	public List<ItemMeta> findAllItemMeta(); // 모든 아이템 메타 검색
	public ItemMeta findItemMetaByItemMetaID(String userGroupID); // 아이템 메타 아이디로 아이템 메타 찾기
	public ItemMeta findItemMetaByItemMetaName(String userGroupName); //아이템 메타 이름으로 아이템 메타 찾기

}
