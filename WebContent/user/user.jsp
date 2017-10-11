<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	 
	

	if (document.getElementById('password1id').value=="")
	{
		alert("原密码不能为空");
		return false;
	}
	if (document.getElementById('password2id').value=="")
	{
		alert("新密码不能为空");
		return false;
	}
	
	if (document.getElementById('password2id').value.length<6)
	{
		alert("新密码长度必须大于6位");
		return false;
	}
	if (document.getElementById('password2id').value != document.getElementById('password3id').value)
	{
		alert("新密码与新密码确认不一致");
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
  	<span style="font-weight: bold;">修改密码</span>
</td>
</tr>
</table>
  

<form action="method!changepwd2" method="post" onsubmit="return checkform()">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	原密码:
	</td>
	<td width="70%">
	<input  type="password" name="password1"  id='password1id'  size="30"  />
	</td>
	
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	新密码：
	</td>
	<td width="70%">
	<input  type="password" name="password2"  id='password2id'  size="30"  />
	</td>
	
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	确认密码：
	</td>
	<td width="70%">
<input  type="password" name="password3"  id='password3id'  size="30"  />
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