<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR></HEAD>
<!-- 引入jquery库 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	//定义成员变量标志位
	var flag1 = false;
	var flag2 = false;
	var flag3 = false;
	var flag4 = false;

	// 阻止表单提交
	function checkForm(){
		if(flag1 == true && flag2 == true && flag3 == true && flag4 == true){
			return true;
		} else {
			alert("校验不通过")
			return false;
		}
	}
	$(function(){
		//绑定失去焦点事件
		$("#user_code").blur(function(){
			//alert();
			//首先进行非空校验
			//获取value值
			var value = this.value;
			if(value.trim() == ""){
				$("#codeSpan").html("登录名不能为空");
				//如果登录名为空,没有必要发送Ajax请求
				flag1 = false;
				return;
			} else {
				flag1 = true;
				$("#codeSpan").html("");
			}
			
			//发送Ajax校验
			var url = "${pageContext.request.contextPath}/user_validateUserCode.action";
			var data = {"user_code":value};
			$.get(url,data,function(result){
				if(result == "1"){
					//表示不能注册.
					$("#codeSpan").html("用户名已存在,不能注册");
					flag2 = false;
				} else {
					//表示可以注册
					$("#codeSpan").html("用户名不存在,可以注册");
					flag2 = true;
				}	
			});
		})

		//绑定失去焦点事件.校验密码框
		$("#txtPwd").blur(function(){
			//alert();
			//首先进行非空校验
			//获取value值
			var value = this.value;
			if(value.trim() == ""){
				$("#txtPwdSpan").html("密码不能为空");
				flag3 = false;
				return;
			} else {
				flag3 = true;
				$("#txtPwdSpan").html("");
			}
		})
		
		//校验用户名
		$("#user_name").blur(function(){
			//alert();
			//首先进行非空校验
			//获取value值
			var value = this.value;
			if(value.trim() == ""){
				$("#nameSpan").html("用户名不能为空");
				flag4 = false;
				return;
			} else {
				flag4 = true;
				$("#nameSpan").html("");
			}
		})
	})

</script>

<BODY>


<%-- 
	注册的表单 
	onsubmit 阻止表单提交的事件
	
--%>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/user_regist.action" onsubmit="return checkForm()"  method=post>

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150>
                	<%-- 登录名对应的文本框 --%>
                	<INPUT id="user_code" style="WIDTH: 130px" name="user_code">
                </TD>
                <TD style="HEIGHT: 28px" width=370>
                	<SPAN id="codeSpan" style="FONT-WEIGHT: bold; COLOR: white"></SPAN>
                </TD>
              </TR>
              
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px">
                	<INPUT id=txtPwd style="WIDTH: 130px" type=password name="user_password">
                </TD>
                <TD style="HEIGHT: 28px">
                	<SPAN id="txtPwdSpan" style="FONT-WEIGHT: bold; COLOR: white"></SPAN>
                </TD>
              </TR>
              
              <TR>
                <TD style="HEIGHT: 28px" width=80>用 户 名：</TD>
                <TD style="HEIGHT: 28px" width=150>
                	<%-- 登录名对应的文本框 --%>
                	<INPUT id="user_name" style="WIDTH: 130px" name="user_name">
                </TD>
                <TD style="HEIGHT: 28px" width=370>
                	<SPAN id="nameSpan" style="FONT-WEIGHT: bold; COLOR: white"></SPAN>
                </TD>
              </TR>
              
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD></TD>
                <TD>
                
                <input id="submit" type="submit" value="注册" />
                  
              </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="images/login_3.jpg" 
border=0></TD></TR></TBODY></TABLE></DIV></DIV>


</FORM></BODY></HTML>


</body>
</html>