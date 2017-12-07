package com.emu.bloodbank.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emu.bloodbank.dao.SeekDAO;
import com.emu.bloodbank.model.Seek;

@Service @Transactional
public class SeekServiceImpl implements SeekService{
	
	@Autowired
	private SeekDAO seekDAO;

	@Override @Transactional
	public void insertSeek(Seek seek) {
		this.seekDAO.insertSeek(seek);
	}

	@Override @Transactional
	public void deleteSeek(int seekID) {
		this.seekDAO.deleteSeek(seekID);
	}

	@Override @Transactional
	public Seek getSeek(int seekID) {
		return this.seekDAO.getSeek(seekID);
	}

	@Override @Transactional
	public List<Seek> getSeeks(String seekerEmail) {
		return this.seekDAO.getSeeks(seekerEmail);
	}

	@Override @Transactional
	public List<Seek> getAllSeeks() {
		return this.seekDAO.getAllSeeks();
	}

	@Override @Transactional
	public List<Seek> searchSeeker(String city, String bloodType) {
		return this.seekDAO.searchSeeker(city, bloodType);
	}

	@Override @Transactional
	public void delSeek(String seekerEmail) {
		this.seekDAO.delSeek(seekerEmail);
	}
}
