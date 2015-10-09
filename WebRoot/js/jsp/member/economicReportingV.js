function autoValidate(){
	validateMinMax(false,"#zczj","请输入注册资金",null,null,"注册资金不能为0或空",1,200000,"number");
	validateMinMax(false,"#zgs","请输入职工数",null,null,"职工数不能为0或空",1,200000,"number");
	validateMinMax(false,"#tbrq","请输入填表日期",null,null,"填表日期不能为空",1,20,"size");
	validateMinMax(false,"#dwfzr","请输入单位负责人",null,null,"单位负责人不能为空",1,20,"size");
	validateMinMax(false,"#tjfzr","请输入统计负责人",null,null,"统计负责人不能为空",1,20,"size");
	validateMinMax(false,"#tbr","请输入填表人",null,null,"填表人不能为空",1,20,"size");
	validateMinMax(false,"#tbrlxdh","请输入联系电话",null,null,"联系电话不能为空",1,20,"size");
	validateMinMax(false,"#yfjfLjs","请输入研发经费（累计数）",null,null,"研发经费（累计数）不能为空",1,20,"size");
	validateMinMax(false,"#gxjscpsrLjs","请输入高新技术产品收入（累计数）",null,null,"高新技术产品收入（累计数）不能为空",1,20,"size");
	validateMinMax(false,"#gyzczLjs","请输入工业总产值（累计数）",null,null,"工业总产值（累计数）不能为空",1,20,"size");
	validateMinMax(false,"#gyzjzLjs","请输入工业增加值（累计数）",null,null,"工业增加值（累计数）不能为空",1,20,"size");
	validateMinMax(false,"#chLjs","请输入创汇（累计数）",null,null,"创汇（累计数）不能为空",1,20,"size");
	
}