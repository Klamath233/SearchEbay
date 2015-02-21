<%@ page import="edu.ucla.cs.cs144.SearchResult" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Ebay Keyword Search</title>
    <meta charset="utf-8">
  </head>
  <body>
    <div class="wrapper">
      <img src="img/logo.png">
      <form method="GET" action="search">
        <input type="text" name="q">
        <input type="hidden" name="numResultsToSkip" value="0">
        <input type="hidden" name="numResultsToReturn" value="20">
        <input type="submit" value="Search!">
      </form>
      <table>
        <%
          SearchResult[] srArray = (SearchResult[]) request.getAttribute("basicSearchResult");
          for (int i = 0; i < srArray.length; i++) {
            SearchResult sr = srArray[i];
            %>
              <tr>
                <td><a href="item?id=<%= sr.getItemId() %>"><%= sr.getItemId() %></td>
                <td><%= sr.getName() %></td>
              </tr>
            <%
          }
        %>
      </table>
    </div>
  </body>
</html>
