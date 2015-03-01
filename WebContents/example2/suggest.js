if (window.XMLHttpRequest) {
	var xmlHttp = new XMLHttpRequest();
} else if (window.ActiveXObject) {
    var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
} else {
    alert("Error!");
}

function StateSuggestions() {
}

StateSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/,
                                                          bTypeAhead /*:boolean*/) {
	var sTextboxValue = oAutoSuggestControl.textbox.value;
    var request = "../suggest?q=" + encodeURI(sTextboxValue);

    xmlHttp.open("GET", request);
    xmlHttp.onreadystatechange = this.showSuggestion(oAutoSuggestControl, bTypeAhead);
    xmlHttp.send(null);
};

StateSuggestions.prototype.showSuggestion = function (oAutoSuggestControl, bTypeAhead) {
    return function() {
        if (xmlHttp.readyState == 4){
            var suggestions = xmlHttp.responseXML.getElementsByTagName('CompleteSuggestion');
            var aSuggestions = new Array();
    
            var htmlCode = "";
            for(i = 0; i < suggestions.length; i++) {
                var text = suggestions[i].childNodes[0].getAttribute("data");
                htmlCode += text;
                aSuggestions.push(text);
            }
            
            oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
        }
    }
};