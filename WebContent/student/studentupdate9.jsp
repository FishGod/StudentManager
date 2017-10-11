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

</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>


<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif" align="center">
  	<span style="font-weight: bold;">${title }</span>
</td>
</tr>
</table>
  
<form action="${url }" method="post" onsubmit="return checkform()">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	学号:
	</td>
	<td width="70%">
	${bean.sno }
	</td>
	
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	姓名：
	</td>
	<td width="70%">
	${bean.sname }
	</td>
	
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	照片：
	</td>
	<td width="70%">
	<img src="<%=basePath %>uploadfile/${bean.touxiang }" width="200" height="200"/>
	</td>
	
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	性别：
	</td>
	<td width="70%">
		${bean.sex }
		
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	身份证：
	</td>
	<td width="70%">
${bean.sfz }
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	地址：
	</td>
	<td width="70%">
${bean.address }
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	联系电话：
	</td>
	<td width="70%">
${bean.lianxidianhua }
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	班级名称：
	</td>
	<td width="70%">
${bean.banjimingchen }
	</td>
	
</tr>



<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	备注：
	</td>
	<td width="70%">
${bean.beizhu }
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	离校日期：
	</td>
	<td width="70%">
${bean.lixiaoriqi }
	</td>
	
</tr>

<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="30%">
	离校原因：
	</td>
	<td width="70%">
${bean.lixiaoyuanyin }
	</td>
	
</tr>



</table>

</form>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">

<tr align="center" bgcolor="#FAFAF1" height="22">
	
	<td >课程</td>
	<td >成绩</td>
	<td >学分</td>
	<td >任课老师</td>
	<td >添加时间</td>

	
</tr>

 <c:forEach items="${chengjilist}"  var="bean">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >

	
	<td>${bean.kecheng }</td>
	<td>${bean.chengji }</td>
	<td>${bean.xuefen }</td>
	<td>${bean.laoshi }</td>
	<td>${fn:substring(bean.createtime,0, 19)}</td>


</tr>
</c:forEach>


</table>

<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
<input  onclick="javascript:window.print();" style="width: 60px" type="button" value="打印" />

</body>
</html>