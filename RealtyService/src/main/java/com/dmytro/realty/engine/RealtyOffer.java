package com.dmytro.realty.engine;

/**
 * Created with IntelliJ IDEA. User: dmytro Date: 29.01.13 Time: 0:01 To change
 * this template use File | Settings | File Templates.
 */
public class RealtyOffer {
	private String link;
	private String price;
	private String offerContent;
	private String offender;

	private String phone;
    private boolean phoneLink;

	private String date;

	public RealtyOffer() {
	}

	public RealtyOffer(String link, String price, String offender,
			String phoneRef, String offerContent, String date) {
		this.link = link;
		this.price = price;
		this.offender = offender;
		this.offerContent = offerContent;
		this.phone = phoneRef;
		this.date = date;
	}

	/*
	 * public String getUrgent() { switch (type) { case
	 * RealtyConstants.APARTMENTS: if (offerContent.contains("Левобереж") ||
	 * offerContent.contains("Дарница") || offerContent.contains("Радуж"))
	 * return "URGENT"; break; case RealtyConstants.ROOMS: if
	 * (offerContent.contains("Левобереж") || offerContent.contains("Дарница")
	 * || offerContent.contains("Радуж")) return "URGENT"; break; default:
	 * return "not urgent"; } return "not urgent"; }
	 */

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOfferContent() {
		return offerContent;
	}

	public void setOfferContent(String offerContent) {
		this.offerContent = offerContent;
	}

	public String getOffender() {
		return offender;
	}

	public void setOffender(String offender) {
		this.offender = offender;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    public boolean isPhoneLink() {
        return phoneLink;
    }

    public void setPhoneLink(boolean phoneLink) {
        this.phoneLink = phoneLink;
    }

    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RealtyUnit{"
				+
				// getUrgent() +
				"\n\rlink='"
				+ link
				+ '\''
				+ "\n\r--------------------------------------------------------------------------"
				+ "\n\r, offerContent='"
				+ offerContent.replace(".", ".\n\r")
				+ '\''
				+ "\n\r, price='"
				+ price
				+ '\''
				+ "\n\r--------------------------------------------------------------------------"
				+ "\n\r, offender='" + offender + '\'' + '}';
	}
}
