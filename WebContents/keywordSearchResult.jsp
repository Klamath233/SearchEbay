<%@ page import="edu.ucla.cs.cs144.SearchResult" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Ebay Keyword Search</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/general.css">
    <link rel="stylesheet" type="text/css" href="css/autosuggest.css">
    <script type="text/javascript" src="js/autosuggest2.js"></script>
    <script type="text/javascript" src="js/suggest.js"></script>
    <script type="text/javascript">
      window.onload = function () {
        var oTextbox = new AutoSuggestControl(document.getElementById("txt1"), new StateSuggestions());        
      }
    </script>
  </head>
  <body>
    <div class="wrapper">
      <div class="col">
        <img src="img/logo.png">
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
       <a href="search?q=<%= request.getParameter("q") %>&amp;numResultsToSkip=<%= Integer.parseInt(request.getParameter("numResultsToSkip")) == 0 ? 0 : Integer.parseInt(request.getParameter("numResultsToSkip")) - 20 %>&amp;numResultsToReturn=<%= request.getParameter("numResultsToReturn") %>">Previous</a>
       <a href="search?q=<%= request.getParameter("q") %>&amp;numResultsToSkip=<%= srArray.length != 20 ? Integer.parseInt(request.getParameter("numResultsToSkip")) : Integer.parseInt(request.getParameter("numResultsToSkip")) + 20 %>&amp;numResultsToReturn=<%= request.getParameter("numResultsToReturn") %>">Next</a>
       <form method="GET" action="search">
          <input id="txt1" type="text" name="q">
          <input type="hidden" name="numResultsToSkip" value="0">
          <input type="hidden" name="numResultsToReturn" value="20">
          <input type="submit" value="Search!">
        </form>
      </div>
    </div>
  </body>
</html>
