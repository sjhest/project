<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	    	$('#giTree').tree({
	    		url:'GiAction_giTree',
	    		animate:'true',
	    		checkbox:'true',
	    		lines:'true',
	    		onContextMenu: function(e,node){
	    			nodeS = node;
	    			e.preventDefault();
	    			$(this).tree('select',node.target);
	    			$('#contextMenu').menu('show',{
	    				left: e.pageX,
	    				top: e.pageY
	    			});
	    		}
	    	})
	    })
	    
    
	    function appendAft(){
	    	$('#addGiForm')[0].reset();
	    	operation = "appendAft";
	    	var node = $('#giTree').tree('getParent', nodeS.target);
	    	if(node != null)
	    		$('#parentId').attr("value", node.id);
			
			$('#win').window('open'); 
		}
	    
	    function appendSub(){
	    	$('#addGiForm')[0].reset();
	    	operation = "appendSub";
	    	$('#parentId').attr("value", nodeS.id);
			
			$('#win').window('open'); 
		}
	    
	    function removeNode(){
	    	$.ajax({
	    		url:"GiAction_removeNode",
	    		type:"POST",
	    		data:{id:nodeS.id},
	    		success:function(data){  
	    			$('#giTree').tree('remove', nodeS.target);
	             } 
	    	})
	    	
	    }
	    
	    function update(){
	    	$('#updateGiForm')[0].reset();
	    	$.ajax({
	    		url:"GiAction_disUpdateNode",
	    		type:"POST",
	    		data:{id:nodeS.id},
	    		success:function(data){ 
	    			$('#id').attr("value",data[0].id);
	    			$('#text').attr("value",data[0].text);
	    			var attrMap = data[0].attributes;
	    			$('#type').attr("value",attrMap.type);
	    			$('#code').attr("value",attrMap.code);
	    			$('#remark')[0].innerHTML = attrMap.remark;
	    			$('#longitude').attr("value",attrMap.longitude);
	    			$('#latitude').attr("value",attrMap.latitude);
	    			$('#updatewin').window('open');
	             } 
	    	})
	    }
	    
	    function cancelAddGi(){
	    	$('#nodeid').attr("value", "");
	    	$('#win').window('close'); 
	    }
	    
	    function cancelUpdateGi(){
	    	$('#nodeid').attr("value", "");
	    	$('#updatewin').window('close'); 
	    }

    </script>  
</head>
<body>
	<div id="gi" class="easyui-panel">
		<ul id="giTree"  class="easyui-tree" ></ul> 
	</div>
	
	<div id="contextMenu" class="easyui-menu" style="width:120px;">
		<div onclick="appendSub()">插入子节点</div>
		<div onclick="appendAft()">该节点后插入</div>
		<div onclick="removeNode()">删除本节点及所有子节点</div>
		<div onclick="update()">修改本节点</div>
		<!--  
		<div class="menu-sep"></div>
		<div onclick="expand()">Expand</div>
		<div onclick="collapse()">Collapse</div>
		-->
	</div>
	
	
	<div id="win" class="easyui-window" data-options="closed:true,modal:true,title:'增加地点信息',collapsible:false,minimizable:false,maximizable:false" >

   		<form id="addGiForm" method="post">
   			<script type="text/javascript">   
			    $('#addGiForm').form({
			    	url:'GiAction_appendGi',
					onSubmit:function(){
						return $(this).form('validate');
					},
			        success:function(data){
				        	$('#win').window('close');
					    	var treeData = JSON.parse(data);
					    	if(operation == "appendAft")
					    	{
						    	$('#giTree').tree('insert', {
						    		after:nodeS.target,
						    		data:treeData
						    	});
					    	}
					    	if(operation == "appendSub")
						    	$('#giTree').tree('append', {
						    		parent:nodeS.target,
						    		data:treeData
						    	});
			        	}
			    });
			</script>
   			<input id="parentId" type="hidden" name="parentId" value="" />
	        <table>
	            <tr>
	            	<td><label for="text">名称:</label></td>
	            	<td><input class="easyui-validatebox" type="text" name="text" data-options="required:true"></input></td>
	            </tr>
	            <tr>
	            	<td><label for="type">类型:</label></td>
	            	<td><input class="easyui-validatebox" type="text" name="type" data-options="required:true"></input></td>
	            </tr>
	            <tr>
		            <td><label for="code">Code:</label></td>
		            <td><input class="easyui-validatebox" type="text" name="code" data-options="required:true"></input></td>
		        </tr>
		         <tr>
		            <td><label for="remark">标记:</label></td>
		            <td><textarea name="remark" style="height:60px;"></textarea></td>
		        </tr>
		        <tr>
		            <td><label for="longitude">经度:</label></td> <!-- 经度的输入还不是很完善 -->
		            <td><input class="easyui-numberbox" name="longitude" data-options="min:0,max:360"></input></td>
		        </tr>
		        <tr>
		            <td><label for="latitude">纬度:</label></td>
		            <td><input class="easyui-numberbox" name="latitude"  data-options="min:0,max:90"></input></td>
		        </tr>
		         <tr>
		            <td><input type="submit" value="Submit"></td>
		            <td><input type="reset" value="Reset"></td>
		            <td><input type="button" value="Cancel" onClick="cancelAddGi()"></td>
		        </tr>
	        </table>
	    </form>
	</div>
	
	
		<div id="updatewin" class="easyui-window" data-options="closed:true,modal:true,title:'增加地点信息',collapsible:false,minimizable:false,maximizable:false" >

   		<form id="updateGiForm" method="post">
   			<script type="text/javascript">   
			    $('#updateGiForm').form({
			    	url:'GiAction_updateGi',
					onSubmit:function(){
						return $(this).form('validate');
					},
			        success:function(data){
				        	$('#updatewin').window('close');
					    	var treeData = JSON.parse(data);
					       	$('#giTree').tree('update', {
						    	target:nodeS.target,
						    	text:treeData[0].text
						    });
					    	
			        	}
			    });
			</script>
   			<input id="id" type="hidden" name="id" value="" />
	        <table>
	            <tr>
	            	<td><label for="text">名称:</label></td>
	            	<td><input id="text" class="easyui-validatebox" type="text" name="text" data-options="required:true"></input></td>
	            </tr>
	            <tr>
	            	<td><label for="type">类型:</label></td>
	            	<td><input id="type" class="easyui-validatebox" type="text" name="type" data-options="required:true"></input></td>
	            </tr>
	            <tr>
		            <td><label for="code">Code:</label></td>
		            <td><input id="code" class="easyui-validatebox" type="text" name="code" data-options="required:true"></input></td>
		        </tr>
		         <tr>
		            <td><label for="remark">标记:</label></td>
		            <td><textarea id="remark" name="remark" style="height:60px;"></textarea></td>
		        </tr>
		        <tr>
		            <td><label for="longitude">经度:</label></td> <!-- 经度的输入还不是很完善 -->
		            <td><input id="longitude" class="easyui-numberbox" name="longitude" data-options="min:0,max:360"></input></td>
		        </tr>
		        <tr>
		            <td><label for="latitude">纬度:</label></td>
		            <td><input id="latitude" class="easyui-numberbox" name="latitude"  data-options="min:0,max:90"></input></td>
		        </tr>
		         <tr>
		            <td><input type="submit" value="Submit"></td>
		            <td><input type="reset" value="Reset"></td>
		            <td><input type="button" value="Cancel" onClick="cancelUpdateGi()"></td>
		        </tr>
	        </table>
	    </form>
	</div>
</body>
</html>