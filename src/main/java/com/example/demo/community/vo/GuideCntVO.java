package com.example.demo.community.vo;
import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("GuideCntVO")
public class GuideCntVO {
	private String sessionKey;
	private GuideBoardVO guideBoard;

}
