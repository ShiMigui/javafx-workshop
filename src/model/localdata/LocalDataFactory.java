package model.localdata;

public class LocalDataFactory {
    private LocalDataFactory() {
    }

    private static SellerLocalData localSellers;
    private static DepartmentLocalData localDepartments;

    public static SellerLocalData getLocalSellers() {
	if (localSellers == null)
	    localSellers = new SellerLocalData();

	return localSellers;
    }

    public static DepartmentLocalData getLocalDepartments() {
	if (localDepartments == null)
	    localDepartments = new DepartmentLocalData();

	return localDepartments;
    }
}
