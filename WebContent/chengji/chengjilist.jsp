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

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="98%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td align="center">
  <span style="font-weight: bold;"> ${title } </span>
 </td>
 </tr>
</table>
</td>
</tr>
</table>
  
<!--  内容列表   -->


<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="skin/images/tbg.gif">
	<form action="${url }" method="post">
<a href="${url2 }add?sid=${sid }"><span style="font-size: 15px;font-weight: bold;color: red;">添加新成绩</span></a>

</form>
	
	</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td >学号</td>
	<td >学生姓名</td>
	<td >课程</td>
	<td >成绩</td>
	<td >学分</td>
	<td >任课老师</td>
	<td >添加时间</td>

	<td >操作</td>
	
</tr>

 <c:forEach items="${list}"  var="bean">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >

	<td>${bean.student.sno }</td>
	<td>${bean.student.sname }</td>
	<td>${bean.kecheng }</td>
	<td>${bean.chengji }</td>
	<td>${bean.xuefen }</td>
	<td>${bean.laoshi }</td>
	<td>${fn:substring(bean.createtime,0, 19)}</td>

	<td>
	<a href="${url2 }update3?id=${bean.id }">查看</a>|
  	<a href="${url2 }update?id=${bean.id }">更新</a> |
  	<a href="${url2 }delete?id=${bean.id }">删除</a>
	</td>
</tr>
</c:forEach>




<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center">${pagerinfo }</td>
</tr>
</table>


</body>
</html>