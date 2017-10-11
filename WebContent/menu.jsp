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
<title>menu</title>
<link rel="stylesheet" href="skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="skin/js/frame/menu.js"></script>
<base target="main" />
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">

      
      <c:if test="${user.role==1}">
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>常规管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='method!teacherlist' target='main'>班主任管理</a></div>
               
              </div>
            </li>
            
            
          </ul>
        </dd>
      </dl>
    
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>学籍查询</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            <li><a href='method!studentlist6' target='main'>学籍查询</a></li>

          </ul>
        </dd>
      </dl>
      
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>系统账户查询</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            <li><a href='method!userlist' target='main'>班主任账户查询</a></li>
            <li><a href='method!userlist2' target='main'>学生账户查询</a></li>
          </ul>
        </dd>
      </dl>
      
      </c:if>
      
      <c:if test="${user.role==2}">
      
    
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>常规管理</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            <li><a href='method!studentlist' target='main'>学籍基本信息管理</a></li>
            <li><a href='method!studentlist2' target='main'>成绩管理</a></li>
            <li><a href='method!studentlist4' target='main'>离校信息管理</a></li>
          </ul>
        </dd>
      </dl>
      
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>学籍查询</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            <li><a href='method!studentlist6' target='main'>学籍查询</a></li>
          </ul>
        </dd>
      </dl>
      </c:if>
      
      <c:if test="${user.role==3}">
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>个人学籍查询</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
            <li><a href='method!studentlist7' target='main'>个人学籍查询</a></li>
          </ul>
        </dd>
      </dl>
      </c:if>
	  </td>
  </tr>
</table>
</body>
</html>