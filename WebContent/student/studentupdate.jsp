<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	if (document.getElementById('snoid').value=="")
	{
		alert("学号不能为空");
		return false;
	}
	if (document.getElementById('snameid').value=="")
	{
		alert("姓名不能为空");
		return false;
	}
	if (document.getElementById('sfzid').value=="")
	{
		alert("身份证不能为空");
		return false;
	}
	if (document.getElementById('addressid').value=="")
	{
		alert("地址不能为空");
		return false;
	}
	if (document.getElementById('lianxidianhuaid').value=="")
	{
		alert("联系电话不能为空");
		return false;
	}
	
	if (document.getElementById('banjimingchenid').value=="")
	{
		alert("班级名称不能为空");
		return false;
	}
	
	return true;
	
}


</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>


<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif" align="center">
  	<span style="font-weight: bold;">${title }</span>
</td>
</tr>
</table>
  

<form action="${url }" method="post" onsubmit="return checkform()" enctype="multipart/form-data" >

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">


<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	姓名：
	</td>
	<td width="70%">
	<input  type="text" name="sname"  id='snameid'  size="30" value="${bean.sname }" />
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	学生照片：
	</td>
	<td width="70%">
	  <img src="<%=basePath %>uploadfile/${bean.touxiang }" width="200" height="200"/>
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	上传新学生照片：
	</td>
	<td width="70%">
	 <input type="file" name="uploadfile"  id="uploadfileid" size="30"/>
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	性别：
	</td>
	<td width="70%">
		<select name="sex">
		<option value="男" <c:if test="${bean.sex=='男' }">selected</c:if> >男</option>
		<option value="女" <c:if test="${bean.sex=='女' }">selected</c:if> >女</option>
		</select>
		
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	身份证：
	</td>
	<td width="70%">
<input  type="text" name="sfz"  id='sfzid'  size="30" value="${bean.sfz }" />
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	地址：
	</td>
	<td width="70%">
<input  type="text" name="address"  id='addressid'  size="30" value="${bean.address }" />
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	联系电话：
	</td>
	<td width="70%">
<input  type="text" name="lianxidianhua"  id='lianxidianhuaid'  size="30" value="${bean.lianxidianhua }" />
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	班级名称：
	</td>
	<td width="70%">
<input  type="text" name="banjimingchen"  id='banjimingchenid'  size="30" value="${bean.banjimingchen }" />
	</td>
	
</tr>



<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	备注：
	</td>
	<td width="70%">
<textarea rows="7" cols="50"  name="beizhu">${bean.beizhu }</textarea>
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	操作：
	</td>
	<td width="70%">
 <input type="submit" value="提交" style="width: 60px" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
	</td>
	
</tr>




</table>

</form>


</body>
</html>