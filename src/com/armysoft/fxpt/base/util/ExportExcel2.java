package com.armysoft.fxpt.base.util;

import jxl.*; 
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*; 

import java.io.*; 
import java.math.BigDecimal;
import java.util.*; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ExportExcel2 { 
	private String excelName="excel导出";
	private String sheetTitle="excel标题";
	private Object[] columnField;
	private Object[] columnField1;
	private Object[] columnCaption;
	public ExportExcel2(String excelName, String sheetTitle,List exportlist,List exportlist1)
	  {
		this.excelName=excelName;
		this.sheetTitle=sheetTitle;
		setColumnNameAndMean(exportlist);
		setColumnNameAndMean1(exportlist1);
	  }
	private void setColumnNameAndMean(List exportlist){
		int i;
		int q;
		Object erowData[];
		Map map = new HashMap();
		List columnFieldList=new ArrayList();
		List columnCaptionList=new ArrayList();
		if (exportlist==null || exportlist.size()==0){return ;}
		for(i=0;i<exportlist.size();i++){
			erowData=(Object[]) exportlist.get(i);
			columnFieldList.add(erowData[0].toString());
			columnCaptionList.add(erowData[1].toString());
		}
		columnField= columnFieldList.toArray();
		columnCaption= columnCaptionList.toArray();
	}
	private void setColumnNameAndMean1(List exportlist1){
		int i;
		int q;
		Object erowData[];
		Map map = new HashMap();
		List columnFieldList1=new ArrayList();
		if (exportlist1==null || exportlist1.size()==0){return ;}
		for(i=0;i<exportlist1.size();i++){
			erowData=(Object[]) exportlist1.get(i);
			columnFieldList1.add(erowData[0].toString());
		}
		columnField1= columnFieldList1.toArray();
	}
	

	/**
	 * 
	 * exportExcel_Applicant(导出excel)
	 * @param response
	 * @param list ：查询的记录
	 * @param exportlist ：excel导出配置(配置里的字段名要和查询的记录中的字段名要�?��，不然会报错)
	 * @return    
	 * boolean
	 */
	public  boolean exportExcel_Applicant(HttpServletRequest request,HttpServletResponse response,List list,List list2,List list3,List list4,List list5,List list6,List list7,List list8,List countHyfl1List,List countHyfl2List,List countHyfl3List,List countHyfl4List,List countHyfl5List,List countHyfl6List,List countHyfl7List,List countHyfl8List,String fjjzbNy ) {		
		try{ 
			int columnCount=columnField.length;
			int columnCount1=columnField1.length;
			OutputStream os = response.getOutputStream();// 取得输出�?  
	        response.reset();// 清空输出�?  
//	        String fileName=java.net.URLEncoder.encode(excelName, "UTF-8");
	        //String fileName=new String((excelName).getBytes("utf-8"),"ISO-8859-1");;
	        String fileName=null;
	        if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){
	        	 fileName=new String((excelName).getBytes("utf-8"),"ISO-8859-1");
	        }else if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
	        	 fileName=java.net.URLEncoder.encode(excelName, "UTF-8");
	        }
	        else if(request.getHeader("User-Agent").toLowerCase().indexOf("chrome") > 0) {
	        	 fileName=java.net.URLEncoder.encode(excelName, "UTF-8");
	        }
	        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");// 设定输出文件�?  
	        response.setContentType("application/msexcel");// 定义输出类型 
	        
	        WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件   
	        String tmptitle = sheetTitle+"                                                                                                        资料保密"; // 标题   
	        WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称  
	        wsheet.mergeCells( 0, 0, columnCount-1, 1);//合并单元�?    
	        
			// 设置excel标题   
	       // wsheet.mergeCells( 0, 0, 5, 0);//合并单元�?
			WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,WritableFont.BOLD, 
			                       false,UnderlineStyle.NO_UNDERLINE,Colour.RED);   
			WritableFont wfont1 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD, 
                    false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
			WritableCellFormat wcfFC = new WritableCellFormat(wfont); 
			wcfFC.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFC.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
			wcfFC.setBackground(Colour.WHITE); 
			wsheet.addCell(new Label(0, 0, tmptitle, wcfFC));   
			wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 0,WritableFont.BOLD, 
			                   false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
			wcfFC = new WritableCellFormat(wfont);  
			WritableCellFormat wcfFQ = new WritableCellFormat(wfont1); 
			wcfFQ.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
//			WritableSheet.setRowView( int i, int height); // 作用是指定第i+1行的高度，比如：
 			wsheet.mergeCells(2, 1, columnCount-1, 1);//合并单元�?     
			wsheet.addCell(new Label(0, 2, "单位名称（公章）：广州市海珠科技产业园有限公司 ",wcfFQ)); 
			wsheet.addCell(new Label(columnCount-1, 2 , "单位：万元",wcfFQ));    
			// 将第�?��的高度设�?00 sheet.setRowView( 0 , 200 );
			wsheet.mergeCells(0, 3, 0, 5);//合并垮了3列。
	         wsheet.addCell(new Label(0, 3, "行业分类",wcfFQ));   
	         wsheet.mergeCells(1, 3, 1, 5);//合并垮了3列。
	         wsheet.addCell(new Label(1, 3, "编号",wcfFQ));  
	         wsheet.mergeCells(2, 3, 2, 5);//合并垮了3列。
	         wsheet.addCell(new Label(2, 3, "入驻企业",wcfFQ));  
	         wsheet.mergeCells(3, 3, columnCount-1, 3);//合并垮了3列。 
	         wsheet.addCell(new Label(3, 3, fjjzbNy+"底累计",wcfFQ));  
	         wsheet.mergeCells(3, 4, 3, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(3, 4, "注册资金(万元)",wcfFQ));  
	         wsheet.mergeCells(4, 4, 5, 4);//合并垮了3列。 
	         wsheet.addCell(new Label(4, 4, "技工贸总收入",wcfFQ));  
	         wsheet.mergeCells(4, 5, 4, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(4, 5, "本月数",wcfFQ));  
	         wsheet.mergeCells(5, 5, 5, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(5, 5, "累计数",wcfFQ));  
	         
	         
	         wsheet.mergeCells(6, 4, 7, 4);//合并垮了3列。  
	         wsheet.addCell(new Label(6, 4, "利润总额",wcfFQ));  
	         wsheet.mergeCells(6, 5, 6, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(6, 5, "本月数",wcfFQ));  
	         wsheet.mergeCells(7, 5, 7, 5);//合并垮了3列。  
	         wsheet.addCell(new Label(7, 5, "累计数",wcfFQ));    
	         
	         wsheet.mergeCells(8, 4, 9, 4);//合并垮了3列。  
	         wsheet.addCell(new Label(8, 4, "纳税",wcfFQ));  
	         wsheet.mergeCells(8, 5, 8, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(8, 5, "本月数",wcfFQ));  
	         wsheet.mergeCells(9, 5, 9, 5);//合并垮了3列。  
	         wsheet.addCell(new Label(9, 5, "累计数",wcfFQ));    
	         
	         wsheet.mergeCells(10, 4, 11, 4);//合并垮了3列。  
	         wsheet.addCell(new Label(10, 4, "利税总额",wcfFQ));   
	         wsheet.mergeCells(10, 5, 10, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(10, 5, "本月数",wcfFQ));  
	         wsheet.mergeCells(11, 5, 11, 5);//合并垮了3列。  
	         wsheet.addCell(new Label(11, 5, "累计数",wcfFQ));    
	         
	         wsheet.mergeCells(12, 4, 12, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(12, 4, "创汇",wcfFQ));  
	         wsheet.mergeCells(13, 4, 13, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(13, 4, "职工数",wcfFQ));  
	         wsheet.mergeCells(14, 4, 14, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(14, 4, "研发经费",wcfFQ));  
	         wsheet.mergeCells(15, 4, 15, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(15, 4, "高新技术产品收入",wcfFQ));  
	         wsheet.mergeCells(16, 4, 16, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(16, 4, "工业总产值",wcfFQ));  
	         wsheet.mergeCells(17, 4, 17, 5);//合并垮了3列。 
	         wsheet.addCell(new Label(17, 4, "工业增加值",wcfFQ));  
	         
			for(int i=0;i<columnCount;i++){
				wsheet.setColumnView(i, 10); // 作用是指定第i+1列的宽度，比如： // 将第�?��的宽度设�?0 sheet.setColumnView( 0 , 30 );.setColumnView( 0 , 30 );
			}			
			// �?��生成列标�?           
			
			int countHyfl1=0;
			int countHyfl2=0;
			int countHyfl3=0;
			int countHyfl4=0;
			int countHyfl5=0;
			int countHyfl6=0;
			int countHyfl7=0;
			int countHyfl8=0;
			if(countHyfl1List.get(0)==null){
				countHyfl1=0;
			}else{
				countHyfl1=1;
			}
			if(countHyfl2List.get(0)==null){
				countHyfl2=0;
			}else{
				countHyfl2=1;
			}
			if(countHyfl3List.get(0)==null){
				countHyfl3=0;
			}else{
				countHyfl3=1;
			}
			if(countHyfl4List.get(0)==null){
				countHyfl4=0;
			}else{
				countHyfl4=1;
			}
			if(countHyfl5List.get(0)==null){
				countHyfl5=0;
			}else{
				countHyfl5=1;
			}
			if(countHyfl6List.get(0)==null){
				countHyfl6=0;
			}else{
				countHyfl6=1;
			}
			if(countHyfl7List.get(0)==null){
				countHyfl7=0;
			}else{
				countHyfl7=1;
			}
			if(countHyfl8List.get(0)==null){
				countHyfl8=0;
			}else{
				countHyfl8=1;
			}
			System.out.println("test======"+countHyfl8);
			
//			for (int j=0 ;j<columnCount;j++){
//			    wsheet.addCell(new Label(j,6, columnCaption[j].toString(),wcfFQ));   //列头部标�?
//			}
			//行内�?
			Object rowData[];
			StringBuffer value=new StringBuffer("");
			if(list!=null){
			for(int i=0;i<list.size();i++){   
				System.out.println(String.valueOf(list.get(i)));
				 Map p = (Map)list.get(i);
				 if(p!=null){
				for (int j=0 ;j<columnCount;j++){
					value.delete(0, value.length());
						if (p.get(columnField[j])==null){
							 value.append("空");
						  }else{
					   value.append(p.get(columnField[j]).toString());
                    }
						if(j!=0){
							wsheet.addCell(new Label(j, i+6, value.toString(),wcfFQ));
						}
						
				       //姓名
				}
				 }
				if(i==list.size()-1){
					wsheet.mergeCells(0, 6, 0, i+6);
					 wsheet.addCell(new Label(0, 6, "生物/医药技术业",wcfFQ));   
				}
			}  
			}
			
			if(countHyfl1List!=null){
				for(int h=0;h<countHyfl1List.size();h++){   
					System.out.println(String.valueOf(countHyfl1List.get(h)));
					 Map p = (Map)countHyfl1List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size(), 2, 6+list.size());
					 wsheet.addCell(new Label(0, 6+list.size(), "小计：",wcfFQ));   
					
					for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("空");
							  }else{
						   value.append(p.get(columnField1[j]).toString());
	                    }
                         
                            		wsheet.addCell(new Label(j+3, h+6+list.size(), value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			
			
			if(list2!=null){
			for(int i2=0;i2<list2.size();i2++){   
				System.out.println(String.valueOf(list2.get(i2)));
				 Map p2 = (Map)list2.get(i2);
				 if(p2!=null){
				for (int j2=0 ;j2<columnCount;j2++){
					value.delete(0, value.length());
						if (p2.get(columnField[j2])==null){
							 value.append("空");
						  }else{
					   value.append(p2.get(columnField[j2]).toString());
                    }
						
						if(j2!=0){
							wsheet.addCell(new Label(j2, i2+6+list.size()+countHyfl1, value.toString(),wcfFQ));
						}
				   
				}
				 }
				if(i2==list2.size()-1){
					wsheet.mergeCells(0, 6+list.size()+countHyfl1, 0, i2+6+list.size()+countHyfl1);
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1, "电子与信息业",wcfFQ));   
				}
			}
			}
			
			if(countHyfl2List!=null){
				for(int h=0;h<countHyfl2List.size();h++){   
					System.out.println(String.valueOf(countHyfl2List.get(h)));
					 Map p = (Map)countHyfl2List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size()+countHyfl1+list2.size(), 2, 6+list.size()+countHyfl1+list2.size());
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1+list2.size(), "小计：",wcfFQ));   
					
					 for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("空");
							  }else{
						   value.append(p.get(columnField1[j]).toString());
	                    }
                         
                            		wsheet.addCell(new Label(j+3, h+6+list.size()+countHyfl1+list2.size(), value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			
			if(list3!=null){
				for(int i3=0;i3<list3.size();i3++){   
					 Map p3 = (Map)list3.get(i3);
					 if(p3!=null){
					for (int j3=0 ;j3<columnCount;j3++){
						value.delete(0, value.length());
							if (p3.get(columnField[j3])==null){
								 value.append("空");
							  }else{
						   value.append(p3.get(columnField[j3]).toString());
	                    }
							if(j3!=0){
								 wsheet.addCell(new Label(j3, i3+6+list.size()+list2.size()+countHyfl1+countHyfl2, value.toString(),wcfFQ));
							}
					      //姓名
					}
					 }
					if(i3==list3.size()-1){
						wsheet.mergeCells(0, 6+list.size()+list2.size()+countHyfl1+countHyfl2, 0, i3+6+list.size()+list2.size()+countHyfl1+countHyfl2);
						 wsheet.addCell(new Label(0, 6+list.size()+list2.size()+countHyfl1+countHyfl2, "新材料技术/新材料业",wcfFQ));   
					}
				}
				}
			if(countHyfl3List!=null){
				for(int h=0;h<countHyfl3List.size();h++){   
					System.out.println(String.valueOf(countHyfl3List.get(h)));
					 Map p = (Map)countHyfl3List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size(), 2, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size());
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size(), "小计：",wcfFQ));   
					 
					 for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("空");
							  }else{
						   value.append(p.get(columnField1[j]).toString());
	                    }
                         
                            		wsheet.addCell(new Label(j+3, h+6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size(), value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			if(list4!=null){
				for(int i4=0;i4<list4.size();i4++){   
					System.out.println(String.valueOf(list4.get(i4)));
					 Map p4 = (Map)list4.get(i4);
					 if(p4!=null){
					for (int j4=0 ;j4<columnCount;j4++){
						value.delete(0, value.length());
							if (p4.get(columnField[j4])==null){
								 value.append("空");
							  }else{
						   value.append(p4.get(columnField[j4]).toString());
	                    }
							if(j4!=0){
					    wsheet.addCell(new Label(j4, i4+6+list.size()+list2.size()+list3.size()+countHyfl1+countHyfl2+countHyfl3, value.toString(),wcfFQ));   //姓名
					 }
					}
					 }
					if(i4==list4.size()-1){
						wsheet.mergeCells(0, 6+list.size()+list2.size()+list3.size()+countHyfl1+countHyfl2+countHyfl3, 0, i4+6+list.size()+list2.size()+list3.size()+countHyfl1+countHyfl2+countHyfl3);
						 wsheet.addCell(new Label(0, 6+list.size()+list2.size()+list3.size()+countHyfl1+countHyfl2+countHyfl3, "展览服务",wcfFQ));   
					}
				}
				}
			
			if(countHyfl4List!=null){
				for(int h=0;h<countHyfl4List.size();h++){   
					System.out.println(String.valueOf(countHyfl4List.get(h)));
					 Map p = (Map)countHyfl4List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size(), 2, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size());
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size(), "小计：",wcfFQ));   
					 
					 for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("空");
							  }else{
						   value.append(p.get(columnField1[j]).toString());
	                    }
                         
                            		wsheet.addCell(new Label(j+3, h+6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size(), value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			
			if(list5!=null){
				for(int i5=0;i5<list5.size();i5++){   
					 Map p5 = (Map)list5.get(i5);
					for (int j5=0 ;j5<columnCount;j5++){
						value.delete(0, value.length());
							if (p5.get(columnField[j5])==null){
								 value.append("空");
							  }else{
						   value.append(p5.get(columnField[j5]).toString());
	                    }
							if(j5!=0){
					    wsheet.addCell(new Label(j5, i5+6+list.size()+list2.size()+list3.size()+list4.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4, value.toString(),wcfFQ));   //姓名
					}
					}
					if(i5==list5.size()-1){
						wsheet.mergeCells(0, 6+list.size()+list2.size()+list3.size()+list4.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4, 0, i5+6+list.size()+list2.size()+list3.size()+list4.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4);
						 wsheet.addCell(new Label(0, 6+list.size()+list2.size()+list3.size()+list4.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4, "其他",wcfFQ));   
					}
				}
				}
			
			if(countHyfl5List!=null){
				for(int h=0;h<countHyfl5List.size();h++){   
					System.out.println(String.valueOf(countHyfl5List.get(h)));
					 Map p = (Map)countHyfl5List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size(), 2, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size());
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size(), "小计：",wcfFQ));   
					
					 for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("空");
							  }else{
						   value.append(p.get(columnField1[j]).toString());
	                    }
					
                         
                            		wsheet.addCell(new Label(j+3, h+6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size(), value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			
			if(list6!=null){
				for(int i6=0;i6<list6.size();i6++){   
					 Map p6 = (Map)list6.get(i6);
					for (int j6=0 ;j6<columnCount;j6++){
						value.delete(0, value.length());
							if (p6.get(columnField[j6])==null){
								 value.append("空");
							  }else{
						   value.append(p6.get(columnField[j6]).toString());
	                    }
							if(j6!=0){
					    wsheet.addCell(new Label(j6, i6+6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5, value.toString(),wcfFQ));   //姓名
					}
					}
					if(i6==list6.size()-1){
						wsheet.mergeCells(0, 6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5, 0, i6+6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5);
						 wsheet.addCell(new Label(0, 6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5, "工业",wcfFQ));   
					}
				}
				}
			if(countHyfl6List!=null){
				for(int h=0;h<countHyfl6List.size();h++){   
					System.out.println(String.valueOf(countHyfl6List.get(h)));
					 Map p = (Map)countHyfl6List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size(), 2, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size());
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size(), "小计：",wcfFQ));   
					 
					 for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("空");
							  }else{
						   value.append(p.get(columnField1[j]).toString());
	                    }
					
                            		wsheet.addCell(new Label(j+3, h+6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size(), value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			
			if(list7!=null){
				for(int i7=0;i7<list7.size();i7++){   
					 Map p7 = (Map)list7.get(i7);
					 if(p7!=null){
					for (int j7=0 ;j7<columnCount;j7++){
						value.delete(0, value.length());
							if (p7.get(columnField[j7])==null){
								 value.append("空");
							  }else{
						   value.append(p7.get(columnField[j7]).toString());
	                    }
							if(j7!=0){
					    wsheet.addCell(new Label(j7, i7+6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+list6.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5+countHyfl6, value.toString(),wcfFQ));   //姓名
					}
					}
					if(i7==list7.size()-1){
						wsheet.mergeCells(0, 6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+list6.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5+countHyfl6, 0, i7+6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+list6.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5+countHyfl6);
						 wsheet.addCell(new Label(0, 6+list.size()+list2.size()+list3.size()+list4.size()+list5.size()+list6.size()+countHyfl1+countHyfl2+countHyfl3+countHyfl4+countHyfl5+countHyfl6, "出园企业",wcfFQ));   
					}
					 }
				}
				}
			
			if(countHyfl7List!=null){
				for(int h=0;h<countHyfl7List.size();h++){   
					System.out.println(String.valueOf(countHyfl7List.get(h)));
					 Map p = (Map)countHyfl7List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size(), 2, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size());
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size(), "小计：",wcfFQ));   
					 
					 for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("");
							  }else{
						   value.append(p.get(columnField1[j]).toString());
	                    }
                         
                            		wsheet.addCell(new Label(j+3, h+6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size(), value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			if(countHyfl8List!=null){
				for(int h=0;h<countHyfl8List.size();h++){   
					System.out.println(String.valueOf(countHyfl8List.get(h)));
					 Map p = (Map)countHyfl8List.get(h);
					 Map p1 = (Map)countHyfl7List.get(h);
					 if(p!=null){
					 wsheet.mergeCells(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size()+countHyfl7, 2, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size()+countHyfl7);
					 wsheet.addCell(new Label(0, 6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size()+countHyfl7, "琶洲园区合计：",wcfFQ));   
					
					 for (int j=0 ;j<columnCount1;j++){
						value.delete(0, value.length());
							if (p.get(columnField1[j])==null){
								 value.append("空");
							  }else{
								 
									  Double newvlue=Double.valueOf(p.get(columnField1[j]).toString())+Double.valueOf(p1.get(columnField1[j]).toString());
									  BigDecimal   newvlueb   =   new   BigDecimal(newvlue);
									  double   newvluef   =   newvlueb.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
						   value.append(newvluef);
								  
	                    }
                         
                            		wsheet.addCell(new Label(j+3, h+6+list.size()+countHyfl1+list2.size()+countHyfl2+list3.size()+countHyfl3+list4.size()+countHyfl4+list5.size()+countHyfl5+list6.size()+countHyfl6+list7.size()+countHyfl7, value.toString(),wcfFQ));
								
							
							
					       //姓名
					}
					 }
				}  
				}
			// 主体内容生成结束           
			wbook.write(); // 写入文件   
			wbook.close();  
			os.close(); // 关闭�?
			return true; 
		}catch(Exception e) { 
			//System.out.println("列名为："+columnField.toString());
			e.printStackTrace(); 
			return false; 
		}
	} 

}