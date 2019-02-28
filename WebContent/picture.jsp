<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获得</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function getPic() {
		var picNo=$("#picNo").val();
		var content=$("#content").val();
	  	//var userName = '<%=session.getAttribute("CurUser")%>';
	    $.ajax({
	        url : "PictureAction.action",
	        type : "GET",
	        data : "picNo=" +picNo +"&content="+content ,
	        success : function(data, textStatus) {
					document.getElementById("pic").innerHTML = data;
	        }
	    });
	}
	function writeId(id){
		var oldId=$("#picNo").val();
		
		$("#picNo").val(id);
		$("#"+oldId).removeClass("cur");
		$("#"+id).addClass("cur");
		
	}
</script>
<style>
body{
text-align:center;
}
#imgbox{
width:800px;
margin:auto auto;
height:300px;
overflow:auto;
}
form{
margin:auto auto;
width:240px;
padding:10px 15px;
background: linear-gradient(to bottom, rgba(0,0,0,0.1), rgba(0,0,0,0.3));
border-radius:10px;
}
.img{
	float:left;
	padding:2px;
}
.img img{
	height:50px;
}
.cur{
	background-color:rgba(0,0,0,0.1);
}
form input#getPicButton{
clear:both;
}
form .textinput{
	display:block;
	float:left;
	width:100px;
	height:20px;
	margin:5px 10px;
}
form span{
	width:80px;
	height:20px;
	padding:3px;
	margin:auto auto;
	display:block;
	float:left;
	margin:5px 10px;
}

</style>
</head>
<body>

<div id="imgbox">
<script>
	for(var i=1;i<307;i++){
		var n=("0000"+i).slice(-4);
		var h='<div class="img" id="'+n+'" onclick="writeId(this.id)"><img src="files/aru/'+n+'.png"/><br/>'+n+'</div>';
		document.write(h);
	}
</script>
</div>
	<!-- <form action="PictureAction.action" method="get"> -->
	<form>
		<span>图片ID：</span><input class="textinput" type="text" name="picNo" id="picNo"/><br/>
		<span>文字：</span><input class="textinput" type="text" name="content" id="content"/><br/>
		<input id="getPicButton" type="button" value="提交" onclick="getPic()"/>
	</form>
	<div id="pic">
	
	</div>
</body>
</html>