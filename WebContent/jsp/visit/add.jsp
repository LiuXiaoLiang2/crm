<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户拜访</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>

<!-- 日期插件，使用jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/jquery/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery.datepick-zh-CN.js"></script>

<script type="text/javascript">
	$(function(){
		//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
		$('#timeId').datepick({dateFormat: 'yy-mm-dd'}); 
		$('#nextTimeId').datepick({dateFormat: 'yy-mm-dd'}); 
		
		//异步发送请求,返回客户列表
		var url = "${pageContext.request.contextPath }/customer_findAll.action";
		var param = {"":""};
		$.post(url,param,function(result){
			//alert(JSON.stringify(result))
			//遍历数组
			$(result).each(function(index,element){
				$("#customer").append("<option value="+element.cust_id+">"+element.cust_name+"</option>")
			})
		},"json");
		
	});
	
	function setLinkMan(){
		//alert()
		//获取被选中的value值,使用jquery
		//中间要使用 空格 ，表示下面的子元素
		var value = $("#customer :selected").val();
		//发送Ajax请求,查找当前用户下的联系人
		var url = "${pageContext.request.contextPath }/linkMan_findByCustId.action";
		var param = {"customer.cust_id":value};
		$.post(url,param,function(result){
			//alert(JSON.stringify(result));
			if(result.length == "0"){
				//alert();
				$("#linkMan").html("<option value='0'>该用户没有联系人，请选择其他用户</option>");
			} else {
				$("#linkMan").html("");
				//填充数据
				$(result).each(function(index,element){
					$("#linkMan").append("<option value="+element.lkm_id+">"+element.lkm_name+"</option>");
				})
			}
		},"json");
	}
	
	function checkForm(){
		
		var valLinkMan = $("#linkMan :selected").val();
		var valCustomer = $("#customer :selected").val();
		if(valLinkMan == "0" || valCustomer == "0"){
			alert("请选择有效的客户联系人");
			return false;
		}
	}
</script>

</HEAD>
<BODY>
	<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/saleVisit_add.action" method=post onsubmit="return checkForm()">
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户拜访管理 &gt; 添加客户拜访</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>客户名称：</td>
								<td>
									<select name="customer.cust_id" id="customer" onchange="setLinkMan()">
										<option value="0">请选择客户</option>
									</select>
								</td>
								
								<td>被拜访人(客户对应的联系人)：</td>
								<td>
									<select name="linkMan.lkm_id" id="linkMan">
										<option value="0">--请首先选择客户--</option>
									</select>
								</td>
								
							</tr>
							
							<TR>
								<td>拜访时间：</td>
								<td>
									<INPUT class=textbox id="timeId" style="WIDTH: 180px" name="visit_time" readonly="readonly">
								</td>
								
								<td>拜访地点：</td>
								<td>
									<INPUT class=textbox style="WIDTH: 180px" maxLength=50 name="visit_addr">
								</td>
							</TR>
							
							<TR>
								<td>下次拜访时间 ：</td>
								<td>
									<INPUT class=textbox id="nextTimeId" style="WIDTH: 180px" maxLength=50 name="visit_nexttime">
								</td>
								
							</TR>
							
							<TR>	
								<td>拜访详情 ：</td>
								<td>
									<textarea rows="5" cols="26" name="visit_detail"></textarea>
								</td>
							</TR>
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
