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
	 
	

	if (document.getElementById('kechengid').value=="")
	{
		alert("课程不能为空");
		return false;
	}
	if (document.getElementById('chengjiid').value=="")
	{
		alert("成绩不能为空");
		return false;
	}
	if (document.getElementById('xuefenid').value=="")
	{
		alert("学分不能为空");
		return false;
	}
	if (document.getElementById('laoshiid').value=="")
	{
		alert("任课老师不能为空");
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
  

<form action="${url }?sid=${sid }" method="post" onsubmit="return checkform()">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	课程:
	</td>
	<td width="70%">
	<input  type="text" name="kecheng"  id='kechengid'  size="30"  />
	</td>
	
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	成绩：
	</td>
	<td width="70%">
	<input  type="text" name="chengji"  id='chengjiid'  size="30"  />
	</td>
	
</tr>


<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	学分：
	</td>
	<td width="70%">
<input  type="text" name="xuefen"  id='xuefenid'  size="30"  />
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	任课老师：
	</td>
	<td width="70%">
<input  type="text" name="laoshi"  id='laoshiid'  size="30"  />
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