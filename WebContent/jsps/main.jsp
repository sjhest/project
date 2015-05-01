<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="../js/jslib/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jslib/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../theme/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="../theme/icon.css">
	<link rel="stylesheet" type="text/css" href="../theme/demo/demo.css"> 
	<link rel="stylesheet" type="text/css" href="../theme/metro/menu.css"> 
	<link rel="stylesheet" type="text/css" href="../theme/metro/tree.css"> 
	<link rel="stylesheet" type="text/css" href="../theme/metro/window.css"> 
	<script type="text/javascript">
		$(function(){
			$("#giTree").load("giTree.jsp");
		})
		
	</script>
</head>
<body>

	<div id="cc" class="easyui-layout" style="width:900px;height:900px;margin:0px;">
	    <div region="north" split="false" style="height:100px;"></div>
	    <div id="giTree" region="west" split="false" style="width:200px; include-source: url('giTree.jsp');">
	    </div>
	    
	    <div region="center" title="center title" style="padding:5px;background:#eee;" frameborder="no" border="0" ></div>
	</div>
	
	
</body>
</html>