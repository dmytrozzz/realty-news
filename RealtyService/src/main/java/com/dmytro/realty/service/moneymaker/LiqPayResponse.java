package com.dmytro.realty.service.moneymaker;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created with IntelliJ IDEA. User: dmytro Date: 31.05.13 Time: 21:36 To change
 * this template use File | Settings | File Templates.
 */
@Root
public class LiqPayResponse {
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String WAIT_SECURE = "wait_secure";
	
	@Element(name = "version")
	private String version;
	
	@Element(name = "merchant_id")
	private String merchant;
	
	@Element(name = "amount")
	private String amount;
	
	@Element(name = "currency")
	private String currency;
	
	@Element(name = "description")
	private String description;
	
	@Element(name = "pay_details", required=false)
	private String details;
	
	@Element(name = "action", required=false)
	private String action;
	
	@Element(name = "order_id")
	private String billyId;

	@Element(name = "status")
	private String status;

	@Element(name = "code", required=false)
	private String errorCode;

	@Element(name = "transaction_id")
	private String transactionId;

	@Element(name = "pay_way")
	private String payWay;
	
	@Element(name = "sender_phone")
	private String senderPhone;
	
	@Element(name = "goods_id", required=false)
	private String goodsId;
	
	@Element(name = "pays_count", required=false)
	private String paysCount;

	public String getBillyId() {
		return billyId;
	}

	public void setBillyId(String billyId) {
		this.billyId = billyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	
	
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGoodsId() {
		return goodsId;
	}	
	

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getPaysCount() {
		return paysCount;
	}

	public void setPaysCount(String paysCount) {
		this.paysCount = paysCount;
	}

	public boolean isSuccess(){
		return status.equals(SUCCESS);
	}
	
	public boolean isWaiting(){
		return status.equals(WAIT_SECURE);
	}
	
	public boolean isFailure(){
		return status.equals(FAILURE);
	}
}
