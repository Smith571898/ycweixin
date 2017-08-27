package ycweixin;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.yc.weixin.model.MediaModel;
import com.yc.weixin.utils.MediaUtil;

public class TestUtil {

	@Test
	public void testMediaUtil() {
		File file = new File("d:\\k.jpg");
		MediaModel mm = MediaUtil.uploadPicFromNewsMaterial( file.getAbsolutePath());
		System.out.println(mm.getUrl());
	}
	
	
}
