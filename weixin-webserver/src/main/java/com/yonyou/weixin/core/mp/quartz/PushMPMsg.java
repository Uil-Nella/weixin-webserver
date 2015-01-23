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
		Date oneweek=new Date(new Date().getTime() -60*60*24*1*1000/24/12);
        String timeStamp = String.valueOf(oneweek.getTime());
        String systemcode = "WB";
        String type = "TASK";

		if (logger.isInfoEnabled()) {
			logger.info("run()----------------------------------------定时消息--------------------------------------");
		}

        System.out.println("----------------------------------------定时消息--------------------------------------");
//        try {
//            messageVOs = MPServiceFactory.getServiceInstance().getMessageVOsByUserAndSystemAndType(timeStamp, systemcode, type);
//            int i =0;
//            for(MpMessageUrlVO m :messageVOs){
//            	i++;
//            	SendMessage.postTextMsg(m.getReceiver(), "\"<a href=\""+m.getUrl()+"\">"+m.getSendername()+"提交单据请您审批,猛戳这里"+"</a>\"");
//            	SendMessage.postTextMsg("0000049835","<a href="+m.getUrl()+">"+  m.getSendername()+"提交单据请"+m.getReceiver()+"审批,猛戳这里</a>");
//            }
//            SendMessage.postTextMsg("0000049835",  "<a>共拉取"+i+"条单据</a>");
//        } catch (Exception_Exception e) { 
//			logger.error("run()", e);
//        }
        SendMessage.postTextMsg("0000049835",  "<a href='http://bugkillers.org'>尝试拉取单据共拉取条单据</a>");
        
        
	}
}
