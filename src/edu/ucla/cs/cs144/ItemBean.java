package edu.ucla.cs.cs144;

import java.io.Serializable;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ItemBean implements Serializable {

	public class BidBean {
		protected String bidderId;
		protected int bidderRating;
		protected String bidderLocation;
		protected String bidderCountry;
		protected String bidTime;
		protected String bidAmount;
		
		public BidBean() {}
		
		public String getBidderId() {
			return bidderId;
		}
		public void setBidderId(String bidderId) {
			this.bidderId = bidderId;
		}
		public int getBidderRating() {
			return bidderRating;
		}
		public void setBidderRating(int bidderRating) {
			this.bidderRating = bidderRating;
		}
		public String getBidderLocation() {
			return bidderLocation;
		}
		public void setBidderLocation(String bidderLocation) {
			this.bidderLocation = bidderLocation;
		}
		public String getBidderCountry() {
			return bidderCountry;
		}
		public void setBidderCountry(String bidderCountry) {
			this.bidderCountry = bidderCountry;
		}
		public String getBidTime() {
			return bidTime;
		}
		public void setBidTime(String bidTime) {
			this.bidTime = bidTime;
		}
		public String getBidAmount() {
			return bidAmount;
		}
		public void setBidAmount(String bidAmount) {
			this.bidAmount = bidAmount;
		}
		
		
	}

	protected String itemId;
	protected String name;
	protected ArrayList<String> categories;
	protected String currently;
	protected String buyPrice;
	protected String firstBid;
	protected ArrayList<BidBean> bids;
	protected String location;
	protected String latitude;
	protected String longitude;
	protected String country;
	protected String started;
	protected String ends;
	protected String sellerId;
	protected String sellerRating;
	protected String description;

	public ItemBean() {}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

	public String getCurrently() {
		return currently;
	}

	public void setCurrently(String currently) {
		this.currently = currently;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getFirstBid() {
		return firstBid;
	}

	public void setFirstBid(String firstBid) {
		this.firstBid = firstBid;
	}

	public ArrayList<BidBean> getBids() {
		Collections.sort(bids, new Comparator<BidBean>() {
			public int compare(BidBean b1, BidBean b2) {
				Date d1;
				Date d2;
				try {
					d1 = new SimpleDateFormat("MMM-dd-yy HH:mm:ss").parse(b1.getBidTime());
				} catch (ParseException e) {
					return 1;
				}
				
				try {
					d2 = new SimpleDateFormat("MMM-dd-yy HH:mm:ss").parse(b2.getBidTime());
				} catch (ParseException e) {
					return -1;
				}
				
				return d2.compareTo(d1);
			}
		});
		return bids;
	}

	public void setBids(ArrayList<BidBean> bids) {
		this.bids = bids;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public String getEnds() {
		return ends;
	}

	public void setEnds(String ends) {
		this.ends = ends;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerRating() {
		return sellerRating;
	}

	public void setSellerRating(String sellerRating) {
		this.sellerRating = sellerRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
