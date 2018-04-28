package main;

public class Department {
	private int ID;
	private String department_name;
	private String cur_status;
	private String update_time;
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the department_name
	 */
	public String getDepartment_name() {
		return department_name;
	}
	/**
	 * @param department_name the department_name to set
	 */
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	/**
	 * @return the cur_status
	 */
	public String getCur_status() {
		return cur_status;
	}
	/**
	 * @param cur_status the cur_status to set
	 */
	public void setCur_status(String cur_status) {
		this.cur_status = cur_status;
	}
	/**
	 * @return the update_time
	 */
	public String getUpdate_time() {
		return update_time;
	}
	/**
	 * @param update_time the update_time to set
	 */
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
}
