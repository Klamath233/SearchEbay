<%@ page import="edu.ucla.cs.cs144.ItemBean" %>
<%@ page import="edu.ucla.cs.cs144.ItemBean.BidBean" %>
<%@ page import="java.util.ArrayList" %>
<% ItemBean item = (ItemBean) request.getAttribute("item"); %>
<!DOCTYPE html>
<html>
  <head>
    <title>Ebay Item Search</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/general.css">
    <link rel="stylesheet" type="text/css" href="css/getItemResult.css">
    <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script>
      var geocoder;
      var map;

      function initialize() {
        geocoder = new google.maps.Geocoder();

        <%
          if (item.getLatitude() != null && item.getLongitude() != null) {
            out.println("var latlng = new google.maps.LatLng(" + item.getLatitude() + ", " + item.getLongitude() + ");");
            out.println("var zoomLevel = 14;");
          } else {
            out.println("var latlng = new google.maps.LatLng(41.850033, -87.6500523);");
            out.println("var zoomLevel = 3;");
          }
        %>
        
        var mapOptions = {
          zoom: zoomLevel,
          center: latlng
        }
        map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

        <%
          if (item.getLatitude() == null || item.getLongitude() == null) {
            out.println("codeAddress();");
          }
        %>
      }

      function codeAddress() {
        var address = "<%= item.getLocation() + "" + item.getCountry() %>";
        geocoder.geocode( { 'address': address}, function(results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });
          }
        });
      }

      google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
    <div class="wrapper">
      <a href="keywordSearch.html"><img src="img/logo.png"></a>
      <form method="GET" action="item">
        <input type="text" name="id">
        <input type="submit" value="Get Item!">
      </form>
      <div class="wrapper">
        <div class="col">
          <table>
            <tr>
              <td>ID</td>
              <td><%= item.getItemId() %></td>
            </tr>
            <tr>
              <td>Name</td>
              <td><%= item.getName() %></td>
            </tr>
            <%
              ArrayList<String> categoryArray = item.getCategories();
              for (int i = 0; i < categoryArray.size(); i++) {
                String category = categoryArray.get(i);
                %>
                  <tr>
                    <td>Category</td>
                    <td><%= category %></td> 
                  </tr>
                <%
              }
            %>
            <tr>
              <td>Current Bid</td>
              <td><%= item.getCurrently() %></td>
            </tr>
            <tr>
              <td>Buy Price</td>
              <td><%= item.getBuyPrice() %></td>
            </tr>
            <tr>
              <td>First Bid</td>
              <td><%= item.getFirstBid() %></td>
            </tr>
            <%
              ArrayList<BidBean> bidArray = item.getBids();
              for (int i = 0; i < bidArray.size(); i++) {
                BidBean bid = bidArray.get(i);
                %>
                  <tr>
                    <td>Bidder <%= i + 1 %></td>
                    <td></td> 
                  </tr>
                  <tr>
                    <td>Bidder ID</td>
                    <td><%= bid.getBidderId() %></td> 
                  </tr>
                  <tr>
                    <td>Bidder Rating</td>
                    <td><%= bid.getBidderRating() %></td> 
                  </tr>
                  <tr>
                    <td>Bidder Location</td>
                    <td><%= bid.getBidderLocation() %></td> 
                  </tr>
                  <tr>
                    <td>Bidder Country</td>
                    <td><%= bid.getBidderCountry() %></td> 
                  </tr>
                  <tr>
                    <td>Bid Time</td>
                    <td><%= bid.getBidTime() %></td> 
                  </tr>
                  <tr>
                    <td>Bid Amount</td>
                    <td><%= bid.getBidAmount() %></td> 
                  </tr>
                <%
              }
            %>
            <tr>
              <td>Seller</td>
              <td></td>
            </tr>
            <tr>
              <td>Seller ID</td>
              <td><%= item.getSellerId() %></td>
            </tr>
            <tr>
              <td>Seller Rating</td>
              <td><%= item.getSellerRating() %></td>
            </tr>
            <tr>
              <td>Location</td>
              <td><%= item.getLocation() %></td>
            </tr>
            <tr>
              <td>Country</td>
              <td><%= item.getCountry() %></td>
            </tr>
            <tr>
              <td>Started</td>
              <td><%= item.getStarted() %></td>
            </tr>
            <tr>
              <td>Ends</td>
              <td><%= item.getEnds() %></td>
            </tr>
            <tr>
              <td>Description</td>
              <td><%= item.getDescription() %></td>
            </tr>
          </table>
        </div>
        <div class="col">
          <div alt="Map" id="map-canvas"></div>
        </div>
      </div>
    </div>
  </body>
</html>
