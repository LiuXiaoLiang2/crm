<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/crm.js"></script> --%>
<script type="text/javascript">
	$(function(){
		var url = "${pageContext.request.contextPath }/dict_init.action";
		
		//从值栈获取数据
		var industry_id = "${industry.dict_id}";
		var source_id = "${source.dict_id}";
		var level_id = "${level.dict_id}";

		sendAjax("001","cust_industry");
		sendAjax("002","cust_source");
		sendAjax("006","cust_level");
		
		function sendAjax(dict_type_code,id){
			$.post(url,{"dict_type_code":dict_type_code},function(data){
				//alert(JSON.stringify(data))
				$(data).each(function(index,element){
					$("#"+id).append("<option value="+element.dict_id+">"+element.dict_item_name+"</option>")
				})
				//已经填充完毕,下面就需要根据用户本身来选择默认值
				$("option[value='"+industry_id+"']").prop("selected",true);
				$("option[value='"+source_id+"']").prop("selected",true);
				$("option[value='"+level_id+"']").prop("selected",true);
			},"json");
		}
	})
</script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/customer_update.action"
		method=post enctype="multipart/form-data">
		<input type="hidden" name="cust_id" value="${cust_id }"/>
		<input type="hidden" name="file_path" value="${file_path }"/>
		<input type="hidden" name="file_name" value="${file_name }"/>

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background=${pageContext.request.contextPath }/images/new_020.jpg
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
								<TD class=manageHead>当前位置：客户管理 &gt; 修改客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<TR>
								<td>客户名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
											style="WIDTH: 180px" maxLength=50 name="cust_name" value="${cust_name }">
								</td>
								<td>客户级别 ：</td>
								<td>
								<!-- <INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_level" value="">
								</td> -->
									<!-- <INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cust_level"> -->
									<select id="cust_level" name="level.dict_id">
										
									</select>
								</td>
							</TR>
							
							<TR>
								<td>信息来源：</td>
								<td>
								<!-- <INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_source" value=""> -->
									<select id="cust_source" name="source.dict_id">
										
									</select>
								</td>
								<td>联系人：</td>
								<td>
							<!-- 	<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_linkman" value=""> -->
									<select id="cust_industry" name="industry.dict_id">
										
									</select>
								</td>
							</TR>
							<TR>
								
								
								<td>固定电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_phone" value="${cust_phone }">
								</td>
								<td>移动电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile" value="${cust_mobile }">
								</td>
							</TR>
							<tr>
								<td>上传图片</td>
						
								<td rowspan=2>
									<input type="file" name="upload"/>
								</td>
							</tr>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
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
