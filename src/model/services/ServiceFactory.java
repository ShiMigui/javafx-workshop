package model.services;

public class ServiceFactory {
    private static DepartmentService departments;
    private static SellerService sellers;

    private ServiceFactory() {
    }

    public static DepartmentService getDepartmentService() {
	if (departments == null)
	    departments = new DepartmentService();
	return departments;
    }

    public static SellerService getSellerService() {
	if (sellers == null)
	    sellers = new SellerService();
	return sellers;
    }
}
