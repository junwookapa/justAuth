package team.justauth.server.main;

public class Status {
	
	public enum OSType{
		linux,
		windows
	}
	
	public enum TokenStatus {
		notFoundToken, // 토큰을 찾을 수 없다.
		tokenExpired, // 토큰이 만료됨
		tokenUpdateFail, // 토큰 업데이트 실패
		tokenExpiringsoon, // 토큰이 곧 만료 됩니다.
		unknownError,  // 알려지지 않은 에러
		success // 성공하였습니다.
	}
	public enum UserStatus {
		wrongPassword, // 잘못된 비밀번호
		duplicatedID, // 중복된 아이디
		signFail, // 가입 실패
		loginFail, // 로그인 실패
		unkwonError, // 알려지지 않은 에러
		success //성공
	}
	public enum DBStatus{
		insertFail, // 생성 실패
		updateFail, // 수정 실패
		deleteFail, // 삭제 실패
		readFail, // 읽기 실패
		duplicated, // 중복 됨
		noproblem, //문제 없음
		success // 성공
	}

	public enum Role{
		admin, // 운영자
		enduser // 일반유저
	}
	

}