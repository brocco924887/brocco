package com.excel;

import java.io.FileNotFoundException;
import java.io.IOException;

public class App {

	public static void main(String[] args) {
//		Excel代码练习：
//		a.实现输出全部Excel内容到控制台。
//		b.只输出第一行内容。
//		c.用代码，修改内容，并且保存。
		try {
			
			// ExcelUtil.readExcelToTxt("d:\\test.xlsx", "d:\\test_a.txt");//a.实现输出全部Excel内容到控制台。b.只输出第一行内容。
			// ExcelUtil.readExcelToExcel("d:\\test02.xlsx", "d:\\test_3.xlsx");
			// ExcelUtil.readExcelsToExcel(new String[] { "d:\\test.xlsx", "d:\\test01.xlsx", "d:\\test02.xlsx"
			//		}, "d:\\testTotal180912.xlsx");
			// ExcelUtil.modifyExcel("d:\\test.xlsx", 1, 1, "我们改变了单元格里面的内容！"); //c.用代码，修改内容，并且保存。
			ExcelUtil.hasName(new String[] { "d:\\test.xlsx", "d:\\test01.xlsx", "d:\\test02.xlsx"
					}, "d:\\test.txt");
			ExcelUtil.copyImage(new String[] {"d:\\test_a.xlsx", "d:\\test_b.xlsx", "d:\\test_c.xlsx", 
					"d:\\test_d.xlsx", "d:\\test_e.xlsx","d:\\test_f.xlsx", "d:\\test_g.xlsx", 
					"d:\\test_h.xlsx","d:\\test_i.xlsx", "d:\\test_j.xlsx", "d:\\test_k.xlsx",
					"d:\\test_l.xlsx", "d:\\test_m.xlsx", "d:\\test_n.xlsx", "d:\\test_o.xlsx", 
					"d:\\test_p.xlsx", "d:\\test_q.xlsx", "d:\\test_r.xlsx", "d:\\test_s.xlsx", 
					"d:\\test_t.xlsx", "d:\\test_u.xlsx", "d:\\test_v.xlsx", "d:\\test_w.xlsx", 
					"d:\\test_x.xlsx", "d:\\test_y.xlsx", "d:\\test_z.xlsx", "d:\\test_1a.xlsx",
					"d:\\test_1b.xlsx", "d:\\test_1c.xlsx", "d:\\test_1d.xlsx", "d:\\test_1e.xlsx",
					"d:\\test_1f.xlsx", "d:\\test_1g.xlsx", "d:\\test_1h.xlsx","d:\\test_1i.xlsx", 
					"d:\\test_1j.xlsx", "d:\\test_1k.xlsx", "d:\\test_1l.xlsx", "d:\\test_1m.xlsx", 
					"d:\\test_1n.xlsx", "d:\\test_1o.xlsx", "d:\\test_1p.xlsx", "d:\\test_1q.xlsx", 
					"d:\\test_1r.xlsx", "d:\\test_1s.xlsx", "d:\\test_1t.xlsx", "d:\\test_1u.xlsx", 
					"d:\\test_1v.xlsx", "d:\\test_1w.xlsx", "d:\\test_1x.xlsx", "d:\\test_1y.xlsx", 
					"d:\\test_1z.xlsx", "d:\\test_2a.xlsx", "d:\\test_2b.xlsx", "d:\\test_2c.xlsx", 
					"d:\\test_2d.xlsx", "d:\\test_2e.xlsx","d:\\test_2f.xlsx", "d:\\test_2g.xlsx", 
					"d:\\test_2h.xlsx","d:\\test_2i.xlsx", "d:\\test_2j.xlsx", "d:\\test_2k.xlsx", 
					"d:\\test_2l.xlsx", "d:\\test_2m.xlsx", "d:\\test_2n.xlsx", "d:\\test_2o.xlsx", 
					"d:\\test_2p.xlsx", "d:\\test_2q.xlsx", "d:\\test_2r.xlsx", "d:\\test_2s.xlsx", 
					"d:\\test_2t.xlsx", "d:\\test_2u.xlsx", "d:\\test_2v.xlsx", "d:\\test_2w.xlsx", 
					"d:\\test_2x.xlsx", "d:\\test_2y.xlsx", "d:\\test_2z.xlsx","d:\\test_3a.xlsx", 
					"d:\\test_3b.xlsx", "d:\\test_3c.xlsx", "d:\\test_3d.xlsx", "d:\\test_3e.xlsx",
					"d:\\test_3f.xlsx", "d:\\test_3g.xlsx", "d:\\test_3h.xlsx","d:\\test_3i.xlsx", 
					"d:\\test_3j.xlsx", "d:\\test_3k.xlsx", "d:\\test_3l.xlsx", "d:\\test_3m.xlsx", 
					"d:\\test_3n.xlsx", "d:\\test_3o.xlsx", "d:\\test_3p.xlsx", "d:\\test_3q.xlsx", 
					"d:\\test_3r.xlsx", "d:\\test_3s.xlsx", "d:\\test_3t.xlsx", "d:\\test_3u.xlsx", 
					"d:\\test_3v.xlsx"} );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
