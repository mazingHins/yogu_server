/**
 * 
 */
package com.mazing.commons.cache.aspect.expr.vo;

/**
 * <br>
 *
 * @author JFan 2015年8月21日 下午6:05:01
 */
public class PersonUser {

	private String name;

	private int age;

	private Boolean sex;

	private PersonUser user;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age 要设置的 age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return sex
	 */
	public Boolean getSex() {
		return sex;
	}

	/**
	 * @param sex 要设置的 sex
	 */
	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	/**
	 * @return user
	 */
	public PersonUser getUser() {
		return user;
	}

	/**
	 * @param user 要设置的 user
	 */
	public void setUser(PersonUser user) {
		this.user = user;
	}

}
