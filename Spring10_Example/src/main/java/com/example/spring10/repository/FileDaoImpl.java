package com.example.spring10.repository;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao{

	@Autowired private SqlSession session;
	
	@Override
	public List<FileDto> getList() {
		return session.selectList("file.getFileList");
	}

	

	@Override
	public int upload(FileDto dto) {
		// TODO Auto-generated method stub
		return session.insert("file.upload", dto);
	}

	@Override
	public int delete(long num) {
		// TODO Auto-generated method stub
		return session.delete("file.delete", num);
	}

	@Override
	public FileDto getData(long num) {
		// TODO Auto-generated method stub
		return session.selectOne("file.getData", num);
	}

	@Override
	public long getSequence() {
		// TODO Auto-generated method stub
		return session.selectOne("file.getSequence");
	}

}
