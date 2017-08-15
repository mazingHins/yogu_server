package com.yogu.services.user.business.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户收货地址
 * 
 * <pre>
 *     自动生成代码: 表名 mz_user_address, 日期: 2015-08-24
 *     address_id <PK>        bigint(20)
 *     uid              bigint(20)
 *     country_code     varchar(16)
 *     province_code    varchar(16)
 *     city_code        varchar(16)
 *     district_code    varchar(16)
 *     lat              double(22,31)
 *     lng              double(22,31)
 *     name             varchar(40)
 *     full_address        varchar(128)
 *     detail           varchar(40)
 *     contacts         varchar(24)
 *     phone            varchar(24)
 *     remark           varchar(40)
 *     status           tinyint(4)
 *     create_time      datetime(19)
 * </pre>
 */
public class UserAddressPO implements Serializable {

	private static final long serialVersionUID = -3074457345243033334L;

	/** 地址id */
	private long addressId;

	/** 用户id */
	private long uid;

	/** 国家地区编码，如中国+86 */
	private String countryCode;

	/** 省、州code */
	private String provinceCode;

	/** 城市code */
	private String cityCode;

	/** 地区code */
	private String districtCode;

	/** 纬度 */
	private double lat;

	/** 经度 */
	private double lng;

	/** 送餐地址 */
	private String name;
	
	/** 送餐地址全称（直接保存app的传值） */
	private String fullAddress = "";

	/** 地址详情（某栋或门牌号） */
	private String detail;

	/** 收货人名称 */
	private String contacts;

	/** 联系电话 */
	private String phone;

	/** 备注 */
	private String remark;

	/** 状态：1-默认地址；2-非默认 */
	private short status = 2;

	/** 创建时间 */
	private Date createTime;

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLat() {
		return lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLng() {
		return lng;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetail() {
		return detail;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContacts() {
		return contacts;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
