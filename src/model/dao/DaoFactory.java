package model.dao;

import dao.SellerDao;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDao();
	}
}
