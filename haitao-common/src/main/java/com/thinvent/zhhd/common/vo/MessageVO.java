package com.thinvent.zhhd.common.vo;

import java.util.Date;
import java.util.List;

import com.thinvent.library.vo.UserVO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class MessageVO {
	

	public MessageVO() {
		this.sendTime = new Date();
		this.modifyTime = new Date();
	}

	private String msgId;

	private String text;

	private String key;

	private String isAuto;

	private String sender;
	
	private Date sendTime;

	private String msgStatus;

	private String editor;
	
	private Date modifyTime;
	
	private boolean isUpdate;
	
	private String messageId;
	
	private String Status;
	
	private List<String> messageReceiver;

	private List<String> messageFailReceiver;

	private int loadingCount;
	
	private String senderName;
	
	private String alarmId;
	
	private String remark;
	
	private List<MessageReceiverVO> messageReceiverLsit;
	
	private List<UserVO> userMessageList;

}
