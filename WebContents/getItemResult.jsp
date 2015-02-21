<%@ page import="edu.ucla.cs.cs144.ItemBean" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Ebay Item Search</title>
    <meta charset="utf-8">
  </head>
  <body>
    <div class="wrapper">
      <img src="img/logo.png">
      <form method="GET" action="item">
        <input type="text" name="id">
        <input type="submit" value="Get!">
        <div>
          <%= ((ItemBean) request.getAttribute("item")).getDescription() %>
        </div>
      </form>
    </div>
  </body>
</html>
