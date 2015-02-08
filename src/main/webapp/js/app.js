var wsUrl;
var appUrl;
if (window.location.protocol == 'http:') {
  	// wsUrl = 'ws://' + window.location.host +
	// ':8000/websocket-reverse-echo-example/echo';
	 wsUrl = 'ws://' + window.location.host + ':8000/cric';
	//wsUrl = 'ws://localhost:8080/wildfly8/cric';
	//wsUrl = 'ws://192.168.1.5:8080/cric/cric';	
} else {
  	// wsUrl = 'wss://' + window.location.host +
	// ':8443/websocket-reverse-echo-example/echo';
  	wsUrl = 'wss://' + window.location.host + ':8443/cric';
}
console.log('WebSockets Url : ' + wsUrl);
var ws = new WebSocket(wsUrl);

ws.onopen = function(event){
	console.log('WebSocket connection started');
	init();
};

ws.onclose = function(event){
	 console.log("Remote host closed or refused WebSocket connection");
     console.log(event);
};

ws.onmessage = function(event){
    console.log(event.data);
    var jdata = $.parseJSON(event.data);
    
    buildUI(jdata);
    
};

function init(){
	
	var jqxhr = $.getJSON( "init.htm", function(data) {
		console.log( "success : " + data );
		buildUI(data);
		var uname = $('#uname').text();
		ws.send("login:" + uname );
		
		})
		.done(function() {
		console.log( "second success" );
		})
		.fail(function() {
		console.log( "error" );
		})
		.always(function() {
		console.log( "complete" );
		});
	
}

function buildUI(jdata){
	if(jdata == null || jdata.match == null){
		if(jdata.refresh){
			$( "#mainCric" ).append("Moderater has not started the session.");
		}
	}
	
	var uname = $('#uname').text();
	
	if(jdata.refresh){
		
		
		
		console.log(uname);
		console.log(jdata.playerCount);
		console.log(jdata.match);
		console.log(jdata.nextUser);
		pcount = jdata.playerCount;
		
		$( "#mainCricRes" ).empty();
		if(jdata.players != null){
			$.each(jdata.players, function(i,players){
				content ="";
				content = '<p>' + players.name + ' : ';
				content += '' + players.selectedPlayers + '</p>';
				$(content).appendTo("#mainCricRes");
			});
		}
		
		var disableButton="";
		if(jdata != null && jdata.nextUser != null && jdata.nextUser != uname){
			//$("button[psClass='']").removeAttr("disabled");
			disableButton="disabled";
		}
		
		$( "#mainCric" ).empty();
		if(pcount > 0 && jdata.match != null && jdata.match != ""){
			for(i=0; i< pcount; i++){
				
				$( "#mainCric" ).append( "<button class='btn btn-success pickClass' "+disableButton+" style='padding : 15px 30px' id='selectPlayer"+i+"'  > Pick Me </button>" );
				
				$("button#selectPlayer"+i+"").on('click',function(){
					var uname = $('#uname').text();
					console.log('Input message .. '+uname);
					$("button[class=pickClass]").attr("disabled", "disabled");
					ws.send("pick:" + uname );
					
					 //$("button[psClass='']").attr("disabled", "disabled");
					 
					//$( "#cricModal" ).addClass("modal");
				});
			}
		}
		
		if(jdata.match != null){
			$("#midTxt").html(jdata.match);
		}else{
			$("#midTxt").empty();
		}
		
	}else{
		if(jdata != null && jdata.nextUser != null && jdata.nextUser != uname){
			$(function(){
				$("button.pickClass").attr("disabled", true);
				});
		}else{
			$(function(){
				$("button.pickClass").removeAttr("disabled");
				})
		}
	}
	
	if(jdata.selectionOrder != null){
		$("#selectionOrderTxt").text(jdata.selectionOrder);
	}else{
		$("#selectionOrderTxt").empty();
	}
	
	if(jdata.nextUser != null){
		$("#nextUserTxt").text(jdata.nextUser);
	}else{
		$("#nextUserTxt").empty();
	}
	
	//Message
	if(jdata.message != null){
		var mSpan = "<p class='bg-info'>"+jdata.message+"</p>";
		$( "#circMessage" ).prepend(mSpan);
	}else{
		$("#circMessage").empty();
	}
	
	
	
}

$("button#messageSubmit").on('click',function(){
    // var message = $('textarea#inputMessage').val();
    console.log('Start session');
    ws.send("start");
});

$("button#midSubmit").on('click',function(){
    // var message = $('textarea#inputMessage').val();
    console.log('Start session');
    var mid = $("#mid").val();
    console.log(mid);
    
    //ws.send("new:"+ mid );	
    
    var jqxhr = $.ajax( "checkMatch.htm?matchname="+mid, function(data) {})
	.done(function(data) {
		console.log( "Check Matchname : " + data );
		if(data == "success"){
			ws.send("new:"+ mid );
			console.log("true");
		}else{
			$("#midValMessage").html("Matchname already exists.");
		}
	})
	.fail(function() {
		console.log( "error" );
	})
	.always(function() {
		//console.log( "complete" );
	});
    
});


$("button#orderSubmit").on('click',function(){
    // var message = $('textarea#inputMessage').val();
    console.log('ORder selection');
    var selectionOrder = $("#mid").val();
    
    ws.send("order:"+selectionOrder);
});

$("button#restSubmit").on('click',function(){
    // var message = $('textarea#inputMessage').val();
    console.log('Rest session');
    $("#midTxt").empty();
    $( "#circMessage" ).empty();
    
    ws.send("reset");
});

$("button#saveSubmit").on('click',function(){
    console.log('Save');
    var jqxhr = $.ajax( {type: "POST",url : "save.htm"} )
	.done(function(data) {
		console.log( "done" );
	})
	.fail(function() {
		console.log( "error" );
	})
	.always(function() {
		//console.log( "complete" );
	});
});

$("button#emailSubmit").on('click',function(){
    console.log('Save');
    var jqxhr = $.ajax( "email.htm", function(data) {})
	.done(function(data) {
		console.log( "done" );
	})
	.fail(function() {
		console.log( "error" );
	})
	.always(function() {
		//console.log( "complete" );
	});
});

