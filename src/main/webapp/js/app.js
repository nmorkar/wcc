var wsUrl;
if (window.location.protocol == 'http:') {
  	//wsUrl = 'ws://' + window.location.host + ':8000/websocket-reverse-echo-example/echo';
	wsUrl = 'ws://' + window.location.host + ':8000/cric';
	//wsUrl = 'ws://localhost:8080/wildfly8/cric';
} else {
  	//wsUrl = 'wss://' + window.location.host + ':8443/websocket-reverse-echo-example/echo';
  	wsUrl = 'wss://' + window.location.host + ':8443/cric';
}
console.log('WebSockets Url : ' + wsUrl);
var ws = new WebSocket(wsUrl);

ws.onopen = function(event){
	console.log('WebSocket connection started');
};

ws.onclose = function(event){
	 console.log("Remote host closed or refused WebSocket connection");
     console.log(event);
};

ws.onmessage = function(event){
    console.log(event.data);
    var jdata = $.parseJSON(event.data);
   if(jdata == null){
	   $( "#mainCric" ).append("Moderater has not started the session.");
   }
    	console.log(jdata.userName);
    	console.log(jdata.playerCount);
    		pcount = jdata.playerCount;
    		 $( "#mainCricRes" ).empty();
              $.each(jdata.players, function(i,players){
            	  content ="";
            	 // console.log("Done " + i);
                content = '<p>' + players.name + ' : ';
                content += '' + players.selectedPlayers + '</p>';
                //content += '<br/>';
                $(content).appendTo("#mainCricRes");
              });
              $( "#mainCric" ).empty();
              for(i=0; i< pcount; i++){
              	$( "#mainCric" ).append( "<button class='btn btn-success' style='padding : 15px 30px' id='selectPlayer"+i+"'  > Pick Me </button>" );
              	//console.log("Done " + i);
   
              	$("button#selectPlayer"+i+"").on('click',function(){
              		var uname = $('#uname').text();
              	    console.log('Input message .. '+uname);
              	    ws.send("pick:" + uname);
              	});
              	
              }
              
    
    
   // $("textarea#outputMessage").val(event.data);
};

$("button#messageSubmit").on('click',function(){
    //var message = $('textarea#inputMessage').val();
    console.log('Start session');
    ws.send("start");
});

$("button#midSubmit").on('click',function(){
    //var message = $('textarea#inputMessage').val();
    console.log('Start session');
    var mid = $("#mid").val();
    console.log(mid);
    ws.send("new:"+ mid );
});

$("button#restSubmit").on('click',function(){
    //var message = $('textarea#inputMessage').val();
    console.log('Rest session');
    ws.send("reset");
});

