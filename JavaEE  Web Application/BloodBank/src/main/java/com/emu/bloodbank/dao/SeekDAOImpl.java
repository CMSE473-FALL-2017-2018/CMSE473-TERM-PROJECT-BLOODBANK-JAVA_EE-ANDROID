package com.emu.bloodbank.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emu.bloodbank.model.Seek;

@Repository
public class SeekDAOImpl implements SeekDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertSeek(Seek seek) {
		this.sessionFactory.getCurrentSession().persist(seek);
	}

	@Override
	public void deleteSeek(int seekID) {
		Seek seek = (Seek) sessionFactory.getCurrentSession().get(Seek.class, seekID);
		
		if(seek != null)
			this.sessionFactory.getCurrentSession().delete(seek);
	}

	@Override
	public Seek getSeek(int seekID) {
		return (Seek) this.sessionFactory.getCurrentSession().get(Seek.class, seekID);
	}

	@Override @SuppressWarnings("unchecked")
	public List<Seek> getSeeks(String seekerEmail) {
		return this.sessionFactory.getCurrentSession().createQuery("From Seek Where seekerEmail='" + seekerEmail +"'").list();
	}

	@Override @SuppressWarnings("unchecked")
	public List<Seek> getAllSeeks() {
		return this.sessionFactory.getCurrentSession().createQuery("From Seek").list();
	}

	@Override @SuppressWarnings("unchecked")
	public List<Seek> searchSeeker(String city, String bloodType) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Seek.class);
		
		Criterion seekerCity = Restrictions.eq("city", city);
		Criterion seekerBloodType = Restrictions.eq("bloodType", bloodType);
		
		LogicalExpression exAnd = Restrictions.and(seekerCity, seekerBloodType);		
		criteria.add(exAnd);

		return criteria.list();
	}

	@Override
	public void delSeek(String seekerEmail) {
		List<Seek> list = getSeeks(seekerEmail);
		
		if(!list.isEmpty()) {
			for(Seek s: list)
				deleteSeek(s.getSeekID());
		}
	}
}
