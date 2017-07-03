<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
	}
	
	
	function deleteCustomer(cust_id){
		if(confirm("是否删除")){
			location.href="${pageContext.request.contextPath }/customer_delete.action?cust_id="+cust_id;
		}
	}
	
	
	
	function showLinkMans(obj,cust_id){
		//alert(obj.name)
		if(obj.name=="关闭"){
			$("#"+cust_id).html("");
			obj.name="打开";
		} else {
			var url = "${pageContext.request.contextPath }/linkMan_findByCustId.action";
			var param = {"customer.cust_id":cust_id};
			$.post(url,param,function(result){
				$("#"+cust_id).html("");
				if(result==""){
					//表示该用户没有联系人
					$("#"+cust_id).html("无联系人");
				}
				//alert(JSON.stringify(result));
				//alert($("#"+cust_id).html());
				$(result).each(function(index,element){
					$("#"+cust_id).append("<div>"+element.lkm_name+"&nbsp&nbsp&nbsp&nbsp"+element.lkm_phone+"</div>");
				})
				obj.name="关闭";
			},"json");
		}
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findByPage.action"
		method=post>
		
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
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id="cust_name"
														style="WIDTH: 80px" maxLength=50 name="cust_name" value="${model.cust_name }"></TD>
													<TD>信息来源：</TD>
													<td>
														<s:select list="dictSourceList" name="source.dict_id" headerKey="" headerValue="--请选择--" 
															listKey="dict_id" listValue="dict_item_name" theme="simple"></s:select>
													</td>
													<TD>所属行业：</TD>
													<td>
														<s:select list="dictIndustryList" name="industry.dict_id" headerKey="" headerValue="--请选择--" 
															listKey="dict_id" listValue="dict_item_name" theme="simple"></s:select>
													</td>
													<TD>客户级别：</TD>
													
													<td>
														<s:select list="criteriaLevelList" name="level.dict_id" headerKey="" headerValue="--请选择--" 
															listKey="dict_id" listValue="dict_item_name" theme="simple"></s:select>
													</td>
													
													<TD><INPUT class=button id=sButton2 type="submit"
														value=" 筛选 " name=sButton2 ></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>所属行业</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${list}" var="customer">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${customer.cust_name }</TD>
													<!-- 客户级别 -->
													<TD>${customer.level.dict_item_name }</TD>
													<!-- 客户信息来源 -->
													<TD>${customer.source.dict_item_name }</TD>
													<!-- 客户所属行业 -->
													<TD>${customer.industry.dict_item_name }</TD>
													<TD>${customer.cust_phone }</TD>
													<TD>${customer.cust_mobile }</TD>
													<TD>
													<a href="${pageContext.request.contextPath }/customer_toEdit.action?cust_id=${customer.cust_id}">修改</a>
													&nbsp;&nbsp;
													<a href="javascript:void(0);" onclick="deleteCustomer(${customer.cust_id})">删除</a>
													&nbsp;&nbsp;
													<a href="javascript:void(0);" name="打开" onclick="showLinkMans(this,${customer.cust_id})">查看联系人</a>
													<div id="${customer.cust_id}" >
													</div>
													</TD>
												</TR>
												</c:forEach>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<c:if test="${not empty totalPage }">
									<TR>
										<TD><SPAN id=pagelink>
												<DIV
													style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
													共[<B>${totalCount}</B>]条记录,[<B>${totalPage}</B>]页
													,每页显示
													<select name="pageSize" onchange="to_page()">
														<option value="3" <c:if test="${pageSize==3 }">selected</c:if>>3</option>
														<option value="5" <c:if test="${pageSize==5 }">selected</c:if>>5</option>
													</select> 
													条
													<c:if test="${currentPage != 1}">
														[<A href="javascript:to_page(${currentPage-1})">前一页</A>]
													</c:if>
													<c:if test="${currentPage != totalPage}">	
														[<A href="javascript:to_page(${currentPage+1})">后一页</A>] 
													</c:if>
													<B>${page}</B>
													到
													<input type="text" size="3" id="page" name="currentPage" value="${currentPage }"/>
													页
													
													<input type="button" value="Go" onclick="to_page()"/>
												</DIV>
										</SPAN></TD>
									</TR>
								</c:if>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
