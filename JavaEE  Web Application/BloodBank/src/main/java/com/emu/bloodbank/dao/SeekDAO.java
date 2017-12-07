package com.emu.bloodbank.dao;

import java.util.List;

import com.emu.bloodbank.model.Seek;

public interface SeekDAO {
	public void insertSeek(Seek seek);
	public void deleteSeek(int seekID);
	public void delSeek(String seekerEmail);
	public Seek getSeek(int seekID);
	public List<Seek> getSeeks(String seekerEmail);
	public List<Seek> getAllSeeks();
	public List<Seek> searchSeeker(String city, String bloodType);
}
