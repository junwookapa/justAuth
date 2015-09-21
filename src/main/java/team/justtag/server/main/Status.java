package team.justtag.server.main;

public class Status {
	public enum TokenStatus {
		notFoundToken, //토큰 찾을 수 없음
		notFoundUser, // 사용자를 찾을 수 없음
		tokenExpired, // 토큰이 만료됨
		tokenUpdateFail, // 토큰 업데이트 실패
		tokenExpiringsoon, // 토큰이 곧 만료 됩니다.
		unknownError,  // 알려지지 않은 에러
		success // 성공하였습니다.
	}
	public enum UserStatus {
		duplicateID, // 중복된 아이디
		notFoundID, // 아이디를 찾을 수 없습니다.
		wrongPassword, // 잘못된 패스워드
		signFail, // 가입 실패
		loginFail, // 로그인 실패
		unkwonError, // 알려지지 않은 에러
		success; //성송
	}
	
	private String status;
	public Status(TokenStatus status){
		this.status = status.toString();
	}
	public Status(UserStatus status){
		this.status = status.toString();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}