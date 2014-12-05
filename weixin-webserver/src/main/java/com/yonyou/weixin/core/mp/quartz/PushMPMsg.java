package com.yonyou.weixin.core.mp.quartz;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

import com.yonyou.weixin.core.message.model.SendMessage;
import com.yonyou.weixin.core.mp.client.Exception_Exception;
import com.yonyou.weixin.core.mp.client.MPServiceFactory;
import com.yonyou.weixin.core.mp.client.MpMessageUrlVO;

/**
 * 定时推送消息
 * <p/>
 * <p> @author 刘新宇
 *
 * <p> @date 2014年12月4日 下午7:13:47
 * <p> @version 0.0.1
 */
public class PushMPMsg {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PushMPMsg.class);

	public void run() {
		List<MpMessageUrlVO> messageVOs;
        //messageVOs = hw.getMessageVOsByUserAndSystemAndType("shenjie", "WB", "TASK");
		//每半小时
//		Date oneweek=new Date(new Date().getTime() -60*60*24*1*1000/48);
		//每分钟
		Date oneweek=new Date(new Date().getTime() -60*60*24*1*1000/24);
        String timeStamp = String.valueOf(oneweek.getTime());
        String systemcode = "WB";
        String type = "TASK";
        System.out.println("----------------------------------------定时消息--------------------------------------");
        try {
            messageVOs = MPServiceFactory.getServiceInstance().getMessageVOsByUserAndSystemAndType(timeStamp, systemcode, type);
            String msg = "";
            for(MpMessageUrlVO m :messageVOs){
            	msg = m.getSendername()+"提交单据，请"+m.getReceivername()+"审批："+m.getUrl();
            	msg +=msg;
            }
            SendMessage.postTextMsg("0000049835",  "有人提交单据请审批:"+"【"+msg+"】");
            msg = "";
        } catch (Exception_Exception e) { 
			logger.error("run()", e);
        }
	}
}
