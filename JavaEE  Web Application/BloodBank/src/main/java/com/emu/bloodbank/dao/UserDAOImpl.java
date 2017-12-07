package com.emu.bloodbank.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emu.bloodbank.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertUser(User user) {
		this.sessionFactory.getCurrentSession().persist(user);
	}

	@Override
	public void deleteUser(String email) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class, email);
		
		if(user != null)
			this.sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public void updateUser(User user) {
		this.sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public User getUser(String email) {
		return (User) this.sessionFactory.getCurrentSession().get(User.class, email);
	}

	@Override @SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return this.sessionFactory.getCurrentSession().createQuery("From User").list();
	}

	@Override
	public User checkLogin(String email, String password) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);

		Criterion userEmail = Restrictions.eq("userEmail", email);
		Criterion userPassword = Restrictions.eq("userPassword", password);

		// To get records matching with AND conditions
		LogicalExpression andExp = Restrictions.and(userEmail, userPassword);
		criteria.add(andExp);

		User user;
		
		if(criteria.list().isEmpty())
			user = null;
		else
			user =  (User) criteria.list().get(0);
				
		return user;
	}

	@Override @SuppressWarnings("unchecked")
	public List<User> findDonors() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
		
		Criterion isDonor = Restrictions.eq("donor", "Yes"); //fetch people only available for donation
		
		criteria.add(isDonor);
		
		List<User> donorList;
		
		if(criteria.list().isEmpty())
			donorList = null;
		else
			donorList = (List<User>) criteria.list();
		
		return donorList;
	}

	@Override @SuppressWarnings("unchecked")
	public List<User> searchDonor(String city, String bloodType) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(User.class);
				
		Criterion donorCity = Restrictions.eq("city", city);
		Criterion donorBloodType = Restrictions.eq("bloodType", bloodType);
		
		LogicalExpression exAnd = Restrictions.and(donorCity, donorBloodType);		
		criteria.add(exAnd);

		return criteria.list();
	}

	@Override
	public void changeUserStatus(String email, String donor, String password) {
		String hql = "UPDATE User set donor = :_donor WHERE userEmail = :_email AND userPassword = :_pass";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("_donor", donor);
		query.setParameter("_email", email);
		query.setParameter("_pass", password);
	
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);	
		
	}

	@Override
	public void updateUser(String email, String password, String city, String newPassword) {
		String hql = "UPDATE User set userPassword=:_password, city=:_city WHERE userEmail=:_mail AND userPassword=:_pass";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		
		query.setParameter("_mail", email);
		query.setParameter("_pass", password);
		query.setParameter("_city", city);
		query.setParameter("_password", newPassword);
		
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);	
		
	}
}
