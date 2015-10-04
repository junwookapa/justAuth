package team.justauth.server.board.service;

import java.util.List;

import team.justauth.server.board.model.Board;

public interface BoardService {
	public List<Board> findAll();
	public void createNewContents(String body, String toke);
	public Board find(String id);
	public Board update(String boardID, String body) ;
}