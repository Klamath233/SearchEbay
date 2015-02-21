package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    private static ItemBean parseXMLString(String xml) {
    	
    	Document doc = null;
    	
    	try {
    		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	Element item = doc.getDocumentElement(); // the root element.
    	String itemId = item.getAttribute("ItemID");
    	NodeList name = item.getElementsByTagName("Name");
    	NodeList categories = item.getElementsByTagName("Categories");
    	NodeList currently = item.getElementsByTagName("Currently");
    	NodeList buyPriceOptional = item.getElementsByTagName("Buy_Price");
    	NodeList firstBid = item.getElementsByTagName("First_Bid");
    	NodeList bids = item.getElementsByTagName("Bids");
    	NodeList location = item.getElementsByTagName("Location");
    	if (location.item(0).getAttributes().getNamedItem("Latitude") != null) {
    		String latitudeOptional = location.item(0).getAttributes().getNamedItem("Latitude").getNodeValue();
    	}
    	
    	if (location.item(0).getAttributes().getNamedItem("Longitude") != null) {
    		String longitudeOptional = location.item(0).getAttributes().getNamedItem("Longitude").getNodeValue();
    	}
    	
    	NodeList country = item.getElementsByTagName("Country");
    	NodeList started = item.getElementsByTagName("Started");
    	NodeList ends = item.getElementsByTagName("Ends");
    	NodeList seller = item.getElementsByTagName("Seller");
    	String sellerId = seller.item(0).getAttributes().getNamedItem("UserID").getNodeValue();
    	String sellerRating = seller.item(0).getAttributes().getNamedItem("Rating").getNodeValue();
    	NodeList description = item.getElementsByTagName("Description");
    	
    	ItemBean itemBean = new ItemBean();
    	
    	ArrayList<String> categoryList = new ArrayList<String>();
    	for (int i = 0; i < categories.getLength(); i++) {
    		categoryList.add(categories.item(i).getTextContent());
    	}
    	itemBean.setCategories(categoryList);
    	
    	ArrayList<ItemBean.BidBean> bidList = new ArrayList<ItemBean.BidBean>();
    	for (int i = 0; i < bids.getLength(); i++) {
    		if (bids.item(i).getClass() == Element.class) {
	    		Element currentBid = (Element) bids.item(i);
	    		ItemBean.BidBean bidBean = itemBean.new BidBean();
	    		
	    		bidBean.setBidAmount(currentBid.getElementsByTagName("Amount").item(0).getTextContent());
	    		bidBean.setBidTime(currentBid.getElementsByTagName("Time").item(0).getTextContent());
	    		
	    		NodeList bidder = currentBid.getElementsByTagName("Bidder");
	    		bidBean.setBidderId(bidder.item(0).getAttributes().getNamedItem("UserID").getNodeValue());
	    		bidBean.setBidderRating(Integer.parseInt(bidder.item(0).getAttributes().getNamedItem("Rating").getNodeValue()));
	    		
	    		NodeList bidderLocation = ((Element) bidder.item(0)).getElementsByTagName("Location");
	    		if (bidderLocation.getLength() == 0) {
	    			bidBean.setBidderLocation("");
	    		}
	    		
	    		NodeList bidderCountry = ((Element) bidder.item(0)).getElementsByTagName("Country");
	    		if (bidderCountry.getLength() == 0) {
	    			bidBean.setBidderCountry("");
	    		}
	    		
	    		bidList.add(bidBean);
    		}
    	}
    	
    	itemBean.setItemId(itemId);
    	itemBean.setName(name.item(0).getTextContent());
    	itemBean.setCurrently(currently.item(0).getTextContent());
    	itemBean.setFirstBid(firstBid.item(0).getTextContent());
    	itemBean.setLocation(location.item(0).getTextContent());
    	itemBean.setCountry(country.item(0).getTextContent());
    	itemBean.setStarted(started.item(0).getTextContent());
    	itemBean.setEnds(ends.item(0).getTextContent());
    	itemBean.setDescription(description.item(0).getTextContent());
    	
    	if (buyPriceOptional.getLength() == 0) {
    		itemBean.setBuyPrice("");
    	} else {
    		itemBean.setBuyPrice(buyPriceOptional.item(0).getTextContent());
    	}
    	
    	return itemBean;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /** In this method, we first extract the item id from the request. Then, we 
         *  use AuctionSearchClient to get the xml data of the item from the axis service
         *  . After that, we parse the xml to the JavaBean class ItemBean. Finally, we
         *  forward the ItemBean to the JSP file.
         */

        String itemId = request.getParameter("id");

        String xml = AuctionSearchClient.getXMLDataForItemId(itemId);
        ItemBean itemBean = parseXMLString(xml);
        
        request.setAttribute("item", itemBean);
        request.getRequestDispatcher("/getItemResult.jsp").forward(request, response);
        
    }
    
}
