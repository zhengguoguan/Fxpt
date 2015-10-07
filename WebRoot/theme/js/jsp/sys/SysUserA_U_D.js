//页面表单验证，自动执行
function autoValidate(){
	
	validateRegex(false,"#userNo","用户编号由3-20个数字、英文字母或者下划线组成",null,null,"用户编号由3-20个数字、英文字母或者下划线组成",false,"^\\w{3,20}$");
	validateMinMax(false,"#userName","请输入用户名",null,null,"用户名不能为空",1,20,"size");
	validateRegex(false,"#phone","请输入手机号码",null,null,"手机号码格式不正确",true,"mobile");
	validateRegex(false,"#email","请输入邮箱",null,null,"邮箱格式不正确",true,"email");
	validateRegex(false,"#pwd","请输入密码",null,null,"密码由6-20个数字、英文字母或者下划线组成",false,"^\\w{6,20}$");
	validateRepassword(false,"#repwd","请输入重复密码","两次密码必须一致哦",null,"重复密码不能为空","pwd","2次密码不一致,请确认");
	validateRegex(true,"#tel","请输入联系电话，可以为空哦","格式例如：0577-88888888",null,"电话号码格式不正确",true,"tel");
	validateRegex(true,"#QQNum","请输入QQ，可以为空哦",null,null,"QQ号码格式不正确",true,"qq");
	
}